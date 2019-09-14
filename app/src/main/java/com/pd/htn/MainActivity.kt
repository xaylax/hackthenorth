package com.pd.htn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val imageCapture = 1
    private var currentPhotoPath: String = ""
    private lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar = supportActionBar!!
        setContentView(R.layout.activity_main)
        // get reference to button
        val addReceiptBtn = findViewById<Button>(R.id.receipt)
// set on-click listener
        addReceiptBtn.setOnClickListener {
            dispatchTakePictureIntent()
            galleryAddPic()
        }
        val bottomNavigation: BottomNavigationView = findViewById(R.id.nav)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }


    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    Toast.makeText(this, "Error!", Toast.LENGTH_LONG).show()
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.pd.htn.android.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, imageCapture)
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)
        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.environment_nav -> {
                    toolbar.title = "Environment"
                    val environmentActivity =
                        Intent(applicationContext, EnvironmentActivity::class.java)
                    startActivity(environmentActivity)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.home_nav -> {
                    toolbar.title = "Dashboard"
                    val mainActivity = Intent(applicationContext, MainActivity::class.java)
                    startActivity(mainActivity)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.finance_nav -> {
                    toolbar.title = "Finances"
                    val financeActivity = Intent(applicationContext, FinanceActivity::class.java)
                    startActivity(financeActivity)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}
