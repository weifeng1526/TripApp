package com.example.swithscreen

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.R
import com.example.tripapp.ui.feature.trip.dataObjects.contries
import com.example.tripapp.ui.feature.trip.dataObjects.convertLongToDate
import com.example.tripapp.ui.feature.trip.plan.create.PlanCreateViewModel
import com.example.tripapp.ui.feature.trip.plan.create.checkDateRange
import com.example.tripapp.ui.feature.trip.plan.create.uriToBitmap
import com.example.tripapp.ui.theme.purple100
import com.example.tripapp.ui.theme.purple200
import com.example.tripapp.ui.theme.purple500
import com.example.tripapp.ui.theme.white100
import com.example.tripapp.ui.theme.white200
import com.example.tripapp.ui.theme.white400

@Composable
fun PlanCoverPicture(
    bitmap: () -> Bitmap,
    onSelect: (Bitmap) -> Unit
) {
    Log.d("enter", "PlanCoverPicture")
    val context = LocalContext.current
    val default by remember { mutableStateOf(Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)) }
    var rememberSelectedUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        uri?.let {
            rememberSelectedUri = it
            val imageBitmap = uriToBitmap(context, it)
            onSelect(imageBitmap?: default)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(start = 6.dp, end = 6.dp, top = 6.dp, bottom = 10.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, purple500, shape = RoundedCornerShape(8.dp))
            .background(white200),
    ) {
        Box {
            Image(
                bitmap = bitmap().asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxSize()
            )
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomEnd)
                    .clip(RectangleShape)
                    .padding(12.dp)
                    .background(white100)
                    .clickable {
                        pickImageLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_box),
                    contentDescription = "Add Icon",
                    modifier = Modifier.size(40.dp),
                    tint = purple200
                )
            }
        }
    }
}

