package com.example.lockscreen.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.lockscreen.ui.MainActivity

class StartActivityOnBootReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == p1?.action) {
            val intent = Intent(p0, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            p0?.startActivity(intent)
        }
    }
}