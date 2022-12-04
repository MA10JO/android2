package com.example.mafriend.DataClass

import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

//댓글 생성
data class commentPostBody(val post:Int?, val name:Int?,val content:String? )

data class commentGetBody(val post:Int?, val name:Int?,val nickname:String?,val content:String?,val created_at: Date )
