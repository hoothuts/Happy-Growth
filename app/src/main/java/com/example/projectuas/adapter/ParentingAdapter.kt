package com.example.projectuas.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuas.databinding.ItemParentingBinding
import com.example.projectuas.model.ParentingItem
import com.example.projectuas.parenting.ArticleDetailActivity
import com.example.projectuas.parenting.VideoPlayerActivity

class ParentingAdapter(
    private val items: List<ParentingItem>
) : RecyclerView.Adapter<ParentingAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemParentingBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemParentingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.tvTitle.text = item.title
        holder.binding.tvDesc.text = item.description

        holder.itemView.setOnClickListener {
            if (item.type == "article") {
                val intent = Intent(holder.itemView.context, ArticleDetailActivity::class.java)
                intent.putExtra("title", item.title)
                intent.putExtra("url", item.articleUrl)
                holder.itemView.context.startActivity(intent)
            } else if (item.type == "video") {
                val intent = Intent(holder.itemView.context, VideoPlayerActivity::class.java)
                intent.putExtra("youtubeUrl", item.youtubeUrl)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}