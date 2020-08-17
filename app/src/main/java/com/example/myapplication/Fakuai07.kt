package com.example.myapplication

/**
 *
 */
class Fakuai07(maxRight: Int, startPos: Int) : FakuaiProduct(maxRight, startPos) {

    private val shape1 = arrayOf(
        arrayOf(0,0,0,0),
        arrayOf(1,1,1,0),
        arrayOf(1,0,0,0),
        arrayOf(0,0,0,0)
    )
    private val shape2 = arrayOf(
        arrayOf(0,0,0,0),
        arrayOf(1,1,0,0),
        arrayOf(0,1,0,0),
        arrayOf(0,1,0,0)
    )
    private var state = 0
    private var shape: Array<Array<Int>>? = null

    init {
        this.shape = shape1
    }

    /**
     * 实现方块的变形
     */
    override fun reshape() {
        if (state==0){
            this.shape = shape2
            this.state = 1
        } else if (state==1){
            this.shape = shape1
            this.state = 0
        }
    }

}