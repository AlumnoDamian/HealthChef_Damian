package com.damian.healthchef.utils

import com.damian.healthchef.data.model.Recipe
import com.damian.healthchef.viewmodel.recipe.RecipeViewModel

fun inicializarRecetas(recipeViewModel: RecipeViewModel) {
    val recetas = listOf(
        Recipe(
            nombre = "Ensalada César",
            descripcion = "Deliciosa ensalada César",
            ingredientes = "200g de lechuga, 150g de pollo, 50g de crutones, aderezo al gusto",
            instrucciones = "Mezcla todos los ingredientes y sirve",
            tiempoDePreparacion = "15 minutos",
            calorias = "300",
            grasas = "10g",
            proteinas = "20g"
        ),
        Recipe(
            nombre = "Sopa de Tomate",
            descripcion = "Sopa de tomate casera",
            ingredientes = "500g de tomates, 1 cebolla, 2 dientes de ajo, 1 litro de caldo de pollo",
            instrucciones = "Cocina todos los ingredientes y licua",
            tiempoDePreparacion = "30 minutos",
            calorias = "200",
            grasas = "5g",
            proteinas = "8g"
        ),
        Recipe(
            nombre = "Espaguetis a la Boloñesa",
            descripcion = "Deliciosos espaguetis con salsa de carne",
            ingredientes = "400g de espaguetis, 300g de carne molida, 1 lata de tomate triturado, 1 cebolla, 2 dientes de ajo",
            instrucciones = "Cocina la carne, añade el tomate y las verduras, cocina los espaguetis y sirve con la salsa",
            tiempoDePreparacion = "40 minutos",
            calorias = "400",
            grasas = "15g",
            proteinas = "25g"
        ),
        Recipe(
            nombre = "Tarta de Manzana",
            descripcion = "Postre clásico de tarta de manzana",
            ingredientes = "250g de harina, 125g de mantequilla, 4 manzanas, 150g de azúcar, canela al gusto",
            instrucciones = "Prepara la masa, corta las manzanas en rodajas, monta la tarta y hornea",
            tiempoDePreparacion = "1 hora",
            calorias = "350",
            grasas = "20g",
            proteinas = "5g"
        )
        ,
        Recipe(
            nombre = "Pasta Alfredo",
            descripcion = "Pasta cremosa con salsa Alfredo",
            ingredientes = "300g de pasta, 200ml de crema de leche, 100g de queso parmesano rallado, 50g de mantequilla",
            instrucciones = "Cocina la pasta, mezcla con la salsa Alfredo y sirve",
            tiempoDePreparacion = "20 minutos",
            calorias = "500",
            grasas = "30g",
            proteinas = "15g"
        ),
        Recipe(
            nombre = "Pollo al Curry",
            descripcion = "Pollo cocido en una salsa de curry cremosa",
            ingredientes = "500g de pechugas de pollo, 200ml de leche de coco, 2 cucharadas de pasta de curry, verduras al gusto",
            instrucciones = "Cocina el pollo con la salsa de curry y sirve con arroz",
            tiempoDePreparacion = "45 minutos",
            calorias = "450",
            grasas = "25g",
            proteinas = "30g"
        ),
        Recipe(
            nombre = "Hamburguesa Clásica",
            descripcion = "La hamburguesa más clásica con queso, lechuga y tomate",
            ingredientes = "200g de carne molida, 1 pan de hamburguesa, queso cheddar, lechuga, tomate, cebolla, kétchup, mostaza",
            instrucciones = "Cocina la carne, monta la hamburguesa y sirve con tus acompañamientos favoritos",
            tiempoDePreparacion = "25 minutos",
            calorias = "600",
            grasas = "35g",
            proteinas = "25g"
        ),
        Recipe(
            nombre = "Pancakes de Arándanos",
            descripcion = "Pancakes esponjosos con arándanos frescos",
            ingredientes = "200g de harina, 2 huevos, 150ml de leche, 100g de arándanos frescos, 2 cucharadas de azúcar",
            instrucciones = "Prepara la masa, añade los arándanos y cocina en un sartén caliente",
            tiempoDePreparacion = "30 minutos",
            calorias = "400",
            grasas = "15g",
            proteinas = "10g"
        ),
        Recipe(
            nombre = "Ensalada de Frutas",
            descripcion = "Ensalada refrescante con una variedad de frutas",
            ingredientes = "Plátanos, manzanas, fresas, uvas, melón, miel",
            instrucciones = "Corta las frutas en trozos, mezcla y sirve con un chorrito de miel",
            tiempoDePreparacion = "15 minutos",
            calorias = "200",
            grasas = "2g",
            proteinas = "3g"
        ),
        Recipe(
            nombre = "Pasta Primavera",
            descripcion = "Pasta llena de verduras frescas de primavera",
            ingredientes = "400g de pasta, 1 calabacín, 1 zanahoria, 1 pimiento rojo, 1 pimiento amarillo, 100g de guisantes, 2 cucharadas de aceite de oliva, sal y pimienta al gusto",
            instrucciones = "Cocina la pasta y saltea las verduras en aceite de oliva, mezcla todo y sirve",
            tiempoDePreparacion = "25 minutos",
            calorias = "350",
            grasas = "10g",
            proteinas = "8g"
        )
        // Agrega aquí las otras recetas...
    )
}