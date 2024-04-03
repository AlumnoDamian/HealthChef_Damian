package com.damian.healthchef.ui.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
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
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val onSearch: (String) -> Unit = {
        active = false
    }

    SearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = onSearch,
        active = active,
        onActiveChange = { active = it },
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(start = 4.dp, end = 4.dp),
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
            val recipe = recipeViewModel.searchRecipe(query).collectAsState(initial = emptyList())

            LazyColumn {
                items(recipe.value) { recipeItem ->
                    RecipeItem(
                        recipe = recipeItem,
                        onRecipeDetailsClick = {
                            navController.navigate(
                                "detallesReceta/${recipeItem.id}/${recipeItem.nombre}/${recipeItem.descripcion}/${recipeItem.ingredientes}/${recipeItem.instrucciones}/${recipeItem.tiempoDePreparacion}/${recipeItem.calorias}/${recipeItem.grasas}/${recipeItem.proteinas}"
                            )
                        },
                        onEditRecipeClick = {},
                        onClickDeleteRecipe = {}
                    )
                }
            }
        }
    }
}