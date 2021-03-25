package com.irzstudio.quranjson.`interface`

import com.irzstudio.quranjson.dataquran.QuranData

interface OnSurahListener {
    fun onClick(quranData: QuranData)
}