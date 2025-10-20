package com.sopt.dive.core.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.util.NoRippleInteractionSource

@Composable
fun DiveButton(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(10.dp),
    shape: Shape = RoundedCornerShape(30.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    textStyle: TextStyle = TextStyle.Default,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding,
        interactionSource = NoRippleInteractionSource,
    ) {
        Text(
            text = buttonText,
            style = textStyle,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DiveButtonPreview() {
    DiveTheme {
        DiveButton(
            buttonText = "Welcome To SOPT",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}