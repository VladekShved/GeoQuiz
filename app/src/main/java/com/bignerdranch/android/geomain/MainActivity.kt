package com.bignerdranch.android.geomain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var questionTextView: TextView
    data class Question(@StringRes val textResId: Int,
                        val answer: Boolean,
                        var answered: Boolean = false)
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton =
            findViewById(R.id.next_button)
        backButton =
            findViewById(R.id.back_button)
        questionTextView =
            findViewById(R.id.question_text_view)
        trueButton.setOnClickListener {
            trueButton.isEnabled=false
            falseButton.isEnabled=false
            questionBank[currentIndex].answered=true
            checkAnswer(true)
        }
        falseButton.setOnClickListener {
            trueButton.isEnabled=false
            falseButton.isEnabled=false
            questionBank[currentIndex].answered=true
            checkAnswer(false)

        }
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            isAnswered(currentIndex)
            updateQuestion()

        }
//        updateQuestion()
        backButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            if (currentIndex == -1) currentIndex = questionBank.lastIndex
            isAnswered(currentIndex)
            updateQuestion()

        }
        updateQuestion()
    }
    private fun isAnswered(index: Int) {
        val isQuestionAnswered = questionBank[index].answered
        trueButton.isEnabled = !isQuestionAnswered
        falseButton.isEnabled = !isQuestionAnswered
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG,
            "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG,
            "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG,
            "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG,
            "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,
            "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId =
            questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(
        userAnswer:
        Boolean
    ) {
        val correctAnswer =
            questionBank[currentIndex].answer
        val messageResId = if (userAnswer ==
            correctAnswer
        ) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(
            this, messageResId,
            Toast.LENGTH_SHORT
        )
            .show()
    }

}



