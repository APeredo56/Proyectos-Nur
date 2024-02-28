package com.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.elements.Board
import com.elements.Game
import com.example.a2048.R
import com.utilities.Direction
import com.utilities.OnSwipeTouchListener

class MainActivity : AppCompatActivity(), Game.GameListener {
    private lateinit var board: Board
    private var game: Game? = null
    private lateinit var btnNewGame : Button
    private lateinit var lblScore : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board = findViewById(R.id.board)
        btnNewGame = findViewById(R.id.btnNewGame)
        lblScore = findViewById(R.id.lblScore)

        startGame()
        setupListeners()
    }

    private fun setupListeners() {
        btnNewGame.setOnClickListener {
            game?.makeNewGame()
            board.invalidate()
        }

        board.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeLeft() {
                game?.move(Direction.LEFT)
                lblScore.text = game?.score.toString()
                board.invalidate()
            }

            override fun onSwipeRight() {
                game?.move(Direction.RIGHT)
                lblScore.text = game?.score.toString()
                board.invalidate()
            }

            override fun onSwipeUp() {
                game?.move(Direction.UP)
                lblScore.text = game?.score.toString()
                board.invalidate()
            }

            override fun onSwipeDown() {
                game?.move(Direction.DOWN)
                lblScore.text = game?.score.toString()
                board.invalidate()
            }

        })
    }

    private fun startGame() {
        game = Game(this, this)
        board.game = game
    }

    override fun onFinish() {
        board.invalidate()
        board.game?.score = 0
    }

}