package com.example.myapplication

class FakuaiFactory(number: Int){

    private var number = 0
    private var pos = 0

    init {
        this.number = number
    }

    /**
     * 返回
     */
    fun getProduct(): FakuaiProduct{
        pos = (10*Math.random()).toInt()
        when(number){
            1 -> return Fakuai01(10, pos)
            2 -> return Fakuai02(10, pos)
            3 -> return Fakuai03(10, pos)
            4 -> return Fakuai04(10, pos)
            5 -> return Fakuai05(10, pos)
            6 -> return Fakuai06(10, pos)
            7 -> return Fakuai07(10, pos)
            else -> {
                print("没有这种方块")
            }
        }
        return Fakuai01(10, pos)
    }
}