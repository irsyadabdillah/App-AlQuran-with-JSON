package com.irzstudio.quranjson.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irzstudio.quranjson.R
import com.irzstudio.quranjson.`interface`.OnSurahListener
import com.irzstudio.quranjson.dataquran.QuranData
import kotlinx.android.synthetic.main.item_surah.view.*

class SurahAdapter(private val list: ArrayList<QuranData>):RecyclerView.Adapter<SurahAdapter.SurahViewHolder>(){

    var onClickListener: OnSurahListener? = null

    inner class SurahViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(quranData: QuranData){
            itemView.tv_id.text = quranData.number_of_surah.toString()
            itemView.tv_namesurah.text = quranData.name
            itemView.tv_translate.text = quranData.name_translations.id
            itemView.tv_arabic.text = quranData.name_translations.ar

            itemView.setOnClickListener{
                onClickListener?.onClick(quranData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_surah, parent, false)
        return SurahViewHolder(view)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}