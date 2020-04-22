package com.example.nguyenxuansang98.alarmclock

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var calendar :Calendar?=null
    lateinit var alarmClock:AlarmManager
    lateinit var inten:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calendar = Calendar.getInstance()
        alarmClock = getSystemService(ALARM_SERVICE) as AlarmManager
        var pendingInten :PendingIntent?=null
        button_hengio.setOnClickListener {
            inten = Intent(this,AlarmClockReceiver::class.java)
            inten.putExtra("extra","on")
            calendar?.set(Calendar.HOUR_OF_DAY,timePicker.currentHour)
            calendar?.set(Calendar.MINUTE,timePicker.currentMinute)
            var gio = timePicker.currentHour
            var phut = timePicker.currentMinute
            var string_gio = gio.toString()
            var string_phut = phut.toString()
            pendingInten = PendingIntent.getBroadcast(this,0,inten,PendingIntent.FLAG_UPDATE_CURRENT)
            alarmClock.set(AlarmManager.RTC_WAKEUP,calendar!!.timeInMillis,pendingInten)
            textview_giohen.text = "Giờ bạn hẹn là "+string_gio+" : "+string_phut
        }
        button_tathengio.setOnClickListener {
            inten = Intent(this,AlarmClockReceiver::class.java)
            textview_giohen.text = "Bạn đã tắt hẹn giờ "
            alarmClock.cancel(pendingInten)
            inten.putExtra("extra","off")
            sendBroadcast(intent)
        }
    }
}
