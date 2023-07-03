package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Picasso.get().load(intent.getStringExtra("img")).into(imageView2)
       textView2.text="name:"+ intent.getStringExtra("name")
       textView3.text= intent.getStringExtra("img")
       textView4.text="gender:"+ intent.getStringExtra("gen")
       textView5.text="status:"+ intent.getStringExtra("sta")
       textView6.text="species:"+ intent.getStringExtra("spe")
    }
}