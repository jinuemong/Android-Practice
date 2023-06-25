package com.example.myapplication.JetpackStudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.JetpackStudy.ui.theme.MyApplicationTheme
import com.example.myapplication.JetpackStudy.Message
import com.example.myapplication.R

class JetPackLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 테마는 프로젝트 이름을 기준으로 생성된다.
            // 예제에서는 포로젝트 이름을 ComposeTutorial르 횄기 때문에
            // ComposeTutorialTheme가 적용 된다.
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCard(msg = Message("Android","Jetpack Compose"))
                }
            }
        }
    }
}

@Composable
fun MessageCard(msg:Message) {
    // 디자인 구조를 위한 Row 스코프 추가

    // 메시지 근처에 패딩 추가
    Row(modifier = Modifier.padding(all = 8.dp)){
        // 이미지  추가
        Image(
            painter = painterResource(id = R.drawable.ic_garbage),
            contentDescription ="Contack profile picture",
            // 이미지에 페딩 추가
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                    //이미지에 테두리 추가
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        // horizontal 공간 추가 (이미지와 콜럼 사이)
        Spacer(modifier = Modifier.width(8.dp))

        // 수직 정렬
        Column {
            Text(
                text = msg.author,
                // 매핑 된 색상 변경  :Surface를 활용한 테마 변경
                color = MaterialTheme.colors.secondaryVariant
            )
            // 텍스트 사이에 패딩 추가
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.body)
        }
    }
}

// preview도 테마에 맞게 수정
@Preview
@Composable
fun PreviewMessageCard2() {
    MyApplicationTheme{
        Surface{
            MessageCard(
                msg = Message(
                    "Colleague",
                    "Hey, take a look at Jetpack Compose, it's great!"
                )
            )
        }
    }
}