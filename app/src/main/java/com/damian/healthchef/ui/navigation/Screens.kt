package com.damian.healthchef.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.damian.healthchef.R

// Clase sellada para definir las pantallas de navegación
sealed class Screens(
    val route: String, // Ruta de la pantalla
    val arguments: List<NamedNavArgument>? = null // Argumentos opcionales de la pantalla
){

    // Clases internas para las pantallas del menú inferior
    sealed class BottomBarScreens(
        route: String, // Ruta de la pantalla
        val title: String, // Título de la pantalla
        val icon: ImageVector? = null // Icono de la pantalla (opcional)
    ) : Screens(route) {
        object Splash : BottomBarScreens(
            route = "splash", // Ruta de la pantalla Splash
            title = "Splash" // Título de la pantalla Splash
        )
        object Login : BottomBarScreens(
            route = "login", // Ruta de la pantalla de inicio de sesión
            title = "Login" // Título de la pantalla de inicio de sesión
        )
        object Recipe : BottomBarScreens(
            route = "recipe", // Ruta de la pantalla de recetas
            title = "Recetas", // Título de la pantalla de recetas
        )
        object AddRecipe : BottomBarScreens(
            route = "addrecipe", // Ruta de la pantalla para agregar recetas
            title = "Añadir", // Título de la pantalla para agregar recetas
            icon = Icons.Outlined.AddCircle // Icono de la pantalla para agregar recetas
        )

        object Perfil : BottomBarScreens(
            route = "perfil", // Ruta de la pantalla de perfil
            title = "Perfil", // Título de la pantalla de perfil
            icon = Icons.Outlined.Person // Icono de la pantalla de perfil
        )

    }

}