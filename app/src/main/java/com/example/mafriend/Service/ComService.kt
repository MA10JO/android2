package com.example.mafriend.Service

import com.example.mafriend.DataClass.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ComService {
    companion object{
        public val API_URL = "http://10.0.2.2:8000/"
    }


    //게시판 등록
    @POST("community/")
    fun post_com(@Body request:comPostBody

    ):Call<boardGetBody>

    //댓글 등록
    @POST("community/comment/")
    fun post_comment(@Body  request:commentPostBody

    ):Call<commentGetBody>


    //게시판 단건 조회
    @GET("community/{comId}/")
    fun get_comById(@Path("comId") comId:Int):Call<boardGetBody>

    //카테고리 목록 조회
    @GET("community/categories")
    fun get_categories():Call<List<categories>>

    //카테고리 별 게시글 조회
    @GET("community/categories/{pk}/")
    fun get_comByCt(@Path("pk") pk:Int):Call<categoryBody>

    //게시판 목록 조회
    @GET("community/")
    fun get_com():Call<List<boardGetBody>>

}

