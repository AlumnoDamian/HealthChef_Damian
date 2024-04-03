package com.damian.healthchef.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.damian.healthchef.data.model.Recipe
import com.damian.healthchef.ui.screens.LoginScreen
import com.damian.healthchef.ui.screens.UserFeedScreen
import com.damian.healthchef.ui.screens.recipe.AddRecipeScreen
import com.damian.healthchef.ui.screens.recipe.EditarView
import com.damian.healthchef.ui.screens.recipe.RecipeDetailsScreen
import com.damian.healthchef.ui.screens.recipe.RecipeScreen
import com.damian.healthchef.viewmodel.login.SignInViewModel
import com.damian.healthchef.viewmodel.recipe.RecipeViewModel

@Composable
fun HealthChefAppNavigation(
    navController: NavHostController = rememberNavController(),
    recipeViewModel: RecipeViewModel,
    signInViewModel: SignInViewModel
) {


    NavHost(
        navController = navController,
        startDestination = Screens.BottomBarScreens.Login.route,
    ) {
        composable(route = Screens.BottomBarScreens.Login.route) {
            LoginScreen(
                navController
            )
        }

        composable(route = Screens.BottomBarScreens.Recipe.route){
            RecipeScreen(
                navController = navController,
                recipeViewModel = recipeViewModel
            )
        }

        composable(route = Screens.BottomBarScreens.AddRecipe.route){
            AddRecipeScreen(navController, recipeViewModel)
        }

        composable(
            route = "detallesReceta/{id}/{nombre}/{descripcion}/{ingredientes}/{instrucciones}/{tiempoDePreparacion}/{calorias}/{grasas}/{proteinas}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("nombre") { type = NavType.StringType },
                navArgument("descripcion") { type = NavType.StringType },
                navArgument("ingredientes") { type = NavType.StringType },
                navArgument("instrucciones") { type = NavType.StringType },
                navArgument("tiempoDePreparacion") { type = NavType.StringType },
                navArgument("calorias") { type = NavType.StringType },
                navArgument("grasas") { type = NavType.StringType },
                navArgument("proteinas") { type = NavType.StringType }
            )
        ) {
            val ingredientes = it.arguments?.getString("ingredientes")?.split(",") ?: emptyList()
            val ingredientesString = ingredientes.joinToString(separator = ", ")

            RecipeDetailsScreen(
                navController = navController,
                recipeViewModel = recipeViewModel,
                recipeItem = Recipe(
                    id = it.arguments?.getInt("id") ?: -1,
                    nombre = it.arguments?.getString("nombre") ?: "",
                    descripcion = it.arguments?.getString("descripcion") ?: "",
                    ingredientes = ingredientesString,
                    instrucciones = it.arguments?.getString("instrucciones") ?: "",
                    tiempoDePreparacion = it.arguments?.getString("tiempoDePreparacion") ?: "",
                    calorias = it.arguments?.getString("calorias") ?: "",
                    grasas = it.arguments?.getString("grasas") ?: "",
                    proteinas = it.arguments?.getString("proteinas") ?: "",
                    isFavorite = false // Aquí puedes definir el valor por defecto para isFavorite
                )
            )
        }

        composable(
            route = "editar/{id}/{nombre}/{descripcion}/{ingredientes}/{instrucciones}/{tiempoDePreparacion}/{calorias}/{grasas}/{proteinas}",
            arguments =
                listOf(
                    navArgument("id"){ type = NavType.IntType},
                    navArgument("nombre"){ type = NavType.StringType},
                    navArgument("descripcion"){ type = NavType.StringType},
                    navArgument("ingredientes"){ type = NavType.StringType},
                    navArgument("instrucciones"){ type = NavType.StringType},
                    navArgument("tiempoDePreparacion"){ type = NavType.StringType},
                    navArgument("calorias"){ type = NavType.StringType},
                    navArgument("grasas"){ type = NavType.StringType},
                    navArgument("proteinas"){ type = NavType.StringType}
                )
        ) {
            val ingredientesString = it.arguments?.getString("ingredientes") ?: ""
            val ingredientes = ingredientesString.split(",") // Convierte la cadena a una lista

            EditarView(
                navController,
                recipeViewModel,
                it.arguments!!.getInt("id"),
                it.arguments?.getString("nombre"),
                it.arguments?.getString("descripcion"),
                ingredientes.joinToString(", "),
                it.arguments?.getString("instrucciones"),
                it.arguments?.getString("tiempoDePreparacion"),
                it.arguments?.getString("calorias"),
                it.arguments?.getString("grasas"),
                it.arguments?.getString("proteinas")
            )
        }

        composable(route = Screens.BottomBarScreens.Perfil.route){
            UserFeedScreen(
                navController = navController,
                signInViewModel = signInViewModel,
                recipeViewModel = recipeViewModel,
                onLogOut = {
                    signInViewModel.signOut {
                        navController.navigate(Screens.BottomBarScreens.Login.route)
                    }
                }
            )
        }
    }
}