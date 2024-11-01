package com.example.pianosense

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter(private val musicList: List<MusicItem>) :
    RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val musicImage: ImageView = itemView.findViewById(R.id.musicImage)
        val musicTitle: TextView = itemView.findViewById(R.id.musicTitle)
        val musicArtist: TextView = itemView.findViewById(R.id.musicArtist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false)
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val musicItem = musicList[position]
        holder.musicTitle.text = musicItem.title
        holder.musicArtist.text = musicItem.artist
        holder.musicImage.setImageResource(musicItem.imageResId)
    }

    override fun getItemCount(): Int {
        return musicList.size
    }
}
