package com.damian.healthchef.ui.screens.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.CommentBank
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Send
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.damian.healthchef.R
import com.damian.healthchef.ui.components.ButtonIcons
import com.damian.healthchef.ui.navigation.BottomAppBarContent
import com.damian.healthchef.ui.viewmodel.BlogViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun BlogScreen(
    viewModel: BlogViewModel,
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                items(20) { post ->
                    PostRandom()
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


        //Imagen del usuario
        Image(
            painter = painterResource(id = image),
            contentDescription = "Imagen",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        IconsButton()


        Spacer(modifier = Modifier.padding(vertical = 8.dp))
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
fun PostRandom() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .background(Color.White)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RoundImageCard(
                image = painterResource(id = R.drawable.health_chef_logo),
                modifier = Modifier
                    .size(48.dp)
                    .padding(4.dp)
            )
            Text(text = "Username", fontWeight = FontWeight.Bold)
        }
        //Imagen del usuario
        Image(
            painter = painterResource(id = R.drawable.health_chef_logo),
            contentDescription = "Imagen",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        var favoriteCount by remember { mutableStateOf((0..1000).random()) }
        var commentCount by remember { mutableStateOf((0..100).random()) }
        var sendCount by remember { mutableStateOf((0..500).random()) }

        ButtonIcons(
            initialValueFavorite = (0..1000).random(),
            initialValueComment = (0..100).random(),
            initialValueSend = (0..500).random(),
            onFavoriteClick = { favoriteCount-- },
            onCommentClick = { commentCount-- },
            onSendClick = { sendCount-- }
        )

        Text(text = "Description", modifier = Modifier.padding(8.dp))

        Text(
            text = "42 comments",
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}

@Composable
fun RoundImageCard(
    image: Painter,
    modifier: Modifier = Modifier
        .padding(8.dp)
        .size(64.dp)
) {
    Card(shape = CircleShape, modifier = modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}