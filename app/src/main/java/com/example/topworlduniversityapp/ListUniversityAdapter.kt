package com.example.topworlduniversityapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListUniversityAdapter (private val listUniversity: ArrayList<University>) : RecyclerView.Adapter<ListUniversityAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
        val tvScore : TextView = itemView.findViewById(R.id.tv_item_score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val viewUniv:View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_university,parent, false)
        return ListViewHolder(viewUniv)
    }

    override fun getItemCount(): Int = listUniversity.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(name,description,score,photo) = listUniversity[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.tvScore.text = score
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listUniversity[holder.adapterPosition])
        }
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback{
        fun onItemClicked(data:University)
    }
}