package com.example.lockscreen.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.lockscreen.R
import kotlinx.android.synthetic.main.call_fragment.*

class CallFragment : Fragment(R.layout.call_fragment) {
    private val REQUEST_CALL = 1
    private var mEditTextNumber: EditText? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mEditTextNumber = edit_text_number
        val imageCall: ImageView = image_call
        imageCall.setOnClickListener {
            makePhoneCall()

        }
    }

    private fun makePhoneCall() {
        val number = mEditTextNumber!!.text.toString()
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