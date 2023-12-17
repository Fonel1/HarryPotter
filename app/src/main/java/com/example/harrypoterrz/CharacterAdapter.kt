package com.example.harrypoterrz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CharacterAdapter(private val mList: List<ApiResponse>) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = mList[position]

        holder.name.text = character.name
        //holder.house.text = character.house
        //holder.date.text = character.dateOfBirth
        //holder.student.text = character.hogwartsStudent.toString()
        //holder.ancestry.text = character.ancestry
        //holder.actor.text = character.actor

        // Sprawdź, czy image nie jest puste przed użyciem
        if (!character.image.isNullOrBlank()) {
            Picasso.get().load(character.image).into(holder.imageView)
        } else {
            holder.imageView.setImageResource(R.drawable.default_image)
        }

        if(!character.dateOfBirth.isNullOrBlank()) {
            holder.date.text = character.dateOfBirth
        } else {
            holder.date.text = "No information"
        }

        if(!character.actor.isNullOrBlank()) {
            holder.actor.text = character.actor
        } else {
            holder.actor.text = "No information"
        }

        if(!character.ancestry.isNullOrBlank()) {
            holder.ancestry.text = character.ancestry
        } else {
            holder.ancestry.text = "No information"
        }

        if(!character.hogwartsStudent.toString().isNullOrBlank() ) {
            holder.student.text = character.hogwartsStudent.toString()
        } else {
            holder.student.text = "No information"
        }

        if(!character.house.isNullOrBlank()) {
            holder.house.text = character.house
        } else {
            holder.house.text = "No information"
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val name: TextView = itemView.findViewById(R.id.textViewName)
        val house: TextView = itemView.findViewById(R.id.textViewHouse)
        val date: TextView = itemView.findViewById(R.id.textViewBirth)
        val student: TextView = itemView.findViewById(R.id.textViewStudent)
        val ancestry: TextView = itemView.findViewById(R.id.textViewAncestry)
        val actor: TextView = itemView.findViewById(R.id.textViewActor)
    }
}





