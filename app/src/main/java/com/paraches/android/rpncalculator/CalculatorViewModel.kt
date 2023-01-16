package com.paraches.android.rpncalculator

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel(
    private val calculator: Calculator = Calculator()
): ViewModel() {
    var uiState by mutableStateOf(CalculatorUiState(calculator.stack.valueList))

    fun push(value: Int) {
        calculator.push(value)
        uiState = uiState.copy(stack = calculator.stack.valueList)
    }

    fun event(event: CalculatorKeyboardEvent) {
        when (event) {
            CalculatorKeyboardEvent.OnClickAdd -> calculator.add()
            CalculatorKeyboardEvent.OnClickSub -> calculator.sub()
            CalculatorKeyboardEvent.OnCLickMul -> calculator.mul()
            CalculatorKeyboardEvent.OnCLickDiv -> calculator.div()
        }
    }
}
