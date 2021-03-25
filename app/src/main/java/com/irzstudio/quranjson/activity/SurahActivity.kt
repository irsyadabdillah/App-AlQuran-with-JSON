package com.irzstudio.quranjson.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.irzstudio.quranjson.R
import com.irzstudio.quranjson.RetrofitClient
import com.irzstudio.quranjson.adapter.AyahAdapter
import com.irzstudio.quranjson.adapter.SurahAdapter
import com.irzstudio.quranjson.dataquran.QuranData
import com.irzstudio.quranjson.datasurah.SurahData
import com.irzstudio.quranjson.datasurah.SurahVerses
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.tv_error
import kotlinx.android.synthetic.main.activity_surah.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SurahActivity : AppCompatActivity() {

    private val list = ArrayList<SurahVerses>()
    private  var extraSurah : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surah)

        extraSurah = intent.getIntExtra("surahnumber", 1)

        progressBar.visibility = View.VISIBLE
        rv_ayah.visibility = View.GONE
        tv_error.visibility = View.GONE
        RetrofitClient.instance.getSurah(idSurah = extraSurah).enqueue(object: Callback<SurahData> {
            override fun onResponse(call: Call<SurahData>, response: Response<SurahData>) {
                response.body()?.let { surahData -> list.addAll(surahData.verses) }

                rv_ayah.setHasFixedSize(true)
                rv_ayah.adapter = AyahAdapter(list, noSurah = response.body()?.number_of_surah ?: 0) //memeriksa null label ?:0
                rv_ayah.layoutManager = LinearLayoutManager(this@SurahActivity)
                rv_ayah.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<SurahData>, t: Throwable) {
                t.printStackTrace()
                tv_error.text = "Error"
                tv_error.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        })
    }
}