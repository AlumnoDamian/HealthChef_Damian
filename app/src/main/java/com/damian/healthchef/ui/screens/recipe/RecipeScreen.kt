package com.damian.healthchef.ui.screens.recipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.damian.healthchef.data.model.Recipe
import com.damian.healthchef.ui.navigation.BottomBarContent
import com.damian.healthchef.ui.navigation.SearchTopBar
import com.damian.healthchef.viewmodel.recipe.RecipeViewModel

@Composable
fun RecipeScreen(
    navController: NavController? = null,
    recipeViewModel: RecipeViewModel
) {
    // Obtener la lista de recetas desde el ViewModel
    val recipes by recipeViewModel.listRecipe.collectAsState(initial = emptyList())

    // Pantalla de la receta con barra de búsqueda en la parte superior y contenido de recetas en el cuerpo
    Scaffold(
        topBar = {
            if (navController != null) {
                SearchTopBar(navController = navController, recipeViewModel = recipeViewModel)
            }
        },
        bottomBar = {
            if (navController != null) {
                BottomBarContent(navController = navController)
            }
        }
    ) { innerPadding ->
        // Lista de recetas
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            recipes.forEach { recipeItem ->
                item {
                    // Elemento de la receta
                    RecipeItem(
                        recipe = recipeItem,
                        isFavorite = recipeItem.isFavorite,
                        onFavoriteRecipeClick = {
                            recipeViewModel.toggleFavorite(recipeItem)
                        },
                        onRecipeDetailsClick = {
                            navController?.navigate(
                                "detallesReceta/${recipeItem.id}/${recipeItem.nombre}/${recipeItem.descripcion}/${recipeItem.ingredientes}/${recipeItem.instrucciones}/${recipeItem.tiempoDePreparacion}/${recipeItem.calorias}/${recipeItem.grasas}/${recipeItem.proteinas}"
                            )
                        },
                        onEditRecipeClick = {
                            navController?.navigate(
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

@Composable
fun RecipeItem(
    recipe: Recipe,
    isFavorite: Boolean,
    onFavoriteRecipeClick: () -> Unit,
    onRecipeDetailsClick: () -> Unit,
    onEditRecipeClick: () -> Unit,
    onClickDeleteRecipe: () -> Unit
) {

    // Estado para controlar si la receta es favorita
    var isRecipeFavorite by rememberSaveable { mutableStateOf(isFavorite) }

    // Elemento de la tarjeta de receta
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
                // Nombre de la receta
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = recipe.nombre,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(14.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botones de acción: favorito, editar y eliminar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Botón favorito
                    IconButton(
                        onClick = {
                            isRecipeFavorite = !isRecipeFavorite
                            onFavoriteRecipeClick()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = if (isRecipeFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorito"
                        )
                    }

                    // Botón editar
                    IconButton(
                        onClick = onEditRecipeClick,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar"
                        )
                    }

                    // Botón eliminar
                    IconButton(
                        onClick = onClickDeleteRecipe,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar"
                        )
                    }
                }
            }
        }

    }
}