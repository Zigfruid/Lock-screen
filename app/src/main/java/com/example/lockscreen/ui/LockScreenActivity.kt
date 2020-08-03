package com.example.lockscreen.ui

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lockscreen.R
import com.example.lockscreen.fragments.CallFragment
import com.example.lockscreen.fragments.ContactFragment
import com.example.lockscreen.fragments.MessageFragment
import kotlinx.android.synthetic.main.activity_lock_screen.*


class LockScreenActivity : AppCompatActivity() {
    private val callFragment = CallFragment()
    private val smsFragment = MessageFragment()
    private val contactFragment = ContactFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        setSupportActionBar(toolbar)

        makeCurrentFragment(CallFragment())

        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.call -> makeCurrentFragment(callFragment)
                R.id.sms -> makeCurrentFragment(smsFragment)
                R.id.contacts -> makeCurrentFragment(contactFragment)
            }
            true
             }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.pop_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.exit -> {
                finish()
                onDestroy()
            }
        }
        return super.onOptionsItemSelected(item)
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

