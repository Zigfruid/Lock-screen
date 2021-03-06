package com.example.lockscreen.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.lockscreen.R
import kotlinx.android.synthetic.main.call_fragment.*

class CallFragment : Fragment(R.layout.call_fragment) {
    private val REQUEST_CALL = 1
    private var mEditTextNumber: EditText? = null
    private lateinit var mTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_zero.setOnClickListener{ setTextFields("0")}
        btn_zero.setOnLongClickListener {
            setTextFields("+")
            return@setOnLongClickListener true }
        btn_num1.setOnClickListener{ setTextFields("1")}
        btn_num2.setOnClickListener{ setTextFields("2")}
        btn_num3.setOnClickListener{ setTextFields("3")}
        btn_num4.setOnClickListener{ setTextFields("4")}
        btn_num5.setOnClickListener{ setTextFields("5")}
        btn_num6.setOnClickListener{ setTextFields("6")}
        btn_num7.setOnClickListener{ setTextFields("7")}
        btn_num8.setOnClickListener{ setTextFields("8")}
        btn_num9.setOnClickListener{ setTextFields("9")}
        btn_reshetka.setOnClickListener { setTextFields("#") }
        btn_star.setOnClickListener { setTextFields("*") }
       mEditTextNumber = edit_text_number
        mTextView = tvEnter
        //val imageCall: ImageView = image_call
        btn_call.setOnClickListener {
            makePhoneCall()
        }
        btn_clear.setOnClickListener{
           tvEnter.text = " "
        }
    }

    private fun setTextFields(str:String){
        tvEnter.append(str)
    }


    private fun makePhoneCall() {
        //mEditTextNumber?.setText(mTextView!!.text.toString())
        val number = tvEnter.text.toString()
        if (number.trim { it <= ' ' }.isNotEmpty()) {
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL)
            } else {
                val dial = "tel:$number"
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
            }
        } else {
            Toast.makeText(requireContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall()
            } else {
                Toast.makeText(requireContext(), "Permission DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }


}