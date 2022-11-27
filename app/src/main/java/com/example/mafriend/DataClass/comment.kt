package com.example.mafriend.DataClass

import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

//댓글 생성
data class commentPostBody(val post:Int?, val author:Int?,val content:String? )

data class commentGetBody(val post:Int?, val author:Int?,val author_userid:String?,val content:String?,val created_At: Date )
