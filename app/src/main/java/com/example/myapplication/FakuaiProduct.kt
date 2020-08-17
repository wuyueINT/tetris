package com.example.myapplication

abstract class FakuaiProduct(maxRight: Int, startPos: Int){

    private var speed: Int = 0
    private var state: Int = 0
    private var position = arrayOf(0, startPos) //这个代表的是方块最下面一个格子的位置
    private var maxRight: Int = maxRight

    fun leftMove(){
        if (this.position[0]!=0){
            this.position[0]--
        }
    }

    fun rightMove(){
        if (this.position[0]!=maxRight){
            this.position[0]++
        }
    }

    fun downMove(){
        this.position[1]++
    }

    fun dropMove(){
        this.speed = 5
    }

    abstract fun reshape()
}