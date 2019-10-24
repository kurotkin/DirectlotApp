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

    private val redColor = Color.parseColor("#FF0000")
    private val blueColor = Color.parseColor("#0000FF")

    private val rect = RectF()
    private val paint = Paint().apply {
        color = redColor
    }

    private val size = resources.displayMetrics.density * 100
    private val halfSize = size / 2

    private var rad = 0f
    private var rot = 0f
    private var color = redColor
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.logodl)
    private var radLittle = 1f

    private var dur = 2000
    private val radC = 70f
    private var wHalf = (width / 2).toFloat()
    private var hHalf = (height / 2).toFloat()
    private var pointsOfCircles = Array(12) { arrayOf(0f, 0f) }
    private var radialGradients = Array(12) { RadialGradient(0f, 0f, radLittle, Color.TRANSPARENT, color, TileMode.MIRROR)}

    init {
        val a = context.theme.obtainStyledAttributes(attributeSet, R.styleable.CustomLoaderView, 0, 0)
        dur = a.getInt(R.styleable.CustomLoaderView_duraction, 2000)
        a.recycle()
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
        canvas.rotate(rot, wHalf, hHalf)
        canvas.drawBitmap(bitmap, ((width - bitmap.width) / 2 ).toFloat() , ((height  - bitmap.height) / 2).toFloat(), paint)

        for(i in pointsOfCircles.indices){
            paint.shader = radialGradients[i]
            canvas.drawCircle(pointsOfCircles[i][0], pointsOfCircles[i][1], radLittle, paint)
        }
    }

    fun startAnimation(){
        val ra = PropertyValuesHolder.ofFloat("rad", 0f, 80f)
        val rProp = PropertyValuesHolder.ofFloat("radL", 1f, 50f)
        val ro = PropertyValuesHolder.ofFloat("rot", 0f, 360f)
        val co = PropertyValuesHolder.ofObject("color", ArgbEvaluator(), redColor, blueColor)

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
                for(i in pointsOfCircles.indices){
                    radialGradients[i] = RadialGradient(pointsOfCircles[i][0], pointsOfCircles[i][1], radLittle, Color.TRANSPARENT, color, TileMode.MIRROR)
                }
                invalidate()
            }
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
        }
        vAnim.start()
    }
}