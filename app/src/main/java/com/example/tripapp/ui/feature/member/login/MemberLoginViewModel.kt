package com.example.tripapp.ui.feature.member.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripapp.ui.feature.member.LoginRequest
import com.example.tripapp.ui.feature.member.Member
import com.ron.restdemo.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MemberLoginViewModel : ViewModel() {
    private val tag = "tag_LoginVM"

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isLoginSuccess = MutableStateFlow(false)
    val isLoginSuccess = _isLoginSuccess.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    val isButtonEnabled = MutableStateFlow(true)


    fun onEmailChanged(email: String) {
        _email.update { email }
    }

    fun onPasswordChange(password: String) {
        _password.update { password }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            val user = login(
                memEmail = _email.value,
                memPw = _password.value,
            )
            if (user != null) {
                _isLoginSuccess.update { true }
//                isButtonEnabled.value = true
            }
            // 儲存 User 資料 跳頁
        }
    }

    fun showErrorMessage(message: String) {
        _errorMessage.value = message
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    suspend fun login(memEmail: String, memPw: String): Member? {
        try {
            val response = RetrofitInstance.api.login(
                LoginRequest(
                    memEmail = memEmail,
                    memPw = memPw
                )
            )
            Log.d(tag, "response: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return null
        }
    }

}