package com.example.tripapp.ui.feature.trip.plan.create

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tripapp.ui.feature.trip.restfulPlan.Plan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class PlanCreateViewModel : ViewModel() {
    private var _isSampleState = MutableStateFlow(false)
    val isSampleState = _isSampleState.asStateFlow()

    private var _planForCreateteState = MutableStateFlow(Plan())
    val planForCreateteState = _planForCreateteState.asStateFlow()

    fun setPlanForCreate(plan: Plan) {
        _planForCreateteState.update {
            plan
        }
        Log.d("setPlanForCreate", plan.toString())
    }

    fun setIsSample(isSample: Boolean) {
        _isSampleState.update {
            isSample
        }
    }
}