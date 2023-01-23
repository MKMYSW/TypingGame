package com.example.typinggame3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class ScoreActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        //===============ここからスコア表示=================================
        // 時間を表示するためのTextViewの定義
        val scoreTime :TextView = findViewById(R.id.scoreTime)

        // GameActivityから受け取った時間をScoreActivityの"TIME"に代入
        var TIME = intent.getStringExtra("TIME")
        // GameActivityから受け取った文字数カウントをScoreActivityの"Count"に代入
        var count = intent.getStringExtra("Count")
        // 時間を表示
        scoreTime.text = TIME

        // 時間の秒、コンマ秒を削る(分数のみ取得)
        var mTime = TIME?.dropLast(6)
        // 時間の分を削る
        var sTime1 = TIME?.substring(3)
        // 時間のコンマ秒を削る(秒数のみ取得)
        var sTime = sTime1?.dropLast(3)

        // 最終的なスコアを表示するための定義
        var score :TextView = findViewById(R.id.textView4)

        // 分数、秒数をString型からInt型に変換
        var numMTime :Int = Integer.parseInt(mTime)
        var numSTime :Int = Integer.parseInt(sTime)

        // 文字数カウントをString型からInt型に変換
        var incount = count?.toInt()

        // incountをDouble型に変換(多分必要ない)
        var numcount = incount?.toDouble()


        // 時間を秒数として計算
        var timesum :Int = numMTime * 60 + numSTime

        // スコアを計算
        var scoresum = numcount!! * 100 / timesum
        scoresum = Math.floor(scoresum)
        var ScoreView = ("スコア : " + scoresum)


        // 時間とスコアを表示
        score.text = ScoreView.toString()

        //========================================================


        //=================ここから画面遷移========================

        //replayBtnとbackBtnのid取得
        val replayBtn: Button = findViewById(R.id.replayBtn)
        val backBtn: Button = findViewById(R.id.backBtn)

        //testBtnが押されたときゲーム画面に戻る
        replayBtn.setOnClickListener{
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
        //testBtnが押されたときにスタート画面に戻る
        backBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //========================================================
    }
}