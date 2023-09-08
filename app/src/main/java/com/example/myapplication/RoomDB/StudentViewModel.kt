package com.example.myapplication.RoomDB

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StudentViewModel
@Inject
constructor(
    private val repository: StudentRepository,
    val handler: SavedStateHandle
) : ViewModel(){

}