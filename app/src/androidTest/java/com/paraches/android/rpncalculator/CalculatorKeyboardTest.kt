package com.paraches.android.rpncalculator

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class CalculatorKeyboardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun test_keyboard_exist() {
        composeTestRule.setContent {
            CalculatorScreen(
                calculatorViewModel = CalculatorViewModel(Calculator())
            )
        }

        composeTestRule.onNodeWithText(DefaultInputString).assertExists()
        composeTestRule.onNodeWithText("1").assertExists()
        composeTestRule.onNodeWithText("2").assertExists()
        composeTestRule.onNodeWithText("3").assertExists()
        composeTestRule.onNodeWithText("4").assertExists()
        composeTestRule.onNodeWithText("5").assertExists()
        composeTestRule.onNodeWithText("6").assertExists()
        composeTestRule.onNodeWithText("7").assertExists()
        composeTestRule.onNodeWithText("8").assertExists()
        composeTestRule.onNodeWithText("9").assertExists()
        composeTestRule.onNodeWithText("0").assertExists()

        composeTestRule.onNodeWithText("DEL").assertExists()
        composeTestRule.onNodeWithText("CLR").assertExists()
        composeTestRule.onNodeWithText("ENT").assertExists()

        composeTestRule.onNodeWithText("+").assertExists()
        composeTestRule.onNodeWithText("-").assertExists()
        composeTestRule.onNodeWithText("*").assertExists()
        composeTestRule.onNodeWithText("/").assertExists()
    }

    @Test
    fun test_click_numeric_key() {
        composeTestRule.setContent {
            CalculatorScreen(
                calculatorViewModel = CalculatorViewModel(Calculator())
            )
        }

        composeTestRule.onNodeWithText("1").performClick()
        composeTestRule.onNodeWithText("00000001").assertExists()
        composeTestRule.onNodeWithText("2").performClick()
        composeTestRule.onNodeWithText("00000012").assertExists()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("00000123").assertExists()
        composeTestRule.onNodeWithText("4").performClick()
        composeTestRule.onNodeWithText("00001234").assertExists()
        composeTestRule.onNodeWithText("5").performClick()
        composeTestRule.onNodeWithText("00012345").assertExists()
        composeTestRule.onNodeWithText("6").performClick()
        composeTestRule.onNodeWithText("00123456").assertExists()
        composeTestRule.onNodeWithText("7").performClick()
        composeTestRule.onNodeWithText("01234567").assertExists()
        composeTestRule.onNodeWithText("8").performClick()
        composeTestRule.onNodeWithText("12345678").assertExists()
        composeTestRule.onNodeWithText("9").performClick()
        composeTestRule.onNodeWithText("23456789").assertExists()
        composeTestRule.onNodeWithText("0").performClick()
        composeTestRule.onNodeWithText("34567890").assertExists()
    }

    @Test
    fun test_enter_key() {
        composeTestRule.setContent {
            CalculatorScreen(
                calculatorViewModel = CalculatorViewModel(Calculator())
            )
        }

        composeTestRule.onNodeWithText("10").assertDoesNotExist()
        composeTestRule.onNodeWithText("2").performClick()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("ENT").performClick()
        composeTestRule.onNodeWithText("23").assertExists()
    }

}