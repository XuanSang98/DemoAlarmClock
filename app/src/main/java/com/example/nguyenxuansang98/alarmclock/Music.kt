package com.example.nguyenxuansang98.alarmclock

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat

class Music : Service() {
    lateinit var media:MediaPlayer
    lateinit var mBuilder : NotificationCompat.Builder
    lateinit var notificationCompat: NotificationManager
    var id =0
    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(intent!!.getStringExtra("extra").equals("on")){
            id=1
        }else if(intent!!.getStringExtra("extra").equals("off")){
            id=0
        }
        if(id==1){
            media = MediaPlayer.create(this,R.raw.start)
            media.start()
            mBuilder = NotificationCompat.Builder(applicationContext).setContentTitle("Báo Thức")
            notificationCompat = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationCompat.notify(1,mBuilder.build())

            id=0
        }else if(id==0){
            media.stop()
            media.reset()
        }
        return START_NOT_STICKY
    }
}