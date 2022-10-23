package com.example.myapplication

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_async.*
import java.lang.Thread

class AsyncActivity : AppCompatActivity() {
    var task : BackgroundAsyncTask? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)
        //async : 비동기  sync : 동기
        //동기 방식 : 작업을 순서대로 진행
        //안드로이드에서 비동기 방식 다루는 법
        //AsyncTask 상속 받기
        //onPreExcute : 쓰레드 출발하기 전 학 작업
        //doIntBackGround : 쓰레드가 할 작업
        //onPregressUpdate : 중간중간에 메인쓰레드로 온다
        //onPostExcute : 작업을 다 마친 후 메인쓰레드로 온다
        //Async의 장점 : 메인 쓰레드를 기다리게 할 필요가 없다
        //네트워크 작업 시 많이 사용

        //장점 : main thread를 기다리게 할 필요 없다 , 네트워크 작업
        //단점 : 재사용 불가능 , 종료시 onpause에 등록을 안해놓으면 종료 안됨
        //      하나만 실행 가능

        start.setOnClickListener {
            task = BackgroundAsyncTask(progress_bar,ment)
            task?.execute()
        }
        stop.setOnClickListener {
            task?.cancel(true)
        }
    }

    override fun onPause() {
        //액티비티를 벗어날 때 정지하는 방법
        task?.cancel(true)
        super.onPause()
    }
}

class BackgroundAsyncTask(
    val progressbar : ProgressBar,
    val progressText : TextView
): AsyncTask<Int, Int, Int>() {
    //params : doInBackground 에서 사용할 타입
    //progress : onProgressUpdate 에서 사용할 타입
    //result : onPostExecute 에서 사용할 타입
    var percent:Int = 0
    override fun onPreExecute() {
        percent =0
        progressbar.setProgress(percent)
    }
    override fun doInBackground(vararg params: Int?): Int {
        // 전달된 URL 사용 작업
        while(isCancelled() == false){ //취소되었는지 확인
            percent++
            if (percent>100){
                break
            }else{
                publishProgress(percent)
            }
            try{
                Thread.sleep(100)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        return percent
    }

    @SuppressLint("SetTextI18n")
    override fun onProgressUpdate(vararg values: Int?) {
        // 파일 다운로드 퍼센티지 표시 작업
        progressbar.setProgress(values[0] ?:0)
        progressText.setText("퍼센트: "+values[0])
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: Int?) {
        // doInBackground 에서 받아온 total 값 사용 장소
        progressText.setText("작업이 종료 되었습니다.")
    }

    override fun onCancelled() {
        progressbar.setProgress(0)
        progressText.setText("작업이 취소되었습니다.")
    }
}
