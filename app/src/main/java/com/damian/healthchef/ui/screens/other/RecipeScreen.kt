package com.damian.healthchef.ui.screens.other

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.damian.healthchef.ui.navigation.BottomAppBarContent
import com.damian.healthchef.ui.viewmodel.RecipeViewModel

@Composable
fun RecipeScreen(
    onContinueHomeScreen: () -> Unit,
    onContinueRecipeScreen: () -> Unit,
    onContinueUplooadRecipeScreen: () -> Unit,
    onContinuePlanificactionDateScreen: () -> Unit,
    onContinueUserFeedScreen: () -> Unit
) {
    val recipeViewModel: RecipeViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBarRecipe()
        },
        bottomBar = {
            BottomAppBarContent(
                onContinueHomeScreen,
                onContinueRecipeScreen,
                onContinueUplooadRecipeScreen,
                onContinuePlanificactionDateScreen,
                onContinueUserFeedScreen
            ) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarRecipe(modifier: Modifier = Modifier) {
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

@Composable
fun RecipeCards(recipes: List<Recipe>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(recipes) { recipe ->
            // Muestra cada receta en una tarjeta de receta
        }
    }
}
@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .width(400.dp)
            .height(90.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen de la receta
            val imageModifier = Modifier
                .size(90.dp)
                .background(MaterialTheme.colorScheme.surface)
            Box(modifier = imageModifier) {
                // Cargar la imagen de la receta
            }

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                // Nombre de la receta
                Text(
                    text = "recipe.label",
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Rectángulo para agregar receta o mostrar detalles
                Box(
                    modifier = Modifier
                        .background(Color.LightGray, RoundedCornerShape(8.dp))
                        .clickable(onClick = onClick)
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    // Icono de detalles
                    IconButton(onClick = { /* Acción al hacer clic en el botón de ver detalles */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Detalles"
                        )
                    }
                }
            }
        }
    }
}

