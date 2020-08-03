package com.example.lockscreen.ui

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lockscreen.R
import com.example.lockscreen.fragments.CallFragment
import kotlinx.android.synthetic.main.activity_lock_screen.*


class LockScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        val callFragment = CallFragment()
        val smsFragment = MessageFragment()
        val contactFragment = ContactFragment()



        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.call -> makeCurrentFragment(callFragment)
                R.id.sms -> makeCurrentFragment(smsFragment)
                R.id.contacts -> makeCurrentFragment(contactFragment)
            }
            true
             }

    }

    fun optionMenu(view : View){
        val menuOption = PopupMenu(this, view)
        val menuInflater = menuOption.menuInflater
        menuInflater.inflate(R.menu.pop_menu, menuOption.menu)
        menuOption.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.exit -> {
                    onDestroy()
                    finish()
                }

            }
            return@setOnMenuItemClickListener true
        }
        menuOption.show()

    }


    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
                .commit()

        }
    }



    override fun onBackPressed() {

    }

    override fun onPause() {
        val activityManager = applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityManager.moveTaskToFront(taskId, 0)
        super.onPause()
    }

}

