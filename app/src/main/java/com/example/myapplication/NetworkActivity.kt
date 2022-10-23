package com.example.myapplication

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_network.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.zip.Inflater

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)
        //로컬 데이터 베이스의 한계 : 동기화 어려움, 상호작용 불가능
        // 따라서 서버와 통신이 필요함
        // 서버의 요청에 따라서 클라이언트가 요청한 자료를 클라이언트에서 보여줌
        //서버와 통신하는 방법 : 1.해당 url로 요청
        //                 : 2.인증 정보 + 데이터(json형식으로)를 보냄
        // json:  자바 스크립트에서 객체를 만들 때 사용하는 표현식
        // Serializable : 직렬화
        // --id-- name---age---intro----->
        // 위의 값을 하나씩 꺼내서  코틀린이나 자바가 이해 할 수 있는 틀로 변경

        NetworkTask(
            recycler_person,
            LayoutInflater.from(this@NetworkActivity)
        ).execute()
    }
}

class NetworkTask(
    val recyclerView: RecyclerView,
    val inflater: LayoutInflater
) : AsyncTask<Any?, Any?, Array<PersonforServer>?>() {
    //어뎁터를 구현하는 부분은 메인 쓰레드에서 실행 - 뷰를 그릴때
    override fun onPostExecute(result: Array<PersonforServer>?) { //메인 스레드에서만 실행
        val adapter = PersonAdapter(result!!,inflater)
        recyclerView.adapter = adapter
        super.onPostExecute(result)
    }

    //네트워크는 메인 스레드에서 실행이 불가능하다 : doInBackground를 활용
    override fun doInBackground(vararg params: Any?): Array<PersonforServer>? { //다른 스레드에서 실행
        val urlString: String = "http://mellowcode.org/json/students/"
        val url: URL = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        // HttpURLConnection 로 커낵션

        //Request Type : GET(정보 요청) ->200 ok로 응답 , POST(정보 추가 요청) -> 201 create로 응답,
        // DELETE (정보 삭제) , PUT (정보 수정 )
        //status code -> 응답 코드 : 200번대는 처리가 잘 되었음을 의미
        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")
        var buffer = ""
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader( //뭉텅이로 읽음 = 버퍼 (한 글자씩 보다 속도가 빠름)
                //인풋스트림을 읽음
                InputStreamReader(
                    connection.inputStream, //이 값을 UTF-8 형식으로 읽음
                    "UTF-8"
                )
            )
            buffer = reader.readLine() //한라인 읽어옴
            Log.d("connn","inputStream:"+buffer)
        }
        //필요 없는 부분을 잘라내고 객체로 저장
        val data = Gson() //array로 받음
            .fromJson(buffer,Array<PersonforServer>::class.java)
        Log.d("conn","data"+data)
        val age = data[0].user_id
        Log.d("age","data[0].age: "+age)
        return data
    }
}

class PersonAdapter(
    var personList : Array<PersonforServer>,
    var inflater : LayoutInflater
): RecyclerView.Adapter<PersonAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name : TextView
        val age : TextView
        val intro : TextView

        init{
            name = itemView.findViewById(R.id.person_name_net)
            age = itemView.findViewById(R.id.person_age_net)
            intro = itemView.findViewById(R.id.person_ment_net)
        }
    }
    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //뷰 홀더 제작
        val view  = inflater.inflate(R.layout.person_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //홀더를 찾아서 바인딩
        holder.name.setText(personList.get(position).user_id ?:"")
        holder.age.setText(personList.get(position).pw1 ?:"")
        holder.intro.setText(personList.get(position).pw2 ?:"")

    }

}