package com.grpcx.androidtask.data.network

import android.content.Context
import com.grpcx.androidtask.data.network.response.EmotionResponse
import kotlinx.serialization.json.Json
import javax.inject.Inject


class AppService @Inject constructor(private val context: Context) {

    fun fetchData(): EmotionResponse {
        val jsonString = context.assets.open("emotions.json")
            .bufferedReader()
            .use {
                it.readText()
            }

        return Json.decodeFromString<EmotionResponse>(jsonString)
    }
}