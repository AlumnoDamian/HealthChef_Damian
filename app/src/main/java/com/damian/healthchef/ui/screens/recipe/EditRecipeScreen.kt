package com.damian.healthchef.ui.screens.recipe

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.damian.healthchef.data.model.Recipe
import com.damian.healthchef.viewmodel.recipe.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarView(
    navController: NavController,
    recipeViewModel: RecipeViewModel,
    id: Int,
    nombre: String?,
    descripcion: String?,
    ingredientes: String?,
    instrucciones: String?,
    tiempoDePreparacion: String?,
    calorias: String?,
    grasas: String?,
    proteinas: String?
){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Editar receta",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        ContentEditarView(
            it,
            navController,
            recipeViewModel,
            id,
            nombre,
            descripcion,
            ingredientes,
            instrucciones,
            tiempoDePreparacion,
            calorias,
            grasas,
            proteinas
        )
    }
}

@Composable
fun ContentEditarView(
    it: PaddingValues,
    navController: NavController,
    recipeViewModel: RecipeViewModel,
    id: Int,
    nombre: String?,
    descripcion: String?,
    ingredientes: String?,
    instrucciones: String?,
    tiempoDePreparacion: String?,
    calorias: String?,
    grasas: String?,
    proteinas: String?
){
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var ingredientesText by remember { mutableStateOf("") }
    var instrucciones by remember { mutableStateOf("") }
    var tiempoDePreparacion by remember { mutableStateOf("") }
    var calorias by remember { mutableStateOf("") }
    var grasas by remember { mutableStateOf("") }
    var proteinas by remember { mutableStateOf("") }

    LazyColumn (
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            OutlinedTextField(
                value = nombre ?: "",
                onValueChange = { nombre = it},
                label = { Text(text = "Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp)
            )

            OutlinedTextField(
                value = descripcion ?: "",
                onValueChange = { descripcion = it},
                label = { Text(text = "Descripcion") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp)
            )
            OutlinedTextField(
                value = ingredientesText,
                onValueChange = { ingredientesText  = it},
                label = { Text(text = "Ingredientes") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp)
            )
            OutlinedTextField(
                value = instrucciones ?: "",
                onValueChange = { instrucciones = it},
                label = { Text(text = "Instrucciones") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp)
            )
            OutlinedTextField(
                value = tiempoDePreparacion ?: "",
                onValueChange = { tiempoDePreparacion = it},
                label = { Text(text = "Tiempo de preparación") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp)
            )
            OutlinedTextField(
                value = calorias ?: "",
                onValueChange = { calorias = it},
                label = { Text(text = "Calorias") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp)
            )
            OutlinedTextField(
                value = grasas ?: "",
                onValueChange = { grasas = it},
                label = { Text(text = "Grasas") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp)
            )
            OutlinedTextField(
                value = proteinas ?: "",
                onValueChange = { proteinas = it},
                label = { Text(text = "Proteinas") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp)
            )

            Button(onClick = {
                val updateRecipe = Recipe(
                    id = id,
                    nombre = nombre!!,
                    descripcion = descripcion!!,
                    ingredientes = ingredientes!!,
                    instrucciones = instrucciones!!,
                    tiempoDePreparacion = tiempoDePreparacion!!,
                    calorias = calorias!!,
                    grasas = grasas!!,
                    proteinas = proteinas!!
                )

                recipeViewModel.updateRecipe(updateRecipe)
                navController.popBackStack()
            }) {
                Text(text = "Editar")
            }
        }

    }

}