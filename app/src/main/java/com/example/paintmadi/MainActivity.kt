package com.example.paintmadi

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.view.SupportActionModeWrapper
import com.example.paintmadi.paintView.Companion.colorList
import com.example.paintmadi.paintView.Companion.currentBrush
import com.example.paintmadi.paintView.Companion.pathList

class MainActivity : AppCompatActivity() {

    //the drawing has 2 imp path : 1. The path and 2. the actual paint
    //..both from android.graphics
    
    //companion object..this works as "public static"
    companion object{
        var path = Path()
        var paintBrush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val redBtn = findViewById<ImageButton>(R.id.redPaint)
        val yellowBtn = findViewById<ImageButton>(R.id.yellowPaint)
        val blueBtn = findViewById<ImageButton>(R.id.bluePaint)
        val whiteBtn = findViewById<ImageButton>(R.id.whitePaint)

        redBtn.setOnClickListener{
            Toast.makeText(this, "Red", Toast.LENGTH_SHORT).show()
            paintBrush.color = Color.RED
            changeColor(paintBrush.color)
        }

        yellowBtn.setOnClickListener{
            Toast.makeText(this, "Yellow", Toast.LENGTH_SHORT).show()
            paintBrush.color = Color.YELLOW
            changeColor(paintBrush.color)
        }

        blueBtn.setOnClickListener{
            Toast.makeText(this, "Blue", Toast.LENGTH_SHORT).show()
            paintBrush.color = Color.BLUE
            changeColor(paintBrush.color)
        }

        //our white button is basically eraser
        whiteBtn.setOnClickListener{
            Toast.makeText(this, "White", Toast.LENGTH_SHORT).show()
            pathList.clear()
            colorList.clear()
            path.reset()
        }
    }

    private fun changeColor (color : Int){
        currentBrush = color
        path = Path() //we also have to create new path for new color
    }
}