package com.example.koindemo.marvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.koindemo.constants.Result
import com.example.koindemo.ui.theme.KoinDemoTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MarvelScreen()
                }
            }
        }
    }
}

@Composable
fun MarvelScreen(viewModel: MarvelViewModel = getViewModel()) {
    val marvelCharactersResult by viewModel.marvelCharacters.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (marvelCharactersResult) {
            is Result.Loading -> {
                // Show loading indicator
//                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is Result.Success -> {
                // Display Marvel characters in a list
                MarvelList((marvelCharactersResult as Result.Success).data)
            }

            is Result.Error -> {
                // Handle error state, you can show an error message or retry button
                Text(text = "Failed to fetch Marvel characters", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun MarvelList(characters: List<MarvelCharacter>) {
    LazyColumn {
        items(characters) { character ->
            // Display each Marvel character in the list
            CharacterItem(character)
        }
    }
}

@Composable
fun CharacterItem(character: MarvelCharacter) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(character.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = character.name ?: "", fontWeight = FontWeight.Bold)
    }
}

