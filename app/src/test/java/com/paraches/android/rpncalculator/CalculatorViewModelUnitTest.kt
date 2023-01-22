package com.paraches.android.rpncalculator

import org.junit.Test

import org.junit.Assert.*

class CalculatorViewModelUnitTest {
    @Test
    fun viewModel_event() {
        val calculator = Calculator(listOf(3, 2, 9, 2, 8, 1, 2))
        val viewModel = CalculatorViewModel(calculator)

        viewModel.event(KeyboardKey("+", KeyboardKeyType.OnClickAddKey))
        assertEquals(5, viewModel.uiState.listValue[0])

        viewModel.event(KeyboardKey("-", KeyboardKeyType.OnClickSubKey))
        assertEquals(4, viewModel.uiState.listValue[0])

        viewModel.event(KeyboardKey("*", KeyboardKeyType.OnClickMulKey))
        assertEquals(8, viewModel.uiState.listValue[0])

        viewModel.event(KeyboardKey("/", KeyboardKeyType.OnClickDivKey))
        assertEquals(1, viewModel.uiState.listValue[0])

        viewModel.event(KeyboardKey("DUP", KeyboardKeyType.OnClickDupKey))
        assertEquals(1, viewModel.uiState.listValue[0])
        assertEquals(1, viewModel.uiState.listValue[1])

        viewModel.event(KeyboardKey("POP", KeyboardKeyType.OnClickPopKey))
        assertEquals(1, viewModel.uiState.listValue[0])
    }
}