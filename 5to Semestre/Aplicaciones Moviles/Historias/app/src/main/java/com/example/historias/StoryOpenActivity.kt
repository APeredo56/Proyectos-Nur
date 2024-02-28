package com.example.historias

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.currentCoroutineContext
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.timerTask

class StoryOpenActivity () : AppCompatActivity() {
    lateinit var storyImg : ImageView
    lateinit var btnReturn : ImageButton
    lateinit var listaImagenes: ArrayList<Int>
    lateinit var btnNext : TextView
    lateinit var btnPrevius : TextView
    lateinit var txtPosicion: TextView
    var posicion : Int = 0

    private var timer = Timer()
    private lateinit var timerTask : TimerTask

    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.story_activity)
        storyImg = findViewById(R.id.storyImg)
        btnReturn = findViewById(R.id.btnReturn)
        btnNext = findViewById(R.id.nextStory)
        btnPrevius = findViewById(R.id.previousStory)
        radioGroup = findViewById(R.id.radioGroup)
        txtPosicion = findViewById(R.id.txtPosicion)
        listaImagenes = intent.getIntegerArrayListExtra("lista") as ArrayList<Int>

        storyImg.setImageResource(listaImagenes[0])

        for (i in 0 until listaImagenes.size){
            val radioButton = RadioButton(applicationContext)
            radioGroup.addView(radioButton)
        }

        txtPosicion.setText("1/${listaImagenes.size}")

        setupTimer()
        setupEventListeners()
        radioGroup.check(posicion+1)
    }

    private fun setupEventListeners(){
        btnReturn.setOnClickListener {
            timer.cancel()
            //radioGroup.clearCheck()
            finish()
        }
        btnNext.setOnClickListener {
            step()
            timerTask.cancel()
            setupTimer()
        }
        btnPrevius.setOnClickListener {
            timerTask.cancel()
            stepBack()
            setupTimer()
        }
    }

    private fun step(){
        if (posicion >= listaImagenes.size - 1){
            radioGroup.clearCheck()
            radioGroup.removeAllViews()
            finish()
            return
        }
        posicion += 1
        txtPosicion.setText("${posicion+1}/${listaImagenes.size}")
        storyImg.setImageResource(listaImagenes[posicion])
        //radioGroup.clearCheck()
        radioGroup.check(posicion+1)
    }

    private fun stepBack(){
        if (posicion == 0){
            return
        }
        posicion -= 1
        txtPosicion.setText("${posicion+1}/${listaImagenes.size}")
        storyImg.setImageResource(listaImagenes[posicion])
        //radioGroup.clearCheck()
        radioGroup.check(posicion+1)
    }

    private fun setupTimer(){
        timerTask = object : TimerTask(){
            override fun run() {
                this@StoryOpenActivity.runOnUiThread {
                    step()
                }
            }
        }
        timer.scheduleAtFixedRate(timerTask, 5000, 5000)
    }

}