package com.progerchat.achat.screens.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.progerchat.achat.R
import com.progerchat.achat.databinding.LoginFragmentBinding
import com.progerchat.achat.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(
                this, ProfileViewModelFactory("test", "", true)).get(ProfileViewModel::class.java)

        binding.progressBarUpload.progress = 0
        binding.progressBarUpload.isVisible = false

        binding.imageViewProfile.setOnClickListener {

              val permissionStatus = activity?.let { it1 -> ContextCompat.checkSelfPermission(it1, Manifest.permission.WRITE_EXTERNAL_STORAGE) }

              if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
                  activity?.let { it1 -> ActivityCompat.requestPermissions(it1, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0) }
              }

              if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
                  val intent = Intent(Intent.ACTION_PICK)
                  intent.type = "image/*"
                  startForResult.launch(intent)
              }

        }

        viewModel.bmp?.observe(viewLifecycleOwner, Observer {
                binding.imageViewProfile.setImageBitmap(it?.let { it1 -> Bitmap.createScaledBitmap(it1, binding.imageViewProfile.width, binding.imageViewProfile.height, false)
            })
        })

        viewModel.progress?.observe(viewLifecycleOwner, Observer {
                binding.progressBarUpload.isVisible =
                    !((binding.progressBarUpload.progress <= 0) or (binding.progressBarUpload.progress >= 99.9))

                 binding.progressBarUpload.progress = it
        })

        return binding.root
    }


    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.data
            val imageStream = imageUri?.let { context?.contentResolver?.openInputStream(it) }
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            binding.imageViewProfile.setImageBitmap(selectedImage)
            imageUri?.let { viewModel.push(it, viewModel.emailChiper) }
        }
    }

}