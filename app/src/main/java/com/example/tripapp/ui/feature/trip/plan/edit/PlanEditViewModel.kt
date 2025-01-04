package com.example.tripapp.ui.feature.trip.plan.edit

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tripapp.ui.feature.trip.plan.home.PlanHomeViewModel
import com.example.tripapp.ui.restful.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update


class PlanEditViewModel: ViewModel() {
    private var _dstState = MutableStateFlow(Destination())
    val dstState = _dstState.asStateFlow()

    private val _dstsState = MutableStateFlow(emptyList<Destination>())
    val dstsState: StateFlow<List<Destination>> = _dstsState.asStateFlow()

    private val _dstsForDateState = MutableStateFlow(emptyList<Destination>())
    val dstsForDateState: StateFlow<List<Destination>> = _dstsForDateState.asStateFlow()

    /* 以下是所有行程明細的MutableStateFlow **/
    fun addToDses(dst: Destination) {
        _dstsState.update {
            val dsts = it.toMutableList()
            dsts.add(dst)
            dsts
        }
    }

    fun setDsts(dsts: List<Destination>) {
        _dstsState.update {
            dsts
        }
    }

    fun removeFromDsts(dst: Destination) {
        _dstsState.update {
            val dsts = it.toMutableList()
            dsts.remove(dst)
            dsts
        }
    }

    /* 以下是某天的行程明細的MutableStateFlow **/
    fun setDstsForDate(date: String) {
        val dstsFordate = _dstsState.value.filter { it.dstDate.equals(date) }
        _dstsForDateState.update {
            dstsFordate
        }
    }
    fun addToDstForDate(dst: Destination) {
        _dstsForDateState.update {
            val dstsFordate = it.toMutableList()
            dstsFordate.add(dst)
            dstsFordate
        }
    }
}