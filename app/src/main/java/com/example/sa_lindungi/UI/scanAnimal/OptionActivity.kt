package com.example.sa_lindungi.UI.scanAnimal

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.sa_lindungi.UI.api.ApiConfig
import com.example.sa_lindungi.UI.api.response.PredictResponse
import com.example.sa_lindungi.UI.home.HomeActivity
import com.example.sa_lindungi.UI.home.MainActivity
import com.example.sa_lindungi.UI.scanAnimal.result.ResultActivity
import com.example.sa_lindungi.databinding.ActivityOptionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.*

class OptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionBinding

    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        binding.backButton.setOnClickListener {
            val intentToMain = Intent(this, HomeActivity::class.java)
            intentToMain.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
        }

        binding.cameraButton.setOnClickListener { startCameraX() }
        binding.galleryButton.setOnClickListener { startGallery() }
        binding.buttonNext.setOnClickListener { postPhoto() }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun postPhoto() {
        showLoading(true)
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)

            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file",
                file.name,
                requestImageFile
            )

            Log.d(TAG, "image $imageMultipart")

            val service = ApiConfig.getApiService().postPredict(imageMultipart)
            service.enqueue(object : Callback<PredictResponse> {
                override fun onResponse(
                    call: Call<PredictResponse>,
                    response: Response<PredictResponse>
                ) {
                    showLoading(false)
                    if (response.isSuccessful && response.body()?.message != "Tidak terdeteksi") {
                        Log.d(TAG, "OnSuccess")
                        val responseBody = response.body()
                        if (responseBody != null) {
                            val id = response.body()?.id
                            Log.d(TAG, "id satwa : $id")
                            val intentToResult = Intent(this@OptionActivity, ResultActivity::class.java)
                            intentToResult.putExtra(ResultActivity.EXTRA_ID, id)
                            startActivity(intentToResult)
                        } else {
                            Toast.makeText(this@OptionActivity, response.body()?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                    Log.d(TAG, "OnFailure : ${t.message}")
                    Toast.makeText(this@OptionActivity, "Gagal melakukan upload gambar", Toast.LENGTH_SHORT).show()
                }

            })
        } else {
            Toast.makeText(this, "Silahkan masukkan berkas gambar terlebih dahulu", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            getFile = myFile

            val result = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                isBackCamera
            )

            Glide.with(this)
                .load(result)
                .centerCrop()
                .into(binding.previewImageView)
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, this@OptionActivity)

            getFile = myFile

            Glide.with(this)
                .load(selectedImg)
                .centerCrop()
                .into(binding.previewImageView)
        }
    }

    private fun reduceFileImage(file: File): File {
        val bitmap = BitmapFactory.decodeFile(file.path)
        var compressQuality = 100
        var streamLength: Int
        do {
            val bmpStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
            compressQuality -= 5
        } while (streamLength > 1000000)
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
        return file
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10

        private const val TAG = "OptionActivity"
    }
}