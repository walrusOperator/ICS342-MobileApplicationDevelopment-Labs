package com.ics342.labs

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ics342.labs.ui.theme.LabsTheme
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsonData = loadData(resources)
        val data = dataFromJsonString(jsonData)
        setContent {
            LabsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    /*
                    Display the items from the Json file in a LazyColumn
                     */
                    LazyColumn {
                        for(person in data){
                            item {
                                personView(data = person)
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
private fun personView(data : PersonalDetails) {
    Column {
        Text(
            text = "ID: ${data.id}",
            style = TextStyle(fontSize = 20.sp),
        )
        Text(
            text = "Name: ${data.firstName} ${data.lastName}",
            style = TextStyle(fontSize = 20.sp),
        )
        Text(
            text = "Age: ${data.age}",
            style = TextStyle(fontSize = 20.sp),
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

private fun loadData(resources: Resources): String {
    return resources
        .openRawResource(R.raw.data)
        .bufferedReader()
        .use { it.readText() }
}

private fun dataFromJsonString(json: String): List<PersonalDetails> {
    val moshi: Moshi = Moshi.Builder().build()
    val jsonAdapter: JsonAdapter<List<PersonalDetails>> = moshi.adapter<List<PersonalDetails>>()
    return jsonAdapter.fromJson(json)
}


