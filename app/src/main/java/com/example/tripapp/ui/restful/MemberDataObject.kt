package com.example.tripapp.ui.restful

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object MemberDataObject {
    private val _memNO: MutableStateFlow<String> = MutableStateFlow("")
    private val _uid = _memNO.asStateFlow()
    val uid = _uid

    fun initUid(context: Context): Unit {
        val uidPref = context.getSharedPreferences("uid", Context.MODE_PRIVATE)
        val uid = uidPref.getString("memNO", "") ?: ""
        _memNO.update { uid }
    }
}