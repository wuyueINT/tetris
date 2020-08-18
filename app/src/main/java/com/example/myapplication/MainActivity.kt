package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import java.sql.Blob

class MainActivity : AppCompatActivity() {

    private var adapter: BoardAdapter? = null
    private var fakuai: FakuaiProduct? = null
    private var fakuaiFactory: FakuaiFactory = FakuaiFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //将RecyclerView的布局放置到左上页面的位置
        adapter = BoardAdapter(this, Array(30) { j -> Array(10) { i -> (i+j)%2 }})
        rv_fakuai.layoutManager = GridLayoutManager(this, 10)
        rv_fakuai.adapter = adapter

        //各种按钮的点击事件
        btn_reset.setOnClickListener {
            var number = (7*Math.random()).toInt()+1
            fakuai = fakuaiFactory.getProduct(number)
            //开始计时器，每隔一秒方块运动一次
        }

        btn_left.setOnClickListener {

        }

        btn_right.setOnClickListener {  }

        btn_drop.setOnClickListener {  }

        btn_reshape.setOnClickListener {
            fakuai?.reshape()
        }
    }



    private var handler: WithoutHandler(this){}


    companion object{

        class WithoutHandler(ma: MainActivity): Handler() {
            private var ma: WeakReference<MainActivity> = WeakReference(ma)

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when(msg.what){

                }
            }
        }
    }



}