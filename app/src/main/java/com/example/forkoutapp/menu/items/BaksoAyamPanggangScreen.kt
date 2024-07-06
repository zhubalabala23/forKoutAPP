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
fun BaksoAyamPanggangScreen(onBackClick: () -> Unit) {
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
                    painter = painterResource(id = R.drawable.bakso_ayam_panggang),
                    contentDescription = "Bakso Ayam Panggang",
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
                        text = "Bakso Ayam Panggang",
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
                    • 500 gram daging ayam cincang
                    • 1 butir telur
                    • 3 siung bawang putih, cincang halus
                    • 2 sendok makan tepung tapioka
                    • Garam dan merica secukupnya
                    • 2 sendok makan kecap manis
                    • 2 sendok makan saus tiram
                    • Minyak untuk menggoreng
                    """.trimIndent(),
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Cara Pengolahan:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = """
                    1. Campur daging ayam cincang, telur, bawang putih, tepung tapioka, garam, dan merica dalam mangkuk besar. Aduk rata.
                    2. Bentuk adonan menjadi bola-bola kecil.
                    3. Panaskan minyak dalam wajan, goreng bakso ayam hingga kecokelatan dan matang. Angkat dan tiriskan.
                    4. Panaskan kecap manis dan saus tiram dalam wajan, masukkan bakso ayam dan aduk rata hingga terbalut saus.
                    5. Panggang bakso ayam dalam oven yang telah dipanaskan pada suhu 180°C selama 10-15 menit hingga matang sempurna.
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

@Preview(showBackground = true)
@Composable
fun BaksoAyamPanggangScreenPreview() {
    BaksoAyamPanggangScreen(onBackClick = {})
}
