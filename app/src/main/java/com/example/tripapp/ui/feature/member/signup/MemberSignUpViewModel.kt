package com.example.tripapp.ui.feature.member.signup

import android.util.Log
import androidx.navigation.Navigator
import com.ron.restdemo.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MemberSignUpViewModel {
    private  val  tag = "tag_MemberVM"

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _icon = MutableStateFlow("")
    val icon = _icon.asStateFlow()

    fun onNameChanged(name:String){
        _name.update { name }
    }

    fun onEmailChanged(email:String){
        _email.update { email }
    }

    fun onPasswordChange(password:String){
        _password.update { password }
    }

    fun onIconChanged(icon:String){
        _icon.update { icon }
    }

    suspend fun onSignUpClick(){
        signup(
            memEmail = _email.value,
            memPw = _password.value,
            memName = _name.value,
            memIcon = _icon.value
        )

    }

    suspend fun signup(
        memName: String, memEmail: String, memPw: String,
        memIcon:String
    ): Boolean {


        try{
            val response = RetrofitInstance.api.signup(memName, memEmail, memPw, memIcon)
            return response.isSuccessful
        } catch (e: Exception){
            Log.e(tag, "error: ${e.message}")
            return false
        }
    }
}