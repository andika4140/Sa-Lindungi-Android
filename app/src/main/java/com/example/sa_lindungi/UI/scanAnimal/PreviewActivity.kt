package com.example.sa_lindungi.UI.scanAnimal

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sa_lindungi.R
import com.example.sa_lindungi.databinding.ActivityPreviewBinding
import java.io.File

class PreviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonUlang.setOnClickListener { toOption() }
        binding.buttonNext.setOnClickListener { toResult() }

        setupAction()
    }

    private fun toOption() {
        val intentToOption = Intent(this, OptionActivity::class.java)
        startActivity(intentToOption)
    }

    private fun toResult() {
        val intentToResult = Intent(this, ResultActivity::class.java)
        startActivity(intentToResult)
    }

    private fun setupAction() {
        val myFile = intent.getSerializableExtra("picture") as File
        val isBackCamera = intent.getBooleanExtra("isBackCamera", true) as Boolean

        val result = rotateBitmap(
            BitmapFactory.decodeFile(myFile.path),
            isBackCamera
        )

        binding.previewImageView.setImageBitmap(result)
    }

    companion object {
        const val CAMERA_X_RESULT = 200
    }
}