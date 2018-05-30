package com.example.internhbaoquoc.firebase_kotlin.model

class StatusDataModel{
    private lateinit var idStt: String
    private lateinit var avarta: String
    private lateinit var name: String
    private lateinit var hour: String
    private lateinit var contentText: String
    private lateinit var contentImage: String
    private var numLike: Long=0
    private var numCmt: Long=0
    private lateinit var listCmt: ArrayList<PeopleComment>
    constructor(avarta: String, contentImage: String, contentText:String, hour:String, idStt: String,
                 name:String, numCmt: Long, numLike:Long)
    {
        this.avarta=avarta
        this.contentImage=contentImage
        this.contentText=contentText
        this.hour=hour
        this.idStt=idStt
        this.name=name
        this.numLike=numLike
        this.numCmt=numCmt
    }

    //setter
    fun setId(id:String)
    {
        idStt=id
    }
    fun setAvata(avt:String)
    {
        avarta=avt
    }

    fun setName(name :String)
    {
        this.name=name
    }

    fun setHour(h: String) {
        hour=h
    }

    fun setContentText(text: String) {
        contentText=text
    }

    fun setContentImage(img: String) {
        contentImage=img
    }

    fun setNumLike(numL: Long) {
        numLike=numL
    }

    fun setNumCmt(numC: Long) {
        numCmt=numC
    }

    //getter

    fun getId():String {
        return idStt
    }

    fun getAvata():String {
        return avarta
    }

    fun getName():String {
        return name
    }

    fun getHour():String {
        return hour
    }

    fun getContentText(): String {
        return contentText
    }

    fun getContentImage():String {
        return contentImage
    }

    fun getNumLike():Long {
        return numLike
    }
    fun getNumCmt():Long

    {
        return numCmt
    }
}