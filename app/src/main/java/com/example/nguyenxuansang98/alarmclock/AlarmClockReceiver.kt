package com.example.nguyenxuansang98.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmClockReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var key : String = intent!!.getStringExtra("extra")
        var inten =Intent(context,Music::class.java)
        inten.putExtra("extra",key)
        Log.e("Key ",key)
        context?.startService(inten)
    }
}