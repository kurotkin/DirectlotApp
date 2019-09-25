package com.kurotkin.directlotapp

import android.animation.ArgbEvaluator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.graphics.Shader.TileMode
import android.util.AttributeSet
import android.view.View



class LoaderView(context: Context, attributeSet: AttributeSet): View(context, attributeSet){

    val blueColor = Color.parseColor("#FF0000")
    val pinkColor = Color.parseColor("#0000FF")

    val rect = RectF()
    val paint = Paint().apply {
        color = blueColor
    }

    val size = resources.displayMetrics.density * 100
    val halfSize = size / 2

    var rad = 0f
    var rot = 0f
    var color = blueColor
    val bitmap = BitmapFactory.decodeResource(resources, R.drawable.logodl)
    var radLittle = 1f

    var dur = 2000
    val radC = 70f
    var wHalf = (width / 2).toFloat()
    var hHalf = (height / 2).toFloat()
    var pointsOfCircles: Array<Array<Float>>
    var radialGradients: Array<RadialGradient>

    init {
        val a = context.theme.obtainStyledAttributes(attributeSet, R.styleable.CustomLoaderView, 0, 0)
        dur = a.getInt(R.styleable.CustomLoaderView_duraction, 2000)
        a.recycle()
        pointsOfCircles = arrayOf(
            arrayOf(0.0f * radC,   -1.0f * radC),
            arrayOf(0.5f * radC,   -0.866f * radC),
            arrayOf(0.866f * radC, -0.5f * radC),
            arrayOf(1.0f * radC,   -0.0f * radC),
            arrayOf(0.866f * radC, 0.5f * radC),
            arrayOf(0.5f * radC,   0.866f * radC),
            arrayOf(-0.0f * radC,   1.0f * radC),
            arrayOf(-0.5f * radC,   0.866f * radC),
            arrayOf(-0.866f * radC, 0.5f * radC),
            arrayOf(-1.0f * radC,   0.0f * radC),
            arrayOf(-0.866f * radC, -0.5f * radC),
            arrayOf(-0.5f * radC,   -0.866f * radC))
        radialGradients = Array(pointsOfCircles.size)
        radialGradients.fill(RadialGradient(0f, 0f, radLittle, Color.TRANSPARENT, color, TileMode.MIRROR), 0, pointsOfCircles.size)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        wHalf = (width / 2).toFloat()
        hHalf = (height / 2).toFloat()
        rect.set(wHalf - halfSize, hHalf - halfSize, wHalf + halfSize, hHalf + halfSize)
        startAnimation()
    }

    override fun onDraw(canvas: Canvas?) {
        if(canvas == null) return
//        paint.color = color
        canvas.rotate(rot, wHalf, hHalf)
        canvas.drawBitmap(bitmap, ((width - bitmap.width) / 2 ).toFloat() , ((height  - bitmap.height) / 2).toFloat(), paint)

        for(o in pointsOfCircles){
            paint.shader = RadialGradient(o[0], o[1], radLittle, Color.TRANSPARENT, color, TileMode.MIRROR)
            canvas.drawCircle(o[0], o[1], radLittle, paint)
        }
    }

    fun startAnimation(){
        val ra = PropertyValuesHolder.ofFloat("rad", 0f, 80f)
        val rProp = PropertyValuesHolder.ofFloat("radL", 1f, 50f)
        val ro = PropertyValuesHolder.ofFloat("rot", 0f, 360f)
        val co = PropertyValuesHolder.ofObject("color", ArgbEvaluator(), blueColor, pinkColor)

        val vAnim = ValueAnimator.ofPropertyValuesHolder(ra, rProp, ro, co).apply {
            duration = this@LoaderView.dur.toLong()
            addUpdateListener {
                rad = it.getAnimatedValue("rad") as Float
                radLittle = it.getAnimatedValue("radL") as Float
                rot = it.getAnimatedValue("rot") as Float
                color = it.getAnimatedValue("color") as Int
                pointsOfCircles = arrayOf(
                    arrayOf(wHalf + 0.0f * radC, hHalf - 1.0f * radC),
                    arrayOf(wHalf + 0.5f * radC, hHalf - 0.866f * radC),
                    arrayOf(wHalf + 0.866f * radC, hHalf - 0.5f * radC),
                    arrayOf(wHalf + 1.0f * radC, hHalf - 0.0f * radC),
                    arrayOf(wHalf + 0.866f * radC, hHalf + 0.5f * radC),
                    arrayOf(wHalf + 0.5f * radC, hHalf + 0.866f * radC),
                    arrayOf(wHalf - 0.0f * radC, hHalf + 1.0f * radC),
                    arrayOf(wHalf - 0.5f * radC, hHalf + 0.866f * radC),
                    arrayOf(wHalf - 0.866f * radC, hHalf + 0.5f * radC),
                    arrayOf(wHalf - 1.0f * radC, hHalf + 0.0f * radC),
                    arrayOf(wHalf - 0.866f * radC, hHalf - 0.5f * radC),
                    arrayOf(wHalf - 0.5f * radC, hHalf - 0.866f * radC))
                invalidate()
            }
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
        }
        vAnim.start()
    }
}