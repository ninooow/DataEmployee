package com.example.dataemployee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dataemployee.model.Data

class DataAdapter(
    private var DataList: List<Data>,
    private val onClickData: (Data) -> Unit
) : RecyclerView.Adapter<DataAdapter.UserViewHolder>() {
    inner class UserViewHolder(itemView: View, private val onClickData: (Data) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvEmail: TextView = itemView.findViewById(R.id.tv_email)
        private val imgProfile: ImageView = itemView.findViewById(R.id.img_profile)
        fun bind(user: Data) {
            tvName.text = "${user.first_name} ${user.last_name}"
            tvEmail.text = user.email
            Glide.with(itemView.context)
                .load(user.avatar)
                .into(imgProfile)
            itemView.setOnClickListener {
                onClickData(user)
            }
        }
    }
    fun updateData(newList: List<Data>) {
        DataList = newList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_employee, parent, false)
        return UserViewHolder(view, onClickData)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(DataList[position])
    }
    override fun getItemCount(): Int = DataList.size
}