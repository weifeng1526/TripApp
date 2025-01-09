package com.example.tripapp.ui.feature.trip.notes.note

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripapp.ui.feature.trip.dataObjects.Notes
import com.example.tripapp.ui.restful.RequestVM
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel : ViewModel() {
    val requestVM =  RequestVM()

    private var _notesState = MutableStateFlow(Notes())
    val notesState = _notesState.asStateFlow()

    fun createNotes(notes: Notes) {
        viewModelScope.launch {
            val response = requestVM.CreateNotes(notes)
            Log.d("createNotes", response.toString())
        }
    }

    fun getNotes(id: Int) {
        viewModelScope.launch {
            val response = requestVM.GetNotes(id)
            Log.d("getNotes", response.toString())
            _notesState.update {
                response!!
            }
        }
    }

    fun updateNotes(notes: Notes) {
        viewModelScope.launch {
            val response = requestVM.UpdateNotes(notes)
            Log.d("updateNotes", response.toString())
        }
    }
}