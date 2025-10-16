package com.sopt.dive.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.designsystem.theme.DiveTheme

@Composable
fun LabelTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    textColor: Color = Color.Black,
    placeholderColor: Color = Color.LightGray,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = label,
            fontSize = 24.sp,
        )

        DiveBasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            textColor = textColor,
            placeholder = placeholder,
            placeholderColor = placeholderColor,
            modifier = Modifier
                .padding(
                    top = 5.dp,
                    bottom = 3.dp,
                ),
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Black,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LabelTextFieldPreview() {
    DiveTheme {
        LabelTextField(
            label = "ID",
            value = "value",
            onValueChange = {},
            placeholder = "placeholder",
        )
    }
}