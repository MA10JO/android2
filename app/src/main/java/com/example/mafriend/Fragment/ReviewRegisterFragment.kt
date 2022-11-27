package com.example.mafriend.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mafriend.DataClass.boardGetBody
import com.example.mafriend.DataClass.boardPostBody
import com.example.mafriend.R
import com.example.mafriend.Service.ReviewService
import kotlinx.android.synthetic.main.fragment_review_register.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ReviewRegisterFragment: Fragment() {
    var category: Int=0
    init{ instance = this }

    companion object{
        private var instance:ReviewRegisterFragment? = null
        fun getInstance(): ReviewRegisterFragment?
        { return instance  }}

    val items: ArrayList<String> = arrayListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_review_register, container, false)


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        write_town.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.list, android.R.layout.simple_spinner_item)
        write_town.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0){
                    category=0
                }else if(position == 1){
                    category=1
                }else if(position == 2){
                    category=2
                }else{
                    category=3
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        write_complete.setOnClickListener {
            //val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()
            var retrofit = Retrofit.Builder()
                .baseUrl(ReviewService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create()).build()

            var apiService = retrofit.create(ReviewService::class.java)
//boardPostBody(val author:Int?,val title:String?,val content:String?,val category:Int?,val star_point:Int?)
            val data = boardPostBody(2, write_title.text.toString(),write_content.text.toString(),category,write_rate.rating.toInt())
            apiService.post_review(data).enqueue(object : Callback<List<boardGetBody>> {
                override fun onResponse(
                    call: Call<List<boardGetBody>>,
                    response: Response<List<boardGetBody>>
                ) {
                    val result = response.body()
                    Log.e("D", result.toString())
                   val fragmentA = HomeFragment()
                    val bundle = Bundle()
                    fragmentA.arguments = bundle
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.add(R.id.container, fragmentA)
                    transaction.replace(R.id.container, fragmentA.apply { arguments = bundle })
                        .addToBackStack(null)
                    transaction.commit()

                }

                override fun onFailure(call: Call<List<boardGetBody>>, t: Throwable) {
                    Log.e("avatar_create", "OnFailuer+${t.message}")
                }
            })

        }}

}