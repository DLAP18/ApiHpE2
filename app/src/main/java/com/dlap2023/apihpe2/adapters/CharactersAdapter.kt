package com.dlap2023.apihpe2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dlap2023.apihpe2.databinding.CharacterElementBinding
import com.dlap2023.apihpe2.model.Character

class CharactersAdapter(private var context: Context, private var characters: ArrayList<Character>, private val clickListener: (Character) -> Unit): RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    class ViewHolder(view: CharacterElementBinding): RecyclerView.ViewHolder(view.root){
        val ivThumbnail = view.ivThumbnail
        val tvTitle = view.tvTitle
        val tvActor = view.tvActor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = characters[position].name

        Glide.with(context)
            .load(characters[position].image)
            .into(holder.ivThumbnail)

        holder.tvActor.text = characters[position].actor

        holder.itemView.setOnClickListener{
            //Para programar los eventos click al elemento completo del ViewHolder
            clickListener(characters[position])
        }
    }
}