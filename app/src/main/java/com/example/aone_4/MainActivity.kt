package com.example.aone_4

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buSend.setOnClickListener {
            if (Build.VERSION.SDK_INT < 23){
                sendMessage()
            }else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1)
            }
        }
        var customer = Customer(this)
        buSendCustomer.setOnClickListener {
            var db = customer.readableDatabase
            var cur = db.rawQuery("select * from customer", null)
            cur.moveToFirst()
            while (!cur.isAfterLast){
                var smsCustomer = SmsManager.getDefault();
                smsCustomer.sendTextMessage(cur.getString(1), null, edMessageText.text.toString(),null,null)
                cur.moveToNext()
            }
            Toast.makeText(this, "Send To Customer", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendMessage()
            }
        }
    }
    private fun sendMessage(){
        var sms = SmsManager.getDefault();
        sms.sendTextMessage(edPhone.text.toString(),null,edMessageText.text.toString(),null,null)
        Toast.makeText(this, "Message Send", Toast.LENGTH_SHORT).show()
    }
}