package com.ics342.labs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ics342.labs.data.DataItem
import com.ics342.labs.ui.theme.LabsTheme

private val dataItems = listOf(
    DataItem(1, "Item 1", "Description 1"),
    DataItem(2, "Item 2", "Description 2"),
    DataItem(3, "Item 3", "Description 3"),
    DataItem(4, "Item 4", "Description 4"),
    DataItem(5, "Item 5", "Description 5"),
    DataItem(6, "Item 6", "Description 6"),
    DataItem(7, "Item 7", "Description 7"),
    DataItem(8, "Item 8", "Description 8"),
    DataItem(9, "Item 9", "Description 9"),
    DataItem(10, "Item 10", "Description 10"),
    DataItem(11, "Item 11", "Description 11"),
    DataItem(12, "Item 12", "Description 12"),
    DataItem(13, "Item 13", "Description 13"),
    DataItem(14, "Item 14", "Description 14"),
    DataItem(15, "Item 15", "Description 15"),
    DataItem(16, "Item 16", "Description 16"),
    DataItem(17, "Item 17", "Description 17"),
    DataItem(18, "Item 18", "Description 18"),
    DataItem(19, "Item 19", "Description 19"),
    DataItem(20, "Item 20", "Description 20"),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsTheme {
                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    DataItemList(dataItems = dataItems)
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Start") {
                        composable(route = "Start") {
                            DataItemList(dataItems = dataItems, navController = navController)
                    }
                        composable(route = "DetailsScreen/{id}", arguments = listOf(navArgument("id") {
                            type = NavType.IntType
                            defaultValue = 0
                        })) {
                            entry -> DetailsScreenView(dataItem = dataItems[entry.arguments!!.getInt("id") -1])
                        }

                }
            }
        }
    }
}

//display id, title, and description
@Composable
fun DetailsScreenView(dataItem:DataItem) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = dataItem.id.toString(),
            style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = dataItem.name,
                style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = dataItem.description,
                style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun DataItemView(dataItem: DataItem, navController : NavController) {
    /* Create the view for the data item her. */
    Row (
        Modifier.padding(bottom = 8.dp)
            .clickable(onClick = {
                navController.navigate(route = buildString {
                    append("DetailsScreen/")
                    append(dataItem.id)
                })
            })){
        Text(text = dataItem.id.toString())
        Spacer(modifier = Modifier.size(8.dp))

        Column {
            Text(text = dataItem.name, style = TextStyle(fontWeight = Bold))
            Text(text = dataItem.description, style = TextStyle(fontSize = 12.sp))
        }
    }

}


@Composable
fun DataItemList(dataItems: List<DataItem>, navController : NavController) {
    /* Create the list here. This function will call DataItemView() */
    LazyColumn {
        for(data in dataItems){
            item{DataItemView(data, navController = navController)}
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabsTheme {
        Greeting("Android")
    }
}
