package com.example.tripapp.ui.feature.member.home.tabs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TabsVM : ViewModel() {
    private val _tabVisibility = MutableStateFlow(true)
    val tabVisibility = _tabVisibility.asStateFlow()
    fun updateTabState(enable: Boolean) {
        _tabVisibility.value = enable
    }
}