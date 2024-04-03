package com.damian.healthchef.ui.screens.recipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.damian.healthchef.data.model.Recipe
import com.damian.healthchef.ui.navigation.BottomBarContent
import com.damian.healthchef.ui.navigation.Screens
import com.damian.healthchef.ui.navigation.SearchTopBar
import com.damian.healthchef.utils.inicializarRecetas
import com.damian.healthchef.viewmodel.recipe.RecipeViewModel
import java.util.Locale.Category

@Composable
fun RecipeScreen(
    navController: NavController,
    recipeViewModel: RecipeViewModel
) {
    val recipes by recipeViewModel.listRecipe.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { SearchTopBar(navController = navController, recipeViewModel = recipeViewModel) },
        bottomBar = { BottomBarContent(navController = navController) }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            recipes.forEach { recipeItem ->
                item {
                    RecipeItem(
                        recipe = recipeItem,
                        onRecipeDetailsClick = {
                            navController.navigate(
                                "detallesReceta/${recipeItem.id}/${recipeItem.nombre}/${recipeItem.descripcion}/${recipeItem.ingredientes}/${recipeItem.instrucciones}/${recipeItem.tiempoDePreparacion}/${recipeItem.calorias}/${recipeItem.grasas}/${recipeItem.proteinas}"
                            )
                        },
                        onEditRecipeClick = {
                            navController.navigate(
                                "editar/${recipeItem.id}/${recipeItem.nombre}/${recipeItem.descripcion}/${recipeItem.ingredientes}/${recipeItem.instrucciones}/${recipeItem.tiempoDePreparacion}/${recipeItem.calorias}/${recipeItem.grasas}/${recipeItem.proteinas}"
                            )
                        }
                    ) {
                        recipeViewModel.deleteRecipe(recipeItem)
                    }
                }
            }
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
        modifier = modifier
    )
}

@Composable
fun RecipeItem(
    recipe: Recipe,
    onRecipeDetailsClick: () -> Unit,
    onEditRecipeClick: () -> Unit,
    onClickDeleteRecipe: () -> Unit
) {
    Card(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = onRecipeDetailsClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Nombre: ${recipe.nombre}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = onEditRecipeClick) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = onClickDeleteRecipe) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
                    }
                }
            }
        }

    }
}