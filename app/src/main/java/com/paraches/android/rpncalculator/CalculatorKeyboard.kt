package com.paraches.android.rpncalculator

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paraches.android.rpncalculator.ui.theme.RPNCalculatorTheme

enum class CalculatorKeyboardEvent {
    OnClickAdd, OnClickSub, OnCLickMul, OnCLickDiv
}

data class CalculatorKeyboard(
    val calculatorKeyboardText: String,
    val calculatorKeyboardEvent: CalculatorKeyboardEvent
)

val calculatorKeyboardList = listOf<CalculatorKeyboard>(
    CalculatorKeyboard("+", CalculatorKeyboardEvent.OnClickAdd),
    CalculatorKeyboard("-", CalculatorKeyboardEvent.OnClickSub),
    CalculatorKeyboard("*", CalculatorKeyboardEvent.OnCLickMul),
    CalculatorKeyboard("/", CalculatorKeyboardEvent.OnCLickDiv),
)

@Composable
fun CalculatorKeyboardScreen(
    modifier: Modifier = Modifier,
    calculatorViewModel: CalculatorViewModel = viewModel()
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(calculatorKeyboardList) {
            Button(
                onClick = {
                    calculatorViewModel.event(it.calculatorKeyboardEvent)
                },
                enabled = calculatorViewModel.uiState.isKeyActive
            ) {
                Text(text = it.calculatorKeyboardText)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorKeyboardScreenPreview(
    calculatorViewModel: CalculatorViewModel = viewModel()
) {
    RPNCalculatorTheme {
        CalculatorKeyboardScreen(
            modifier = Modifier.padding(10.dp),
            calculatorViewModel = calculatorViewModel
        )
    }
}