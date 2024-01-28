package com.example.harrypoterrz

import ApiResponse
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class CharacterAdapter(private val mList: List<ApiResponse>,
                        private val context: Context)
    :RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.character_card,
                parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = mList[position]

        holder.name.text = character.name
        if (holder is ViewHolder){
            Glide
                .with(context)
                .load(character.image)
                .centerCrop()
                .placeholder(R.drawable.ic_board_place_holder)
                .into(holder.image)

            holder.itemView.setOnClickListener {
                if (onClickListener != null) {
                    onClickListener!!.onClick(position, character)
                }
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_name_board)
        val image: ImageView = itemView.findViewById(R.id.iv_card)
    }

    interface  OnClickListener {
        fun onClick(position: Int, model: ApiResponse)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}





