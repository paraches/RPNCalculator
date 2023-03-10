package com.paraches.android.rpncalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paraches.android.rpncalculator.ui.theme.RPNCalculatorTheme

interface CalculatorStackInterface {
    val valueList: List<Int>

    fun push(item: Int)

    fun pop(): Int

    fun clear()
}

class CalculatorStack(initialValueList: List<Int> = emptyList()): CalculatorStackInterface {
    private val _valueList = mutableStateListOf<Int>()
    override val valueList: SnapshotStateList<Int> = _valueList

    init {
        _valueList.addAll(initialValueList)
    }

    override fun push(item: Int) {
        valueList.add(0, item)
    }

    override fun pop(): Int {
        return valueList.removeFirst()
    }

    override fun clear() {
        valueList.clear()
    }
}

@Composable
fun CalculatorStackScreen(
    modifier: Modifier = Modifier,
    calculatorViewModel: CalculatorViewModel = viewModel()
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(calculatorViewModel.uiState.listValue) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 2.dp)
                    .background(Color.Cyan)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    textAlign = TextAlign.Right,
                    text = it.toString()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorStackScreenPreview() {
    val calculatorViewModel = CalculatorViewModel(
        Calculator(listOf(1, 2, 3))
    )
    RPNCalculatorTheme {
        CalculatorStackScreen(
            modifier = Modifier.padding(10.dp),
            calculatorViewModel = calculatorViewModel
        )
    }
}