package com.paraches.android.rpncalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paraches.android.rpncalculator.ui.theme.RPNCalculatorTheme

data class CalculatorUiState(
    val listValue: List<Int> = listOf(),
    val inputNumericText: String = DefaultInputString
)

val CalculatorUiState.isOperatorKeyEnabled: Boolean get() = listValue.count() > 1
val CalculatorUiState.isStackFunctionKeyEnabled: Boolean get() = listValue.isNotEmpty()

class Calculator(initialStackList: List<Int> = emptyList()) {
    private val _stack: CalculatorStackInterface = CalculatorStack(initialStackList)
    val stackValueList = _stack.valueList

    fun push(value: Int) {
        _stack.push(value)
    }

    fun pop(): Int {
        return _stack.pop()
    }

    fun dup() {
        val value = pop()
        push(value)
        push(value)
    }

    fun exchange() {
        val v1 = pop()
        val v2 = pop()
        push(v1)
        push(v2)
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
    val showSnackbar = calculatorViewModel.showSnackbar.observeAsState().value

    SnackbarContainer(
        snackbarText = calculatorViewModel.snackbarText,
        showSnackbar = showSnackbar!!,
        onDismissSnackbar = { calculatorViewModel.dismissSnackbar() }
    ) {
        CalculatorContainerScreen(calculatorViewModel)
    }
}

@Composable
fun SnackbarContainer(
    snackbarText: String,
    showSnackbar: Boolean,
    onDismissSnackbar: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        content()

        val onDismissState by rememberUpdatedState(newValue = onDismissSnackbar)
        LaunchedEffect(showSnackbar, snackbarText) {
            if (showSnackbar) {
                try {
                    snackbarHostState.showSnackbar(
                        message = snackbarText,
                        "OK",
                        duration = SnackbarDuration.Short
                    )
                } finally {
                    onDismissState()
                }
            }
        }

        MaterialTheme(shapes = Shapes()) {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = modifier
                    .align(Alignment.BottomCenter)
                    .systemBarsPadding()
                    .padding(8.dp)
            ) {
                Snackbar(snackbarData = it)
            }
        }
    }
}

@Composable
fun CalculatorContainerScreen(
    calculatorViewModel: CalculatorViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        CalculatorStackScreen(
            modifier = Modifier
                .padding(10.dp)
                .weight(2f),
            calculatorViewModel = calculatorViewModel
        )

        Spacer(modifier = Modifier.weight(1f))

        CalculatorKeyboardScreen(
            modifier = Modifier
                .padding(10.dp)
                .weight(1f),
            calculatorViewModel = calculatorViewModel
        )
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