package com.damian.healthchef.screens.other

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
import androidx.compose.ui.Modifier
import com.damian.healthchef.navigation.BottomAppBarContent

@Composable
fun UploadRecipeScreen(
    onContinueHomeScreen: () -> Unit,
    onContinueRecipeScreen: () -> Unit,
    onContinueUplooadRecipeScreen: () -> Unit,
    onContinuePlanificactionDateScreen: () -> Unit,
    onContinueUserFeedScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUploadRecipe()
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
        Text(
            modifier = Modifier.padding(innerPadding),
            text = "Recipe"
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarUploadRecipe(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = "Recipe") },
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