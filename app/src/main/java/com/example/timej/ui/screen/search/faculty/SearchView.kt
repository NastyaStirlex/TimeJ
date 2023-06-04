package com.example.timej.ui.screen.search.view

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.timej.R
import com.example.timej.ui.theme.Raven
import com.example.timej.view.CustomTextField

@Composable
fun SearchView(state: State<String>, onValueChange: (String) -> Unit, placeholderText: Int) {
    CustomTextField(
        value = state.value,
        onValueChange = onValueChange,
        placeholderText = stringResource(id = placeholderText),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        trailingIcon = {
            if (state.value != "") {
                IconButton(
                    onClick = { onValueChange("") }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null,
                        tint = Raven,
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    tint = Raven,
                    modifier = Modifier
                        .size(19.dp, 19.dp)
                )
            }
        },
        modifier = Modifier
            .width(335.dp)
    )
}