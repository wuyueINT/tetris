package com.example.myapplication


/**
 *   三角形状
 */
class Fakuai03(maxRight: Int, startPos: Int) : FakuaiProduct(maxRight, startPos) {

    private val shape1 = arrayOf(
        arrayOf(0,0,0,0),
        arrayOf(0,1,1,1),
        arrayOf(0,0,1,0),
        arrayOf(0,0,0,0)
    )
    private val shape2 = arrayOf(
        arrayOf(0,0,1,0),
        arrayOf(0,0,1,1),
        arrayOf(0,0,1,0),
        arrayOf(0,0,0,0)
    )
    private val shape3 = arrayOf(
        arrayOf(0,0,1,0),
        arrayOf(0,1,1,1),
        arrayOf(0,0,0,0),
        arrayOf(0,0,0,0)
    )
    private val shape4 = arrayOf(
        arrayOf(0,0,1,0),
        arrayOf(0,1,1,0),
        arrayOf(0,0,1,0),
        arrayOf(0,0,0,0)
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
            this.shape = shape3
            this.state = 2
        } else if (state==2){
            this.shape = shape4
            this.state = 3
        } else if (state==3){
            this.shape = shape
            this.state = 0
        }
    }

}