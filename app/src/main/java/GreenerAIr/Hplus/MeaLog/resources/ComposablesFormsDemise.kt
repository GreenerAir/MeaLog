package GreenerAIr.Hplus.MeaLog.resources

import GreenerAIr.Hplus.MeaLog.demise.LogIn
import GreenerAIr.Hplus.MeaLog.demise.SignIn
import GreenerAIr.Hplus.MeaLog.mainFrags.entrance
import GreenerAIr.Hplus.MeaLog.R
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

class ComposablesFormsDemise {}

@Composable
fun LoginForm(auth: FirebaseAuth) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
            verticalArrangement = Arrangement.Center){

            ThemeAwareLogo(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primary,
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
            ){
            // Email field
                RoundedInputField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Email adress"
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Password field
                RoundedInputField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Password",
                    isPassword = true
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                FloatingActionButton(
                    onClick = { attemptLogin(email, password, auth, context = context) { message -> errorMessage = message } },
                    modifier = Modifier.width(50.dp).height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Filled.Add,"LogIn", tint = MaterialTheme.colorScheme.background)
                }

                Spacer(modifier = Modifier.height(12.dp))

                FloatingActionButton(
                    onClick = { goToSignIn(context = context) },
                    modifier = Modifier.width(50.dp).height(50.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(12.dp)

                ) {
                    Icon(Icons.Filled.ArrowForward, "LogIn", tint = MaterialTheme.colorScheme.background)
                }
            }
        }


    }
}

@Composable
fun SignInForm(auth: FirebaseAuth) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordValidation by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            ThemeAwareLogo(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Text(
                text = "Sign In",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Error message
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Username field
            RoundedInputField(
                value = username,
                onValueChange = { username = it },
                placeholder = "Username"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Email field
            RoundedInputField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password field
            RoundedInputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password Validation field
            RoundedInputField(
                value = passwordValidation,
                onValueChange = { passwordValidation = it },
                placeholder = "Confirm Password",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            FloatingActionButton(
                onClick = { attemptRegistration(username, email, password, passwordValidation, auth, context = context) { message -> errorMessage = message } },
                modifier = Modifier.width(56.dp).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    Icons.Filled.Add,
                    "LogIn",
                    tint = MaterialTheme.colorScheme.background
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            FloatingActionButton(
                onClick = { goToLogIn(context = context) },
                modifier = Modifier.width(56.dp).height(56.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    Icons.Filled.ArrowForward,
                    "LogIn",
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}

private fun goToSignIn(context: Context){
    val intent = Intent(context, SignIn::class.java)
    context.startActivity(intent)
}

private fun goToLogIn(context: Context){
    val intent = Intent(context, LogIn::class.java)
    context.startActivity(intent)
}

private fun attemptLogin(email: String, password: String, auth: FirebaseAuth, context: Context, onError: (String) -> Unit) {
    if (email.isBlank() || password.isBlank()) {
        onError("Please enter both email and password")
        return
    }

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("Login successful!")
                val intent = Intent(context, entrance::class.java)
                context.startActivity(intent)
            } else {
                val errorMsg = task.exception?.message ?: "Login failed"
                println("Login failed: $errorMsg")
                onError(errorMsg)
            }
        }
}

private fun attemptRegistration(username: String, email: String, password: String, passwordValidation: String, auth: FirebaseAuth, context: Context, onError: (String) -> Unit) {
    if (email.isBlank() || password.isBlank()) {
        onError("Please enter both email and password")
        return
    }

    if (password != passwordValidation) {
        onError("Put the password right")
        return
    }

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("Sign In successful!")
                val intent = Intent(context, LogIn::class.java)
                context.startActivity(intent)
            } else {
                val errorMsg = task.exception?.message ?: "Sign In failed"
                println("Sign In failed: $errorMsg")
                onError(errorMsg)
            }
        }
}

@Composable
fun DynamicFormImage(
    lightFormResId: Int,
    darkFormResId: Int,
    modifier: Modifier = Modifier,
    contentDescription: String = "Form Image",
    contentScale: ContentScale = ContentScale.Fit,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    val formImageResId = if (darkTheme) {
        darkFormResId
    } else {
        lightFormResId
    }

    Image(
        painter = painterResource(id = formImageResId),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}

@Composable
fun ThemeAwareLogo(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    DynamicFormImage(
        lightFormResId = R.drawable.lightmd,
        darkFormResId = R.drawable.drkmode,
        modifier = modifier,
        contentScale = contentScale,
        contentDescription = "App Logo"
    )
}

@Composable
fun RoundedInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.primary,
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                placeholder = { Text(placeholder, color = MaterialTheme.colorScheme.background) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedTextColor = MaterialTheme.colorScheme.background,
                    unfocusedTextColor = MaterialTheme.colorScheme.background,
                    disabledTextColor = MaterialTheme.colorScheme.background
                ),
                singleLine = true,
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
            )
        }
    }
}