package com.app.rusmammals.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.rusmammals.theme.MammalColors

@Composable
fun MammalTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    label: String? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        minLines = minLines,
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        colors = getColors(),
        isError = isError,
        label = if (label != null) {
            { Text(text = label) }
        } else null
    )
}

@Preview
@Composable
private fun PreviewMammalTextField() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        MammalTextField(value = "text", onValueChange = {})
    }
}

@Composable
private fun getColors() = TextFieldDefaults.colors(
    focusedContainerColor = MaterialTheme.colorScheme.background,
    unfocusedContainerColor = MaterialTheme.colorScheme.background,
    errorContainerColor = MaterialTheme.colorScheme.background,
    unfocusedIndicatorColor = MammalColors.color1,
    focusedIndicatorColor = MammalColors.color1,
    errorIndicatorColor = MaterialTheme.colorScheme.error
)