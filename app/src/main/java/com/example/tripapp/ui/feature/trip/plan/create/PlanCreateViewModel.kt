package com.example.tripapp.ui.feature.trip.plan.create

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripapp.ui.feature.member.MemberRepository
import com.example.tripapp.ui.feature.trip.dataObjects.PlanInfo
import com.example.tripapp.ui.feature.trip.dataObjects.mapContryToCurrency
import com.example.tripapp.ui.feature.trip.plan.edit.setPlanEditRoute
import com.example.tripapp.ui.feature.trip.repository.network.PlanRepository
import com.example.tripapp.ui.restful.RequestVM
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class PlanCreateViewModel : ViewModel() {
    val requestVM = RequestVM()
    val uid = MemberRepository.getUid()

    private var _planInfo = MutableStateFlow(PlanInfo())
    val planInfo: StateFlow<PlanInfo> = _planInfo.asStateFlow()

    private var _planPicture = MutableStateFlow(Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888))
    val planPicture: StateFlow<Bitmap> = _planPicture.asStateFlow()

    val setPlanPicture: (Bitmap) -> Unit = { bitmap ->
        _planPicture.value = bitmap
    }

    private var _planName = MutableStateFlow("")
    val planName: StateFlow<String> = _planName.asStateFlow()

    private var _planCountry = MutableStateFlow("")
    val planCountry: StateFlow<String> = _planCountry.asStateFlow()

    private var _planCurrency = MutableStateFlow("")
    val planCurrency: StateFlow<String> = _planCurrency.asStateFlow()

    private var _planStart = MutableStateFlow<Long?>(null)
    val planStart: StateFlow<Long?> = _planStart.asStateFlow()

    private var _planEnd = MutableStateFlow<Long?>(null)
    val planEnd: StateFlow<Long?> = _planEnd.asStateFlow()

    private var _planDateRange = MutableStateFlow("")
    val planDateRange: StateFlow<String> = _planDateRange.asStateFlow()

    private var _planNameErrorFlag = MutableStateFlow(false)
    val planNameErrorFlag: StateFlow<Boolean> = _planNameErrorFlag.asStateFlow()

    private var _planCountryErrorFlag = MutableStateFlow(false)
    val planCountryErrorFlag: StateFlow<Boolean> = _planCountryErrorFlag.asStateFlow()

    private var _planCurrencyErrorFlag = MutableStateFlow(false)
    val planCurrencyErrorFlag: StateFlow<Boolean> = _planCurrencyErrorFlag.asStateFlow()

    private var _planDateRangeErrorFlag = MutableStateFlow(false)
    val planDateRangeErrorFlag: StateFlow<Boolean> = _planDateRangeErrorFlag.asStateFlow()

    private var _isRequesting = MutableStateFlow(false)
    val isRequesting: StateFlow<Boolean> = _isRequesting.asStateFlow()

    private var _isCheckForm = MutableStateFlow(false)
    val isCheckForm: StateFlow<Boolean> = _isCheckForm.asStateFlow()

    private var _isCanSubmit = MutableStateFlow(false)
    val isCanSubmit: StateFlow<Boolean> = _isCanSubmit.asStateFlow()

    private var _isCanNavigate = MutableStateFlow(false)
    val isCanNavigate: StateFlow<Boolean> = _isCanNavigate.asStateFlow()

    private var _nextRoute = MutableStateFlow("")
    val nextRoute: StateFlow<String> = _nextRoute.asStateFlow()

    fun setIsRequesting(boolean: Boolean) {
        _isRequesting.update { boolean }
    }

    fun setPlanInfo(update: (PlanInfo) -> Unit) {
        _planInfo.value.apply(update)
    }

    fun setPlanPicture(bitmap: Bitmap) {
        _planPicture.value = bitmap
    }

    fun setPlanName(planName: String) {
        _planName.value = planName
    }

    fun setPlanCountry(planCountry: String) {
        _planCountry.value = planCountry
    }

    fun setPlanCurrency(planCurrency: String) {
        _planCurrency.value = planCurrency
    }

    fun setPlanStart(planStart: Long?) {
        _planStart.value = planStart
    }

    fun setPlanEnd(planEnd: Long?) {
        _planEnd.value = planEnd
    }

    fun setPlanDateRange(planDateRange: String) {
        _planDateRange.value = planDateRange
    }

    fun setPlanNameErrorFlag(value: Boolean) {
        _planNameErrorFlag.value = value
    }

    fun setPlanCountryErrorFlag(value: Boolean) {
        _planCountryErrorFlag.value = value
    }

    fun setPlanCurrencyErrorFlag(value: Boolean) {
        _planCurrencyErrorFlag.value = value
    }

    fun setPlanDateRangeErrorFlag(value: Boolean) {
        _planDateRangeErrorFlag.value = value
    }

    fun setIsCheckForm(value: Boolean) {
        _isCheckForm.value = value
    }

    fun setIsCanSubmit(value: Boolean) {
        _isCanSubmit.value = value
    }

    fun setIsCanNavigate(value: Boolean) {
        _isCanNavigate.value = value
    }

    fun setNextRoute(route: String) {
        _nextRoute.value = route
    }

    fun getIsRequesting(): Boolean {
        return isRequesting.value
    }

    fun getPlanInfo(): PlanInfo {
        return planInfo.value
    }

    fun getPlanPicture(): Bitmap {
        return planPicture.value
    }

    fun getPlanName(): String {
        return planName.value
    }

    fun getPlanCountry(): String {
        return planCountry.value
    }

    fun getPlanCurrency(): String {
        return planCurrency.value
    }

    fun getPlanStart(): Long? {
        return planStart.value
    }

    fun getPlanEnd(): Long? {
        return planEnd.value
    }

    fun getPlanDateRange(): String {
        return planDateRange.value
    }

    fun getPlanNameErrorFlag(): Boolean {
        return planNameErrorFlag.value
    }

    fun getPlanCountryErrorFlag(): Boolean {
        return planCountryErrorFlag.value
    }

    fun getPlanCurrencyErrorFlag(): Boolean {
        return planCurrencyErrorFlag.value
    }

    fun getPlanDateRangeErrorFlag(): Boolean {
        return planDateRangeErrorFlag.value
    }

    fun getIsCheckForm(): Boolean {
        return isCheckForm.value
    }

    fun getIsCanSubmit(): Boolean {
        return isCanSubmit.value
    }

    fun getIsCanNavigate(): Boolean {
        return isCanNavigate.value
    }

    fun getNextRoute(): String {
        return nextRoute.value
    }

    val onPlanNameChange: (String) -> Unit = { newValue ->
        setPlanName(newValue)
    }

    val onPlanContryChange: (String) -> Unit = { newValue ->
        setPlanCountry(newValue)
        setPlanCurrency(mapContryToCurrency[newValue] ?: "")
    }

    val onPlanDateRangeChange: (Long?, Long?) -> Unit = { start, end ->
        setPlanStart(start)
        setPlanEnd(end)
        setPlanDateRange(convertDateRangeToString(getPlanStart(), getPlanEnd()))
    }

    val onClickSubmit: (Boolean) -> Unit = { isClicked ->
        setIsCheckForm(isClicked)
    }

    fun isErrorPlanName(errorFlag: Boolean): Boolean {
        val isError = isBlankOrEmptyTextField(getPlanName())
        if (errorFlag && !isError) setPlanNameErrorFlag(false)
        return errorFlag && isError
    }

    fun isErrorPlanCountry(errorFlag: Boolean): Boolean {
        val isError = isBlankOrEmptyTextField(getPlanCountry())
        if (errorFlag && !isError) setPlanCountryErrorFlag(false)
        return errorFlag && isError
    }

    fun isErrorPlanCurrency(errorFlag: Boolean): Boolean {
        val isError = isBlankOrEmptyTextField(getPlanCurrency())
        if (errorFlag && !isError) setPlanCurrencyErrorFlag(false)
        return errorFlag && isError
    }

    fun isErrorPlanDateRange(errorFlag: Boolean): Boolean {
        val isError = isBlankOrEmptyTextField(getPlanDateRange())
        if (errorFlag && !isError) setPlanDateRangeErrorFlag(false)
        return errorFlag && isError
    }

    fun checkSubmit() {
        if(getIsCheckForm()) {
            setPlanNameErrorFlag(isBlankOrEmptyTextField(getPlanName()))
            setPlanCountryErrorFlag(isBlankOrEmptyTextField(getPlanCountry()))
            setPlanCurrencyErrorFlag(isBlankOrEmptyTextField(getPlanCurrency()))
            setPlanDateRangeErrorFlag(isBlankOrEmptyTextField(getPlanDateRange()))
            val isError =
                !(getPlanNameErrorFlag() && getPlanCountryErrorFlag() && getPlanCurrencyErrorFlag() && getPlanDateRangeErrorFlag())
            setIsCanSubmit(isError)
            setIsCheckForm(false)
        }
    }

    fun createPlan(planInfo: RequestBody, image: MultipartBody.Part?) {
        if (getIsRequesting()) return
        viewModelScope.launch {
            setIsRequesting(true)
            val response = PlanRepository.createPlan(planInfo, image)
            response.takeIf { it > 0 }?.let {
                setNextRoute(setPlanEditRoute(it))
                setIsCanNavigate(true)
            }
            setIsRequesting(false)
        }
    }

    fun submitPlan() {
        if(getIsCanSubmit()) {
            setPlanInfo { planInfo ->
                planInfo.schNo = 0
                planInfo.memNo = uid
                planInfo.schName = getPlanName()
                planInfo.schCon = getPlanCountry()
                planInfo.schCur = getPlanCurrency()
                planInfo.schStart = getPlanStart()
                planInfo.schEnd = getPlanEnd()
                planInfo.schState = 1
                planInfo.schLastEdit = System.currentTimeMillis()
            }
            Log.d("plan", "${getPlanInfo()}")
            val imagePart = bitmapToMultipartBodyPart(getPlanPicture(), "planImage")
            val planInfoJson = Gson().toJson(getPlanInfo())
            val planInfoPart = planInfoJson.toRequestBody("application/json".toMediaTypeOrNull())
            createPlan(planInfoPart, imagePart)
        }
    }

    fun clear() {
        setPlanInfo { PlanInfo() }
        setPlanPicture(Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888))
        setPlanName("")
        setPlanNameErrorFlag(false)
        setPlanCountry("")
        setPlanCountryErrorFlag(false)
        setPlanCurrency("")
        setPlanCurrencyErrorFlag(false)
        setPlanStart(null)
        setPlanEnd(null)
        setPlanDateRange("")
        setPlanDateRangeErrorFlag(false)
        setIsRequesting(false)
        setIsCheckForm(false)
        setIsCanSubmit(false)
        setIsCanNavigate(false)
        setNextRoute("")
    }

