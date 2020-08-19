package com.example.myapplication

/**
 * 返回
 */
fun getProduct(number: Int): FakuaiProduct{
    var pos = getRandomNum(1, 7)
    when(number){
        1 -> return Fakuai01(30, pos)
        2 -> return Fakuai02(30, pos)
        3 -> return Fakuai03(30, pos)
        4 -> return Fakuai04(30, pos)
        5 -> return Fakuai05(30, pos)
        6 -> return Fakuai06(30, pos)
        7 -> return Fakuai07(30, pos)
        else -> {
            print("没有这种方块")
        }
    }
    return Fakuai01(30, pos)
}
