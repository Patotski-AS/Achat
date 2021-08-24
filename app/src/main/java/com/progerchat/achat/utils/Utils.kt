package com.progerchat.achat.utils

import java.io.Serializable


data class StateLogin(var emailError: String? = null, var passwordError: String? = null, var state: Boolean = false)

data class UserModel(var Mail: String = "this@email.com", var link: String? = null) : Serializable

val array: String = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789" +
        "йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ!#"