@Composable
fun PlanNameField(
    planName: String,
    isError: Boolean,
    onValueChange: (String) -> Unit
) {
    Log.d("enter", "PlanNameField")
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp, end = 6.dp, top = 0.dp, bottom = 2.dp)
            .background(white400)
    ) {
        Text(
            text = "行程名稱",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp, top = 6.dp, bottom = 2.dp)
        )
        TextField(
            value = planName,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = "命名這個行程",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = if(isError) Color.Red else Color.LightGray
                    ),
                    maxLines = 1
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = white100,
                focusedContainerColor = white100,
                focusedIndicatorColor = purple100,
                unfocusedIndicatorColor = purple100,
            ),
            textStyle = TextStyle(fontSize = 18.sp),
            shape = RectangleShape,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDropdownMenu(
    planCountry: String,
    onCountrySelected: (String) -> Unit,
    isExpand: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    isError: Boolean
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp, end = 6.dp, top = 0.dp, bottom = 2.dp)
            .background(white400)
    ) {
        Text(
            text = "前往國家",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp, top = 6.dp, bottom = 2.dp)
        )
        ExposedDropdownMenuBox(
            expanded = isExpand,
            onExpandedChange = onExpandChange
        ) {
            TextField(
                value = planCountry,
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "選擇前往國家",
                        style = TextStyle(fontSize = 18.sp, color = Color.LightGray),
                        maxLines = 1
                    )
                },
                textStyle = TextStyle(fontSize = 18.sp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.drop_down),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp),
                        tint = purple500
                    )
                },
                maxLines = 1,
                readOnly = true,
                isError = isError,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = white100,
                    focusedContainerColor = white100,
                    focusedIndicatorColor = purple100,
                    unfocusedIndicatorColor = purple100,
                    errorContainerColor = Color.Red,
                    errorIndicatorColor = Color.Red
                ),
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
            )
            ExposedDropdownMenu(
                expanded = isExpand,
                onDismissRequest = onDismissRequest
            ) {
                contries.filter { it.isNotBlank() }.forEach {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = it,
                                style = TextStyle(fontSize = 18.sp)
                            )
                        },
                        onClick = {
                            onCountrySelected(it)
                            onDismissRequest()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CurrencyTextField(planCurrency: String, isError: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp, end = 6.dp, top = 0.dp, bottom = 2.dp)
            .background(white400),
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "幣別",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp, top = 6.dp, bottom = 2.dp)
        )
        TextField(
            value = planCurrency,
            onValueChange = {},
            placeholder = {
                Text(
                    text = "選擇前往國家",
                    style = TextStyle(fontSize = 18.sp, color = Color.LightGray),
                    maxLines = 1
                )
            },
            readOnly = true,
            maxLines = 1,
            isError = isError,
            textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = white100,
                focusedContainerColor = white100,
                focusedIndicatorColor = purple100,
                unfocusedIndicatorColor = purple100,
                errorContainerColor = Color.Red,
                errorIndicatorColor = Color.Red
            ),
            shape = RectangleShape,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun PlanDateRangeField(
    dateRange: String,
    isError: Boolean,
    onExpandChange: (Boolean) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp, end = 6.dp, top = 0.dp, bottom = 2.dp)
            .background(white400)
    ) {
        Text(
            text = "行程日期",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp, top = 6.dp, bottom = 2.dp)
                .background(white400)
        )
        TextField(
            value = dateRange,
            onValueChange = {},
            placeholder = {
                Text(
                    text = "選擇日期範圍",
                    style = TextStyle(fontSize = 18.sp, color = Color.LightGray),
                    maxLines = 1
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.date_range),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    tint = purple200
                )
            },
            enabled = false,
            readOnly = true,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp
            ),
            isError = isError,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = white100,
                unfocusedIndicatorColor = purple100,
                disabledContainerColor = if (isError) Color.Red else white100,
                disabledIndicatorColor = if (isError) Color.Red else purple100,
            ),
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onExpandChange(true) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePikerDialog(
    isExpand: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onResultDateRange: (Long?, Long?) -> Unit,
) {
    var dateRangePickerState = rememberDateRangePickerState()
    var start = dateRangePickerState.selectedStartDateMillis
    var end = dateRangePickerState.selectedEndDateMillis
    var today = System.currentTimeMillis()
    if (isExpand) {
        DatePickerDialog(
            onDismissRequest = { onExpandChange(false) },
            dismissButton = {
                Button(
                    onClick = { onExpandChange(false) },
                    colors = ButtonDefaults.buttonColors(containerColor = purple200)
                ) {
                    Text(
                        text = "取消",
                        style = TextStyle(fontSize = 18.sp),
                        maxLines = 1
                    )
                }
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = purple200),
                    onClick = {
                        checkDateRange(start, end) { errorCode ->
                            var isOpenDialog = false
                            if (errorCode in 1..3) isOpenDialog = true
                            else onResultDateRange(start, end)
                            onExpandChange(isOpenDialog)
                        }
                    }
                ) {
                    Text(
                        text = "確定",
                        style = TextStyle(fontSize = 18.sp),
                        maxLines = 1
                    )
                }
            },
            colors = DatePickerDefaults.colors(containerColor = white100),
            shape = RectangleShape, // 保持矩形外觀
            modifier = Modifier
                .padding(16.dp) // 設置外邊距
                .fillMaxWidth() // 使對話框寬度填滿
        ) {
            DateRangePicker(
                modifier = Modifier.background(Color.LightGray), // 背景顏色設為白色
                state = dateRangePickerState,
                showModeToggle = false,
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, color = Color.Black, shape = RectangleShape)
                            .padding(2.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(0.5f),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "${start?.let { convertLongToDate(it) } ?: ""}",
                                style = TextStyle(fontSize = 18.sp)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(0.5f),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "${end?.let { convertLongToDate(it) } ?: ""}",
                                style = TextStyle(fontSize = 18.sp),
                                color = if (end?.let { it > today } != false) Color.Black else Color.Red
                            )
                        }
                    }
                },
                headline = {},
                colors = DatePickerDefaults.colors(
                    containerColor = white100,
                    selectedDayContainerColor = purple100,
                    dayInSelectionRangeContainerColor = purple100
                )
            )
        }
    }
}

