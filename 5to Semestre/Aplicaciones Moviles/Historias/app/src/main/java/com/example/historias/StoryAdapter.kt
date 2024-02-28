package com.example.historias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.modelos.Persona

class StoryAdapter(val personaList: ArrayList<Persona>, val storyListener : StoryListener) :
    RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.persona_icon, parent, false)
        return StoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val persona = personaList[position]
        holder.personIcon.setImageResource(persona.listaStories[0])
        holder.personIcon.setOnClickListener{
            storyListener.onStoryClick(persona)
        }
    }

    class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val personIcon : ImageView = itemView.findViewById(R.id.personIcon)
    }

    interface StoryListener{
        fun onStoryClick(persona:Persona)
    }
}