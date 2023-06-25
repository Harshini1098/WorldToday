package com.example.harsha_news_app

import android.os.AsyncTask
import com.google.gson.Gson
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class FetchNewsTask(private val listener: NewsFetchListener) : AsyncTask<Unit, Unit, List<Article>>() {
    private val url = URL("https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json")

    override fun doInBackground(vararg params: Unit?): List<Article> {
        val connection = url.openConnection() as HttpURLConnection
        val responseCode = connection.responseCode
        return if (responseCode == HttpURLConnection.HTTP_OK) {
            val responseString = connection.inputStream.bufferedReader().use(BufferedReader::readText)
            val response = Gson().fromJson(responseString, NewsResponse::class.java)
            response.articles
        } else {
            emptyList()
        }
    }

    override fun onPostExecute(result: List<Article>) {
        listener.onNewsFetched(result)
    }
}

interface NewsFetchListener {
    fun onNewsFetched(articles: List<Article>)
}

