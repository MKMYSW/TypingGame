package com.example.typinggame3

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class GameActivity : AppCompatActivity() {
    val handler = Handler()
    var timeValue = 0
    val interval:Long = 50
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        if (currentFocus != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(
                currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }

        //===============ここから表示する文字などの定義======================================

        // 表示させる文字を配列として代入
        var word = arrayOf("はかたのしお","えちごせいか","みるくここあ","あるまげどん","ほっかいどう","てっぽうだま","ぱいなっぷる","こんくりーと","まくどなるど","ひるなんです","みにとまと","おおみそか","みんてぃあ","かめはめは","にんきょう","さいぜりあ","ぴかちゅう","すにーかー","えびふらい","みししっぴ","だいこん","えんぴつ","なっとう","わびさび","しゃてい","かまとと","きんぞく","かちこみ","ぐらさん","けいさつ","びんた","わさび","えのき","しめじ","うさぎ","すーつ","やくざ","おやじ","けじめ","さんま")
        // 出題される問題の文字をString型で定義
        var QuesNum :String = ""
        // 正解数をカウント
        var corCount = 0
        // 乱数を発生させて変数に代入
        var number = (0..39).random()
        // 文字の表示を定義
        var result: TextView = findViewById(R.id.textView3)
        // 乱数で出た数字と一致する文字を代入
        QuesNum = word.get(number)
        // 乱数で出た数字と一致する文字を表示させる
        result.text = word.get(number)
        // 乱数で出た文字の文字数を代入するための変数
        var wordCount :Int = 0
        // 乱数で出た文字の文字数を代入
        wordCount += QuesNum.length

        // ==============================================================


        // ===============ここからストップウォッチの定義など===============================

        //ストップウォッチのボタン定義
        val timeText = findViewById(R.id.timeText) as TextView

        // ストップウォッチの動き
        val runnable = object : Runnable {
            override fun run() {
                timeValue++
                timeToText(timeValue*interval)?.let {
                    timeText.text = it
                }
                handler.postDelayed(this, interval)
            }
        }

        // ゲーム画面に遷移したのと同時にタイマースタート
        handler.post(runnable)


        // ==============================================================


        // =================ここからボタンを押したときの動作==================================

        //「editTextTextPersonName」テキストボックスに入力された文字を「Text」と定義する
        val Text = findViewById<EditText>(R.id.WordEnter)
        //「button」ボタンを押したときの動作を「editText」と定義する
        val editText: Button = findViewById(R.id.button)

        //「editText」の動作内容
        editText.setOnClickListener {
            //「textView2」に表示する内容を「resultTextView」と定義する
            val resultTextView: TextView = findViewById(R.id.textView2)
            //テキストボックスに入力された文字を文字列として「resultTextView」を実行
            resultTextView.text = Text.text.toString()
            // textを文字列としてAnsWordに代入
            var AnsWord = Text.text.toString()

            //正誤をダイアログで表示
            //val alertTitle: String
            // 問題と解答が一緒の時(正解の時)
            if (QuesNum == AnsWord) {
                // 正解カウントに+1する
                corCount += 1
                // 正解を表示
                //alertTitle = "正解"

                // 乱数を発生させて変数に代入
                var number = (0..39).random()
                // 文字の表示を定義
                var result: TextView = findViewById(R.id.textView3)
                // 乱数で出た数字と一致する文字を代入
                QuesNum = word.get(number)
                // 乱数で出た数字と一致する文字を表示させる
                result.text = word.get(number)
                // 正解すると入力した文字が消える
                var WordDel: TextView = findViewById(R.id.WordEnter)
                WordDel.text = ("")

                Toast.makeText(this, "正解", Toast.LENGTH_LONG).show();


                // 3回正解したとき(仮)(ゲームクリアしたあとの動作)
                if (corCount == 3) {
                    // ストップウォッチの停止
                    handler.removeCallbacks(runnable)
                    // ScoreActivityへの画面遷移の定義
                    val intent = Intent(this, ScoreActivity::class.java)

                    // 時間をScoreActivityに渡す
                    intent.putExtra("TIME",timeText.text.toString())
                    // 出題された問題の文字数をScoreActivityに渡す
                    intent.putExtra("Count", wordCount.toString())
                    // ScoreActivityに画面遷移
                    startActivity(intent)
                    //}
                }

                // 出題された文字の長さを代入
                wordCount += QuesNum.length


            } else {
                // 不正解を表示
                //alertTitle = "不正解"
                Toast.makeText(this, "不正解", Toast.LENGTH_LONG).show();
            }

            // ポップアップ表示の定義
            //AlertDialog.Builder(this)
                //.setTitle(alertTitle)
                //.setPositiveButton("OK") { dialogInterface, i ->
                //}
                //.setCancelable(false)
                //.show()
        }
}

    //=================================================================

    //================ここからストップウォッチの定義===============================

    // ストップウォッチの表示
    public fun timeToText(time: Long = 0): String? {
    return if (time < 0) {
        null
    } else if (time.toInt() == 0) {
        "00:00:00"
    } else {
        val m = (time % 3600000) / 60000
        val s = ((time % 3600000) % 60000) / 1000
        val ms = (time  % 1000) / 10
        "%1$02d:%2$02d:%3$02d".format(m, s, ms)
    }
    }
    //===========================================================================
}

