package com.paraches.android.rpncalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paraches.android.rpncalculator.ui.theme.RPNCalculatorTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CalculatorUiState(
    val stack: List<Int> = listOf()
)

val CalculatorUiState.isKeyActive: Boolean get() = stack.count() > 1

class Calculator(initialStackList: List<Int> = emptyList()) {
    private val _stackStateFlow = MutableStateFlow(CalculatorStack(initialStackList)).asStateFlow()
    val stack = _stackStateFlow.value

    fun push(value: Int) {
        stack.push(value)
    }

    fun pop(): Int {
        return stack.pop()
    }

    // provide operand from stack
    private fun popOperands(): Pair<Int, Int> {
        val rightOperand = pop()
        val leftOperand = pop()
        return Pair(leftOperand, rightOperand)
    }

    fun add() {
        val (leftOperand, rightOperand) = popOperands()
        push(leftOperand + rightOperand)
    }

    fun sub() {
        val (leftOperand, rightOperand) = popOperands()
        push(leftOperand - rightOperand)
    }

    fun mul() {
        val (leftOperand, rightOperand) = popOperands()
        push(leftOperand * rightOperand)
    }

    fun div() {
        val (leftOperand, rightOperand) = popOperands()
        push(leftOperand / rightOperand)
    }
}

@Composable
fun CalculatorScreen(
    calculatorViewModel: CalculatorViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        CalculatorInputScreen(
            calculatorViewModel = calculatorViewModel
        )

        Row {
            CalculatorStackScreen(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(2f),
                calculatorViewModel = calculatorViewModel
            )
            CalculatorKeyboardScreen(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
                calculatorViewModel = calculatorViewModel
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview(
    calculatorViewModel: CalculatorViewModel =
        CalculatorViewModel(Calculator(listOf(1, 2, 3)))
) {
    RPNCalculatorTheme {
        CalculatorScreen(calculatorViewModel)
    }
}