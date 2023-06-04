package com.example.timej.utils

import java.util.*

class RandomColor() {
    private val recycle: Stack<Int> = Stack()
    private val colors: Stack<Int> = Stack()

    init {
        recycle.addAll(
            listOf(
                // ARGB hex to int >> (0xFFEE5670.toInt(),...)
                0xffADD6FF.toInt(),
                0xccADD6FF.toInt(),
                0xffCDFCD1.toInt(),
                0xccCDFCD1.toInt(),
                0xffFF9083.toInt(),
                0xccFF9083.toInt(),
                0xffE6C7FF.toInt(),
                0xccE6C7FF.toInt(),
                0xff63A959.toInt(),
                0xcc63A959.toInt(),
                0xff95AAF5.toInt(),
                0xcc95AAF5.toInt(),
                0xffDA5B5B.toInt(),
                0xccDA5B5B.toInt(),
                0xccE36F1B.toInt(),
                0xffE36F1B.toInt(),
                0xCC4F7ACD.toInt(),
                0xCC3C89AA.toInt(),
                0xCCDCDDFA.toInt(),
                0xCC6467C9.toInt(),
                0xCCDCFAF1.toInt(),
                0xCC349D7E.toInt(),

            )
        )
    }

    fun getColor(): Int {
        if (colors.size == 0)
            while (!recycle.isEmpty()) colors.push(recycle.pop())
        Collections.shuffle(colors)
        val c = colors.pop()
        recycle.push(c)

        return c
    }
}