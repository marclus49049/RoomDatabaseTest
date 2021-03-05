package com.example.testroomapp.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.testroomapp.R
import com.example.testroomapp.data.User
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.apply {
            textId.text = currentItem.id.toString()
            firstName.text = currentItem.firstName
            lastName.text = currentItem.lastName
            age.text = currentItem.age.toString()
        }
    }

    override fun getItemCount(): Int = userList.size

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}