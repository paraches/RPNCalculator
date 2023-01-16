package com.paraches.android.rpncalculator

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paraches.android.rpncalculator.ui.theme.RPNCalculatorTheme

@Composable
fun CalculatorInputScreen(
    modifier: Modifier = Modifier,
    calculatorViewModel: CalculatorViewModel = viewModel()
) {
    Row(
        modifier = modifier
    ) {
        var value by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            modifier = Modifier.weight(2f),
            value = value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            onValueChange = { value = it },
            label = { Text(text = "Input")}
        )

        Button(
            modifier = Modifier
                .padding(10.dp)
                .weight(1f),
            onClick = {
                value.toIntOrNull()?.let {
                    value = ""
                    calculatorViewModel.push(it)
                }
            }
        ) {
            Text(text = "Enter")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorInputScreenPreview(
    calculatorViewModel: CalculatorViewModel = viewModel()
) {
    RPNCalculatorTheme {
        CalculatorInputScreen(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            calculatorViewModel = calculatorViewModel
        )
    }
}
