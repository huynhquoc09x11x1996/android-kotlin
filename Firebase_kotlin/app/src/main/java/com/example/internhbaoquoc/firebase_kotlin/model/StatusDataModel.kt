package com.example.internhbaoquoc.firebase_kotlin.model

data class StatusDataModel(val idStt: String,
                           val avarta: String,
                           val name: String,
                           val hour: String,
                           val contentText: String,
                           val contentImage: String,
                           val numLike: Int,
                           val numCmt: Int,
                           val listCmt: ArrayList<PeopleComment>
)