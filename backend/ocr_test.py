import requests
from datetime import date, datetime
from season import get_season
# If you are using a Jupyter notebook, uncomment the following line.
# %matplotlib inline
import matplotlib.pyplot as plt
from PIL import Image
import os
import sys
from io import BytesIO
import re
import difflib
from setup_db import my_database
from setup_api import setUpKey

#checks if a string is a word or word like entity
def isLegitimateString(inputString):
    count=0
    for i in inputString:
        if i.isdigit():
            count+=1
    if count> len(inputString)/2:
        return False

    return True

def getClosestMatch(phrase, word):
    #print(phrase)
    closestMatch=difflib.get_close_matches(word.upper(), [phrase])
    #print('word', word,'phrase ',phrase, 'closest match',closestMatch)

    if closestMatch: #if did not rturn a null array
    #print(word)
        seq = difflib.SequenceMatcher(None,closestMatch[0].upper(),word.upper())
        d = seq.ratio()*100
        #print('match',closestMatch,word,phrase,d)
        if(d>70):
            #print('True',d,word,closestMatch[0].upper())
            return True
    return False

def getClosestMatchFromTable(my_DB,phrase): #scans the entire table and checks which word matches the closest
    myList=my_DB.getListNamesDB()
    #print(phrase)
    for i in myList:
        if getClosestMatch(phrase, i[0]): #db word
            #print('i',i)
            return i[0]
    return None
    
    
def analyze_receipt(analysis,my_DB):
    envScore=0
    numberOfPlastics=0
    scoreExplanation=[]
    words=[]
    #print(analysis)
    for region in analysis["regions"]:
        for line in region["lines"]:
            phrase=""
            phraseArray=[]
            for word in line["words"]: #each word add them together in a phrase 
                #print(word['text'],word['boundingBox'])
                if isLegitimateString(word['text']):
                    if not phrase:
                        phrase+=word['text'].upper() #sum up the words in a single bounding box
                    else:
                        phrase=phrase+' '+word['text'].upper() #only add space if not empty string
                    phraseArray.append(word['text'].upper())
                    #print(phraseArray,phrase)

            if len(phrase)>3:
                words.append(phrase) #remove short phrases so onnly relevant phrases stay
                myword=getClosestMatchFromTable(my_DB,phrase)
                
                #now we are comparing the word with it's season and whether or not is is exported
                info=my_DB.getInfoFromName(myword)
                if info:
                    season=get_season(date.today())
                    if info[0][1]!="No":
                        envScore-=1
                        scoreExplanation.append("Exported rather than local")
                    if info[0][0]!='None' and season not in info[0][1]:
                        envScore-=1
                        scoreExplanation.append("Offseason, must be shipped in")

                #remember to use upper on all strings
                if getClosestMatch(phrase,"PLASTIC BAG") or getClosestMatch(phrase,"SIXPACK") or getClosestMatch(phrase,"STRAW") or getClosestMatch(phrase,"PLASTIC BOTTLE"):
                    list_of_nums = map(int, re.findall('\d+', phrase)) #find all the integers in that phrase
                    try:   
                        maxList=max(list_of_nums)
                    #print(maxList)
                        envScore-=maxList if maxList>1 else 1 #get the largest integer
                        scoreExplanation.append("Use of plastic pollutants")
                        numberOfPlastics=maxList
                    except:
                        envScore-=1
                        scoreExplanation.append("Use of plastic pollutants")
                if getClosestMatch(phrase,"GREEN BAG") or getClosestMatch(phrase,"REFILL"):
                    list_of_nums = map(int, re.findall('\d+', phrase)) #find all the integers in that phrase
                    try:   
                        maxList=max(list_of_nums)
                    #print(maxList)
                        envScore+=maxList if maxList>1 else 1 #get the largest integer
                        scoreExplanation.append("Use of reusable items")
                        numberOfPlastics=maxList
                    except:
                        envScore+=1
                        scoreExplanation.append("Use of resusable items")
    #print('words',words)
    return envScore, scoreExplanation,numberOfPlastics
