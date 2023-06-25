package com.example.harsha_news_app

data class NewsResponse(
    val status: String,
    val articles: List<Article>
)

data class Article(
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val image:String,
    val publishedAt: String
)
