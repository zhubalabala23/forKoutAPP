import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun LatihanTanganScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        // Header Image
        Image(
            painter = painterResource(id = R.drawable.dumbell_image),
            contentDescription = "Latihan Tangan Pemula",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Latihan Tangan Pemula",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Training Details
        Text(
            text = "20 menit - 19 latihan",
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
        TrainingVideoItem("Angkat Lengan Ke Samping", "<iframe width=\"310\" height=\"320\" src=\"https://www.youtube.com/embed/nLnsXGLgrcw?si=gdXonEBlj4icF66w\" title=\"YouTube video player\" frameborder=\"1\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>")
        TrainingVideoItem("Diagonal Plank x10", "<iframe width=\"310\" height=\"320\" src=\"https://www.youtube.com/embed/rLuSzj4l2GQ?si=-zoZ8R2DQW5NVIiP\" title=\"YouTube video player\" frameborder=\"1\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>")
        TrainingVideoItem("Loncat Bintang", "<iframe width=\"310\" height=\"320\" src=\"https://www.youtube.com/embed/nnC7FWBM8ZI?si=Hcdfyvk0dROJHrRF\" title=\"YouTube video player\" frameborder=\"1\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>")
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun TrainingVideoItem(title: String, videoHtml: String) {
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
                    .aspectRatio(16f / 9)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                AndroidView(
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            webViewClient = WebViewClient()
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
fun LatihanTanganScreenPreview() {
    val navController = rememberNavController()
    LatihanTanganScreen(navController)
}
