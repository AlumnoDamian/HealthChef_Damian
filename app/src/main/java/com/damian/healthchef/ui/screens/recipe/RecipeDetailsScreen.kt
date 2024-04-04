@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
            ExperimentalLayoutApi::class
)

package com.damian.healthchef.ui.screens.recipe

import androidx.compose.foundation.Image
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.damian.healthchef.R
import com.damian.healthchef.data.model.Recipe
import com.damian.healthchef.viewmodel.recipe.RecipeViewModel
import com.google.common.io.Files.append

@Composable
fun RecipeDetailsScreen(
    navController: NavController,
    recipeViewModel: RecipeViewModel,
    recipeItem: Recipe
) {

    val isFavorite by rememberSaveable { mutableStateOf(recipeItem.isFavorite) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Detalles",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            item {
                RecipeDetails(
                    recipe = recipeItem,
                    isFavorite = recipeItem.isFavorite,
                    onFavoriteRecipeClick = {
                        recipeViewModel.toggleFavorite(recipeItem)
                    }
                )
            }
        }
    }
}

@Composable
fun RecipeDetails(
    recipe: Recipe,
    isFavorite: Boolean,
    onFavoriteRecipeClick: () -> Unit
) {
    var isRecipeFavorite by rememberSaveable { mutableStateOf(isFavorite) }

    Card(
        Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = recipe.nombre,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            RecipeInfo(title = "Descripción", content = recipe.descripcion)
            Text(
                text = "Ingredientes",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = buildAnnotatedString {
                    recipe.ingredientes.split(",").forEachIndexed { index, ingrediente ->
                        withStyle(style = SpanStyle(fontSize = 18.sp)) {
                            append("• ")
                            append(ingrediente.trim())
                            if (index != recipe.ingredientes.split(",").size - 1) {
                                appendLine()
                            }
                        }
                    }
                },
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 16.dp)
            )

            Text(
                text = "Intrucciones",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = buildAnnotatedString {
                    recipe.instrucciones.split(",").forEachIndexed { index, instrucciones ->
                        withStyle(style = SpanStyle(fontSize = 18.sp)) {
                            append("• ")
                            append(instrucciones.trim())
                            if (index != recipe.instrucciones.split(",").size - 1) {
                                appendLine()
                            }
                        }
                    }
                },
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 16.dp)
            )
            RecipeInfo(title = "Tiempo de preparacion", content = recipe.tiempoDePreparacion)
            RecipeInfo(title = "Calorías", content = recipe.calorias)
            RecipeInfo(title = "Grasas", content = recipe.grasas)
            RecipeInfo(title = "Proteínas", content = recipe.proteinas)
        }
    }
}

@Composable
fun RecipeInfo(title: String, content: String) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 18.sp)) {
                    append(content)
                }
            },
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

