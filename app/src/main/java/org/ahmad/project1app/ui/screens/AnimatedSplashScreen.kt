package org.ahmad.project1app.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import org.ahmad.project1app.navigation.Screen

@Composable
fun AnimatedSplashScreen(navController: NavHostController){
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue =  if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3500
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float){
    Box(
        modifier = Modifier.background(if (isSystemInDarkTheme()) Color.Black else Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "BreathEasy",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            modifier = Modifier.alpha(alpha)

        )
    }
}

@Preview(showBackground = true)

@Composable
fun SplashScreenPreview(){
    Splash(alpha = 1f)
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun SplashScreenDarkPreview(){
    Splash(alpha = 1f)
}