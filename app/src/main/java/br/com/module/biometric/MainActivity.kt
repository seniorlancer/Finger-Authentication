package br.com.module.biometric

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var mImgFingerPrint: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val executor = Executors.newSingleThreadExecutor()
        val activity: FragmentActivity = this // reference to activity
        val biometricPrompt = BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)

                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Access")
            .setSubtitle("Enter more easily")
            .setDescription("Access your account securely.")
            .setNegativeButtonText("\n" +
                    "Use password")
            .setConfirmationRequired(true)
            .setDeviceCredentialAllowed(false)
            .build()

        findViewById<ImageView>(R.id.idImgFingerPrint).setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }
}
