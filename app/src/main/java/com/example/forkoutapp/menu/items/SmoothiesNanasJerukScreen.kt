package com.example.forkoutapp.menu.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.forkoutapp.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

@Composable
fun SmoothiesNanasJerukScreen(onBackClick: () -> Unit) {
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
                text = "Menu Sehat Hari Ini",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            IconButton(onClick = { /* Handle notification click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_stat_notifications),
                    contentDescription = "Notifications"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color(0xFFF5F5F5))
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.smoothies_nanas_jeruk),
                    contentDescription = "Smoothies Nanas Jeruk",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Smoothies Nanas Jeruk",
                        color = Color(0xFF800000),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                Text(
                    text = "Bahan:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = """
                    • 1 buah nanas matang
                    • 2 buah jeruk
                    • 1 cangkir air es
                    • 1 sendok makan madu
                    • Es batu secukupnya
                    """.trimIndent(),
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Cara Pembuatan:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = """
                    1. Kupas dan potong nanas serta jeruk.
                    2. Masukkan potongan nanas dan jeruk ke dalam blender.
                    3. Tambahkan air es, madu, dan es batu sesuai selera.
                    4. Blender hingga halus dan siap disajikan.
                    """.trimIndent(),
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(13.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(Color(0xFFF5F5F5))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Menu Lainnya...",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ayam_panggang_lebanon),
                        contentDescription = "Ayam Panggang Lebanon",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .size(64.dp)
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Ayam Panggang Lebanon",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.roti_gandum_selai_kacang),
                        contentDescription = "Roti Gandum Selai Kacang",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .size(64.dp)
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Roti Gandum Selai Kacang",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SmoothiesNanasJerukScreenPreview() {
    SmoothiesNanasJerukScreen(onBackClick = {})
}
