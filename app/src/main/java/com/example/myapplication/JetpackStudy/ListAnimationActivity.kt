package com.example.myapplication.JetpackStudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.JetpackStudy.ui.theme.MyApplicationTheme
import com.example.myapplication.R

// Compose를 활용해서 목록과 애니메이션 추가
// UI 상태를 저장하기 위해서 메시지 확장을 추적해야 함
// 이 추적 기능을 구현하기 위해서 remember와 mutableStateOf 함수 사용
class ListAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewConversation()
        }
    }
}

@Composable
fun Conversation(messages : List<Message>){
    LazyColumn{
        items(messages){message->
            MessageCard2(msg = message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation(){
    // 제작해둔 테마 사용
    MyApplicationTheme {
        Conversation(messages = SampleData.conversationSample)
    }
}

// 애니메이션 적용을 위한 메시지 카드 2
@Composable
fun MessageCard2(msg:Message) {
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

        // 메시지 확장 여부를 추적하기 위한 변수 (remember로 ui 상태 기억)
        var isExpanded by remember { mutableStateOf(false) }
        // surfacecolor를 적용 : 다른 색상으로 점진적 변화
        // primary -> surface로 점진적 수정 배경 애니메이션 적용
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary
            else MaterialTheme.colors.surface
        )
        // 수직 정렬
        //isExpanded가 클릭 상태인지 확인
        // 이 column을 클릭하면, isExpanded 변수를 토글(확장)함
        Column (modifier = Modifier.clickable { isExpanded=!isExpanded }){
            Text(
                text = msg.author,
                // 매핑 된 색상 변경  :Surface를 활용한 테마 변경
                color = MaterialTheme.colors.secondaryVariant,
                // 텍스트 스타일 지정
                style = MaterialTheme.typography.subtitle2
            )
            // 텍스트 사이에 패딩 추가
            Spacer(modifier = Modifier.height(4.dp))

            // 텍스트에 도형 추가 (텍스트를 감싸는 이미지
            Surface(
                shape = MaterialTheme.shapes.medium, elevation = 1.dp,
                color = surfaceColor, // 위에서 적용한 점진적 애니메이션 칼라 적용
                // 점차적인 표면 크기 변화 적용 (1dp)
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // 메세지가 확장 상태라면 모든 메시지를 보여주고
                    // 확장 상태가 아니라면 1줄만 보여주기
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }

        }
    }
}