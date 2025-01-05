package com.example.tripapp.ui.restful

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Member (
    val memNo: Int = 0,
    val memEmail: String = "",
    val memName: String = "",
    val memPw: String = "",
    val memSta: Byte = 0,
    val memIcon: String = ""
)

data class LoginRequest(
    val memEmail: String,
    val memPw:String
)

data class SignUpRequest(
    val memNo: Int
)

class MemberDataObjects {
    private val _memNO: MutableStateFlow<String> = MutableStateFlow("")
    private val _uid = _memNO.asStateFlow()
    val uid = _uid

    fun initUid(context: Context): Unit {
        val uidPref = context.getSharedPreferences("uid", Context.MODE_PRIVATE)
        val uid = uidPref.getString("memNo", "") ?: ""
        _memNO.update { uid }
    }
}