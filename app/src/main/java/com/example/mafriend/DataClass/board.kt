package com.example.mafriend.DataClass

import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

//게시판 생성
data class boardPostBody(val author:Int?,val title:String?,val content:String?,val category:Int?,val star_point:Int?)

data class boardGetBody(val author:Int?,val author_userid:String?,val title:String?,val content:String?,val category:Int?,val star_point:Int?,val post_comment:ArrayList<commentGetBody>)


