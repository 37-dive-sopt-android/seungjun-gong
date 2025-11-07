package com.sopt.dive.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.core.compositionlocal.LocalInnerPadding
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.presentation.home.component.HomeProfileItem
import com.sopt.dive.presentation.home.model.ProfileItemUiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadProfiles()
    }

    HomeScreen(
        profileList = uiState.profiles,
    )
}

@Composable
private fun HomeScreen(
    profileList: ImmutableList<ProfileItemUiState>,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val innerPadding = LocalInnerPadding.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp),
        contentPadding = innerPadding,
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        item {
            Text(
                text = stringResource(R.string.home),
                modifier = Modifier
                    .padding(bottom = 20.dp),
                fontSize = 24.sp,
            )
        }
        items(profileList) {
            HomeProfileItem(
                profileUrl = it.profileUrl,
                name = it.name,
                description = it.description,
                profileStatus = it.profileStatus,
                trailingType = it.trailingType,
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    DiveTheme {
        HomeScreen(
            profileList = persistentListOf(),
        )
    }
}
