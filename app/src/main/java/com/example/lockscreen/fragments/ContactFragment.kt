package com.example.lockscreen.fragments

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.pm.PackageManager
import android.media.tv.TvContract.Channels.CONTENT_URI
import android.media.tv.TvContract.PreviewPrograms.CONTENT_URI
import android.net.Uri
import android.os.Bundle
import android.provider.CallLog.CONTENT_URI
import android.provider.Contacts.People
import android.provider.ContactsContract
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.lockscreen.R


class ContactFragment : Fragment(R.layout.contacts_framgent) {
    var contactList: ListView? = null
    var contacts = ArrayList<String?>()
    var addBtn: Button? = null
   // var contactText: EditText? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contactList = activity?.findViewById<View>(R.id.contactList) as ListView
        //addBtn = activity?.findViewById<View>(R.id.addBtn) as Button
     //   contactText = activity?.findViewById<View>(R.id.newContact) as EditText
        val hasReadContactPermission =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS)
        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
            READ_CONTACTS_GRANTED = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_CONTACTS),
                REQUEST_CODE_READ_CONTACTS
            )
        }
        if (READ_CONTACTS_GRANTED) {
            loadContacts()
        }
//        addBtn!!.isEnabled = READ_CONTACTS_GRANTED


//        addBtn!!.setOnClickListener {
//            onAddContact(it)
//        }

    }

//    private fun onAddContact(v: View?) {
//        val contactValues = ContentValues()
//        val newContact = contactText!!.text.toString()
//        contactText!!.setText("")
//        contactValues.put(ContactsContract.RawContacts.ACCOUNT_NAME, newContact)
//        contactValues.put(ContactsContract.RawContacts.ACCOUNT_TYPE, newContact)
//        val newUri =
//            activity?.contentResolver?.insert(ContactsContract.RawContacts.CONTENT_URI, contactValues)
//        val rawContactsId = ContentUris.parseId(newUri!!)
//        contactValues.clear()
//        contactValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactsId)
//        contactValues.put(
//            ContactsContract.Data.MIMETYPE,
//            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
//        )
//        contactValues.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, newContact)
//        activity?.contentResolver?.insert(ContactsContract.Data.CONTENT_URI, contactValues)
//        Toast.makeText(
//            requireContext(),
//            "$newContact добавлен в список контактов",
//            Toast.LENGTH_LONG
//        ).show()
//        loadContacts()
//    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_READ_CONTACTS -> {
                if (!(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)) {
                    READ_CONTACTS_GRANTED = true
                    loadContacts()
                }
                addBtn!!.isEnabled = READ_CONTACTS_GRANTED
            }

        }
    }
    private fun loadContacts() {
        contacts.clear()
        val contentResolver = activity?.contentResolver
        val cursor = contentResolver?.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // получаем каждый контакт
                val contact = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                )
                val numberContact = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))


                // добавляем контакт в список
                contacts.add("$contact \n$numberContact")


            }
            cursor.close()
        }
        // создаем адаптер
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, contacts
        )
        // устанавливаем для списка адаптер
        contactList?.adapter = adapter
    }
    companion object {
        private const val REQUEST_CODE_READ_CONTACTS = 1
        private var READ_CONTACTS_GRANTED = false
    }
}


