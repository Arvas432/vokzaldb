package com.example.vokzaldb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity: AppCompatActivity() {

    private val trainsViewModel: TrainsViewModel by viewModels {
        TrainsViewModelFactory((application as TrainsApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = TrainsListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_tablo)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        trainsViewModel.allTrains.observe(this, Observer { trains ->
            trains?.let { adapter.submitList(it) }
        })
    }
}