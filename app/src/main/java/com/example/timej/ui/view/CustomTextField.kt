package com.example.timej.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.timej.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardActions: KeyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable() (() -> Unit),
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    val enabled = true
    val singleLine = true

    val colors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Nero,
        focusedContainerColor = White,
        unfocusedContainerColor = White,
        disabledContainerColor = White,
        cursorColor = Nero,
        focusedBorderColor = Nero,
        unfocusedBorderColor = BonJour,
        errorBorderColor = RadicalRed,
        disabledPlaceholderColor = LightGrey,
    )

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .background(White, RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(48.dp),
        interactionSource = interactionSource,
        enabled = enabled,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    ) {
        OutlinedTextFieldDefaults.DecorationBox(
            value = value,
            innerTextField = it,
            visualTransformation = visualTransformation,
            singleLine = singleLine,
            enabled = enabled,
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                start = 15.dp, top = 0.dp, bottom = 0.dp
            ),
            placeholder = {
                Text(
                    text = placeholderText,
                    style = Placeholder,
                    color = LightGrey
                )
            },
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    enabled = enabled,
                    isError = false,
                    colors = colors,
                    interactionSource = interactionSource,
                    shape = RoundedCornerShape(10.dp),
                    unfocusedBorderThickness = 1.dp,
                    focusedBorderThickness = 1.dp
                )
            },
            colors = colors,
            trailingIcon = trailingIcon
        )
    }
}