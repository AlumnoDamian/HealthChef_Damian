package com.damian.healthchef.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.damian.healthchef.ui.screens.recipe.RecipeItem
import com.damian.healthchef.viewmodel.recipe.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    navController: NavController,
    recipeViewModel: RecipeViewModel
) {
    val context = LocalContext.current
    var query by remember { mutableStateOf("") } // Estado para almacenar la consulta de búsqueda
    var active by remember { mutableStateOf(false) } // Estado para indicar si la barra de búsqueda está activa o no

    val onSearch: (String) -> Unit = {
        active = false // Cuando se realiza una búsqueda, se desactiva la barra de búsqueda
    }

    Surface(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        shape = RoundedCornerShape(0.dp) // Esquinas no redondeadas
    ) {
        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = onSearch,
            active = active,
            onActiveChange = { active = it },
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            placeholder = { Text(text = "Buscar") },
            trailingIcon = {
                if (active) {
                    IconButton(
                        onClick = {
                            query = "" // Limpiar la consulta
                            active = false // Desactivar la búsqueda
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                        )
                    }
                } else {
                    IconButton(
                        onClick = { active = true },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                        )
                    }
                }
            }
        ) {
            if (query.isNotEmpty()) {
                // Obtener los resultados de la búsqueda y almacenarlos en un estado
                val recipe =
                    recipeViewModel.searchRecipe(query).collectAsState(initial = emptyList())

                // Mostrar los resultados de la búsqueda en una lista
                LazyColumn (modifier = Modifier.background(MaterialTheme.colorScheme.secondary)) {
                    items(recipe.value) { recipeItem ->
                        RecipeItem(
                            recipe = recipeItem,
                            isFavorite = recipeItem.isFavorite,
                            onFavoriteRecipeClick = {
                                recipeViewModel.toggleFavorite(recipeItem)
                            },
                            onRecipeDetailsClick = {
                                // Navegar a la pantalla de detalles de la receta
                                navController.navigate(
                                    "detallesReceta/${recipeItem.id}/${recipeItem.nombre}/${recipeItem.descripcion}/${recipeItem.ingredientes}/${recipeItem.instrucciones}/${recipeItem.tiempoDePreparacion}/${recipeItem.calorias}/${recipeItem.grasas}/${recipeItem.proteinas}"
                                )
                            },
                            onEditRecipeClick = {
                                // Navegar a la pantalla de edición de la receta
                                navController.navigate(
                                    "editar/${recipeItem.id}/${recipeItem.nombre}/${recipeItem.descripcion}/${recipeItem.ingredientes}/${recipeItem.instrucciones}/${recipeItem.tiempoDePreparacion}/${recipeItem.calorias}/${recipeItem.grasas}/${recipeItem.proteinas}"
                                )
                            }
                        ) {
                            // Eliminar la receta
                            recipeViewModel.deleteRecipe(recipeItem)
                        }
                    }
                }
            }
        }
    }
}