package com.example.myapplication.JetpackStudy

import androidx.lifecycle.ViewModel

class viewModel : ViewModel(){
    var user = User("jinwoo")
}

data class User(
    val name : String
) {
}