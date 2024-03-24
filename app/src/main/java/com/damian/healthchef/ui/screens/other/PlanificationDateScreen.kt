package com.damian.healthchef.ui.screens.other

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.damian.healthchef.ui.navigation.BottomAppBarContent
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate

@Composable
fun PlanificationDateScreen(
    onContinueHomeScreen: () -> Unit,
    onContinueRecipeScreen: () -> Unit,
    onContinueUplooadRecipeScreen: () -> Unit,
    onContinuePlanificactionDateScreen: () -> Unit,
    onContinueUserFeedScreen: () -> Unit
) {

    val currentDayOfWeek = remember { Calendar.getInstance().get(Calendar.DAY_OF_WEEK) }
    val currentDay = remember { Calendar.getInstance().get(Calendar.DAY_OF_MONTH) }

    Scaffold(
        topBar = {
            TopAppBarPlanificationDate()
        },
        bottomBar = {
            BottomAppBarContent(
                onContinueHomeScreen,
                onContinueRecipeScreen,
                onContinueUplooadRecipeScreen,
                onContinuePlanificactionDateScreen,
                onContinueUserFeedScreen
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FunctionalCalendar()

            // Título con el día actual
            Text(
                text = "Recetas para hoy: ${SimpleDateFormat("EEEE, d MMMM", Locale("es", "ES")).format(Calendar.getInstance().time)}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            // Tarjetas para las recetas de cada día de la semana
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(5) { index ->
                    val dayOfWeek = (currentDayOfWeek + index) % 7 // ciclo de días de la semana
                    val dayRecipes = getDayRecipes(dayOfWeek) // función ficticia para obtener las recetas

                    DayRecipeCard(
                        dayOfWeek = dayOfWeek,
                        dayRecipes = dayRecipes,
                        onClick = { /* Acción al hacer clic en la tarjeta */ }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarPlanificationDate(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = "Planificación") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
        navigationIcon = {  },
        actions = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Busqueda"
            )
        }
    )
}

@Composable
fun FunctionalCalendar() {
    val currentDate = remember { mutableStateOf(Calendar.getInstance()) }
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }

    DisposableEffect(Unit) {
        val timer = Timer()
        timer.scheduleAtFixedRate(1000, 60000) {
            selectedDate = Calendar.getInstance()
        }
        onDispose {
            timer.cancel()
        }
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .height(120.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val monthFormat = SimpleDateFormat("MMMM yyyy", Locale("es", "ES"))
            val monthString = rememberUpdatedState(monthFormat.format(selectedDate.time).capitalize(Locale.getDefault()))

            IconButton(onClick = {
                selectedDate.add(Calendar.MONTH, -1)
                if (selectedDate.before(currentDate.value)) {
                    selectedDate = currentDate.value.clone() as Calendar
                }
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowLeft,
                    contentDescription = "Decrease Month",
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = monthString.value,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )

            IconButton(onClick = {
                selectedDate.add(Calendar.MONTH, 1)
                if (selectedDate.get(Calendar.MONTH) != currentDate.value.get(Calendar.MONTH)) {
                    selectedDate = Calendar.getInstance().clone() as Calendar
                }
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                    contentDescription = "Increase Month",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        CalendarGrid(currentDate.value, selectedDate) { day ->
            selectedDate = day
        }
    }
}

@Composable
fun CalendarGrid(
    currentDate: Calendar,
    selectedDate: Calendar,
    onDateSelected: (Calendar) -> Unit
) {
    val startOfMonth = Calendar.getInstance()
    startOfMonth.time = selectedDate.time
    startOfMonth.set(Calendar.DAY_OF_MONTH, 1)
    val daysInMonth = startOfMonth.getActualMaximum(Calendar.DAY_OF_MONTH)

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(daysInMonth) { day ->
            val currentDay = startOfMonth.clone() as Calendar
            currentDay.set(Calendar.DAY_OF_MONTH, day + 1)
            if (!currentDay.before(currentDate)) {
                CalendarDay(
                    day = currentDay,
                    isSelected = currentDay == selectedDate,
                    onDateSelected = { onDateSelected(it) }
                )
            }
        }
    }
}

@Composable
fun CalendarDay(
    day: Calendar,
    isSelected: Boolean,
    onDateSelected: (Calendar) -> Unit
) {

    val selected = rememberUpdatedState(isSelected)

    Box(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .size(60.dp)
            .background(
                if (selected.value) Color.LightGray else Color.Transparent,
                shape = CircleShape
            )
            .clickable { onDateSelected(day) },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = day.get(Calendar.DAY_OF_MONTH).toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
            )

            Text(
                text = SimpleDateFormat("E", Locale("es", "ES")).format(day.time),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun DayRecipeCard(
    dayOfWeek: Int,
    dayRecipes: DayRecipe,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = SimpleDateFormat("EEEE", Locale("es", "ES")).format(dayRecipes.date),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            dayRecipes.recipes.forEach { recipe ->
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

data class Recipe(val title: String, val description: String)
data class DayRecipe(val date: Date, val recipes: List<Recipe>)
// Función ficticia para obtener las recetas de un día de la semana
fun getDayRecipes(dayOfWeek: Int): DayRecipe {
    val recipes = listOf(
        Recipe("Receta 1", "Descripción de la receta 1"),
        Recipe("Receta 2", "Descripción de la receta 2"),
        Recipe("Receta 3", "Descripción de la receta 3")
    )
    return DayRecipe(Calendar.getInstance().time, recipes)
}

// Función para obtener la fecha de un día de la semana dado
fun getDateForDayOfWeek(dayOfWeek: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek)
    return calendar.time
}