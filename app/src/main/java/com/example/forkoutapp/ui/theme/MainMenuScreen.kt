package com.example.forkoutapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forkoutapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun MainMenuScreen(
    onLogoutClick: () -> Unit,
    onTrainingScheduleClick: () -> Unit,
    onFindRecipeClick: () -> Unit,
    onTipsTrickClick: () -> Unit,
    onSubscriptionClick: () -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    val userId = auth.currentUser?.uid
    var username by remember { mutableStateOf("Loading...") }
    var email by remember { mutableStateOf("Loading...") }

    LaunchedEffect(userId) {
        if (userId != null) {
            FirebaseFirestore.getInstance().collection("users").document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        username = document.getString("username") ?: "N/A"
                        email = document.getString("email") ?: "N/A"
                    }
                }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Profile Section
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_round),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(63.dp)
                    .clip(RoundedCornerShape(50))
            )
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(username, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(email, fontWeight = FontWeight.SemiBold, color = Color.Gray)
                Text("Mau latihan apa hari ini?", fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* Handle notification click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_stat_notifications),
                    contentDescription = "Notifications"
                )
            }
        }
        Spacer(modifier = Modifier.height(68.dp))

        // Menu Buttons
        Button(
            onClick = onTrainingScheduleClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
                .padding(bottom = 8.dp)
                .shadow(4.dp, shape = RoundedCornerShape(18.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.White, RoundedCornerShape(10.dp))
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_fitness_center_black_24),
                        contentDescription = "TrainingSchedule",
                        tint = Color(0xFF000000)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text("Jadwal Latihanmu", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onFindRecipeClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
                .padding(bottom = 8.dp)
                .shadow(4.dp, shape = RoundedCornerShape(18.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.White, RoundedCornerShape(10.dp))
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_restaurant_black_24),
                        contentDescription = "FindRecipe",
                        tint = Color(0xFF000000)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text("Temukan Resep Anda", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onTipsTrickClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
                .padding(bottom = 8.dp)
                .shadow(4.dp, shape = RoundedCornerShape(18.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.White, RoundedCornerShape(10.dp))
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_wb_sunny_black_24),
                        contentDescription = "TipsTrick",
                        tint = Color(0xFF000000),
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text("Tips & Trick", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(105.dp))

        // Logout Button and Subscription Text
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onLogoutClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Log Out", color = Color.White)
            }


            Spacer(modifier = Modifier.height(4.dp))

            TextButton(onClick = { onSubscriptionClick() }) {
                Text("Subscription Now!",
                    color = Color(0xFF536FFF)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuScreenPreview() {
    MainMenuScreen(
        onLogoutClick = {},
        onTrainingScheduleClick = {},
        onFindRecipeClick = {},
        onSubscriptionClick = {},
        onTipsTrickClick = {}
    )
}
