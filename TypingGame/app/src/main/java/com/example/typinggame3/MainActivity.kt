package com.example.typinggame3

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1)idを取得
        val startBtn : Button = findViewById(R.id.startBtn)

        //2)「スタート」ボタンを押したらゲーム画面へ
        startBtn.setOnClickListener{
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

    }

}
