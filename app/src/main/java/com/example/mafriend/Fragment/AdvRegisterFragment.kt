package com.example.mafriend.Fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.avatwin.Converter.LocalDateTimeConverter
import com.example.mafriend.DataClass.advPostBody
import com.example.mafriend.DataClass.boardGetBody
import com.example.mafriend.DataClass.boardPostBody
import com.example.mafriend.DataClass.categories
import com.example.mafriend.R
import com.example.mafriend.Service.AdvService
import com.example.mafriend.Service.ReviewService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_review_list.view.*
import kotlinx.android.synthetic.main.fragment_review_register.*
import kotlinx.android.synthetic.main.fragment_review_register.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.LocalDateTime

class AdvRegisterFragment: Fragment() {
    var category: Int=0
    init{ instance = this }

    companion object{
        private var instance:AdvRegisterFragment? = null
        fun getInstance(): AdvRegisterFragment?
        { return instance  }}
    val names= ArrayList<String>()
    val items: ArrayList<String> = arrayListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_adv_register, container, false)

        var retrofit1 = Retrofit.Builder()
            .baseUrl(AdvService.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create()).build()

        var apiService1 = retrofit1.create(AdvService::class.java)
//boardPostBody(val author:Int?,val title:String?,val content:String?,val category:Int?,val star_point:Int?)
        //val data = boardPostBody(2, write_title.text.toString(),write_content.text.toString(),category,write_rate.rating.toInt())
        apiService1.get_categories().enqueue(object : Callback<List<categories>> {
            override fun onResponse(
                call: Call<List<categories>>,
                response: Response<List<categories>>
            ) {
                var result = response.body()
                Log.e("D", result.toString())
                result!!.forEach {
                    names.add(it.name)
                    Log.e("avatar_creat",names[0])
                }

                val itemss= names.toArray(arrayOfNulls<String>(names.size))
                val adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,itemss)
                root.write_town.adapter = adapter1
                root.write_town.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        category = position
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }}

            override fun onFailure(call: Call<List<categories>>, t: Throwable) {
                Log.e("avatar_create", "OnFailuer+${t.message}")
            }
        })

        return root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    //spinner adapter에 categoriy 목록 설정



        write_complete.setOnClickListener {
            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeConverter()).create()
            var retrofit = Retrofit.Builder()
                .baseUrl(AdvService.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create()).build()

            var apiService = retrofit.create(AdvService::class.java)
//boardPostBody(val author:Int?,val title:String?,val content:String?,val category:Int?,val star_point:Int?)
            val data = advPostBody(2, write_title.text.toString(),write_content.text.toString(),category+1,1,"Ss")
            apiService.post_adv(data).enqueue(object : Callback<boardGetBody> {
                override fun onResponse(
                    call: Call<boardGetBody>,
                    response: Response<boardGetBody>
                ) {
                    val result = response.body()
                    Log.e("D", result.toString())
                   val fragmentA = AdvFragment()
                    val bundle = Bundle()
                    fragmentA.arguments = bundle
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.add(R.id.container, fragmentA)
                    transaction.replace(R.id.container, fragmentA.apply { arguments = bundle })
                        .addToBackStack(null)
                    transaction.commit()

                }

                override fun onFailure(call: Call<boardGetBody>, t: Throwable) {
                    Log.e("avatar_create", "OnFailuer+${t.message}")
                }
            })

        }}

}