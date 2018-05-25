package com.example.internhbaoquoc.customview_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private var p1y: Float=0F
    private var p1x: Float=0F
    private var p2x: Float=0F
    private var p2y: Float=0F


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.getAction()) {
            MotionEvent.ACTION_DOWN -> {
                p1x = event.getX()
                p1y = event.getY()
            }
            MotionEvent.ACTION_MOVE -> {
                p2x = event.getX()
                p2y = event.getY()
            }
            MotionEvent.ACTION_UP -> {
                p2x = event.getX()
                p2y = event.getY()
            }
        }

        return true
    }
}
