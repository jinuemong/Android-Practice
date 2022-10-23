package com.example.myapplication

import java.io.Serializable

class PersonforServer(
    var user_id : String?  = null,
    var pw1 : String? = null,
    var pw2 : String? = null
) : Serializable //상속