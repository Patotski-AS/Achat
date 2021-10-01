package com.progerchat.achat.screens.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.database.FirebaseDatabase
import com.progerchat.achat.database.auth.AuthDB
import com.progerchat.achat.databinding.LoginFragmentBinding
import com.progerchat.achat.model.Auth
import kotlinx.coroutines.GlobalScope


class LoginFragment: Fragment() {

    private lateinit var viewModel:LoginViewModel

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    private var mAuthListener: AuthStateListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = LoginFragmentBinding.inflate(inflater, container, false)


        val db =  AuthDB.getInstance(requireContext()).authDao



       // db.insert(Auth(1, "", ""))
       // viewModel = ViewModelProvider(this, LoginFactory(db)).get(LoginViewModel::class.java)



        binding.emailSignInButton.setOnClickListener {

            if (viewModel.validateForm(binding.fieldEmail.text.toString(), binding.fieldPassword.text.toString()).state) {

                activity?.let { result ->
                    viewModel.mAuth!!.value?.signInWithEmailAndPassword(viewModel.user, viewModel.password)
                        ?.addOnCompleteListener(
                            result
                        ) { task ->

                            if (!task.isSuccessful)
                                stateLogin("Вы не авторизованы")
                            else {

                                viewModel.getCorrectByte(viewModel.mAuth!!.value?.currentUser?.uid.toString())
                                viewModel.correctByte?.observe(viewLifecycleOwner, {

                                    if (viewModel.mFirebaseUser?.value!!.isEmailVerified) {
                                        viewModel.getModel(viewModel.user, "")
                                        successIn()
                                    } else
                                        stateLogin("Пожалуйста авторизуйте почту")
                                })

                            }

                        }
                }
            }

        }

        binding.emailCreateAccountButton.setOnClickListener {

            if (viewModel.validateForm(binding.fieldEmail.text.toString(), binding.fieldPassword.text.toString()).state) {
                activity?.let { create ->
                    viewModel.mAuth!!.value?.createUserWithEmailAndPassword(viewModel.user, viewModel.password)
                        ?.addOnCompleteListener(create) { task ->

                            if (!task.isSuccessful)
                                stateLogin("Вы не авторизованы")
                            else {
                                stateLogin("Проверьте почту")
                                viewModel.mFirebaseUser?.value?.sendEmailVerification()
                                viewModel.addBytesFirebase(
                                    viewModel.initKey(viewModel.mAuth!!.value?.currentUser?.uid.toString()),
                                    viewModel.mAuth!!.value?.currentUser?.uid.toString()
                                )
                            }

                        }
                }
            }
        }

        viewModel.stateLogin?.observe(viewLifecycleOwner, {

            if (!it.state){
                binding.fieldEmail.error = it.emailError
                binding.fieldPassword.error = it.passwordError
            }

        })

        viewModel.updateFirebaseAuth(FirebaseAuth.getInstance())

        viewModel.mAuth?.observe(viewLifecycleOwner, {

            if (it != null) {

                if (viewModel.mAuth!!.value?.currentUser?.isEmailVerified == true){

                    viewModel.getCorrectByte(viewModel.mAuth!!.value?.currentUser?.uid.toString())

                    viewModel.correctByte?.observe(viewLifecycleOwner, {
                        viewModel.getModel(viewModel.mAuth!!.value?.currentUser?.email, "")
                        successIn()
                    })

                }
            }

        })


        mAuthListener = AuthStateListener { firebaseAuth -> val user = firebaseAuth.currentUser
            viewModel.updateFirebaseUser(user)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun stateLogin(text:String){
        val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL, Gravity.CENTER)
        toast.show()
    }

    override fun onStart() {
        super.onStart()
        viewModel.mAuth!!.value?.addAuthStateListener(mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null)
            viewModel.mAuth!!.value?.removeAuthStateListener(mAuthListener!!)

    }

    private fun successIn(){
       // if (viewModel.correctByte?.value == true)
        view?.findNavController()?.navigate(LoginFragmentDirections.actionLoginFragmentToChatFragment("test"))
    }

}