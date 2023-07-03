package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Toast
import com.squareup.picasso.Picasso
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.mylistwid.view.*
import java.util.Objects
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

                fetchData()



    }


    fun fetchData() {

        val uri="https://rickandmortyapi.com/api/"
        val  retrofit=Retrofit.Builder().baseUrl(uri).addConverterFactory(GsonConverterFactory.create()).build()
    val Api=retrofit.create(myapi::class.java)
        val call=Api.getData()
        val item=call.enqueue(object :Callback<charactermodel>{
            override fun onResponse(
                call: Call<charactermodel>,
                response: Response<charactermodel>
            ) {
                println("...................${response.body()}")
                Toast.makeText(this@MainActivity,"ok",Toast.LENGTH_SHORT).show()
                myListView.adapter=myAdabter(this@MainActivity, response.body()!!.results)
            }

            override fun onFailure(call: Call<charactermodel>, t: Throwable) {
                println("................${t.message}")
                Toast.makeText(this@MainActivity,"fail",Toast.LENGTH_SHORT).show()
            }

        })

    }
}


class myAdabter(cxt:Context,charcs:Array<character>):BaseAdapter()
{
    val  cxt:Context
    val charcs:Array<character>
    init {
        this.cxt=cxt
        this.charcs=charcs
    }
    override fun getCount(): Int {
        return charcs.size
    }

    override fun getItem(p0: Int): Any {
        return ""
    }

    override fun getItemId(p0: Int): Long {
        return 1
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
       val myInflate=LayoutInflater.from(cxt).inflate(R.layout.mylistwid,p2,false)
        myInflate.textView.text=charcs[p0].name
        Picasso.get().load(Uri.parse(charcs[p0].image)).into(myInflate.imageView)
        myInflate.setOnClickListener {
            var intent=Intent(p1!!.context,MainActivity2::class.java)
            val  ch=charcs[p0]
            intent.putExtra("name",ch.name)
            intent.putExtra("img",ch.image)
            intent.putExtra("gen",ch.gender)
            intent.putExtra("sta",ch.status)
            intent.putExtra("spe",ch.species)
            p1.context.startActivity(intent)

        }


        return myInflate
    }

}