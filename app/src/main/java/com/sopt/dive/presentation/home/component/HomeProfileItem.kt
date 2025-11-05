package com.sopt.dive.presentation.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.sopt.dive.R
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.util.noRippleClickable
import com.sopt.dive.presentation.home.type.ProfileStatus
import com.sopt.dive.presentation.home.type.ProfileTrailingType

@Composable
fun HomeProfileItem(
    profileUrl: String,
    name: String,
    description: String?,
    profileStatus: ProfileStatus,
    trailingType: ProfileTrailingType?,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = profileUrl,
            contentDescription = null,
            modifier = modifier
                .padding(end = 10.dp)
                .size(56.dp)
                .clip(CircleShape),
        )

        ProfileInfo(
            name = name,
            profileStatus = profileStatus,
            description = description,
        )

        Spacer(modifier = Modifier.weight(1f))

        trailingType?.let { type ->
            when (type) {
                is ProfileTrailingType.MusicButton -> {
                    TrailingButton(
                        title = type.text,
                        borderColor = Color.Green,
                        icon = R.drawable.ic_play_24,
                    )
                }

                ProfileTrailingType.GiftButton -> {
                    TrailingButton(
                        title = "선물하기",
                        borderColor = Color.Red,
                        icon = R.drawable.ic_gift_24,
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileInfo(
    name: String,
    profileStatus: ProfileStatus,
    modifier: Modifier = Modifier,
    description: String? = null,
) {
    val statusIcon = when (profileStatus) {
        ProfileStatus.NONE -> null
        ProfileStatus.BIRTHDAY -> R.drawable.ic_cake_24
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Text(
                text = name,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )

            statusIcon?.let { res ->
                Icon(
                    imageVector = ImageVector.vectorResource(res),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }
        }

        description?.let { text ->
            Text(
                text = text,
                color = Color.Gray,
                fontSize = 12.sp,
            )
        }
    }
}

@Composable
private fun TrailingButton(
    title: String,
    borderColor: Color,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(50.dp),
            )
            .noRippleClickable(onClick = onClick)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            text = title,
            color = Color.DarkGray,
            fontSize = 12.sp,
        )

        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            modifier = Modifier
                .size(14.dp),
            tint = borderColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeProfileListItemPreview() {
    DiveTheme {
        HomeProfileItem(
            profileUrl = "",
            name = "공승준",
            description = "안녕하세요 공승준입니다",
            profileStatus = ProfileStatus.NONE,
            trailingType = ProfileTrailingType.GiftButton,
        )
    }
}
