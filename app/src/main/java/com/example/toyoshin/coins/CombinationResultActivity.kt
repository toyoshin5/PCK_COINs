package com.example.toyoshin.coins

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class CombinationResultActivity : AppCompatActivity() {
    var total = 0
    var recommended = 0
    var change = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combination_result_view)

        val totalTextView = findViewById<TextView>(R.id.total_text_view)
        val recommendedTextView = findViewById<TextView>(R.id.recommended_payment_text)
        val changeTextView = findViewById<TextView>(R.id.change_text)

        val intent = this.intent
        val total:Int = intent.getIntExtra("total",0)
        totalTextView.setText("¥ "+total)
    }

    fun tapOkay(view: View){
        val intent = Intent(this,ChangeCheckActivity::class.java)
        intent.putExtra("change",change)
        startActivity(intent)
    }

}
