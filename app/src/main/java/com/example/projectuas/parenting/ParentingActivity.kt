package com.example.projectuas.parenting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectuas.adapter.ParentingAdapter
import com.example.projectuas.databinding.ActivityParentingBinding
import com.example.projectuas.model.ParentingArticle
import com.google.firebase.firestore.FirebaseFirestore

class ParentingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParentingBinding
    private val list = mutableListOf<ParentingArticle>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvParenting.layoutManager = LinearLayoutManager(this)

        FirebaseFirestore.getInstance()
            .collection("parenting_articles")
            .whereEqualTo("targetClass", "TK-B")
            .get()
            .addOnSuccessListener { result ->
                list.clear()
                for (doc in result) {
                    list.add(doc.toObject(ParentingArticle::class.java))
                }
                binding.rvParenting.adapter = ParentingAdapter(list)
            }
    }
}
