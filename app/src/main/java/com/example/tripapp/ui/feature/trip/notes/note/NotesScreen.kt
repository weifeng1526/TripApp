package com.example.tripapp.ui.feature.trip.notes.note

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.R
import com.example.tripapp.ui.feature.trip.dataObjects.Destination
import com.example.tripapp.ui.feature.trip.dataObjects.Notes
import com.example.tripapp.ui.feature.trip.plan.edit.PlanEditViewModel


@Composable
fun NotesScreen(
    navController: NavController,
    notesViewModel: NotesViewModel,
    dstNo: Int,
    uid: Int,
    dstName: String,
) {
    val dstPicForNotes by notesViewModel.imageState.collectAsState()
    val newNotesState by notesViewModel.notesState.collectAsState()
    // 畫面進入時執行
    Log.d("Top_newNotesState", "NotesState: $newNotesState")
    LaunchedEffect(newNotesState) {
        notesViewModel.setNotesByApi(dstNo, uid)
        notesViewModel.setImageByApi(dstNo)
        Log.d("NotesScreen_LaunchedEffect_uid", "NotesState: $uid")
        Log.d("NotesScreen_LaunchedEffect_newNotesState", "NotesState: $newNotesState")
        Log.d("NotesScreen_LaunchedEffect_notesViewModel", "dstNo: ${dstNo}")
    }
    val imageBitmap =
        dstPicForNotes?.dstPic?.let { BitmapFactory.decodeByteArray(dstPicForNotes?.dstPic, 0, it.size).asImageBitmap() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.black_200))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${dstName}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                if (imageBitmap != null ) {
                    Log.d("PIC", "imageBitmap: $imageBitmap")
                    Image(
                        bitmap = imageBitmap,
                        contentDescription = "image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.padding(8.dp).fillMaxSize().clip(
                            RoundedCornerShape(16.dp)))
                }else{
                Image(
                    painter = painterResource(R.drawable.aaa),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight().clip(RoundedCornerShape(16.dp))
                )
            }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "旅遊筆記內容",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
                Log.d("NotesScreen_OutlinedTextField", "NotesState: $newNotesState")
            OutlinedTextField(
                value = newNotesState?.drText ?: "",
                onValueChange = { newText ->
                    if (newNotesState!=null){
                        notesViewModel.updateNotes(
                            newNotesState?.copy(drText = newText) ?: Notes()
                            // 使用新內容更新資料庫
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                placeholder = { Text(text = "這邊可以輸入文字") },
                singleLine = false,
                maxLines = 6,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
    }
}


fun saveNote(notes: String) {
    Log.d("TravelNoteScreen", "Note saved: $notes")
}

@Preview
@Composable
fun NotesScreenPreview() {
    NotesScreen(
        navController = rememberNavController(),
        notesViewModel = NotesViewModel(),
        dstNo = viewModel(),
        uid = viewModel(),
        dstName = viewModel(),
    )
}