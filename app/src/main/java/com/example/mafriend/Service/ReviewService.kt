package com.example.mafriend.Service

import com.example.mafriend.DataClass.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ReviewService {
    companion object{
        public val API_URL = "http://10.0.2.2:8000/"
    }


    //게시판 등록
    @POST("review/")
    fun post_review(@Body  request:boardPostBody

    ):Call<boardGetBody>

    //댓글 등록
    @POST("review/comment/")
    fun post_comment(@Body  request:commentPostBody

    ):Call<commentGetBody>


    //게시판 단건 조회
    @GET("review/{reviewId}/")
    fun get_reviewById(@Path("reviewId") reivewId:Int):Call<boardGetBody>

    //카테고리 목록 조회
    @GET("review/categories")
    fun get_categories():Call<List<categories>>

    //카테고리 별 게시글 조회
    @GET("review/categories/{pk}/")
    fun get_reviewByCt(@Path("pk") pk:Int):Call<categoryBody>

    //게시판 목록 조회
    @GET("review/")
    fun get_review():Call<List<boardGetBody>>

    //동네 글 등록
    // @POST("community/")
    // fun post_com(@Body request:

    //동네 글 조회
    // @GET("community/")

    //동네 글 목록
    // @GET("community/")

    //로그인
    @FormUrlEncoded
    @POST("user/signin")
    fun requestLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ) : Call<loginPost>


    //회원가입
    @FormUrlEncoded
    @POST("user/signup")
    //fun post_join(@Body  request: joinPost):Call<joinPost>
    fun requestJoin(
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("name") name:String,
        @Field("nickname") nick:String,
        @Field("phoneNum") phone:String
    ):Call<joinPost>
    //Call<ResponseBody> post_accounts(@Part("")RequestBody param1)



}

