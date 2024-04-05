package com.damian.healthchef.ui.screens.recipe

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.damian.healthchef.data.model.Recipe
import com.damian.healthchef.ui.components.inputs.EditRecipeInputField
import com.damian.healthchef.viewmodel.recipe.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditRecipeScreen(
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
    // Pantalla para editar una receta con barra superior y contenido
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
                    // Botón de flecha atrás para volver a la pantalla anterior
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        // Contenido de la pantalla de edición de receta
        EditRecipeContent(
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
fun EditRecipeContent(
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
    // Estado para el nombre de la receta
    var nombre by remember { mutableStateOf(nombre ?: "") }
    // Estado para la descripción de la receta
    var descripcion by remember { mutableStateOf(descripcion ?: "") }
    // Estado para los ingredientes de la receta
    var ingredientesText by remember { mutableStateOf(ingredientes ?: "") }
    // Estado para las instrucciones de la receta
    var instruccionesText by remember { mutableStateOf(instrucciones ?: "") }
    // Estado para el tiempo de preparación de la receta
    var tiempoDePreparacion by remember { mutableStateOf(tiempoDePreparacion ?: "") }
    // Estado para las calorías de la receta
    var calorias by remember { mutableStateOf(calorias ?: "") }
    // Estado para las grasas de la receta
    var grasas by remember { mutableStateOf(grasas ?: "") }
    // Estado para las proteínas de la receta
    var proteinas by remember { mutableStateOf(proteinas ?: "") }

    // Estado que indica si todos los campos están llenos y válidos
    val valido = remember(nombre, descripcion, ingredientesText, instruccionesText, tiempoDePreparacion, calorias, grasas, proteinas) {
        nombre.trim().isNotEmpty() &&
                descripcion.trim().isNotEmpty() &&
                ingredientesText.trim().isNotEmpty() &&
                instruccionesText.trim().isNotEmpty() &&
                tiempoDePreparacion.trim().isNotEmpty() &&
                calorias.trim().isNotEmpty() &&
                grasas.trim().isNotEmpty() &&
                proteinas.trim().isNotEmpty()
    }

    // Columna para mostrar los campos de edición de la receta
    LazyColumn (
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item{
            EditRecipeInputField(
                value = nombre ?: "Ejemplo: Ensalada César",
                onValueChange = { nombre = it },
                label = "Nombre",
                keyboardType = KeyboardType.Text
            )
            EditRecipeInputField(
                value = descripcion ?: "Ejemplo: Deliciosa ensalada César",
                onValueChange = { descripcion = it },
                label = "Descripción",
                keyboardType = KeyboardType.Text
            )
            EditRecipeInputField(
                value = ingredientesText ?: "Ejemplo: 200g de lechuga, 150g de pollo, 50g de crutones, aderezo al gusto",
                onValueChange = { ingredientesText = it },
                label = "Ingredientes",
                keyboardType = KeyboardType.Text
            )
            EditRecipeInputField(
                value = instruccionesText ?: "Ejemplo: Mezcla todos los ingredientes y sirve",
                onValueChange = { proteinas = it },
                label = "Instrucciones",
                keyboardType = KeyboardType.Text
            )
            EditRecipeInputField(
                value = tiempoDePreparacion ?: "Ejemplo: 15 minutos",
                onValueChange = { tiempoDePreparacion = it },
                label = "Tiempo de preparación",
                keyboardType = KeyboardType.Text
            )
            EditRecipeInputField(
                value = calorias ?: "Ejemplo: 300",
                onValueChange = { calorias = it },
                label = "Calorías",
                keyboardType = KeyboardType.Text
            )
            EditRecipeInputField(
                value = grasas ?: "Ejemplo: 10g",
                onValueChange = { grasas = it },
                label = "Grasas",
                keyboardType = KeyboardType.Text
            )
            EditRecipeInputField(
                value = proteinas ?: "Ejemplo: 20g",
                onValueChange = { proteinas = it },
                label = "Proteínas",
                keyboardType = KeyboardType.Text
            )

            // Botón para editar la receta, habilitado solo si todos los campos están llenos y válidos
            Button(
                onClick = {
                    val listaIngredientes = ingredientes?.split(",")?.map { it.trim() }
                    val ingredientesString = listaIngredientes?.joinToString(", ")

                    val listaInstrucciones = instrucciones?.split(",")?.map { it.trim() }
                    val instruccionesString = listaInstrucciones?.joinToString(", ")

                    val updateRecipe = Recipe(
                        id = id,
                        nombre = nombre!!,
                        descripcion = descripcion!!,
                        ingredientes = ingredientesString!!,
                        instrucciones = instruccionesString!!,
                        tiempoDePreparacion = tiempoDePreparacion!!,
                        calorias = calorias!!,
                        grasas = grasas!!,
                        proteinas = proteinas!!
                    )

                    recipeViewModel.updateRecipe(updateRecipe)
                    navController.popBackStack()
                },
                enabled = valido
            ) {
                Text(text = "Editar")
            }
        }

    }

}