package com.sopt.dive.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.util.noRippleClickable
import com.sopt.dive.presentation.main.MainTab
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainBottomBar(
    tabs: ImmutableList<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(50.dp)
            )
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        tabs.forEach { tab ->
            val selectedColor =
                if (tab == currentTab) Color.Black
                else Color.Gray

            MainNavigatorBarItem(
                tab = tab,
                onClick = { onTabSelected(tab) },
                selectedColor = selectedColor,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun MainNavigatorBarItem(
    tab: MainTab,
    onClick: () -> Unit,
    selectedColor: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .noRippleClickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(tab.iconRes),
            contentDescription = stringResource(tab.label),
            modifier = Modifier
                .size(36.dp),
            tint = selectedColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainBottomBarPreview() {
    DiveTheme {
        MainBottomBar(
            tabs = MainTab.entries.toImmutableList(),
            currentTab = MainTab.HOME,
            onTabSelected = {},
        )
    }
}
