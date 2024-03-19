package com.damian.healthchef.screens.other

import android.app.AlertDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.damian.healthchef.navigation.BottomAppBarContent

@Composable
fun UserFeedScreen(
    onContinueHomeScreen: () -> Unit,
    onContinueRecipeScreen: () -> Unit,
    onContinueUplooadRecipeScreen: () -> Unit,
    onContinuePlanificactionDateScreen: () -> Unit,
    onContinueUserFeedScreen: () -> Unit,
    onLogOut: () -> Unit
) {

    val context = LocalContext.current

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
            Button(
                onClick = {
                    AlertDialog.Builder(context)
                        .setTitle("Cerrar sesión")
                        .setMessage("¿Estás seguro de que quieres cerrar sesión?")
                        .setPositiveButton("Sí") { dialog, _ ->
                            onLogOut()
                            dialog.dismiss()
                        }
                        .setNegativeButton("Cancelar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                },
                modifier = Modifier
                    .padding(10.dp)
                    .width(300.dp),
                shape = CircleShape,
            ) {
                Text(text = "Cerrar sesión")
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarUserFeed(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = "Home") },
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