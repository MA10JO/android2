package com.example.mafriend.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mafriend.DataClass.boardGetBody
import com.example.mafriend.R
import kotlinx.android.synthetic.main.item_board_list.view.*
import kotlinx.android.synthetic.main.item_review_list.view.*


class AdvAdapter(var items: List<boardGetBody>):RecyclerView.Adapter<AdvAdapter.ViewHolder>(){
   // var items= ArrayList<boardGetBody>()
        interface ItemClickListener {
            fun onClick(view: View, position: Int)
        }

    //클릭리스너 선언
    private lateinit var itemClickListner: ItemClickListener

    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):AdvAdapter.ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_board_list,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdvAdapter.ViewHolder, position: Int) {
        val item=items[position]
        holder.setItem(item)

        holder.itemView.setOnClickListener {
            itemClickListner.onClick(it, position)

        }
    }

    override fun getItemCount()=items.size


    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        @SuppressLint("ResourceAsColor")
        fun setItem(item:boardGetBody){
            itemView.board_title.text = item.title
            itemView.board_username.text = item.nickname
            itemView.board_time.text = item.created_at.toString().substring(0,10)+" "+item.created_at.toString().substring(11,16)
            if(item.state==1){
            itemView.board_istogether.text="모집중"}
            else{
                itemView.board_istogether.text="모집완료"
                itemView.board_istogether.setBackgroundColor(R.color.gray)
            }
        // var a= URLDecoder.decode(item.image!!.substring(ApiService.API_URL.length+1), "utf-8");
            //Glide.with(itemView).load(item.image!!).into(itemView.limg)

        }
    }

}