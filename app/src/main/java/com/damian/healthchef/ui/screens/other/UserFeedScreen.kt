package com.damian.healthchef.ui.screens.other

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.damian.healthchef.ui.components.ButtonLogOut
import com.damian.healthchef.ui.navigation.BottomAppBarContent

@Composable
fun UserFeedScreen(
    onContinueHomeScreen: () -> Unit,
    onContinueRecipeScreen: () -> Unit,
    onContinueUplooadRecipeScreen: () -> Unit,
    onContinuePlanificactionDateScreen: () -> Unit,
    onContinueUserFeedScreen: () -> Unit,
    onLogOut: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBarUserFeed()
        },
        bottomBar = {
            BottomAppBarContent(
                onContinueHomeScreen,
                onContinueRecipeScreen,
                onContinueUplooadRecipeScreen,
                onContinuePlanificactionDateScreen,
                onContinueUserFeedScreen
            )        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonLogOut(onLogOut = onLogOut)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarUserFeed(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = "User") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
        navigationIcon = {  },
        actions = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Busqueda"
            )
        }
    )
}