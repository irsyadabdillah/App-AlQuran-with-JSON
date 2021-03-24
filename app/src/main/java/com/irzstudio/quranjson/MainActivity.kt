package com.irzstudio.quranjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.irzstudio.quranjson.data.QuranData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<QuranData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.VISIBLE
        rv_surah.visibility = View.GONE
        tv_error.visibility = View.GONE
        RetrofitClient.instance.getQuran().enqueue(object: Callback<ArrayList<QuranData>>{
            override fun onResponse(call: Call<ArrayList<QuranData>>, response: Response<ArrayList<QuranData>>) {
               response.body()?.let { list.addAll(it) }

                rv_surah.setHasFixedSize(true)
                rv_surah.adapter = SurahAdapter(list)
                rv_surah.layoutManager = LinearLayoutManager(this@MainActivity)
                rv_surah.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
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