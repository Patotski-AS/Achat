package com.progerchat.achat.screens.feedback

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.FirebaseDatabase
import com.progerchat.achat.R
import com.progerchat.achat.databinding.FeedbackFragmentBinding
import com.progerchat.achat.databinding.LoginFragmentBinding
import com.progerchat.achat.screens.login.LoginViewModel

class FeedbackFragment : Fragment() {


    private lateinit var viewModel: FeedbackViewModel
    private var _binding: FeedbackFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FeedbackFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(FeedbackViewModel::class.java)

        binding.sendFeedback.setOnClickListener {

            val textSendFeedback = binding.editTextTextEmailAddress.text.toString() + ": " + binding.textInputEditText.text.toString()

            if (textSendFeedback != ": ") {
                FirebaseDatabase.getInstance().reference.child("Feedback").push().setValue(
                    textSendFeedback
                )
            }

            binding.textInputEditText.setText("")
            binding.textInputEditText.hint = "Write feedback"

            // binding.editTextTextEmailAddress.setText("")
            // binding.editTextTextEmailAddress.hint = "E-mail"

        }



        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}