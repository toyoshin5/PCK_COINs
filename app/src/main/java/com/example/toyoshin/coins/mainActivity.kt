package com.example.toyoshin.coins

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class mainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun tapVoice(view: View){
        val intent = Intent(this, voiceActivity::class.java)
        startActivity(intent)
    }
    fun tapKeyPad(view: View){
        val intent = Intent(this, keypadActivity::class.java)
        startActivity(intent)
    }
}
