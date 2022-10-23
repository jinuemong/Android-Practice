package com.example.myapplication

import io.realm.RealmObject
//open 키워드 필ㅇ!
open class School :RealmObject(){
    var name: String?  = null
    var location: String?  = null
}