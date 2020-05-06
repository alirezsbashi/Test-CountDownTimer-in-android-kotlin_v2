package arb.test.count.down.timer.v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val start = 10_000L
    var timer = start
    lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        not clickable
        btn_main_pause.isEnabled = false
        btn_main_restart.isEnabled = false
//        test time
        setText()
//        line of text
        setLineNoText()
    }

    fun on(view: View){
        when(view.id){
            R.id.btn_main_start -> fun_start()
            R.id.btn_main_pause -> fun_paus()
            R.id.btn_main_restart -> fun_rest()
        }
    }

    private fun fun_start() {
        countDownTimer = object : CountDownTimer(timer ,1000){
            override fun onFinish() {
//                to hide
                tv_main_timer.visibility = View.INVISIBLE
                tv_main_discount.visibility = View.INVISIBLE
//                set the text
                tv_main_money.text = "$10"
                tv_main_money.setTextSize(30f)
//                clickable
                btn_main_restart.isEnabled = true
//              not  clickable
                btn_main_start.isEnabled = false
                btn_main_pause.isEnabled = false

            }

            override fun onTick(millisUntilFinished: Long) {
                timer = millisUntilFinished
                setText()
            }

        }.start()
//        clickable
        btn_main_pause.isEnabled = true
//      not  clickable
        btn_main_start.isEnabled = false
        btn_main_restart.isEnabled = false

    }

    private fun fun_paus() {
        countDownTimer.cancel()
//        clickable
        btn_main_start.isEnabled = true
        btn_main_restart.isEnabled = true
//        not clickable
        btn_main_pause.isEnabled = false

    }

    private fun fun_rest() {
        countDownTimer.cancel()
        timer = start
        setText()
        //        not clickable
        btn_main_pause.isEnabled = false
        btn_main_restart.isEnabled = false
//        clickable
        btn_main_start.isEnabled = true
        //                to hide
        tv_main_timer.visibility = View.VISIBLE
        tv_main_discount.visibility = View.VISIBLE
//                set the text
        setLineNoText()
        tv_main_money.setTextSize(20f)
    }

    fun setText(){
        var format = String.format("%02d:%02d" , (timer / 1000) / 60 , (timer / 1000) % 60)
        tv_main_timer.setText(format)
    }

    fun setLineNoText(){
        val money = "$10"
        val spannable = SpannableString(money)
        spannable.setSpan(StrikethroughSpan(),0,money.length,Spanned.SPAN_MARK_MARK)
        tv_main_money.text = spannable
    }
}
