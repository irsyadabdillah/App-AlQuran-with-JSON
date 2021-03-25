package com.irzstudio.quranjson.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.irzstudio.quranjson.R
import com.irzstudio.quranjson.RetrofitClient
import com.irzstudio.quranjson.`interface`.OnSurahListener
import com.irzstudio.quranjson.adapter.SurahAdapter
import com.irzstudio.quranjson.dataquran.QuranData
import com.irzstudio.quranjson.datasurah.SurahData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SurahAdapter
    private val list = ArrayList<QuranData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.VISIBLE
        rv_surah.visibility = View.GONE
        tv_error.visibility = View.GONE
        RetrofitClient.instance.getQuran().enqueue(object : Callback<ArrayList<QuranData>> {


            override fun onResponse(
                call: Call<ArrayList<QuranData>>,
                response: Response<ArrayList<QuranData>>) {
                response.body()?.let { list.addAll(it) }


                adapter = SurahAdapter(list)
                rv_surah.setHasFixedSize(true)
                rv_surah.adapter = adapter
                rv_surah.layoutManager = LinearLayoutManager(this@MainActivity)
                rv_surah.visibility = View.VISIBLE
                progressBar.visibility = View.GONE

                adapter.onClickListener = object : OnSurahListener {
                    override fun onClick(quranData: QuranData) {
                        val intent = Intent(applicationContext, SurahActivity::class.java)
                        intent.putExtra("surahnumber", quranData.number_of_surah )
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<QuranData>>, t: Throwable) {
                t.printStackTrace()
                tv_error.text = "Error"
                tv_error.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        })


    }
}

