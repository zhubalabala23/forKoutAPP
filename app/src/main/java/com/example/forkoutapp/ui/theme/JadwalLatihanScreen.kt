package com.example.forkoutapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.size.Size
import com.example.forkoutapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun JadwalLatihanScreen(
    auth: FirebaseAuth,
    onTrainingItemClick: (String) -> Unit,
    onHomeClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    val user = auth.currentUser
    var username by remember { mutableStateOf("Loading...") }
    var email by remember { mutableStateOf("Loading...") }

    LaunchedEffect(user) {
        val db = FirebaseFirestore.getInstance()
        user?.let {
            val docRef = db.collection("users").document(it.uid)
            docRef.get().addOnSuccessListener { document ->
                if (document != null) {
                    username = document.getString("username") ?: ""
                    email = document.getString("email") ?: ""
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
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

            IconButton(onClick = { onHomeClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_home_black_24),
                    contentDescription = "Backhome"
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Training Schedule Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        ) {
            Text(
                text = "Latihan Hari Ini...",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        }

        // Training Item List
        TrainingItem(title = "Latihan Tangan", imageRes = R.drawable.dumbell_image, onTrainingItemClick)
        TrainingItem(title = "Latihan Perut", imageRes = R.drawable.abs_training_image, onTrainingItemClick)
        TrainingItem(title = "Latihan Kaki", imageRes = R.drawable.leg_training_image, onTrainingItemClick)

        Spacer(modifier = Modifier.height(28.dp))

        // Tips Section Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Tips Latihan...",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.idea),
                contentDescription = "Tips",
                tint = Color(0xFFFFAA00),
                modifier = Modifier.size(28.dp)
            )
        }

        // Tips Item
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.swimming_tips),
                    contentDescription = "Swimming Tips",
                    contentScale = ContentScale.Crop,

                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .height(130.dp)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Ketika pergi ke kolam renang umum, hanya segelintir orang yang melakukan pemanasan sebelum menceburkan diri ke kolam, padahal pemanasan sebelum berenang itu sangat penting untuk mencegah kram.",
                    fontSize = 16.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { /* Handle See Details click */ }) {
                        Text(
                            text = "See Details ...",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TrainingItem(title: String, imageRes: Int, onTrainingItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .height(100.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(title, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { onTrainingItemClick(title) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp)
                    ) {
                        Text("Mulai Sekarang", color = Color.White, fontSize = 14.sp)
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxHeight()
                    .width(150.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JadwalLatihanScreenPreview() {
    JadwalLatihanScreen(
        auth = FirebaseAuth.getInstance(),
        onTrainingItemClick = {},
        onHomeClick = {}
    )
}
