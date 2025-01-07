package com.example.tripapp.ui.feature.member.home.tabs

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotifyViewModel : ViewModel() {
    private val _tabVisibility = MutableStateFlow(true)
    val tabVisibility = _tabVisibility.asStateFlow()
    fun updateTabState(enable: Boolean) {
        _tabVisibility.value = enable
    }
}