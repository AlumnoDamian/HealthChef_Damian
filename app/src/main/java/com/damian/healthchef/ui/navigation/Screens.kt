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

sealed class Screens(
    val route: String,
    val arguments: List<NamedNavArgument>? = null
){

    sealed class BottomBarScreens(
        route: String,
        val title: String,
        val icon: ImageVector? = null
    ) : Screens(route) {
        object Splash : BottomBarScreens(
            route = "splash",
            title = "Splash"
        )
        object Login : BottomBarScreens(
            route = "login",
            title = "Login"
        )
        object Recipe : BottomBarScreens(
            route = "recipe",
            title = "Recetas",
        )
        object AddRecipe : BottomBarScreens(
            route = "addrecipe",
            title = "Añadir",
            icon = Icons.Outlined.AddCircle
        )

        object Perfil : BottomBarScreens(
            route = "perfil",
            title = "Perfil",
            icon = Icons.Outlined.Person
        )

    }

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }

}