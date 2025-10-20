package com.sopt.dive.core.designsystem.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.util.noRippleClickable

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    textColor: Color = Color.Black,
    placeholderColor: Color = Color.LightGray,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    val passwordVisualTransformation = remember { PasswordVisualTransformation() }

    val (visualTransformation, passwordVisibleIcon) = if (isPasswordVisible) {
        Pair(
            VisualTransformation.None,
            R.drawable.ic_visibility_24
        )
    } else {
        Pair(
            passwordVisualTransformation,
            R.drawable.ic_visibility_off_24
        )
    }

    DiveBasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        textColor = textColor,
        placeholder = placeholder,
        placeholderColor = placeholderColor,
        modifier = modifier
            .padding(
                top = 5.dp,
                bottom = 3.dp,
            ),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    imageVector = ImageVector.vectorResource(passwordVisibleIcon),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .noRippleClickable(onClick = { isPasswordVisible = !isPasswordVisible })
                        .padding(
                            end = 10.dp,
                        )
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    DiveTheme {
        PasswordTextField(
            value = "value",
            onValueChange = {},
            placeholder = "placeholder",
        )
    }
}