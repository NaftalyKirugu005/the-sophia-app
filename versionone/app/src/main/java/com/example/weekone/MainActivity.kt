package com.example.weekone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weekone.ui.theme.WEEKONETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WEEKONETheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChangeableTitleApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ChangeableTitleApp(modifier: Modifier = Modifier) {
    var title by remember { mutableStateOf("Change Me!") }
    var fontSize by remember { mutableFloatStateOf(24f) }
    var textColor by remember { mutableStateOf(Color.Black) }

    val availableColors = listOf(
        Color.Black, Color.Red, Color.Blue, Color.Green, 
        Color.Magenta, Color.DarkGray, Color(0xFFFFA500) // Orange
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Enter Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Font Size: ${fontSize.toInt()} sp")
            Slider(
                value = fontSize,
                onValueChange = { fontSize = it },
                valueRange = 10f..100f,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Pick a Color", modifier = Modifier.padding(bottom = 8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                availableColors.forEach { colorOption ->
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(colorOption, CircleShape)
                            .clickable { textColor = colorOption }
                    )
                }
            }
        }

        Text(
            text = title,
            fontSize = fontSize.sp,
            lineHeight = fontSize.sp,
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WEEKONETheme {
        ChangeableTitleApp()
    }
}
