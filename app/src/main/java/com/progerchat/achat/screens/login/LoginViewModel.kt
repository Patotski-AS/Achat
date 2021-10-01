package com.progerchat.achat.screens.login

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.progerchat.achat.database.auth.AuthDAO
import com.progerchat.achat.utils.StateLogin
import com.progerchat.achat.utils.UserModel
import com.progerchat.achat.utils.array
import java.lang.Exception
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec
import kotlin.math.abs

class LoginViewModel(val db: AuthDAO): ViewModel() {

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

    private var _correctByte: MutableLiveData<Boolean>? = null
    val correctByte: LiveData<Boolean>? get() = _correctByte!!

    // add repository
    var initKey: SecureRandom? = null
    var key: SecretKeySpec? = null

    init {
        _stateLogin = MutableLiveData<StateLogin>()
        password = ""
        user = ""
        _correctByte = MutableLiveData<Boolean>()
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

    fun initKey(string: String): String? {


        if (initKey == null)
            initKey = SecureRandom.getInstance("SHA1PRNG") // надо сохранить

        if (key == null)
        try {
            val kg: KeyGenerator = KeyGenerator.getInstance("AES")
            kg.init(256, SecureRandom.getInstance("SHA1PRNG"))
            key = SecretKeySpec(kg.generateKey().encoded, "AES")
        } catch (e: Exception) {
            Log.e("Crypto", "AES secret key spec error")
        }

        var encodedBytes: ByteArray? = null // отправить на сервер

        try {

            val c: Cipher = Cipher.getInstance("AES")
            c.init(Cipher.ENCRYPT_MODE, key)
            encodedBytes = c.doFinal(initKey.toString().toByteArray())

        } catch (e: Exception) {
            Log.e("Crypto", "AES encryption error")
        }

        var test:String? = null

        if (encodedBytes != null) {

            test = ""
            for (i in encodedBytes.indices)
                test += array[abs(encodedBytes[i].toInt())]

        }


        return test
    }

    fun addBytesFirebase(test:String?, string: String) {

        if (!test.isNullOrEmpty()) {
            FirebaseDatabase.getInstance().reference.child(string).push().setValue(
                test
            )
        }
    }



    fun getCorrectByte(string: String) {

        FirebaseDatabase.getInstance().reference.child(string).get()?.addOnSuccessListener {

            if (it.value is HashMap<*, *>) {

                val a = it.value as HashMap<String, String>
                _correctByte?.value = a.containsValue(initKey(string))
            }

        }?.addOnFailureListener {
            _correctByte?.value = false
        }

    }


}