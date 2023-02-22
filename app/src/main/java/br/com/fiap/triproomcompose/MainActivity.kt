package br.com.fiap.triproomcompose

import android.content.ClipData.Item
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.triproomcompose.database.repository.DestinationRepository
import br.com.fiap.triproomcompose.model.Destination
import br.com.fiap.triproomcompose.ui.theme.TripRoomComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TripRoomComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    var idState by remember {
        mutableStateOf("")
    }

    var listDestinationState by remember {
        mutableStateOf(listOf<Destination>())
    }

    val context = LocalContext.current
    val destinationRepository = DestinationRepository(context)
    
    //val destinations = destinationRepository.getAll()
    listDestinationState = destinationRepository.getAll()

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()) {
        Button(onClick = {

            val destination = Destination(
                id = 0,
                name = "Rio de Janeiro",
                country = "Brazil",
                price = 1200.0,
                description = "The most beautiful place in the World."
            )

            var id = destinationRepository.save(destination)

            listDestinationState = destinationRepository.getAll()

            Toast.makeText(context, "$id", Toast.LENGTH_SHORT).show()

        }) {
            Text(text = "Save")
        }
        Row {
            OutlinedTextField(value = idState, onValueChange = {idState = it})
            Button(onClick = {
                var id = destinationRepository.delete(Destination(id = idState.toLong()))
                listDestinationState = destinationRepository.getAll()
            }) {
                Text(text = "Delete")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()){
            items(listDestinationState) { destination ->
                DestinationCard(destination, modifier = Modifier)
            }
        }

    }
}

@Composable
private fun DestinationCard(destination: Destination, modifier: Modifier) {
    var context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                Toast
                    .makeText(context, "${destination.id}", Toast.LENGTH_SHORT)
                    .show()
            },
        backgroundColor = Color.Gray
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "${destination.id} - ${destination.name}")
            Text(text = "${destination.description}")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    TripRoomComposeTheme {
        Greeting("Android")
    }
}