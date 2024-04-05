@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
            ExperimentalLayoutApi::class
)

package com.damian.healthchef.ui.screens.recipe

import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.damian.healthchef.data.model.Recipe
import com.damian.healthchef.viewmodel.recipe.RecipeViewModel


@Composable
fun RecipeDetailsScreen(
    navController: NavController,
    recipeItem: Recipe
) {

    // Pantalla de detalles de la receta con barra superior y contenido
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detalles",
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
        // Contenido de la pantalla
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            item {
                // Detalles de la receta
                RecipeDetails(recipe = recipeItem)
            }
        }
    }
}

@Composable
fun RecipeDetails(recipe: Recipe) {

    // Elemento de la tarjeta de detalles de la receta
    Card(
        modifier = Modifier
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
            // Nombre de la receta
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
            // Descripción de la receta
            RecipeInfo(title = "Descripción", content = recipe.descripcion)
            // Ingredientes de la receta
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

            // Instrucciones de la receta
            Text(
                text = "Instrucciones",
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
            // Otros detalles de la receta
            RecipeInfo(title = "Tiempo de preparación", content = recipe.tiempoDePreparacion)
            RecipeInfo(title = "Calorías", content = recipe.calorias)
            RecipeInfo(title = "Grasas", content = recipe.grasas)
            RecipeInfo(title = "Proteínas", content = recipe.proteinas)
        }
    }
}

@Composable
fun RecipeInfo(title: String, content: String) {
    // Componente para mostrar detalles específicos de la receta
    Column(Modifier.padding(8.dp)) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = content,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}