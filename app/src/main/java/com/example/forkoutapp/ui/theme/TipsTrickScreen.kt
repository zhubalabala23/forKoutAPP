package com.example.forkoutapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forkoutapp.R

@Composable
fun TipsTrickScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_black_24),
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Tips Of The Day ..",
                color = Color.Black,
                style = MaterialTheme.typography.h5,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { /* Handle notification click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_stat_notifications),
                    contentDescription = "Notifications"
                )
            }
        }
        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Temukan tips berolahragamu disini...",
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        val tips = listOf(
            Triple("Khabib Nurmagomedov", R.drawable.khabib_nurmagedov, "Find a workout partner to train with, interaction with people whilst exercising can distract the brain from the activity and help you go for longer."),
            Triple("Gareth Bale", R.drawable.gareth_bale, "Find a workout partner to train with, interaction with people whilst exercising can distract the brain from the activity and help you go for longer."),
            Triple("Tomoka Miyazaki", R.drawable.tomoka_miyazaki, "Find a workout partner to train with, interaction with people whilst exercising can distract the brain from the activity and help you go for longer."),
            Triple("Cristiano Ronaldo", R.drawable.cristiano_ronaldo, "Find a workout partner to train with, interaction with people whilst exercising can distract the brain from the activity and help you go for longer."),
            Triple("Stephen Curry", R.drawable.stephan_curry, "Find a workout partner to train with, interaction with people whilst exercising can distract the brain from the activity and help you go for longer.")
        )

        tips.forEach { (name, imageRes, description) ->
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(150.dp),
                backgroundColor = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color.LightGray, Color.DarkGray)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = imageRes),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(RoundedCornerShape(50))
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.White,

                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = description,
                                    fontSize = 12.sp,
                                    color = Color.White
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxHeight(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            TextButton(onClick = { /* Handle See Details click */ }) {
                                Text(
                                    text = "Lihat selengkapnya...",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TipsTrickScreenPreview() {
    TipsTrickScreen(onBackClick = {})
}
