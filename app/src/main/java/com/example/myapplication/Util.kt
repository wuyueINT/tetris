package com.example.myapplication

/**
 * 获得从start到end的随机int数，包括边界
 */
fun getRandomNum(start: Int, end: Int): Int{
    return (Math.random()*(end+1)+start).toInt()
}