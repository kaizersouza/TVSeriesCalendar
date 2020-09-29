package com.bychinin.tvseriescalendar.UI.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.bychinin.tvseriescalendar.R

class Loady (context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint: Paint = Paint()
    private var radius : Float = 1f
    private val speed : Int = 3

    init {
        val array : TypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.Loady)
        paint.color = array.getInt(R.styleable.Loady_Color,0)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {

        canvas.drawCircle(width/2f, height/2f, radius, paint)

        if (radius >= width/2f) {
            radius = 0f
        } else {
            radius = radius + speed
        }



        invalidate()
    }


}