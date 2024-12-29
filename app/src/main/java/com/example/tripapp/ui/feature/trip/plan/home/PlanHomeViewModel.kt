package com.example.tripapp.ui.feature.trip.plan.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.tripapp.ui.feature.trip.plan.restful.Plan

object PlanHomeViewModel: ViewModel() {
    private var _planState = MutableStateFlow(Plan())
    val planState = _planState.asStateFlow()

    private val _plansState = MutableStateFlow(emptyList<Plan>())
    val plansState: StateFlow<List<Plan>> = _plansState.asStateFlow()

    fun addPlan(plan: Plan) {
        _plansState.update {
            val plans = it.toMutableList()
            plans.add(plan)
            plans
        }
    }

    fun setPlan(plan: Plan) {
        _planState.update {
            it.let { plan }
        }
    }

    fun setPlans(plans: List<Plan>) {
        _plansState.update {
            plans
        }
    }

    fun removePlan(id: Int) {
        _plansState.update {
            val plans = it.toMutableList()
            plans.removeIf { it.schNo == id }
            plans
        }
    }

}

