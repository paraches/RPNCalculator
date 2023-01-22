package com.paraches.android.rpncalculator

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel(
    private val calculator: Calculator = Calculator()
): ViewModel() {
    var uiState by mutableStateOf(CalculatorUiState(calculator.stackValueList))

    fun event(event: KeyboardKey) {
        when (event.keyType) {
            KeyboardKeyType.OnClickCLRKey -> {
                uiState = uiState.copy(
                    inputNumericText = DefaultInputString
                )
            }
            KeyboardKeyType.OnClickDelKey -> {
                uiState = uiState.copy(
                    inputNumericText = "0" + uiState.inputNumericText.dropLast(1)
                )
            }
            KeyboardKeyType.OnClickEntKey -> {
                val value = uiState.inputNumericText.toInt()
                calculator.push(value)
                uiState = uiState.copy(
                    listValue = calculator.stackValueList,
                    inputNumericText = DefaultInputString
                )
            }
            KeyboardKeyType.OnClickAddKey -> {
                calculator.add()
                uiState = uiState.copy(
                    listValue = calculator.stackValueList,
                    inputNumericText = DefaultInputString
                )
            }
            KeyboardKeyType.OnClickSubKey -> {
                calculator.sub()
                uiState = uiState.copy(
                    listValue = calculator.stackValueList,
                    inputNumericText = DefaultInputString
                )
            }
            KeyboardKeyType.OnClickMulKey -> {
                calculator.mul()
                uiState = uiState.copy(
                    listValue = calculator.stackValueList,
                    inputNumericText = DefaultInputString
                )
            }
            KeyboardKeyType.OnClickDivKey -> {
                try {
                    calculator.div()
                } catch (e: java.lang.ArithmeticException) {
                    Log.d("teshi", "event: Divide by zero\n$e")
                }
                finally {
                    uiState = uiState.copy(
                        listValue = calculator.stackValueList,
                        inputNumericText = DefaultInputString
                    )
                }
            }
            KeyboardKeyType.OnClickPopKey -> {
                val value = (DefaultInputString + calculator.pop()).takeLast(InputStringMaxDigit)
                uiState = uiState.copy(
                    listValue = calculator.stackValueList,
                    inputNumericText = value
                )
            }
            KeyboardKeyType.OnClickDupKey -> {
                calculator.dup()
                uiState = uiState.copy(
                    listValue = calculator.stackValueList,
                )
            }
            KeyboardKeyType.OnClickExcKey -> {
                calculator.exchange()
                uiState = uiState.copy(
                    listValue = calculator.stackValueList,
                )
            }
            else -> {
                event.text.let {
                    uiState = uiState.copy(
                        inputNumericText = (uiState.inputNumericText + it).takeLast(8)
                    )
                }
            }
        }
    }
}
