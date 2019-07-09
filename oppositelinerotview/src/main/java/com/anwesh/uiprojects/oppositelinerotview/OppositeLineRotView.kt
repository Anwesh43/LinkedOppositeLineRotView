package com.anwesh.uiprojects.oppositelinerotview

/**
 * Created by anweshmishra on 09/07/19.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Canvas
import android.content.Context
import android.app.Activity

val nodes : Int = 5
val lines : Int = 2
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#283593")
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawOppositeLineRot(i : Int, sc1 : Float, sc2 : Float, size : Float, paint : Paint) {
    val sci1 : Float = sc1.divideScale(i, lines)
    val sci2 : Float = sc2.divideScale(i, lines)
    for (j in 0..1) {
        save()
        translate(-size * (1 - 2 * i) * sci1,size * (1 - 2 * i))
        rotate(-j * 45f  * sci2)
        drawLine(0f, 0f, size * (1 - 2 * i) * sci1, 0f, paint)
        restore()
    }
}

fun Canvas.drawOLRNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.color = foreColor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    save()
    translate(w / 2, gap * (i + 2))
    for (j in 0..(lines - 1)) {
        drawOppositeLineRot(j, sc1, sc2, size, paint)
    }
    restore()
}