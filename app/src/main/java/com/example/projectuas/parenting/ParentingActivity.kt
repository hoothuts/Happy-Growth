package com.example.projectuas.ui.parenting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectuas.adapter.ParentingAdapter
import com.example.projectuas.databinding.ActivityParentingBinding
import com.example.projectuas.model.ParentingItem
import com.google.firebase.firestore.FirebaseFirestore

class ParentingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParentingBinding
    private val list = mutableListOf<ParentingItem>()
    private lateinit var adapter: ParentingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParentingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ParentingAdapter(list)

        binding.recyclerParenting.layoutManager = LinearLayoutManager(this)
        binding.recyclerParenting.adapter = adapter

        loadData()
    }

    private fun loadData() {
        FirebaseFirestore.getInstance()
            .collection("parenting")
            .get()
            .addOnSuccessListener { result ->
                list.clear()
                for (doc in result) {
                    val item = doc.toObject(ParentingItem::class.java)
                    list.add(item)
                }
                adapter.notifyDataSetChanged()
            }
    }
}
