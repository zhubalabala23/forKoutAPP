package com.example.forkoutapp.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun RegistrationScreen(
    auth: FirebaseAuth,
    onLoginClick: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopStart),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Daftar",
                color = Color(0xFF4CAF50),
                style = MaterialTheme.typography.h4,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Ayo jadi bagian Indonesia sehat dengan menggunakan aplikasi Forkout.",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                fontWeight = FontWeight.SemiBold,
                text = "Email"
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF4CAF50),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.Gray
                ),
                shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontWeight = FontWeight.SemiBold,
                text = "Username"
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF4CAF50),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.Gray
                ),
                shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontWeight = FontWeight.SemiBold,
                text = "Password"
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF4CAF50),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.Gray
                ),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(18.dp))
            Spacer(modifier = Modifier.weight(1f))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    if (email.isBlank() || username.isBlank() || password.isBlank()) {
                        Toast.makeText(context, "Anda harus mengisi email, username, dan password", Toast.LENGTH_SHORT).show()
                    } else {
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val userId = auth.currentUser?.uid
                                    val user = hashMapOf(
                                        "username" to username,
                                        "email" to email
                                    )
                                    FirebaseFirestore.getInstance().collection("users")
                                        .document(userId!!)
                                        .set(user)
                                        .addOnSuccessListener {
                                            onRegisterSuccess()
                                            Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                                        }
                                        .addOnFailureListener { e ->
                                            Toast.makeText(context, "Registration Failed: ${e.message}", Toast.LENGTH_LONG).show()
                                        }
                                } else {
                                    if (task.exception is FirebaseAuthUserCollisionException) {
                                        Toast.makeText(context, "Registrasion Success, please log in.", Toast.LENGTH_SHORT).show()
                                        onLoginClick()
                                    } else {
                                        Toast.makeText(context, "Registration Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
            ) {
                Text("Daftar", fontSize = 16.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = onLoginClick) {
                Text("Do you have account? ", fontSize = 14.sp, color = Color.Gray)
                Text("Login", fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun registrationScreenPreview() {
    RegistrationScreen(FirebaseAuth.getInstance(), {}, {})
}
