package com.example.tripapp.ui.feature.member

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Member (
    val memNo: String = "",
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
    val memNo: String,
    val memEmail: String,
    val memName: String,
    val memPw: String,
    val memIcon: String
)

class MemberUidPref {
    private val _memNo: MutableStateFlow<String> = MutableStateFlow("")
    private val _uid = _memNo.asStateFlow()
    val uid = _uid

    fun initUid(context: Context): String {
        val uidPref = context.getSharedPreferences("uid", Context.MODE_PRIVATE)
        val uid = uidPref.getString("memNo", "") ?: ""
        _memNo.update { uid }
        return ""
    }
}