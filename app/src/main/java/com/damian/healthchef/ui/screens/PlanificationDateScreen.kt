package com.damian.healthchef.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Remove
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
import androidx.navigation.NavController
import com.damian.healthchef.ui.navigation.BottomBarContent
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate

@Composable
fun PlanificationDateScreen(
    navController: NavController
) {
    var selectedDay by remember { mutableStateOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) }

    Scaffold(
        bottomBar = { BottomBarContent(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FunctionalCalendar(onDaySelected = { day ->
                selectedDay = day
            })
            RecipeSelectorCards(selectedDay)
        }
    }
}

@Composable
fun RecipeSelectorCards(selectedDay: Int) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(5) { index ->
            val dayOfWeek = (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + index) % 7 // ciclo de días de la semana
            val typeFood = getDayType(dayOfWeek) // función ficticia para obtener el tipo de comida

            if (typeFood != null) {
                DayRecipeCard(
                    dayOfWeek = dayOfWeek,
                    typeFood = typeFood,
                    onClick = { /* Acción al hacer clic en la tarjeta */ }
                )
            }
        }
    }
}

@Composable
fun FunctionalCalendar(onDaySelected: (Int) -> Unit) {
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
                    imageVector = Icons.Filled.ArrowLeft,
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
                    imageVector = Icons.Filled.ArrowRight,
                    contentDescription = "Increase Month",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        CalendarGrid(currentDate.value, selectedDate) { day ->
            selectedDate = day
            onDaySelected(day.get(Calendar.DAY_OF_WEEK))
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
    typeFood: TypeFood?,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .width(400.dp)
            .height(90.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (typeFood != null) {
                // Imagen de la receta
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .background(Color.Gray)
                )
            }

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                // Nombre de la hora de comida
                Text(
                    text = typeFood?.type ?: "",
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Rectángulo para agregar receta o mostrar detalles
                Box(
                    modifier = Modifier
                        .background(Color.LightGray, RoundedCornerShape(8.dp))
                        .clickable(onClick = onClick)
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    if (typeFood == null) {
                        // Icono "+" para agregar receta
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Agregar receta",
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        // Nombre de la receta y botones para eliminar y ver detalles
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Nombre de la receta",
                                style = MaterialTheme.typography.labelLarge
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            // Icono "-" para eliminar receta
                            IconButton(onClick = { /* Acción al hacer clic en el botón de eliminar receta */ }) {
                                Icon(
                                    imageVector = Icons.Default.Remove,
                                    contentDescription = "Eliminar receta"
                                )
                            }

                            // Icono de detalles
                            IconButton(onClick = { /* Acción al hacer clic en el botón de ver detalles */ }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Detalles"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

data class TypeFood(val type: String)
fun getDayType(dayOfWeek: Int): TypeFood? {
    val types = listOf(
        TypeFood("Desayuno"), // Se repite para manejar el desajuste
        TypeFood("Mediodía"),
        TypeFood("Desayuno"),
        TypeFood("Mediodía"),
        TypeFood("Almuerzo"),
        TypeFood("Merienda"),
        TypeFood("Cena")
    )
    return types.getOrNull(dayOfWeek - 1)
}
