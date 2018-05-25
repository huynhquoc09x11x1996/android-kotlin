package com.example.internhbaoquoc.customview_kotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class ABCDView : View{

    lateinit var mPaint: Paint


    constructor(context: Context?) : super(context) {
        khoiTao()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        khoiTao()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        khoiTao()
    }

    fun khoiTao() {
        mPaint = Paint()
        mPaint.strokeWidth = 2F
        mPaint.color = Color.RED
    }




    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    private var p1x: Float= 0F
    private var p1y: Float= 0F
    private var p2x: Float= 0F
    private var p2y: Float= 0F


//    fun drawV(canvas: Canvas?,mPaint: Paint)
//    {
//        canvas?.drawLine(p1x, p1y, p2x, p2y, mPaint)
//        canvas?.drawCircle(p1x, p1y,20F, mPaint)
//    }

}