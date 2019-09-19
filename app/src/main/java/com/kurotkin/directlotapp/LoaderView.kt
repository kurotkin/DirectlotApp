package com.kurotkin.directlotapp

import android.animation.ArgbEvaluator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class LoaderView(context: Context, attributeSet: AttributeSet): View(context, attributeSet){

    val blueColor = Color.parseColor("#FF0000")
    val pinkColor = Color.parseColor("#0000FF")

    val rect = RectF()
    val paint = Paint().apply {
        color = blueColor
    }
    val dotPaint = Paint().apply {
        color = Color.RED
        strokeWidth = 2f
    }

    val size = resources.displayMetrics.density * 100
    val halfSize = size / 2

    var rad = 0f
    var rot = 0f
    var color = blueColor
    val bitmap = BitmapFactory.decodeResource(resources, R.drawable.logodl)
    var radLittle = 1f

    var dur = 2000
    init {
        val a = context.theme.obtainStyledAttributes(attributeSet, R.styleable.CustomLoaderView, 0, 0)
        dur = a.getInt(R.styleable.CustomLoaderView_duraction, 2000)
        a.recycle()
    }



    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        rect.set(width / 2 - halfSize, height / 2 - halfSize, width / 2 + halfSize, height / 2 + halfSize)
        startAnimation()
    }

    override fun onDraw(canvas: Canvas?) {
        if(canvas == null) return
        paint.color = color
        canvas.rotate(rot, (width / 2).toFloat(), (height / 2).toFloat())
//        canvas.drawRoundRect(rect, rad, rad, paint)

        val matrix = Matrix()
        matrix.postScale(0.1f, 0.1f)
//        matrix.postRotate(45f)
        canvas.drawBitmap(bitmap, ((width - bitmap.width) / 2 ).toFloat() , ((height  - bitmap.height) / 2).toFloat(), paint)
        val radC = 90f
        val w = width.toFloat()
        val h = height.toFloat()
        canvas.drawCircle(w / 2f + 0.0f * radC, h / 2f - 1.0f * radC, radLittle, paint)
        canvas.drawCircle(w / 2f + 0.5f * radC, h / 2f - 0.866f * radC, radLittle, paint)
        canvas.drawCircle(w / 2f + 0.866f * radC, h / 2f - 0.5f * radC, radLittle, paint)
        canvas.drawCircle(w / 2f + 1.0f * radC, h / 2f - 0.0f * radC, radLittle, paint)
        canvas.drawCircle(w / 2f + 0.866f * radC, h / 2f + 0.5f * radC, radLittle, paint)
        canvas.drawCircle(w / 2f + 0.5f * radC, h / 2f + 0.866f * radC, radLittle, paint)
        canvas.drawCircle(w / 2f - 0.0f * radC, h / 2f + 1.0f * radC, radLittle, paint)
        canvas.drawCircle(w / 2f - 0.5f * radC, h / 2f + 0.866f * radC, radLittle, paint)
        canvas.drawCircle(w / 2f - 0.866f * radC, h / 2f + 0.5f * radC, radLittle, paint)
        canvas.drawCircle(w / 2f - 1.0f * radC, h / 2f + 0.0f * radC, radLittle, paint)
        canvas.drawCircle(w / 2f - 0.866f * radC, h / 2f - 0.5f * radC, radLittle, paint)
        canvas.drawCircle(w / 2f - 0.5f * radC, h / 2f - 0.866f * radC, radLittle, paint)

    }

    fun startAnimation(){
//        val vAnim = ValueAnimator.ofFloat(0f, 150f).apply {
//            duration = 2000
//            addUpdateListener {
//                rad = it.animatedValue as Float
//                invalidate()
//            }
//        }
//        vAnim.start()

        val ra = PropertyValuesHolder.ofFloat("rad", 0f, 150f)
        val rProp = PropertyValuesHolder.ofFloat("radL", 1f, 100f)
        val ro = PropertyValuesHolder.ofFloat("rot", 0f, 360f)
        val co = PropertyValuesHolder.ofObject("color", ArgbEvaluator(), blueColor, pinkColor)
        val vAnim2 = ValueAnimator.ofPropertyValuesHolder(ra, rProp, ro, co).apply {
            duration = this@LoaderView.dur.toLong()
            addUpdateListener {
                rad = it.getAnimatedValue("rad") as Float
                radLittle = it.getAnimatedValue("radL") as Float
                rot = it.getAnimatedValue("rot") as Float
                color = it.getAnimatedValue("color") as Int
                invalidate()
            }
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
        }
        vAnim2.start()
    }
}