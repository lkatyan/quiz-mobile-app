package com.example.quizmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import androidx.appcompat.widget.AppCompatButton
import java.util.*
import java.util.TimerTask as TimerTask

class QuizActivity : AppCompatActivity() {

    lateinit var questionsCount: TextView
    lateinit var question: TextView

    lateinit var answerFirst: AppCompatButton
    lateinit var answerSecond: AppCompatButton
    lateinit var answerThird: AppCompatButton
    lateinit var answerFourth: AppCompatButton
    lateinit var nextQuestion: AppCompatButton

    lateinit var timerCount: TextView
    var quizTimer: CountDownTimer? = null

    lateinit var questionList: List<QuestionList>
    var currentQuestionPosition: Int = 0
    var selectedAnswerByUser: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionsCount = findViewById(R.id.textViewQuestionsCount)
        question = findViewById(R.id.textViewQuestion)

        answerFirst = findViewById(R.id.buttonAnswerFirst)
        answerSecond = findViewById(R.id.buttonAnswerSecond)
        answerThird = findViewById(R.id.buttonAnswerThird)
        answerFourth= findViewById(R.id.buttonAnswerFourth)
        nextQuestion = findViewById(R.id.buttonNextQuestion)

        val choose: String? = intent.getStringExtra("selectedChoose")
        var chooseForXml: String = ""
        when (choose) {
            "stars" -> chooseForXml = "Астрономия"
            "animals" -> chooseForXml = "Животные"
            "music" -> chooseForXml = "Музыка"
            "plants" -> chooseForXml = "Растения"
            else -> chooseForXml = "Астрономия"
        }
        val selectedQuizTitle: TextView = findViewById(R.id.textViewTitleQuiz)
        selectedQuizTitle.text = chooseForXml

        val qBank = QuestionsBank()
        questionList = QuestionsBank.getQuestions(qBank, choose)

        startTimer(10000)
        questionsCount.text = ""+(currentQuestionPosition+1)+"/"+questionList.size
        question.text = questionList.get(0).question
        answerFirst.text = questionList.get(0).answerFirst
        answerSecond.text = questionList.get(0).answerSecond
        answerThird.text = questionList.get(0).answerThird
        answerFourth.text = questionList.get(0).answerFourth

