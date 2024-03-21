package com.damian.healthchef.ui.screens.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.CommentBank
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.damian.healthchef.R
import com.damian.healthchef.ui.navigation.BottomAppBarContent
import com.damian.healthchef.ui.viewmodel.BlogViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun BlogScreen(
    viewModel: BlogViewModel,
    navigation: NavController,
    onContinueHomeScreen: () -> Unit,
    onContinueRecipeScreen: () -> Unit,
    onContinueUplooadRecipeScreen: () -> Unit,
    onContinuePlanificactionDateScreen: () -> Unit,
    onContinueUserFeedScreen: () -> Unit
) {

    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.value.isLoading)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { viewModel.loadingRefresh() }
    ) {
        Scaffold(
            topBar = {
                TopAppBarHome()
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
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
            ) {
                items(10) { index ->
                    ImagesItem(image = R.drawable.health_chef_logo, name = "Name $index")
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHome(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = "Home") },
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
fun ImagesItem(image: Int, name: String){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp) // Añade separación en todos los lados
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {

        TopBarUser(image, name)

        //Imagen del usuario
        Image(
            painter = painterResource(id = image),
            contentDescription = "Imagen",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        IconsButton()

        Description(name)

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
    }
}


@Composable
fun TopBarUser(image: Int, name: String){
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(image),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Magenta, CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text("Hace 3 minutos")
            }
        }

        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = "Tres puntos horizontal"
            )
        }
    }
}


@Composable
fun IconsButton(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = "Me gusta"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.CommentBank,
                    contentDescription = "Comentarios"
                )
            }
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Outlined.Send,
                contentDescription = "Enviar a"
            )
        }
    }
}

@Composable
fun Description(name: String){
    Text(
        text = "Les gusta a $name y a cientos de personas más",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 14.dp)
    )
    Row {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(name)
                }
                append(" Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis accumsan auctor sapien, nec fermentum quam tristique at. In sagittis diam quis massa aliquam suscipit.")

            },
            modifier = Modifier.padding(horizontal = 14.dp)
        )
    }

}