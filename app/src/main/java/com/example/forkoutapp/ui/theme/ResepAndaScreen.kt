package com.example.forkoutapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.forkoutapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ResepAndaScreen(
    navController: NavController,
    auth: FirebaseAuth,
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
            .verticalScroll(scrollState)
            .padding(16.dp)
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
                Text("Mau menu apa hari ini?", fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onHomeClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_home_black_24),
                    contentDescription = "Backhome"
                )
            }
        }

        Spacer(modifier = Modifier.height(27.dp))
        // Menu Hari Ini Section
        Text(
            text = "Menu Hari Ini...",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFFF5F5F5),
            elevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                val menuItems = listOf(
                    Pair("Nasi Merah Sayuran", R.drawable.nasi_merah),
                    Pair("Nasi Tahu Telur", R.drawable.nasi_tahu_telur),
                    Pair("Omelet Isi Sayuran", R.drawable.omelet_isi_sayuran),
                    Pair("Bakso Ayam Panggang", R.drawable.bakso_ayam_panggang),
                    Pair("Pancake Oatmeal Pisang", R.drawable.pancake_oatmeal_pisang),
                    Pair("Smoothies Nanas Jeruk", R.drawable.smoothies_nanas_jeruk),
                    Pair("Ayam Kecap Tumis", R.drawable.ayam_kecap_tumis_bombay),
                    Pair("Smoothies Blueberry", R.drawable.smoothies_blueberry)
                )

                menuItems.chunked(2).forEach { rowItems ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        rowItems.forEach { (title, imageRes) ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        val route = "resepDetail/${title.replace(" ", "")}"
                                        navController.navigate(route)
                                    }
                            ) {
                                Image(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = title,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(118.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Color.Gray)
                                )

                                Text(
                                    text = title,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        // Tips Section Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Tips Makanan...",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
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
                    painter = painterResource(id = R.drawable.avocado_image),
                    contentDescription = "Avocado Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .height(130.dp)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Alpukat mengandung serat dan karbohidrat, sehingga mengonsumsinya bisa membuatmu merasa kenyang lebih lama.",
                    fontSize = 16.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
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

@Preview(showBackground = true)
@Composable
fun ResepAndaScreenPreview() {
    ResepAndaScreen(
        navController = rememberNavController(),
        auth = FirebaseAuth.getInstance(),
        onHomeClick = {}
    )
}
