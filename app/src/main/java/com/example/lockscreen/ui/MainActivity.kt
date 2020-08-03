package com.example.lockscreen.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lockscreen.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        btnActivator.setOnClickListener {
            if (etPassword.text.isNotEmpty()){
                Toast.makeText(this, "Вы успешно активировали программу", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LockScreenActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Заполните поле !!!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}