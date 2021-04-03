package com.irzstudio.quranjson.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irzstudio.quranjson.R
import com.irzstudio.quranjson.datasurah.SurahVerses
import kotlinx.android.synthetic.main.item_ayah.view.*

class AyahAdapter (private val list: ArrayList<SurahVerses>, private val noSurah: Int): RecyclerView.Adapter<AyahAdapter.AyahViewHolder>(){
    inner class AyahViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(surahVerses: SurahVerses){
            itemView.tv_idsurah.text = noSurah.toString()
            itemView.tv_id_ayah.text = surahVerses.number.toString()
            itemView.tv_arabic_ayah.text = surahVerses.text
            itemView.tv_translate_ayah.text = surahVerses.translation_id

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ayah,parent, false)
        return AyahViewHolder(view)
    }

    override fun onBindViewHolder(holder: AyahViewHolder, position: Int) {
       holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}