package com.example.historias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modelos.Persona

class MainActivity : AppCompatActivity(), StoryAdapter.StoryListener {
    private lateinit var lstStories : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lstStories = findViewById(R.id.lstStories)

        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        val listaImagenes : ArrayList<Int> = arrayListOf(R.drawable.img0, R.drawable.img1,
            R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6,
            R.drawable.img7)
        val listaPersonas : ArrayList<Persona> = arrayListOf()
        for (i in 1..5) {
            listaImagenes.shuffle()
            val listaAuxiliar = ArrayList(listaImagenes)
            listaPersonas.add(Persona(listaAuxiliar))

        }
        listaPersonas.get(4).listaStories.remove(R.drawable.img5)
        listaPersonas.get(4).listaStories.remove(R.drawable.img6)
        listaPersonas.get(4).listaStories.remove(R.drawable.img7)

        val lstAdapter = StoryAdapter(listaPersonas, this)
        lstStories.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,
                LinearLayoutManager.HORIZONTAL, false)
            adapter =lstAdapter
        }

    }

    override fun onStoryClick(persona: Persona){
        val intent = Intent(this, StoryOpenActivity::class.java)
        //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("lista", persona.listaStories)
        startActivity(intent)
    }


}