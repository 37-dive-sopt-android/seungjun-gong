package com.sopt.dive.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.dive.core.designsystem.theme.DiveTheme

@Composable
fun DiveBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle,
    textColor: Color,
    placeholder: String,
    placeholderColor: Color,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onDoneAction: () -> Unit = {},
    lineLimits: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable () -> Unit = {},
) = BasicTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    textStyle = textStyle,
    keyboardOptions = KeyboardOptions(imeAction = imeAction),
    keyboardActions = KeyboardActions(onDone = { onDoneAction() }),
    maxLines = lineLimits,
    visualTransformation = visualTransformation,
    cursorBrush = SolidColor(textColor),
) { innerTextField ->
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopStart,
    ) {
        if (value.isEmpty()) {
            Text(
                text = placeholder,
                style = textStyle,
                color = placeholderColor,
            )
        }
        innerTextField()
    }

    trailingIcon()
}

@Preview(showBackground = true)
@Composable
private fun DiveBasicTextFieldPreview() {
    DiveTheme {
        DiveBasicTextField(
            value = "value",
            onValueChange = {},
            textStyle = TextStyle.Default,
            textColor = Color.Black,
            placeholder = "placeholder",
            placeholderColor = Color.LightGray,
        )
    }
}