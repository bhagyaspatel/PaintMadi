package com.example.paintmadi

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.paintmadi.MainActivity.Companion.paintBrush
import com.example.paintmadi.MainActivity.Companion.path


class paintView : View {

    //params variable is responsible for ..what is height and width of our canvas wrt our parent layout

    companion object{
        var pathList = ArrayList<Path>() //..to hold the all the path we have drawn on our canvas
        var colorList = ArrayList<Int>() //..to hold the colors..which actually returns Integer value
        var currentBrush = Color.BLACK
    }

    //constructors for view are atken from google just type "constructors for view in AS kotlin"
    //in every project we have to take it from google for case of View

    constructor(context: Context) : this(context, null){
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0){
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    //we have to initialize our stroke/path that we actually have to draw
    private fun init(){
        //paintBrush is the companion object of Paint() in main Act
        paintBrush.isAntiAlias = true //to make our stroke smooth
        paintBrush.color = currentBrush
        paintBrush.style = Paint.Style.STROKE //for style of our stroke
        paintBrush.strokeJoin = Paint.Join.ROUND //how the path will end
        paintBrush.strokeWidth = 8f // f means float value

        var params = ViewGroup.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    //to register the movement of the fingers on the screen..i.e how the user is running the finger on the screen
    //using this method we will get the x and y coordinates
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.x
        var y = event.y

        //in Kotlin we don't have switch statements we have when statements
        when (event.action){
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x,y)
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x,y) //drawing a line
                pathList.add(path) //adding a path in our arraylist
                colorList.add(currentBrush) //adding the color to our arrayList
            }

            else -> return false
        }
        postInvalidate()
        //postInvalidate informs the non-ui threads that something at the ui is changed
        //it is very necessory to add this

        return false
    }
    //now we have registered the touch event but we have not drawn something on the screen yet

    override fun onDraw(canvas: Canvas) {

//        so to draw something on the canvas what we do is ..
//        we have array list of paths..we take all path from that list one by one and draw it on canvas

        for (i in pathList.indices){
            paintBrush.setColor(colorList[i])
            canvas.drawPath(pathList[i], paintBrush)
            invalidate() //same as postInvalidate
        }
    }

}