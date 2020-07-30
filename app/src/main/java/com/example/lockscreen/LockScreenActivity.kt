package com.example.lockscreen

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_lock_screen.*

class LockScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)
        supportActionBar?.hide()


        btnExit.setOnClickListener {
            finish()
        }

    }

    override fun onBackPressed() {

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
         if(keyCode == KeyEvent.KEYCODE_HOME){
            Toast.makeText(this, "вы нажали на кнопку назад", Toast.LENGTH_SHORT).show()
        }
        return super.onKeyDown(0, event)
    }

    override fun onPause(): Unit {
        super.onPause()
        val activityManager = applicationContext
            .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.moveTaskToFront(taskId, 0)
    }
}