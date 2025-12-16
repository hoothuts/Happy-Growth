package com.example.projectuas.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuas.R

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctIndex: Int
)

class QuizAdapter(private val questions: List<Question>) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuestionNumber: TextView = itemView.findViewById(R.id.tvQuestionNumber)
        val tvQuestion: TextView = itemView.findViewById(R.id.tvQuestion)
        val rbOptionA: RadioButton = itemView.findViewById(R.id.rbOptionA)
        val rbOptionB: RadioButton = itemView.findViewById(R.id.rbOptionB)
        val rbOptionC: RadioButton = itemView.findViewById(R.id.rbOptionC)
        val rbOptionD: RadioButton = itemView.findViewById(R.id.rbOptionD)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz_question, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val question = questions[position]
        holder.tvQuestionNumber.text = "Pertanyaan ${position + 1} / ${questions.size}"
        holder.tvQuestion.text = question.text
        
        holder.rbOptionA.text = question.options[0]
        holder.rbOptionB.text = question.options[1]
        holder.rbOptionC.text = question.options[2]
        holder.rbOptionD.text = question.options[3]

        // Reset checked state (simple implementation, improved would track state)
        holder.rbOptionA.isChecked = false
        holder.rbOptionB.isChecked = false
        holder.rbOptionC.isChecked = false
        holder.rbOptionD.isChecked = false
    }

    override fun getItemCount() = questions.size
}
