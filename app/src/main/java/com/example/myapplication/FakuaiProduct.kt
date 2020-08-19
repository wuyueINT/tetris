package com.example.myapplication

abstract class FakuaiProduct(maxRight: Int, startPos: Int){

    private var state: Int = 0
    var position = arrayOf(0, startPos) //这个代表的是方块左下角格子的位置
    var maxRight: Int = maxRight
    var shape: Array<Array<Int>>? = null

    fun leftMove(){
        if (position[1]>-4){
            position[1]--
        }
    }

    fun rightMove(){
        if (position[1]<maxRight){
            position[1]++
        }
    }

    fun downMove(){
        position[1]++
    }

    fun dropMove(){
        position[1] += 5
    }

    abstract fun reshape()
}