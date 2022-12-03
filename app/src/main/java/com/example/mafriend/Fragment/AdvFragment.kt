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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avatwin.Converter.LocalDateTimeConverter
import com.example.mafriend.Adapter.AdvAdapter
import com.example.mafriend.DataClass.categories
import com.example.mafriend.DataClass.categoryBody
import com.example.mafriend.R
import com.example.mafriend.Service.AdvService
import com.example.mafriend.Service.ReviewService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_review_list.*
import kotlinx.android.synthetic.main.fragment_review_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.LocalDateTime

class AdvFragment: Fragment() {
    var category=0
    // var itemss= arrayOf()
    val names= ArrayList<String>()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_adv_list, container, false)


        //카테고리 목록 가져와서 넣기
        //동네설정
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
                root.town.adapter = adapter1
                root.town.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        category=position
                        getreview(category)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }
            }

            override fun onFailure(call: Call<List<categories>>, t: Throwable) {
                Log.e("avatar_create", "OnFailuer+${t.message}")
            }
        })




        //생성 페이지로 이동
        root.btn_write.setOnClickListener {
            val fragmentA = AdvRegisterFragment()
            val bundle = Bundle()
            fragmentA.arguments=bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.container,fragmentA)
            transaction.replace(R.id.container, fragmentA.apply { arguments = bundle }).addToBackStack(null)
            transaction.commit()
        }




        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getreview(categorys : Int) {
        //layoutmanager설정
        val layoutManager = LinearLayoutManager(requireActivity())
        recyclerview_review.layoutManager = layoutManager
        lateinit var adapter: AdvAdapter

        //리뷰 목록 가져오기
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeConverter()).create()

        var retrofit = Retrofit.Builder()
            .baseUrl(AdvService.API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
        //사용자 동네 가져오면 api주소 바꾸고 adapter에 post_list전달
        //spinner 기본설정
        var apiService = retrofit.create(AdvService::class.java)
        var tests = apiService.get_advByCt(categorys+1)
        tests.enqueue(object : Callback<categoryBody> {
            override fun onResponse(call: Call<categoryBody>, response: Response<categoryBody>) {
                if (response.isSuccessful) {
                    var mList = response.body()!!
                    Log.e("team",mList.toString())
                    adapter = AdvAdapter(mList.post_list)
                    recyclerview_review.adapter= adapter

                    adapter.setItemClickListener(object : AdvAdapter.ItemClickListener {
                        override fun onClick(view: View, position: Int) {


                            //Log.e("ddd", "Ss")

                            val fragmentA = AdvDetailFragment()
                            val bundle = Bundle()
                            bundle.putInt("reviewNum", mList.post_list[position].pk!!)
                            bundle.putString("reviewCategory",mList.name)
                            fragmentA.arguments=bundle
                            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                            transaction.add(R.id.container,fragmentA)
                            transaction.replace(R.id.container, fragmentA.apply { arguments = bundle }).addToBackStack(null)
                            transaction.commit()

                        }
                    })
                } }

            override fun onFailure(call: Call<categoryBody>, t: Throwable) {
                Log.e("team", "OnFailuer+${t.message}")
            } })
    }


}