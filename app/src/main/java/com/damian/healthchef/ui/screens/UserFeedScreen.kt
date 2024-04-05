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
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.damian.healthchef.data.model.Recipe
import com.damian.healthchef.ui.components.buttons.ButtonLogOut
import com.damian.healthchef.ui.navigation.BottomBarContent
import com.damian.healthchef.viewmodel.login.SignInViewModel
import com.damian.healthchef.viewmodel.recipe.RecipeViewModel

@Composable
fun UserFeedScreen(
    navController: NavController? = null,
    signInViewModel: SignInViewModel,
    recipeViewModel: RecipeViewModel,
    onLogOut: () -> Unit
) {
    // Observa los cambios en el usuario actual
    val currentUser by signInViewModel.currentUser.observeAsState()

    // Carga los datos del usuario cuando cambia el usuario actual
    LaunchedEffect(key1 = signInViewModel.currentUser.value) {
        signInViewModel.loadUserData()
    }

    // Observa los cambios en la lista de recetas favoritas
    val favoriteRecipes by recipeViewModel.listRecipe.collectAsState(initial = emptyList())

    // Pantalla principal del usuario con Scaffold
    Scaffold(
        bottomBar = {
            if (navController != null) {
                BottomBarContent(navController = navController)
            }
        }
    ) { innerPadding ->
        // Columna con desplazamiento hacia abajo para las recetas favoritas del usuario
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tarjeta de usuario
            item {
                CardUser(
                    name = currentUser ?: "Anonymous",
                    onLogOut = { signInViewModel.signOut(onLogOut) }
                )
            }

            // Encabezado para las recetas favoritas
            item {
                Text(
                    text = "---------- Mis Favoritos ----------",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
                )
            }

            // Muestra cada receta favorita en una tarjeta
            items(favoriteRecipes) { recipeItem ->
                if (recipeItem.isFavorite) {
                    RecipeFavorite(
                        recipe = recipeItem,
                        onRecipeDetailsClick = { navController?.navigate(
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
    // Tarjeta que muestra el nombre del usuario y un botón de cierre de sesión
    Card(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del usuario (actualmente un icono de persona)
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
                // Nombre del usuario
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        // Botón de cierre de sesión
        Row {
            ButtonLogOut(onLogOut = onLogOut)
        }
    }
}

@Composable
fun RecipeFavorite(
    recipe: Recipe,
    onFavoriteRecipeClick: () -> Unit,
    onRecipeDetailsClick: () -> Unit
) {
    // Tarjeta que muestra detalles de la receta y un botón para agregar o quitar de favoritos
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
                    // Nombre de la receta
                    Text(
                        text = recipe.nombre,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(14.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    // Botón para agregar o quitar de favoritos
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