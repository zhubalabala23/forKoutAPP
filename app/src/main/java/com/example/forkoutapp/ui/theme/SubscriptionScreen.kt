package com.example.forkoutapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.forkoutapp.R

@Composable
fun SubscriptionScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_black_24),
                    contentDescription = "Back"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* Handle notification click */ }) {
                Icon(painter = painterResource(id = R.drawable.ic_stat_notifications),
                    contentDescription = "Notifications"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Subscription",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Ayo menjadi bagian premium dari Gym forKout dan dapatkan fitur dilatih dengan personal trainer di tempat Gym terdekat.",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Subscription Feature",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF536FFF),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SubscriptionFeature(
            imageRes = R.drawable.personal_trainer,
            title = "Personal Trainer",
            description = "Realtime chat in forKout"
        )

        SubscriptionFeature(
            imageRes = R.drawable.premium_video_hd,
            title = "Premium Video",
            description = "Best Training Video Full HD"
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /* Handle subscription click */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF536FFF)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(8.dp)) // rounded button
        ) {
            Text("Subscription!", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun SubscriptionFeature(imageRes: Int, title: String, description: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable { /* Handle click */ }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(description, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubscriptionScreenPreview() {
    val navController = rememberNavController()
    SubscriptionScreen(navController)
}
