package com.damian.healthchef.ui.screens.recipe

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.damian.healthchef.data.model.Recipe
import com.damian.healthchef.ui.components.buttons.AddRecipeButton
import com.damian.healthchef.ui.components.inputs.AddRecipeInputField
import com.damian.healthchef.ui.navigation.BottomBarContent
import com.damian.healthchef.ui.navigation.Screens
import com.damian.healthchef.viewmodel.recipe.RecipeViewModel

@Composable
fun AddRecipeScreen(navController: NavController, recipeViewModel: RecipeViewModel){
    Scaffold(
        bottomBar = { BottomBarContent(navController = navController) }
    ) {
        AddRecipeContent(it, navController, recipeViewModel)
    }
}

@Composable
fun AddRecipeContent(it: PaddingValues, navController: NavController, recipeViewModel: RecipeViewModel){
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var ingredientes by remember { mutableStateOf("") }
    var instrucciones by remember { mutableStateOf("") }
    var tiempoDePreparacion by remember { mutableStateOf("") }
    var calorias by remember { mutableStateOf("") }
    var grasas by remember { mutableStateOf("") }
    var proteinas by remember { mutableStateOf("") }
    
    val valido = remember(nombre, descripcion, ingredientes, instrucciones, tiempoDePreparacion, calorias, grasas, proteinas) {
        nombre.trim().isNotEmpty() &&
        descripcion.trim().isNotEmpty() &&
        ingredientes.trim().isNotEmpty() &&
        instrucciones.trim().isNotEmpty() &&
        tiempoDePreparacion.trim().isNotEmpty() &&
        calorias.trim().isNotEmpty() &&
        grasas.trim().isNotEmpty() &&
        proteinas.trim().isNotEmpty()
    }

    LazyColumn (
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            AddRecipeInputField(
                value = nombre ?: "Ejemplo: Ensalada César",
                onValueChange = { nombre = it },
                label = "Nombre",
                keyboardType = KeyboardType.Text
            )
            AddRecipeInputField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = "Descripción",
                keyboardType = KeyboardType.Text
            )
            AddRecipeInputField(
                value = ingredientes,
                onValueChange = { ingredientes = it },
                label = "Ingredientes",
                keyboardType = KeyboardType.Text
            )
            AddRecipeInputField(
                value = instrucciones,
                onValueChange = { instrucciones = it },
                label = "Instrucciones",
                keyboardType = KeyboardType.Text
            )
            AddRecipeInputField(
                value = tiempoDePreparacion,
                onValueChange = { tiempoDePreparacion = it },
                label = "Tiempo de preparación",
                keyboardType = KeyboardType.Text
            )
            AddRecipeInputField(
                value = calorias,
                onValueChange = { calorias = it },
                label = "Calorías",
                keyboardType = KeyboardType.Text
            )
            AddRecipeInputField(
                value = grasas,
                onValueChange = { grasas = it },
                label = "Grasas",
                keyboardType = KeyboardType.Text
            )
            AddRecipeInputField(
                value = proteinas,
                onValueChange = { proteinas = it },
                label = "Proteínas",
                keyboardType = KeyboardType.Text
            )
            AddRecipeButton(
                onClick = {
                    val listaIngredientes = ingredientes.split(",").map { it.trim() }
                    val ingredientesString = listaIngredientes.joinToString(", ")

                    val listaInstrucciones = instrucciones.split(",").map { it.trim() }
                    val instruccionesString = listaInstrucciones.joinToString(", ")

                    val insertRecipe = Recipe(
                        nombre = nombre,
                        descripcion = descripcion,
                        ingredientes = ingredientesString,
                        instrucciones = instruccionesString,
                        tiempoDePreparacion = tiempoDePreparacion,
                        calorias = calorias,
                        grasas = grasas,
                        proteinas = proteinas
                    )
                    recipeViewModel.insertRecipe(insertRecipe)
                    navController.navigate(Screens.BottomBarScreens.Recipe.route)
                },
                enabled = valido
            )
        }
    }
}