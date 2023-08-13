package com.example.music_player_app

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.music_player_app.MyAdapter.MyViewHolder

class MyAdapter(val context : Activity, val musicData : ArrayList<MusicData>)
    : RecyclerView.Adapter<MyViewHolder>() {

    lateinit var myListener : onItemClickListener

    interface onItemClickListener {
        fun itemClick(position: Int) {

        }
    }

    fun setOnItemClickListener(listener : onItemClickListener) {
        myListener = listener
    }

    override fun getItemCount(): Int {
        return musicData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item,parent, false)
        return MyViewHolder(view, myListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.image.setImageResource(musicData[position].img)
        holder.name.text = musicData[position].name
    }

    class MyViewHolder(itemView : View, listener:onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.song_name)
        var image = itemView.findViewById<ImageView>(R.id.image)

        init{
            itemView.setOnClickListener {
                listener.itemClick(adapterPosition)
            }
        }
    }

}

