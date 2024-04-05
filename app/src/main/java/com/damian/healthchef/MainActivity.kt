package com.damian.healthchef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.damian.healthchef.data.room.RecipeDatabase
import com.damian.healthchef.ui.navigation.HealthChefAppNavigation
import com.damian.healthchef.ui.theme.HealthChefTheme
import com.damian.healthchef.viewmodel.login.SignInViewModel
import com.damian.healthchef.viewmodel.recipe.RecipeViewModel

class MainActivity : ComponentActivity() {
    private val signInViewModel: SignInViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthChefTheme {
                // Contenedor de superficie utilizando el color de fondo del tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Controlador de navegación
                    val navController: NavController = rememberNavController()

                    // Construcción de la base de datos de recetas
                    val database = Room.databaseBuilder(this, RecipeDatabase::class.java, "health_chef").build()
                    val dao = database.recipeDao()
                    // ViewModel de recetas
                    val recipeViewModel = RecipeViewModel(dao)
                    // ViewModel para el inicio de sesión
                    val signInViewModel: SignInViewModel = viewModel()

                    // Componente de navegación de la aplicación HealthChef
                    HealthChefAppNavigation(
                        navController = navController as NavHostController,
                        signInViewModel = signInViewModel,
                        recipeViewModel = recipeViewModel
                    )
                }
            }
        }
    }
}