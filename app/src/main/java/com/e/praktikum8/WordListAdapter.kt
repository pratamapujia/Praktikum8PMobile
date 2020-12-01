package com.e.praktikum8

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater= LayoutInflater.from(context)
    private var words = emptyList<Word>()
    var clickListener : OnClickListener? = null
    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item,parent, false)
        return WordViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: WordViewHolder, position: Int){
        val current = words[position]
        holder.wordItemView.text = current.word
        holder.wordItemView.setOnClickListener({itemView -> clickListener?.onClick(position)})
    }
    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }
    override fun getItemCount() = words.size

    fun setOnClickListener(listener: (Int) -> Unit){
        this.clickListener = object: OnClickListener {
            override fun onClick(position: Int) {
                listener(position)
            }
        }
    }


    interface OnClickListener{
        fun onClick(position: Int)
    }
}