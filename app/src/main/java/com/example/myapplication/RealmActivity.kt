package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_realm.*


class RealmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)
        Realm.init(this@RealmActivity) //초기화 필요
        val config : RealmConfiguration = RealmConfiguration
            .Builder()
            .allowWritesOnUiThread(true)
            .deleteRealmIfMigrationNeeded() //마이그레이션이 필요하다면 지움
                //마이그레이션은 데이터베이스 동기화 해줌을 의미
                // 데이터의 속성이 추가될 때 기존 값들을 지워줌
            .build() //메소드 체이닝
        Realm.setDefaultConfiguration(config)
        val ream = Realm.getDefaultInstance()

        button_save.setOnClickListener{
            ream.executeTransaction {
                //트랜잭션 실행
                with(it.createObject(School::class.java)){
                    //with로 묶으면 this로 처리 가능
                    //데이터 넣어주기
                    this.name = "어떤 대학교"
                    this.location = "서울"
                }
            }
        }
        button_load.setOnClickListener{
            ream.executeTransaction {
                val data = it.where(School::class.java).findFirst()
                Log.d("dataA","data:"+data)

            }
        }
        button_delete.setOnClickListener{
            ream.executeTransaction {
                it.where(School::class.java)
                    .findAll()
                    .deleteAllFromRealm()

            }
        }
    }
}

