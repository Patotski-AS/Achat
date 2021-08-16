package com.progerchat.achat.screens.login


import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.progerchat.achat.utils.StateLogin
import com.progerchat.achat.utils.UserModel

class LoginViewModel : ViewModel() {

    private var _mAuth: MutableLiveData<FirebaseAuth>? = null
    val mAuth: LiveData<FirebaseAuth>? get() = _mAuth

    private var _mFirebaseUser:MutableLiveData<FirebaseUser>? = null
    val mFirebaseUser: LiveData<FirebaseUser>? get() = _mFirebaseUser

    var password: String
    var user: String

    private var _stateLogin: MutableLiveData<StateLogin>? = null
    val stateLogin: LiveData<StateLogin>? get() = _stateLogin!!

    private var _modelIn: MutableLiveData<UserModel>? = null
    val modelIn: LiveData<UserModel>? get() = _modelIn!!

    init {
        _stateLogin = MutableLiveData<StateLogin>()
        password = ""
        user = ""
        _modelIn = MutableLiveData<UserModel>()
        _mAuth = MutableLiveData<FirebaseAuth>()
        _mFirebaseUser = MutableLiveData<FirebaseUser>()
    }


     fun validateForm(email: String, password: String): StateLogin {

        var valid = StateLogin()
        var state = false

        if ((TextUtils.isEmpty(email)) and (email.split('@').size == 2)) {
            valid.emailError = "Required."
            state = false
        }
        else {
            state = true
            valid.emailError = null
            this.user = email
        }

        if (TextUtils.isEmpty(password)) {
            valid.passwordError = "Required."
            state = false
        }
        else {
            state = true
            valid.passwordError = null
            this.password = password
        }

         valid.state = state
         return valid
    }

    fun updateFirebaseUser(user: FirebaseUser?) {
        _mFirebaseUser?.value = user
    }

    fun updateFirebaseAuth(auth: FirebaseAuth?){
        _mAuth?.value = auth
    }


    fun getModel(email: String?, link: String?)  {

        if (!email.isNullOrEmpty())
            _modelIn?.value?.Mail = email

        if (!link.isNullOrEmpty())
            _modelIn?.value?.link = link

    }


}