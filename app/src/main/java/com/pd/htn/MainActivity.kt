package com.pd.htn

//import com.pd.htn.vm.MainViewModel
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pd.htn.vm.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val imageCapture = 1
    private var currentPhotoPath: String = ""
    private lateinit var vm : UserViewModel
//    private lateinit var vm2 : MainViewModel

    private var currentNavHash = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(DashboardFragment())

        // set on-click listener
        receipt.setOnClickListener {
            dispatchTakePictureIntent()
            galleryAddPic()
        }

        val bottomNavigation: BottomNavigationView = findViewById(R.id.nav)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        vm = ViewModelProvider(this).get(UserViewModel::class.java)
//        vm2 = ViewModelProvider(this).get(MainViewModel::class.java)

//        vm2.receipt.observe(this, androidx.lifecycle.Observer {

       // })
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
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CANADA).format(Date())
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
                    openFragment(EnvironmentFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.home_nav -> {
                    openFragment(DashboardFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.finance_nav -> {
                    openFragment(FinanceFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun openFragment(fragment: Fragment) {
        if (currentNavHash == fragment.hashCode()) return
        else {
            currentNavHash = fragment.hashCode()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun imageToByteArray(): ByteArray{
        return Files.readAllBytes(Paths.get(currentPhotoPath)).toUByteArray().asByteArray()
    }

//    private fun sendImageToTunnel() {
//        val url = URL("https://great-falcon-63.localtunnel.me/")
//        with(url.openConnection() as HttpURLConnection) {
//            requestMethod = "POST"
//
//
//        }
//    }
}