package com.example.projectuas.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuas.databinding.ItemParentingBinding
import com.example.projectuas.model.ParentingArticle
import com.example.projectuas.parenting.ArticleDetailActivity

class ParentingAdapter(
    private val list: List<ParentingArticle>
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
        val item = list[position]

        holder.binding.tvTitle.text = item.title
        holder.binding.tvDescription.text = item.description

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ArticleDetailActivity::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("url", item.articleUrl)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size
}
