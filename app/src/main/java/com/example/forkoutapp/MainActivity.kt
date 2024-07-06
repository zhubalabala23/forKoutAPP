package com.example.forkoutapp

import LatihanTanganScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.forkoutapp.menu.items.*
import com.example.forkoutapp.training.schedule.LatihanKakiScreen
import com.example.forkoutapp.training.schedule.LatihanPerutScreen
import com.example.forkoutapp.ui.theme.ForkoutappTheme
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import com.example.forkoutapp.ui.theme.JadwalLatihanScreen
import com.example.forkoutapp.ui.theme.LoginScreen
import com.example.forkoutapp.ui.theme.MainMenuScreen
import com.example.forkoutapp.ui.theme.RegistrationScreen
import com.example.forkoutapp.ui.theme.ResepAndaScreen
import com.example.forkoutapp.ui.theme.SubscriptionScreen
import com.example.forkoutapp.ui.theme.TipsTrickScreen

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContent {
            ForkoutappTheme {
                // Set the status bar color
                window.statusBarColor = 0xFF4CAF50.toInt()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MyApp(auth)
                }
            }
        }
    }
}

@Composable
fun MyApp(auth: FirebaseAuth) {
    val navController = rememberNavController()
    val startDestination = if (auth.currentUser != null) "mainMenu" else "login"

    Scaffold(
        topBar = { TopBar() },
        modifier = Modifier.padding(
            WindowInsets.systemBars
                .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
                .asPaddingValues()
        )
    ) { innerPadding ->
        NavHost(navController, startDestination = startDestination, modifier = Modifier.padding(innerPadding)) {
            composable("register") {
                RegistrationScreen(auth = auth, onLoginClick = {
                    navController.navigate("login")
                }, onRegisterSuccess = {
                    navController.navigate("mainMenu")
                })
            }
            composable("login") {
                LoginScreen(auth = auth, onRegisterClick = {
                    navController.navigate("register")
                }, onLoginSuccess = {
                    navController.navigate("mainMenu") {
                        popUpTo("login") { inclusive = true }
                    }
                })
            }
            composable("mainMenu") {
                MainMenuScreen(
                    onSubscriptionClick = { navController.navigate("subscriptionScreen") },
                    onTrainingScheduleClick = { navController.navigate("TrainingSchedule") },
                    onFindRecipeClick = { navController.navigate("FindRecipe") },
                    onTipsTrickClick = { navController.navigate("TipsTrick") },
                    onLogoutClick = {
                        auth.signOut()
                        navController.navigate("login") {
                            popUpTo("mainMenu") { inclusive = true }
                        }
                    }
                )
            }
            composable("TrainingSchedule") {
                JadwalLatihanScreen(
                    onHomeClick = { navController.navigate("mainMenu") },
                    auth = auth,
                    onTrainingItemClick = { trainingItem ->
                        when (trainingItem) {
                            "Latihan Tangan" -> navController.navigate("latihanTangan")
                            "Latihan Perut" -> navController.navigate("latihanPerut")
                            "Latihan Kaki" -> navController.navigate("latihanKaki")
                        }
                    }
                )
            }
            composable("FindRecipe") {
                ResepAndaScreen(
                    navController = navController,
                    auth = auth,
                    onHomeClick = { navController.navigate("mainMenu") },
                )
            }
            composable("resepDetail/{resepName}") { backStackEntry ->
                val resepName = backStackEntry.arguments?.getString("resepName")
                when (resepName) {
                    "NasiMerahSayuran" -> NasiMerahSayuranScreen(onBackClick = { navController.navigate("FindRecipe") })
                    "NasiTahuTelur" -> NasiTahuTelurScreen(onBackClick = { navController.navigate("FindRecipe") })
                    "OmeletIsiSayuran" -> OmeletIsiSayuranScreen(onBackClick = { navController.navigate("FindRecipe") })
                    "BaksoAyamPanggang" -> BaksoAyamPanggangScreen(onBackClick = { navController.navigate("FindRecipe") })
                    "PancakeOatmealPisang" -> PancakeOatmealPisangScreen(onBackClick = { navController.navigate("FindRecipe") })
                    "SmoothiesNanasJeruk" -> SmoothiesNanasJerukScreen(onBackClick = { navController.navigate("FindRecipe") })
                    "AyamKecapTumis" -> AyamKecapTumisScreen(onBackClick = { navController.navigate("FindRecipe") })
                    "SmoothiesBlueberry" -> SmoothiesBlueberryScreen(onBackClick = { navController.navigate("FindRecipe") })
                    else -> Text("Unknown recipe")
                }
            }
            composable("TipsTrick") {
                TipsTrickScreen(onBackClick = { navController.navigate("mainMenu") })
            }
            composable("latihanTangan") {
                LatihanTanganScreen(navController)
            }
            composable("latihanPerut") {
                LatihanPerutScreen(navController)
            }
            composable("latihanKaki") {
                LatihanKakiScreen(navController)
            }
            composable("subscriptionScreen") {
                SubscriptionScreen(navController)
            }
        }
    }
}

@Composable
fun TopBar() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val auth = FirebaseAuth.getInstance()
    MyApp(auth)
}
