package com.example.tripapp.ui.feature.trip.plan.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.tripapp.ui.feature.trip.restfulPlan.Plan
import com.ron.restdemo.RetrofitInstance
import kotlinx.coroutines.launch

class PlanHomeViewModel : ViewModel() {
    private var _planState = MutableStateFlow(Plan())
    val planState = _planState.asStateFlow()

    private val _plansState = MutableStateFlow(emptyList<Plan>())
    val plansState: StateFlow<List<Plan>> = _plansState.asStateFlow()

    private val _contriesState = MutableStateFlow(emptyList<String>())
    val contriesState: StateFlow<List<String>> = _contriesState.asStateFlow()

    private val _plansByMemberState = MutableStateFlow(emptyList<Plan>())
    val plansByMemberState: StateFlow<List<Plan>> = _plansByMemberState.asStateFlow()

    private val _plansByContryState = MutableStateFlow(emptyList<Plan>())
    val plansByContryState: StateFlow<List<Plan>> = _plansByContryState.asStateFlow()


    private val _isDialogShow = MutableStateFlow(false)
    val isDialogShow: StateFlow<Boolean> = _isDialogShow.asStateFlow()

    private val _searchWord = MutableStateFlow("")
    val searchWord: StateFlow<String> = _searchWord.asStateFlow()

    private var _memberNumber = MutableStateFlow(Int)
    val memberNumberState = _memberNumber.asStateFlow()

//    init {
//        viewModelScope.launch {
//            val response = RetrofitInstance.api.getPlans()
//            response?.let {
//                _plansState.update {
//                    response
//                }
//                setContryNamesFromPlans(response)
//            }
//
//
//        }
//        viewModelScope.launch {
//            val response = RetrofitInstance.api.getPlanByMemId(1)
//            response.let {
//                _plansByMemberState.update {
//                    response
//                }
//            }
//        }
//    }




    //api
    fun createPlan(plan: Plan) {
        viewModelScope.launch {
            val planResponse = RetrofitInstance.api.CreatePlan(plan)
            planResponse.let {
                _planState.update {
                    planResponse
                }
            }
        }
    }

    fun setPlans(plans: List<Plan>) {
        _plansState.update {
            plans
        }
    }

    fun setPlansByMember(memNo: Int) {
        _plansByMemberState.update {
            _plansState.value.filter {
                it.memNo == memNo
            }
        }
    }

    fun setContryNamesFromPlans(plans: List<Plan>) {
        _contriesState.update {
            plans.groupBy {
                it.schCon
            }.keys.toList()
        }
    }

    fun setPlansByContry(contry: String) {
        viewModelScope.launch {
            val planResponse = RetrofitInstance.api.getPlansByContry(contry)
            planResponse?.let {
                _plansByContryState.update {
                    planResponse
                }
            }
            if (plansByContryState.value.isNotEmpty())
                _isDialogShow.update { true }
        }
    }

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


    fun removePlan(id: Int) {
        _plansState.update {
            val plans = it.toMutableList()
            plans.removeIf { it.schNo == id }
            plans
        }
    }

    fun onDismissDialog() {
        _isDialogShow.update { false }
    }

    fun setSearchWord(word: String) {
        _searchWord.update { word }
        Log.d("searchWord", "${word}")
    }
}

