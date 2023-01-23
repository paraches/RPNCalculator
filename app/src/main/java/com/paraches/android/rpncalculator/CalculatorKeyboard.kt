package com.paraches.android.rpncalculator

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paraches.android.rpncalculator.ui.theme.RPNCalculatorTheme

class CalculatorKeyboard {
}

enum class KeyboardKeyType {
    OnClickNumericKey, OnClickDelKey, OnClickCLRKey,
    OnClickAddKey, OnClickSubKey, OnClickMulKey, OnClickDivKey,
    OnClickDupKey, OnClickPopKey, OnClickEntKey, OnClickExcKey
}

val KeyboardKeyTypeOperator: Set<KeyboardKeyType> = setOf(
    KeyboardKeyType.OnClickAddKey,
    KeyboardKeyType.OnClickSubKey,
    KeyboardKeyType.OnClickMulKey,
    KeyboardKeyType.OnClickDivKey,
    KeyboardKeyType.OnClickExcKey
)

val KeyboardKeyTypeStackFunction: Set<KeyboardKeyType> = setOf(
    KeyboardKeyType.OnClickPopKey,
    KeyboardKeyType.OnClickDupKey,
    KeyboardKeyType.OnClickExcKey,
)

const val DefaultInputString = "00000000"
val InputStringMaxDigit = DefaultInputString.count()

data class KeyboardKey(
    val text: String,
    val keyType: KeyboardKeyType
)

val KeyboardLayout = listOf(
    listOf(
        KeyboardKey("1", KeyboardKeyType.OnClickNumericKey),
        KeyboardKey("2", KeyboardKeyType.OnClickNumericKey),
        KeyboardKey("3", KeyboardKeyType.OnClickNumericKey),
        KeyboardKey("EXC", KeyboardKeyType.OnClickExcKey),
        KeyboardKey("+", KeyboardKeyType.OnClickAddKey),
    ),
    listOf(
        KeyboardKey("4", KeyboardKeyType.OnClickNumericKey),
        KeyboardKey("5", KeyboardKeyType.OnClickNumericKey),
        KeyboardKey("6", KeyboardKeyType.OnClickNumericKey),
        KeyboardKey("DUP", KeyboardKeyType.OnClickDupKey),
        KeyboardKey("-", KeyboardKeyType.OnClickSubKey),
    ),
    listOf(
        KeyboardKey("7", KeyboardKeyType.OnClickNumericKey),
        KeyboardKey("8", KeyboardKeyType.OnClickNumericKey),
        KeyboardKey("9", KeyboardKeyType.OnClickNumericKey),
        KeyboardKey("POP", KeyboardKeyType.OnClickPopKey),
        KeyboardKey("*", KeyboardKeyType.OnClickMulKey),
    ),
    listOf(
        KeyboardKey("0", KeyboardKeyType.OnClickNumericKey),
        KeyboardKey("DEL", KeyboardKeyType.OnClickDelKey),
        KeyboardKey("CLR", KeyboardKeyType.OnClickCLRKey),
        KeyboardKey("ENT", KeyboardKeyType.OnClickEntKey),
        KeyboardKey("/", KeyboardKeyType.OnClickDivKey),
    )
)

@Composable
fun CalculatorKeyboardScreen(
    modifier: Modifier = Modifier,
    calculatorViewModel: CalculatorViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Column {
                Text(
                    textAlign = TextAlign.Right,
                    text = calculatorViewModel.uiState.inputNumericText
                )
                LazyColumn {
                    items(KeyboardLayout) {
                        LazyRow {
                            items(it) {
                                Button(
                                    modifier = Modifier
                                        .padding(2.dp),
                                    onClick = {
                                        calculatorViewModel.event(it)
                                    },
                                    enabled =
                                    if (KeyboardKeyTypeOperator.contains(it.keyType)) {
                                        calculatorViewModel.uiState.isOperatorKeyEnabled
                                    } else if (KeyboardKeyTypeStackFunction.contains(it.keyType)) {
                                        calculatorViewModel.uiState.isStackFunctionKeyEnabled
                                    } else {
                                        true
                                    }

                                ) {
                                    Text(text = it.text)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorKeyboardScreenPreview() {
    val calculatorViewModel = CalculatorViewModel(Calculator())
    RPNCalculatorTheme() {
        CalculatorKeyboardScreen(calculatorViewModel = calculatorViewModel)
    }
}