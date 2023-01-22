package com.paraches.android.rpncalculator

import org.junit.Test
import org.junit.Assert.*

class CalculatorUnitTest {
    //  Operation error with stack size 0 and 1
    @Test(expected = NoSuchElementException::class)
    fun add_stackSize_0() {
        val calculator = Calculator()
        calculator.add()
    }

    @Test(expected = NoSuchElementException::class)
    fun add_stackSize_1() {
        val calculator = Calculator()
        calculator.push(0)
        calculator.add()
    }

    //  add
    @Test
    fun add_stackSize2() {
        val calculator = Calculator(listOf(1, 2))
        calculator.add()
        val result = calculator.pop()
        assertEquals(3, result)
    }

    @Test
    fun add_stackSize3() {
        val calculator = Calculator(listOf(1, 2, 3))
        calculator.add()
        val result1 = calculator.pop()
        assertEquals(3, result1)
        calculator.push(result1)

        calculator.add()
        val result2 = calculator.pop()
        assertEquals(6, result2)
    }

    //  sub
    @Test
    fun sub_stackSize2() {
        val calculator = Calculator(listOf(1, 2))
        calculator.sub()
        val result = calculator.pop()
        assertEquals(1, result)
    }

    @Test
    fun sub_stackSize3() {
        val calculator = Calculator(listOf(1, 2, 3))
        calculator.sub()
        val result1 = calculator.pop()
        assertEquals(1, result1)
        calculator.push(result1)

        calculator.sub()
        val result2 = calculator.pop()
        assertEquals(2, result2)
    }

    //  mul
    @Test
    fun mul_stackSize2() {
        val calculator = Calculator(listOf(1, 2))
        calculator.mul()
        val result = calculator.pop()
        assertEquals(2, result)
    }

    @Test
    fun mul_stackSize3() {
        val calculator = Calculator(listOf(1, 2, 3))
        calculator.mul()
        val result1 = calculator.pop()
        assertEquals(2, result1)
        calculator.push(result1)

        calculator.mul()
        val result2 = calculator.pop()
        assertEquals(6, result2)
    }

    //  div
    @Test
    fun div_stackSize2() {
        val calculator = Calculator(listOf(1, 2))
        calculator.div()
        val result = calculator.pop()
        assertEquals(2, result)
    }

    @Test
    fun div_stackSize3() {
        val calculator = Calculator(listOf(1, 2, 4))
        calculator.div()
        val result1 = calculator.pop()
        assertEquals(2, result1)
        calculator.push(result1)

        calculator.div()
        val result2 = calculator.pop()
        assertEquals(2, result2)
    }

    @Test
    fun div_with_remainder() {
        val calculator = Calculator(listOf(3, 10))
        calculator.div()
        val result = calculator.pop()
        assertEquals(3, result)
    }

    @Test(expected = java.lang.ArithmeticException::class)
    fun div_with_divide_by_zero() {
        val calculator = Calculator(listOf(0, 1))
        calculator.div()
    }

    // dup
    @Test
    fun dup() {
        val calculator = Calculator(listOf(1, 2))
        calculator.dup()
        val resultList = calculator.stackValueList
        assertEquals(listOf(1, 1, 2), resultList)
    }

    // exchange
    @Test
    fun exchange() {
        val calculator = Calculator(listOf(1, 2))
        calculator.exchange()
        val resultList = calculator.stackValueList
        assertEquals(listOf(2, 1), resultList)
    }
}