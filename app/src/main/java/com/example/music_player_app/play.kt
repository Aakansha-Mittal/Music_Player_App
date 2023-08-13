package com.example.music_player_app

import android.media.Image
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView

class play : AppCompatActivity() {

    lateinit var musicPlayer : MediaPlayer
    var totalTime : Int = 0
    var song : Int = R.raw.august
    var img : Int = R.drawable.august
    lateinit var name : String
    lateinit var seekBar : SeekBar
    lateinit var play : ImageButton
    lateinit var pause : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        var pos = intent.getIntExtra("pos",0)
        val len = musicData.size

        song = intent.getIntExtra("song", R.raw.august)
        name = intent.getStringExtra("name").toString()
        img = intent.getIntExtra("img", R.drawable.august)


        val tvName = findViewById<TextView>(R.id.tvName)
        val songImg = findViewById<ImageView>(R.id.songImg)
        play = findViewById<ImageButton>(R.id.play)
        pause = findViewById<ImageButton>(R.id.pause)
        seekBar = findViewById<SeekBar>(R.id.seekBar)
        val prev = findViewById<ImageButton>(R.id.prev)
        val next = findViewById<ImageButton>(R.id.next)

        tvName.text = name
        songImg.setImageResource(img)

        prev.setOnClickListener {
            tvName.text= musicData[(pos-1)%len].name
            songImg.setImageResource(musicData[(pos-1)%len].img)
            song= musicData[(pos-1)%len].songs
            playMusic(song)
            pos=pos-1

        }

        next.setOnClickListener {
            tvName.text= musicData[(pos+1)%len].name
            songImg.setImageResource(musicData[(pos+1)%len].img)
            song= musicData[(pos+1)%len].songs
            playMusic(song)
            pos=pos+1

        }

        playMusic(song)

    }

    private fun playMusic(song: Int) {
        musicPlayer = MediaPlayer.create(this, song)
        musicPlayer.setVolume(1f, 1f)
        totalTime = musicPlayer.duration

        play.setOnClickListener {
            musicPlayer.start()
        }

        pause.setOnClickListener {
            musicPlayer.pause()
        }

        seekBar.max = totalTime
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    musicPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }

            override fun onStopTrackingTouch(seekBar: SeekBar?) { }
        })

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                seekBar.progress = musicPlayer.currentPosition
                handler.postDelayed(this, 1000)
            }
        }, 0)

    }
}