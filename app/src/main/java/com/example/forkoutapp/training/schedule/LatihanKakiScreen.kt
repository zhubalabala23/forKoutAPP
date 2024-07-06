package com.example.forkoutapp.training.schedule

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.forkoutapp.R

@Composable
fun LatihanKakiScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        // Header Image
        Image(
            painter = painterResource(id = R.drawable.leg_training_image),
            contentDescription = "Latihan Kaki Pemula",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Latihan Kaki Pemula",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Training Details
        Text(
            text = "15 menit - 12 latihan",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Button(
            onClick = { /* Handle start training */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Mulai Sekarang", color = Color.White, fontSize = 14.sp)
        }

        // Video List
        TrainingVideoItemKaki("Squat Biasa", "<iframe width=\"310\" height=\"320\" src=\"https://www.youtube.com/embed/xqvCmoLULNY?si=e6CIZpWruHdQszEw\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>")
        TrainingVideoItemKaki("Lunges", "<iframe width=\"310\" height=\"320\" src=\"https://www.youtube.com/embed/1D_tsUSW3ZY?si=im6gA2HZzOo53Xk_\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>")
        TrainingVideoItemKaki("Step Up", "<iframe width=\"310\" height=\"320\" src=\"https://www.youtube.com/embed/wfhXnLILqdk?si=ZgBLMTJWlzLsloQB\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>")

    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun TrainingVideoItemKaki(title: String, videoHtml: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                // YouTube Video Player
                // Use a WebView to embed YouTube video
                AndroidView(
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            loadDataWithBaseURL(null, videoHtml, "text/html", "UTF-8", null)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LatihanKakiScreenPreview() {
    val navController = rememberNavController()
    LatihanPerutScreen(navController)
}
