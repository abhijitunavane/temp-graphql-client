package com.example.demographql.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demographql.R
import com.example.demographql.data.UserViewData

class UserListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var userList: ArrayList<UserViewData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return UserItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserItemViewHolder).bind(userList[position])
    }

    fun setUserList(userList: List<UserViewData>) {
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }
}

private class UserItemViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
    private var name: TextView = itemView.findViewById(R.id.user_list_item_name)
    private var age: TextView = itemView.findViewById(R.id.user_list_item_age)
    private var id: Int = 0

    fun bind(userViewData: UserViewData) {
        name.text = userViewData.name
        age.text = userViewData.age.toString()
        id = userViewData.id
    }
}
