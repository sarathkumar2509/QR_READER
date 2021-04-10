package com.example.qrreader

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_scan.setOnClickListener {
            val scan = IntentIntegrator(this)
            scan.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode==Activity.RESULT_OK){
                val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null){
                if (result.contents == null){
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
                }else{
                    //Toast.makeText(this, "Scanned "+ result?.contents, Toast.LENGTH_SHORT).show()
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Message in side QR ")
                    builder.setMessage(result.contents)
                    builder.show()
                }
            }else{
                super.onActivityResult(requestCode, resultCode, data)
            }

        }

    }
}
