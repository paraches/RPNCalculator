package com.paraches.android.rpncalculator

import org.junit.Test

import org.junit.Assert.*

class CalculatorViewModelUnitTest {
    @Test
    fun viewModel_event() {
        val calculator = Calculator(listOf(1, 2))
        val viewModel = CalculatorViewModel(calculator)

        assertEquals(1, viewModel.uiState.listValue[0])
        assertEquals(2, viewModel.uiState.listValue[1])

        calculator.add()
        assertEquals(3, viewModel.uiState.listValue[0])
    }
}