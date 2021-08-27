package com.annazaqaryan.moviesbyaz

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class MovieService : Service() {

    private val mIBinder: IBinder? = null
    private lateinit var player: MediaPlayer

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        player = MediaPlayer.create(this,R.raw.movie_audio)
        player.isLooping = true
        player.start()

        sendMessage("POPULAR MOVIES FROM themoviedb.org")

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mIBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
    }

    private fun sendMessage(msg: String) {
        val intent = Intent(KEY_ACTION)
        intent.putExtra("Extra", msg)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    companion object{

        const val KEY_ACTION = "key"

        fun Context.startService(){
            val intent = Intent(this, MovieService::class.java)
            startService(intent)
        }

        fun Context.stopService(){
            val intent = Intent(this, MovieService::class.java)
            stopService(intent)
        }
    }
}