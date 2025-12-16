package com.example.projectuas.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuas.R

data class ScheduleItem(
    val subject: String,
    val time: String,
    val teacher: String,
    val period: String,
    val iconEmoji: String? = null // Emoji for icon if any (e.g. Sandwich)
)

class ScheduleAdapter(private var items: List<ScheduleItem>) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSubject: TextView = itemView.findViewById(R.id.tvSubject)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val tvTeacher: TextView = itemView.findViewById(R.id.tvTeacher)
        val tvPeriod: TextView = itemView.findViewById(R.id.tvPeriod)
        val ivIcon: TextView = itemView.findViewById(R.id.ivIcon)
        val divider: View = itemView.findViewById(R.id.divider)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule_lesson, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val item = items[position]
        holder.tvSubject.text = item.subject
        holder.tvTime.text = item.time
        
        if (item.teacher.isEmpty()) {
             // Likely a break
             holder.tvTeacher.visibility = View.GONE
             holder.tvPeriod.visibility = View.GONE
             holder.divider.visibility = View.GONE
        } else {
             holder.tvTeacher.text = item.teacher
             holder.tvPeriod.text = item.period
             holder.tvTeacher.visibility = View.VISIBLE
             holder.tvPeriod.visibility = View.VISIBLE
             holder.divider.visibility = View.VISIBLE
        }

        if (item.iconEmoji != null) {
            holder.ivIcon.text = item.iconEmoji
            holder.ivIcon.visibility = View.VISIBLE
        } else {
            holder.ivIcon.visibility = View.GONE
        }
    }

    override fun getItemCount() = items.size
    
    fun updateData(newItems: List<ScheduleItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
