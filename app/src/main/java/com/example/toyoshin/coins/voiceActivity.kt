package com.example.toyoshin.coins

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.Manifest.permission.RECORD_AUDIO
import android.speech.RecognizerIntent
import android.support.v4.app.ActivityCompat
import java.util.*


class voiceActivity : AppCompatActivity() {

    //テスト用(¥1200円が入力)
    fun test(view:View){
        val intent = Intent(this, CombinationResultActivity::class.java)
        intent.putExtra("total",1200)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Voice Input")
        setContentView(R.layout.activity_voice)
    }
    fun tapKeyPad2(view: View){
        val intent = Intent(this, keypadActivity::class.java)
        startActivity(intent)
    }

    fun using_RECORD_AUDIO (){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, RECORD_AUDIO)) {
                // 拒否
            } else {
                // 許可
                val MY_PERMISSIONS_RECORD_AUDIO = 1//Permissionの識別子
                ActivityCompat.requestPermissions(this, arrayOf(RECORD_AUDIO), MY_PERMISSIONS_RECORD_AUDIO)
            }
        }
        return
    }

    //TODO "日本語対応"

    fun tapMike(view: View){
        //permissionチェック
        using_RECORD_AUDIO()
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US.toString())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Give me the total in Japanese")
        println("startActivity")
        startActivityForResult(intent, 0)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        println("endActivity")
        if (requestCode == 0 && resultCode == RESULT_OK) {
            println("getVoice")
            val results = data!!//結果
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val str = results[0]
            val regex = Regex("""[+-]?\d+""")
            val numStrArray = regex.findAll(str)
            val result = StringBuilder()
            var total = 0
            for (matchedText in numStrArray) {
                val num = Integer.parseInt(matchedText.value)
                total = maxOf(num,total)
            }
            println(total)

            if(total != 0){
                val intent = Intent(this, CombinationResultActivity::class.java)
                intent.putExtra("total",total)
                startActivity(intent)
            }



            // TODO ここに取得した文字列に対する処理を記述

        }
    }
}
