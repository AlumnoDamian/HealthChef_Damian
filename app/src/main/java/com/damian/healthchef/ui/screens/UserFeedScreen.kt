package com.damian.healthchef.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.damian.healthchef.data.model.Recipe
import com.damian.healthchef.ui.components.ButtonLogOut
import com.damian.healthchef.ui.navigation.BottomBarContent
import com.damian.healthchef.viewmodel.login.SignInViewModel
import com.damian.healthchef.viewmodel.recipe.RecipeViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun UserFeedScreen(
    navController: NavController,
    signInViewModel: SignInViewModel,
    recipeViewModel: RecipeViewModel,
    onLogOut: () -> Unit
) {
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser = firebaseAuth.currentUser
    val name = currentUser?.displayName ?: "Anonymous"

    val favoriteRecipes by recipeViewModel.listRecipe.collectAsState(initial = emptyList())

    Scaffold(
        bottomBar = { BottomBarContent(navController = navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                CardUser(
                    onLogOut = onLogOut,
                    name = name
                )
            }
            items(favoriteRecipes) { recipeItem ->
                if (recipeItem.isFavorite) {
                    CardRecipeFavorite(
                        recipe = recipeItem,
                        onRecipeDetailsClick = { navController.navigate(
                            "detallesReceta/${recipeItem.id}/${recipeItem.nombre}/${recipeItem.descripcion}/${recipeItem.ingredientes}/${recipeItem.instrucciones}/${recipeItem.tiempoDePreparacion}/${recipeItem.calorias}/${recipeItem.grasas}/${recipeItem.proteinas}"
                        ) },
                        onFavoriteRecipeClick = { recipeViewModel.toggleFavorite(recipeItem) }
                    )
                }
            }

        }
    }
}

@Composable
fun CardUser(
    name: String,
    onLogOut: () -> Unit
){
    Card(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = Icons.Outlined.Person, // Reemplaza esto con el recurso de la imagen
                contentDescription = null, // Añade una descripción adecuada si la imagen es relevante para la accesibilidad
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        Row {
            ButtonLogOut(onLogOut = onLogOut)
        }
    }
}

@Composable
fun CardRecipeFavorite(
    recipe: Recipe,
    onRecipeDetailsClick: () -> Unit,
    onFavoriteRecipeClick: () -> Unit
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
                    IconButton(onClick = onFavoriteRecipeClick) {
                        Icon(
                            imageVector = if (recipe.isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorito"
                        )
                    }
                }
            }
        }

    }
}