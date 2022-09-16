package com.sgut.android.artcomposeapp.artworklists

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sgut.android.artcomposeapp.data.models.ArtworkModel

@Composable
fun ArtworkListScreen(
    navController: NavController,
    viewModel: ArtworkListScreenViewModel = hiltViewModel(),
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            SearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
//                viewModel.searchList
            }
            Spacer(modifier = Modifier.height(16.dp))
            ArtworkList(navController = navController)
        }
        
    }

}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    Box(modifier = modifier) {
        BasicTextField(value = text, 
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)

            )
        }
    }

}

@Composable
fun ArtworkEntry(
    entry: ArtworkModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ArtworkListScreenViewModel = hiltViewModel(),
) {
    Box(
        contentAlignment = Center, 
        modifier = modifier
            .padding(8.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Blue,
                        Color.Cyan,
                    )
                )
            )
            .clickable {
                navController.navigate(
                    "artwork_detail_screen"
                )
            }
    ) {
        Column() {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(entry.getOtherImgUrl())
                    .size(125)
                    .build()
            )
            Image(painter = painter,
                contentDescription = entry.title,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            )

        }
        entry.artistDisplay?.let {
            Text(
                text = it,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            entry.mediumDisplay?.let { it1 ->
                Text(
                    text = it1,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        
    }
}

@Composable
fun ArtworkRow(
    rowIndex: Int,
    entries: List<ArtworkModel>,
    navController: NavController
    
) {
    Column() {
        Row() {
            ArtworkEntry(entry = entries[rowIndex], 
                navController = navController,
            modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            
            if (entries.size>=rowIndex*2+2) {
                ArtworkEntry(entry = entries[rowIndex*2+1],
                    navController = navController,
                modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

    }
    
}

@Composable
fun ArtworkList(
    navController: NavController,
    viewModel: ArtworkListScreenViewModel = hiltViewModel(),
) {
    val artworkList by remember {viewModel.artworkList}
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }
//    val isSearching by remember {viewModel.isSearching}
    
    LazyColumn(contentPadding = PaddingValues(16.dp) ) {
        val itemCount = if (artworkList.size % 2 == 0) {
            artworkList.size /2
        } else {
            artworkList.size / 2 +1
        }
        items(itemCount) {
           ArtworkRow(rowIndex = it, entries = artworkList, navController = navController)
        }
    }
    Box(
        contentAlignment = Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if(isLoading){
            CircularProgressIndicator(color = Color.DarkGray)
        }
//        if(loadError.isNotEmpty()){
//            viewModel.loadArtwork()
//        }
    }
    
}

