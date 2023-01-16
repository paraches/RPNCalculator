package com.paraches.android.rpncalculator

import org.junit.Test
import org.junit.Assert.*

class CalculatorStackUnitTest {
    //  Default
    @Test
    fun default_stack_size() {
        val stack = CalculatorStack()
        assertEquals(0, stack.valueList.size)
    }

    //  Init with default values
    @Test
    fun set_default_values() {
        val stack = CalculatorStack(listOf(1, 2, 3))
        assertEquals(3, stack.valueList.size)
        assertEquals(1, stack.valueList[0])
        assertEquals(2, stack.valueList[1])
        assertEquals(3, stack.valueList[2])
    }

    //  push
    @Test
    fun push() {
        val stack = CalculatorStack()
        stack.push(0)
        assertEquals(0, stack.valueList[0])

        stack.push(1)
        assertEquals(1, stack.valueList[0])
        assertEquals(0, stack.valueList[1])
    }

    //  pop
    @Test
    fun pop() {
        val stack = CalculatorStack(listOf(0, 1))
        val v1 = stack.pop()
        assertEquals(0, v1)
        val v2 = stack.pop()
        assertEquals(1, v2)
    }

    @Test(expected = NoSuchElementException::class)
    fun pop_with_empty_stack() {
        val stack = CalculatorStack()
        stack.pop()
    }

    //  clear
    @Test
    fun clear() {
        val stack = CalculatorStack(listOf(1, 2, 3))
        stack.clear()
        assertEquals(0, stack.valueList.size)
    }
}