//    fun createPlanWithCrewByApi(plan: Plan, callback: (Int) -> Unit) {
//        viewModelScope.launch {
//            val planRresponse = requestVM.CreatePlan(plan)
//            var responsedId = 0
//            var newCrew = CrewMmeber()
//            planRresponse?.let { plan ->
//                newCrew.apply {
//                    crewNo = 0
//                    schNo = plan.schNo
//                    memNo = plan.memNo
//                    crewPeri = 2
//                    crewIde = 2
//                    crewName = plan.schName
//                    crewInvited = 3
//                }
//            }
//            val crewResponse = requestVM.CreateCrew(newCrew)
//            crewResponse?.let { crewResponse ->
//                responsedId = crewResponse.schNo
//                Log.d("createPlanWithCrewByApi", "${crewResponse}")
//                Log.d("createPlanByApi", crewResponse.toString())
//            }
//            callback(responsedId)
//        }
//    }
}


//val imagePart = selectedImageUri?.let { uri ->
//                        Log.d("image", "Selected image URI: $uri")
//                        context.contentResolver.openInputStream(uri)?.readBytes()
//                            ?.let { byteArray ->
//                                // 獲取圖片格式
//                                val mimeType =
//                                    context.contentResolver.getType(uri) ?: "image/jpeg"
//                                val imageRequestBody =
//                                    byteArray.toRequestBody(mimeType.toMediaTypeOrNull())
//                                Log.d("image", "RequestBody created with MIME type: $mimeType")
//                                MultipartBody.Part.createFormData(
//                                    "image",
//                                    "image.${mimeType.substringAfter("/")}",
//                                    imageRequestBody
//                                )
//                            }
//                    }  ?: run {
//                        // 當 imageUri 為 null 時，使用預設圖片
//                        val defaultImage = BitmapFactory.decodeResource(context.resources, R.drawable.aaa)
//                        val byteArrayOutputStream = ByteArrayOutputStream()
//                        defaultImage.compress(Bitmap.CompressFormat.JPEG, 2, byteArrayOutputStream)
//                        val defaultImageByteArray = byteArrayOutputStream.toByteArray()
//
//                        val mimeType = "image/jpeg"  // 預設圖片格式
//                        val imageRequestBody = defaultImageByteArray.toRequestBody(mimeType.toMediaTypeOrNull())
//                        Log.d("image", "Using default image")
//
//                        MultipartBody.Part.createFormData(
//                            "image",
//                            "aaa.jpg",  // 預設圖片名稱
//                            imageRequestBody
//                        )
//                    }
//                    planCreateViewModel.createPlanWithCrewByApi(newPlan) { responseId ->
//                        if (responseId > 0) {
//                            planHomeViewModel.updatePlanImage(responseId, imagePart)
//                            navController.navigate("${PLAN_EDIT_ROUTE}/${responseId}")
//                        } else
//                            Log.d("Not Result", "Created Plan ID: $responseId")
//                    }