@Composable
fun SubmitPlanBt(isClickBt: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp, end = 6.dp, top = 8.dp, bottom = 8.dp),
    ) {
        //確定
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = purple200),
            onClick = { isClickBt(true) }
        ) {
            Text(
                text = "確定",
                fontSize = 20.sp
            )
        }
    }
}


@Composable
fun PlanCreateScreen(
    navController: NavController,
    planCreateViewModel: PlanCreateViewModel,
    navToPlanEditScreen: (String) -> Unit
) {
    Log.d("enter", "PlanCreateScreen")
    val context = LocalContext.current
    val planPicture by planCreateViewModel.planPicture.collectAsState()
    val planName by planCreateViewModel.planName.collectAsState()
    val planCountry by planCreateViewModel.planCountry.collectAsState()
    val planCurrency by planCreateViewModel.planCurrency.collectAsState()
    val planDateRange by planCreateViewModel.planDateRange.collectAsState()
    val planNameErrorFlag by planCreateViewModel.planNameErrorFlag.collectAsState()
    val planCountryErrorFlag by planCreateViewModel.planCountryErrorFlag.collectAsState()
    val planCurrencyErrorFlag by planCreateViewModel.planCurrencyErrorFlag.collectAsState()
    val planDateRangeErrorFlag by planCreateViewModel.planDateRangeErrorFlag.collectAsState()
    val isCheckForm by planCreateViewModel.isCheckForm.collectAsState()
    val isCanNaigate by planCreateViewModel.isCanNavigate.collectAsState()
    var isExpandContries by rememberSaveable { mutableStateOf(false) }
    var isExpandDateRangePicker by rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    DisposableEffect(Unit) {
        onDispose {
            planCreateViewModel.clear()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white100)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PlanCoverPicture(
            bitmap = { planPicture },
            onSelect = planCreateViewModel.setPlanPicture
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .background(white200),
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PlanNameField(
                planName = planName,
                isError = planCreateViewModel.isErrorPlanName(errorFlag = planNameErrorFlag),
                onValueChange = planCreateViewModel.onPlanNameChange
            )
            CountryDropdownMenu(
                planCountry = planCountry,
                isExpand = isExpandContries,
                onExpandChange = { isExpandContries = it },
                onDismissRequest = { isExpandContries = false },
                isError = planCreateViewModel.isErrorPlanCountry(errorFlag = planCountryErrorFlag),
                onCountrySelected = planCreateViewModel.onPlanContryChange
            )
            CurrencyTextField(
                planCurrency = planCurrency,
                isError = planCreateViewModel.isErrorPlanCurrency(errorFlag = planCurrencyErrorFlag),
            )
            PlanDateRangeField(
                dateRange = planDateRange,
                onExpandChange = { isExpandDateRangePicker = it },
                isError = planCreateViewModel.isErrorPlanDateRange(errorFlag = planDateRangeErrorFlag),
            )
            DateRangePikerDialog(
                isExpand = isExpandDateRangePicker,
                onExpandChange = { isExpandDateRangePicker = it },
                onResultDateRange = planCreateViewModel.onPlanDateRangeChange
            )
            SubmitPlanBt(isClickBt = planCreateViewModel.onClickSubmit)
        }
        LaunchedEffect(isCheckForm) {
            planCreateViewModel.apply {
                checkSubmit()
                submitPlan()
            }
        }
        LaunchedEffect(isCanNaigate) {
            planCreateViewModel.apply {
                if((!getIsRequesting() && getIsCanNavigate()) && getNextRoute().isNotEmpty()) {
                    navToPlanEditScreen(getNextRoute())
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPlanCreateScreen() {
    PlanCreateScreen(
        rememberNavController(),
        planCreateViewModel = viewModel(),
        navToPlanEditScreen = {}
    )
}