        answerFirst.setOnClickListener {
            if (selectedAnswerByUser.isEmpty()) {
                selectedAnswerByUser = answerFirst.text.toString()
                answerFirst.setBackgroundResource(R.drawable.round_background_incorrect)

                revealAnswer()
                questionList.get(currentQuestionPosition).userSelectedAnswer = selectedAnswerByUser
            }
        }
        answerSecond.setOnClickListener {
            if (selectedAnswerByUser.isEmpty()) {
                selectedAnswerByUser = answerSecond.text.toString()
                answerSecond.setBackgroundResource(R.drawable.round_background_incorrect)

                revealAnswer()
                questionList.get(currentQuestionPosition).userSelectedAnswer = selectedAnswerByUser
            }
        }
        answerThird.setOnClickListener {
            if (selectedAnswerByUser.isEmpty()) {
                selectedAnswerByUser = answerThird.text.toString()
                answerThird.setBackgroundResource(R.drawable.round_background_incorrect)

                revealAnswer()
                questionList.get(currentQuestionPosition).userSelectedAnswer = selectedAnswerByUser
            }
        }
        answerFourth.setOnClickListener {
            if (selectedAnswerByUser.isEmpty()) {
                selectedAnswerByUser = answerFourth.text.toString()
                answerFourth.setBackgroundResource(R.drawable.round_background_incorrect)

                revealAnswer()
                questionList.get(currentQuestionPosition).userSelectedAnswer = selectedAnswerByUser
            }
        }
        nextQuestion.setOnClickListener {
            if (selectedAnswerByUser.isEmpty()) {
                Toast.makeText(this, "Выберите вариант ответа", Toast.LENGTH_SHORT).show()
            } else {
                changeQuestion()
            }
        }
    }

    fun startTimer (timeMillis: Long) {
        quizTimer?.cancel()
        timerCount = findViewById(R.id.textViewTimerCount)
        quizTimer = object : CountDownTimer(timeMillis, 1) {
            override fun onTick(timeM: Long) {
                timerCount.text = (timeM/1000).toString()
            }
            override fun onFinish() {
                quizTimer?.cancel()
                timerCount.text = "finish"
                Toast.makeText(this@QuizActivity, " Время вышло", LENGTH_SHORT).show()
                finish()
                val intent = Intent(this@QuizActivity, ResultActivity::class.java)
                //intent.putExtra("correct", getCorrectAnswer())
                //intent.putExtra("incorrect", getInCorrectAnswer())
                startActivity(intent)
            }
        }.start()
    }

    private fun getCorrectAnswer(): Int {
        var correctAnswers = 0
        for (i in questionList.indices) {
            var getUserSelectedAnswer: String? = questionList[i].userSelectedAnswer
            var getAnswer: String? = questionList[i].answer
            if (getUserSelectedAnswer == getAnswer) {
                correctAnswers++
            }
        }
        return correctAnswers
    }

    fun getInCorrectAnswer(): Int {
        var correctAnswers: Int = 0
        for (num in 0..questionList.size) {
            var getUserSelectedAnswer: String? = questionList.get(num).userSelectedAnswer
            var getAnswer: String? = questionList.get(num).answer
            if (!getUserSelectedAnswer.equals(getAnswer)) {
                correctAnswers++
            }
        }
        return correctAnswers
    }

    fun onBack(view: View) {
        quizTimer?.cancel()
        finish()
    }

    override fun onBackPressed() {
        quizTimer?.cancel()
        finish()
    }

    fun revealAnswer() {
        var getAnswer: String? = questionList.get(currentQuestionPosition).answer

        if (answerFirst.text.toString() == getAnswer) {
            answerFirst.setBackgroundResource(R.drawable.round_background_correct)
        } else if (answerSecond.text.toString() == getAnswer) {
            answerSecond.setBackgroundResource(R.drawable.round_background_correct)
        } else if (answerThird.text.toString() == getAnswer) {
            answerThird.setBackgroundResource(R.drawable.round_background_correct)
        } else if (answerFourth.text.toString() == getAnswer) {
            answerFourth.setBackgroundResource(R.drawable.round_background_correct)
        }
    }

    private fun changeQuestion() {
        currentQuestionPosition+=1
        if ((currentQuestionPosition+1) == questionList.size) {
            nextQuestion.text = "Finish"
        }
        if (currentQuestionPosition < questionList.size) {
            selectedAnswerByUser = ""
            answerFirst.setBackgroundResource(R.drawable.round_background_button)
            answerSecond.setBackgroundResource(R.drawable.round_background_button)
            answerThird.setBackgroundResource(R.drawable.round_background_button)
            answerFourth.setBackgroundResource(R.drawable.round_background_button)

            questionsCount.text = ""+(currentQuestionPosition+1)+"/"+questionList.size
            question.text = questionList.get(currentQuestionPosition).question
            answerFirst.text = questionList.get(currentQuestionPosition).answerFirst
            answerSecond.text = questionList.get(currentQuestionPosition).answerSecond
            answerThird.text = questionList.get(currentQuestionPosition).answerThird
            answerFourth.text = questionList.get(currentQuestionPosition).answerFourth
            currentQuestionPosition-1
            startTimer(10000)
        } else {
            quizTimer?.cancel()
            finish()
            val intent = Intent(this@QuizActivity, ResultActivity::class.java)
            var correctAnswers: Int = getCorrectAnswer()
            intent.putExtra("correctAnswers", correctAnswers)
            //intent.putExtra("incorrect", getInCorrectAnswer())
            startActivity(intent)
        }
    }
}