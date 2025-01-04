package com.example.tripapp.ui.feature.member.login

import android.util.Log
import com.ron.restdemo.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.log

class MemberLoginViewModel {
    private  val  tag = "tag_MemberVM"

    private val _uid = MutableStateFlow(0)
    val uid = _uid.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onEmailChanged(email:String){
        _email.update { email }
    }

    fun onPasswordChange(password:String){
        _password.update { password }
    }

    suspend fun onLoginClick(){
        login(
            memNo = _uid.value,
            memEmail = _email.value,
            memPw = _password.value,
        )

    }

    suspend fun login(memNo: Int, memEmail: String, memPw: String): Boolean {
        try{
            val response = RetrofitInstance.api.login(memNo,memEmail, memPw)
            return response.isSuccessful
        } catch (e: Exception){
            Log.e(tag, "error: ${e.message}")
            return false
        }
    }

}