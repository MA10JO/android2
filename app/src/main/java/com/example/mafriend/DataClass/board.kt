package com.example.mafriend.DataClass

import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

//게시판 생성
data class boardPostBody(val author:Int?,val title:String?,val content:String?,val category:Int?,val star_point:Int?)

//전체
data class boardGetBody(val pk:Int?,val author:Int?,val author_userid:String?,val title:String?,val content:String?,val category:Int?,val star_point:Int?,val post_comment:ArrayList<commentGetBody>,val created_at: Date)
//카테고리별
data class categoryBody(val name:String,val post_list:List<boardGetBody>)

data class categories(val pk:Int, val name:String)