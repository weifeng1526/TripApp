package com.example.tripapp.ui.feature.trip.plan.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.tripapp.ui.feature.member.MemberRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.tripapp.ui.feature.trip.dataObjects.Plan
import com.example.tripapp.ui.feature.trip.dataObjects.PlanImg
import com.example.tripapp.ui.feature.trip.dataObjects.PlanInfo
import com.example.tripapp.ui.feature.trip.repository.network.PlanRepository
import kotlinx.coroutines.launch
import com.example.tripapp.ui.restful.RequestVM
import kotlinx.coroutines.flow.MutableSharedFlow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class PlanHomeViewModel : ViewModel() {
    val requestVM = RequestVM()

    private var _planState = MutableStateFlow(Plan())
    val planState = _planState.asStateFlow()

    private val _plansState = MutableStateFlow(emptyList<Plan>())
    val plansState: StateFlow<List<Plan>> = _plansState.asStateFlow()

    /* --------------------------------------------------------------------------- */
    private val _plansInfo = MutableStateFlow(emptyList<PlanInfo>())
    val plansInfo: StateFlow<List<PlanInfo>> = _plansInfo.asStateFlow()

    private val _createdPlansInfo = MutableStateFlow(emptyList<PlanInfo>())
    val createdPlansInfo: StateFlow<List<PlanInfo>> = _createdPlansInfo.asStateFlow()

    private val _plansImg = MutableStateFlow(emptyList<PlanImg>())
    val plansImg: StateFlow<List<PlanImg>> = _plansImg.asStateFlow()

    var limit = 10
        set(value) {
            field = value
        }
        get() = field

    private var _offset = MutableStateFlow(0)
    val offset: StateFlow<Int> = _offset.asStateFlow()

    private var _scrollPosition = MutableStateFlow(0)
    val scrollPosition: StateFlow<Int> = _scrollPosition // 暴露給外部觀察

    /* --------------------------------------------------------------------------- */
    // 設定新的滾動位置
    fun setScrollPosition(newPosition: Int) {
        _scrollPosition.value = newPosition
    }

    /* --------------------------------------------------------------------------- */
    private val _plansOfMemberState = MutableStateFlow(emptyList<Plan>())
    val plansOfMemberState: StateFlow<List<Plan>> = _plansOfMemberState.asStateFlow()

    private val _isDialogShow = MutableStateFlow(false)
    val isDialogShow: StateFlow<Boolean> = _isDialogShow.asStateFlow()

    private var _memberNumber = MutableStateFlow(Int)
    val memberNumberState = _memberNumber.asStateFlow()

    /* --------------------------------------------------------------------------- */
    init {
        fetchPlansInfo(MemberRepository.getUid(), limit, getOffset())
        setOffset(limit)
    }

    fun setOffset(offset: Int) {
        _offset.update { offset }
        Log.d("offset", "${getOffset()}")
    }

    fun getOffset(): Int {
        return offset.value
    }

    fun setPlansInfo(plansInfo: List<PlanInfo>) {
        _plansInfo.update { plansInfo }
    }

    fun getPlansInfo(): List<PlanInfo> {
        return _plansInfo.value
    }

    fun fetchPlansInfo(memId: Int) {
        viewModelScope.launch {
            val response = PlanRepository.fetchPlansInfo(memId)
            setPlansInfo(response)
        }
    }

    fun fetchPlansInfo(memId: Int, limit: Int, offset: Int) {
        viewModelScope.launch {
            val response = PlanRepository.fetchPlansInfoPage(memId, limit, offset)
            if (response.isNotEmpty()) {
                PlanRepository.setPlansInfoPage(response)
                addPlansInfo(PlanRepository.getPlansInfoPage())
            }
        }
    }

    fun addPlansInfo(plansInfo: List<PlanInfo>) {
        val newPlansInfo = _plansInfo.value.toMutableList()
        _plansInfo.update {
            newPlansInfo.addAll(plansInfo)
            newPlansInfo
        }
    }

    fun setPlansImg(plansImg: List<PlanImg>) {
        _plansImg.update { plansImg }
    }

    fun getPlansImg(): List<PlanImg> {
        return plansImg.value
    }

    fun fetPlansImg() {

    }
    /* --------------------------------------------------------------------------- */


    fun setPlanByApi(id: Int) {
        viewModelScope.launch {
            val planResponse = requestVM.GetPlan(id)
            planResponse?.let {
                _planState.update {
                    planResponse
                }
                Log.d("planState", "${planState.value}")
            }
        }
    }

    fun createPlan(plan: Plan) {
        viewModelScope.launch {
            val planResponse = requestVM.CreatePlan(plan)
            planResponse?.let {
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

    fun setPlansByMemberByApi(memId: Int) {
        viewModelScope.launch {
            val planResponse = requestVM.GetPlanByMemId(memId)
            Log.d("setPlansByMemberByApi", "${planResponse}")
            setPlansOfMember(planResponse)
            Log.d("setPlansByMemberByApi", "${_plansOfMemberState.value}")
        }
    }

    fun updatePlanByApi(plan: Plan) {
        viewModelScope.launch {
            val planResponse = requestVM.UpdatePlan(plan)
            planResponse?.let {
                _planState.update {
                    planResponse
                }
            }
        }
    }

    fun setPlansOfMember(plans: List<Plan>) {
        _plansOfMemberState.update {
            plans
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

    fun updatePlanImage(schId: Int, image: MultipartBody.Part?) {
        viewModelScope.launch {
            val putIdPart = schId.toString()
                .toRequestBody("text/plain".toMediaTypeOrNull())
            Log.d("putIdPart", "${putIdPart}")
            requestVM.UpdatePlanImage(putIdPart, image)
        }
    }

    /* 已經加入的行程表**/
    fun setPlansOfMemberInCrewByApi(id: Int) {
        viewModelScope.launch {
            val planResponse = requestVM.GetPlansOfMemberInCrew(id)
            Log.d("GetPlansOfMemberInCrew", "${planResponse}")
            setPlans(planResponse)
            Log.d("GetPlansOfMemberInCrew", "${_plansOfMemberState.value}")
        }
    }
}

