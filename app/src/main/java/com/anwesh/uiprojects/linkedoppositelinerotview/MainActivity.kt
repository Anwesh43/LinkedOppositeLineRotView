package com.anwesh.uiprojects.linkedoppositelinerotview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.oppositelinerotview.OppositeLineRotView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        OppositeLineRotView.create(this)
    }
}
