package com.progerchat.achat

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory.decodeStream
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.firebase.storage.FirebaseStorage
import com.progerchat.achat.databinding.ActivityMainBinding
import com.progerchat.achat.databinding.NavHeaderBinding
import com.progerchat.achat.utils.IconHeader
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.OnProgressListener

import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.annotation.NonNull
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.firebase.client.utilities.ParsedUrl
import java.lang.Exception


class MainActivity : AppCompatActivity(), IconHeader {
    private lateinit var binding: ActivityMainBinding
    private lateinit var headerBinding: NavHeaderBinding

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.data
            val imageStream = imageUri?.let { contentResolver.openInputStream(it) }
            val selectedImage = decodeStream(imageStream)
            headerBinding.imageView.setImageBitmap(selectedImage)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        headerBinding = NavHeaderBinding.inflate(layoutInflater)
        headerBinding.apply {
            emaiTextView.text = "Test"
        }

        headerBinding.imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startForResult.launch(intent)
        }

        val navigationView = binding.navView
        navigationView.addHeaderView(headerBinding.root)

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupWithNavController(navigationView, navController)
    }

    override fun getIcon(str: String) {
        val ref: StorageReference = FirebaseStorage.getInstance().reference.child("images/")
    }

    override fun push() {
        /*  val filePath: Uri
        val ref: StorageReference = FirebaseStorage.getInstance().reference.child("images/")
        ref.putFile(filePath)
            .addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot?>() {
                fun onSuccess(taskSnapshot: UploadTask.TaskSnapshot?) {
                    Toast.makeText(this@MainActivity, "Uploaded", Toast.LENGTH_SHORT).show()
                }
            })
            .addOnFailureListener(object : OnFailureListener() {
                fun onFailure(e: Exception) {
                }
            })
            .addOnProgressListener { taskSnapshot ->
                val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                    .totalByteCount
                progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
            }

       */
    }



}