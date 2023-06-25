package com.example.myapplication.JetpackStudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.JetpackStudy.ui.theme.MyApplicationTheme

class JetPackActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting(name = "Android")
            Home()
        }
    }
}

// preview 어노테이션 작성
@Composable
fun MessageCard(name : String){
    Text(text = "hello !! $name")
}
@Preview
@Composable
fun PreviewMessageCard(){
    MessageCard(name = "Android test")
}
///////////////

// 단순 composable 빌드
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}
/////////////////////

// remember 기능 활용
@Composable
fun Home(){
    var workStatus by remember { mutableStateOf("") }
    Harry(workStatus, onWorkStatusChanged = {workStatus = it})
}

@Composable
fun Harry(workStatus:String,onWorkStatusChanged: (String)-> Unit){
    Column {
        Text(text = workStatus.toString())
        TextField(value = workStatus,
            label = { Text(text = "업무 상태")},
            onValueChange = {onWorkStatusChanged(it)})
    }
}
////////