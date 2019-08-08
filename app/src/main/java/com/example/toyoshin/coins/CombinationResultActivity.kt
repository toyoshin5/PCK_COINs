package com.example.toyoshin.coins

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.TextView

class CombinationResultActivity : AppCompatActivity() {
    var total = 0
    var recommended = 0
    var change = 150
    var changeOffset:Point = Point(0,0)
    var isChangeView:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combination_result_view)
        setTitle("Your Payment")

        val totalTextView = findViewById<TextView>(R.id.total_text_view)
        val recommendedTextView = findViewById<TextView>(R.id.recommended_payment_text)
        val changeTextView = findViewById<TextView>(R.id.change_text)


        val intent = this.intent
        total = intent.getIntExtra("total", 0)

        //TODO "totalと、所持金情報から、recommendとchange を計算する"


        totalTextView.setText("¥ %d".format(total))
        changeTextView.setText("¥ %d".format(change))
        recommendedTextView.setText("¥ %d".format(recommended))


    }
    fun tapOkay(view: View){
        val totalTextView = findViewById<TextView>(R.id.total_text_view)
        val recommendedTextView = findViewById<TextView>(R.id.recommended_payment_text)
        val changeTextView = findViewById<TextView>(R.id.change_text)
        val textView1 = findViewById<TextView>(R.id.text1)
        val textView2 = findViewById<TextView>(R.id.text2)
        val textView4 = findViewById<TextView>(R.id.text4)
        val textView5 = findViewById<TextView>(R.id.text5)
        val cTextView = findViewById<TextView>(R.id.text3)
        val button = findViewById<Button>(R.id.button4)
        val yButton = findViewById<Button>(R.id.changeYbutton)
        val nButton = findViewById<Button>(R.id.changeNbutton)

                moveUp(changeTextView)
        fade(totalTextView,false)
        fade(recommendedTextView,false)
        fade(textView1,false)
        fade(textView2,false)
        fade(cTextView,false)
        fade(textView4,true)
        fade(textView5,true)
        fadeB(button,false)
        fadeB(yButton,true)
        fadeB(nButton,true)

        isChangeView = !isChangeView
    }

    fun tapN(view:View){
        val intent = Intent(this, ChangeSelectActivity::class.java)
        intent.putExtra("change",change)
        startActivity(intent)
    }
    fun tapY(view:View){
        val intent = Intent(this, resultActivity::class.java)
        intent.putExtra("change",change)
        startActivity(intent)
    }

    fun moveUp(view:TextView) {//お釣り確認に遷移するアニメーション
        if(isChangeView){

            val moveyoko = ObjectAnimator
                    .ofFloat(view, "translationX",
                            0f)
            val movetate = ObjectAnimator
                    .ofFloat(view, "translationY",
                            0f)

            moveyoko.duration = 400
            movetate.duration = 400

            moveyoko.interpolator = DecelerateInterpolator(2.0f)//減速するやつ?
            moveyoko.start()
            movetate.interpolator = DecelerateInterpolator(2.0f)
            movetate.start()

        }else{

            val moveyoko = ObjectAnimator
                    .ofFloat(view, "translationX",
                            -700f)
            val movetate = ObjectAnimator
                    .ofFloat(view, "translationY",
                            -850f)

            moveyoko.duration = 400
            movetate.duration = 400

            moveyoko.interpolator = DecelerateInterpolator(2.0f)//減速するやつ?
            moveyoko.start()
            movetate.interpolator = DecelerateInterpolator(2.0f)
            movetate.start()

        }

    }

    fun fade(view:TextView,reverse:Boolean) {//お釣り確認に遷移するアニメーション
        if(isChangeView xor reverse){

            val toggleAlpha = ObjectAnimator
                    .ofFloat(view, "alpha", 1f)
            toggleAlpha.duration = 400
            toggleAlpha.start()

        }else{

            val toggleAlpha = ObjectAnimator
                    .ofFloat(view, "alpha", 0f)
            toggleAlpha.duration = 400
            toggleAlpha.start()
        }


    }
    fun fadeB(view:Button,reverse:Boolean) {//お釣り確認に遷移するアニメーション
        if(isChangeView xor reverse){

            val toggleAlpha = ObjectAnimator
                    .ofFloat(view, "alpha", 1f)
            toggleAlpha.duration = 200
            toggleAlpha.start()
            view.isEnabled = true
        }else{

            val toggleAlpha = ObjectAnimator
                    .ofFloat(view, "alpha", 0f)
            toggleAlpha.duration = 200
            toggleAlpha.start()
            view.isEnabled = false
        }


    }

}
