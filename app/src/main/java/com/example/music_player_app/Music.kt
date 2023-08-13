package com.example.music_player_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

lateinit var musicData : ArrayList<MusicData>
class Music : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        recyclerView = findViewById(R.id.recyclerView)

        val songs = arrayOf(R.raw.august, R.raw.gorgeous, R.raw.double_take
            , R.raw.irraday, R.raw.kasoor, R.raw.lemonade, R.raw.obsessed)

        val names = arrayOf("August", "Gorgeous", "Double Take",
        "Iraaday", "Kasoor", "Lemonade", "Obsessed")

        val images = arrayOf(R.drawable.august, R.drawable.gorgeous, R.drawable.doubletake,
        R.drawable.iraaday, R.drawable.kasoor, R.drawable.lemonade, R.drawable.obsessed)

        val l = songs.size-1

        musicData = ArrayList()

        for(i in 0..l) {
            val item = MusicData(songs[i], names[i], images[i])
            musicData.add(item)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MyAdapter(this, musicData)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener{
            override fun itemClick(position: Int) {
                val intent = Intent(applicationContext, play::class.java)
                intent.putExtra("song", musicData[position].songs)
                intent.putExtra("name", musicData[position].name)
                intent.putExtra("img", musicData[position].img)
                intent.putExtra("pos", position)
                startActivity(intent)
            }
        })

    }
}