package com.damian.healthchef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthChefTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavController = rememberNavController()

                    val database = Room.databaseBuilder(this, RecipeDatabase::class.java, "health_chef").build()
                    val dao = database.recipeDao()
                    val recipeViewModel = RecipeViewModel(dao)
                    val signInViewModel: SignInViewModel = viewModel()

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