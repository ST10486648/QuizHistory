package za.ac.iie.quizhistory

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var textViewQuestion: TextView
    private lateinit var buttonTrue: MaterialButton
    private lateinit var buttonFalse: MaterialButton
    private lateinit var buttonNext: Button
    private lateinit var buttonBack: Button
    private lateinit var textViewFeedback: TextView
    private lateinit var textViewScore: TextView
    private lateinit var buttonStart: Button
    private lateinit var buttonReview: Button
    private lateinit var buttonClose: Button

    private val questions = arrayOf(
        "The Great Wall of China is visible from space.",
        "World War I ended in 1918.",
        "Julius Caesar was the first Emperor of Rome.",
        "The pyramids of Egypt are over 4000 years old.",
        "The Berlin Wall fell in 1989."
    )

    private val answers = arrayOf(false, true, false, true, true)

    private var currentIndex = 0
    private var score = 0
    private var inReviewMode = false
    private var userAnswered: Boolean? = null
    private val userAnswers = arrayOfNulls<Boolean>(questions.size)
    private var quizStarted = false

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewQuestion = findViewById(R.id.textViewQuestion)
        buttonTrue = findViewById(R.id.buttonTrue)
        buttonFalse = findViewById(R.id.buttonFalse)
        buttonNext = findViewById(R.id.buttonNext)
        buttonBack = findViewById(R.id.buttonBack)
        textViewFeedback = findViewById(R.id.textViewFeedback)
        textViewScore = findViewById(R.id.textViewScore)
        buttonStart = findViewById(R.id.buttonStart)
        buttonReview = findViewById(R.id.buttonReview)
        buttonClose = findViewById(R.id.buttonClose)

        buttonTrue.setTextColor(Color.GREEN)
        buttonFalse.setTextColor(Color.RED)

        buttonBack.visibility = Button.GONE // hide back initially
        setAnswerButtonsEnabled(false)

        showWelcomePopup()

        buttonStart.setOnClickListener { startQuiz() }

        buttonTrue.setOnClickListener {
            if (!quizStarted) {
                showPleaseStartQuiz()
                return@setOnClickListener
            }
            userAnswered = true
            highlightSelectedButton(buttonTrue, buttonFalse, Color.GREEN)
        }

        buttonFalse.setOnClickListener {
            if (!quizStarted) {
                showPleaseStartQuiz()
                return@setOnClickListener
            }
            userAnswered = false
            highlightSelectedButton(buttonFalse, buttonTrue, Color.RED)
        }

        buttonNext.setOnClickListener {
            if (!inReviewMode) {
                if (userAnswered == null) {
                    textViewFeedback.text = "Please select True or False first."
                } else {
                    checkAnswer()
                    handler.postDelayed({
                        if (currentIndex < questions.size - 1) {
                            currentIndex++
                            loadQuestion()
                        } else {
                            showResults()
                        }
                    }, 1000) // delay for 1 second to show feedback
                }
            } else {
                if (currentIndex < questions.size - 1) {
                    currentIndex++
                    loadQuestion()
                }
            }
        }

        buttonBack.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                loadQuestion()
            }
        }

        buttonReview.setOnClickListener {
            inReviewMode = true
            currentIndex = 0
            loadQuestion()
            buttonBack.visibility = Button.VISIBLE
        }

        buttonClose.setOnClickListener {
            finish()
        }
    }

    private fun showWelcomePopup() {
        AlertDialog.Builder(this)
            .setTitle("Welcome")
            .setMessage("Welcome to the History Quiz! Test your knowledge and have fun.")
            .setPositiveButton("Close") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun startQuiz() {
        quizStarted = true
        buttonStart.isEnabled = false
        buttonStart.visibility = Button.GONE
        inReviewMode = false
        buttonBack.visibility = Button.GONE
        score = 0
        currentIndex = 0
        userAnswered = null
        for (i in userAnswers.indices) {
            userAnswers[i] = null
        }
        setAnswerButtonsEnabled(true)
        loadQuestion()
    }

    private fun loadQuestion() {
        textViewQuestion.text = questions[currentIndex]
        textViewFeedback.text = ""
        textViewScore.text = "Score: $score / ${questions.size}"
        userAnswered = null

        clearButtonOutline(buttonTrue)
        clearButtonOutline(buttonFalse)

        if (inReviewMode) {
            setAnswerButtonsEnabled(false)
            val correctAnswer = answers[currentIndex]
            val userAnswer = userAnswers[currentIndex]
            if (correctAnswer) {
                highlightSelectedButton(buttonTrue, buttonFalse, Color.GREEN)
            } else {
                highlightSelectedButton(buttonFalse, buttonTrue, Color.RED)
            }
            textViewFeedback.text = "Your Answer: ${if (userAnswer == true) "True" else if (userAnswer == false) "False" else "No Answer"}, Correct Answer: ${if (correctAnswer) "True" else "False"}"
        } else {
            setAnswerButtonsEnabled(true)
        }
    }

    private fun checkAnswer() {
        val correctAnswer = answers[currentIndex]
        userAnswers[currentIndex] = userAnswered
        if (userAnswered == correctAnswer) {
            textViewFeedback.text = "Correct!"
            score++
        } else {
            textViewFeedback.text = "Incorrect!"
        }
        textViewScore.text = "Score: $score / ${questions.size}"
        setAnswerButtonsEnabled(false)
    }

    private fun showResults() {
        val message = if (score >= 3) {
            "Good job! You scored $score out of ${questions.size}."
        } else {
            "Keep practicing! You scored $score out of ${questions.size}."
        }

        AlertDialog.Builder(this)
            .setTitle("Quiz Finished")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()

        buttonReview.isEnabled = true
        buttonReview.visibility = Button.VISIBLE
    }

    private fun highlightSelectedButton(selected: MaterialButton, other: MaterialButton, color: Int) {
        selected.strokeWidth = 8
        selected.strokeColor = ColorStateList.valueOf(color)
        other.strokeWidth = 0
    }

    private fun clearButtonOutline(button: MaterialButton) {
        button.strokeWidth = 0
    }

    private fun setAnswerButtonsEnabled(enabled: Boolean) {
        buttonTrue.isEnabled = enabled
        buttonFalse.isEnabled = enabled
    }

    private fun showPleaseStartQuiz() {
        Toast.makeText(this, "Please click Start Quiz", Toast.LENGTH_SHORT).show()
    }
}
