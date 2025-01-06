package com.example.tripapp.ui.feature.trip.plan.edit

import androidx.lifecycle.ViewModel
import com.example.tripapp.ui.feature.trip.restfulPlan.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalTime


class PlanEditViewModel : ViewModel() {
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

    fun setDstForDateByDesc() {
        _dstsForDateState.update { dsts ->
            dsts.sortedBy { LocalTime.parse(it.dstStart).toSecondOfDay() }
        }
    }

    fun addToDstForDate(dst: Destination) {
        _dstsForDateState.update {
            val dstsFordate = it.toMutableList()
            dstsFordate.add(dst)
            dstsFordate
        }
    }

    fun onStartTimeChange(mode: Int) {
        setDstForDateByDesc()
    }
//        Log.d("onStartTimeChange", "第$index: $time")
//        val dstsForDate = _dstsForDateState.value
//        dstsForDate[index].dstStart = time
//        Log.d("toLong", "${LocalTime.parse(time).toSecondOfDay()}")
//        val sorted =
//            dstsForDate.sortedBy { LocalTime.parse(it.dstStart).toSecondOfDay() }
//        _dstsForDateState.update { sorted }
//        Log.d("_dstsForDateState", "${_dstsForDateState.value}")
//    }
}