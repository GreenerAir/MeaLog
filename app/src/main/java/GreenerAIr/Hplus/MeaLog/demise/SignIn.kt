package GreenerAIr.Hplus.MeaLog.demise

import GreenerAIr.Hplus.MeaLog.resources.MeaLogTheme
import GreenerAIr.Hplus.MeaLog.resources.SignInForm
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignIn : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        setContent {
            MeaLogTheme {
                SignInForm(auth)
            }
        }
    }
}

