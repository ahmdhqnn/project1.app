package org.ahmad.project1app.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.ahmad.project1app.R
import org.ahmad.project1app.ui.theme.Project1appTheme
import org.ahmad.project1app.navigation.Screen

@Composable
fun MenuScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
            .fillMaxSize().padding(),
        contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier
                .height(420.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),

            ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(330.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Column(
                    modifier = Modifier.width(170.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                )
                {
                    ColoredBox(
                        color = Color(0xFFFF5722),
                        height = 160.dp,
                        text = stringResource(R.string.menu_3dvisual),
                        alignment = Alignment.BottomEnd,
                        textAlign = TextAlign.Start,
                        onClick = {navController.navigate(Screen.Visual.route)}

                    )
                    ColoredBox(
                        color = Color(0xFF2196F3 ),

                        text = stringResource(R.string.menu_module),
                        alignment = Alignment.BottomStart,
                        textAlign = TextAlign.End,
                        onClick = {navController.navigate(Screen.Module.route)}
                    )

                }
                ColoredBox(
                    color = Color(0xFF8BC34A  ),

                    text = stringResource(R.string.menu_ar),
                    alignment = Alignment.Center,
                    textAlign = TextAlign.Center,
                    onClick = {navController.navigate(Screen.Augmented.route)}
                )
            }
            ColoredBox(
                color = Color(0xFFFFC107 ),
                text = stringResource(R.string.menu_glosarium),
                alignment = Alignment.CenterEnd,
                textAlign = TextAlign.End,
                onClick = {navController.navigate(Screen.Glosarium.route)}
            )
        }

    }
}

@Composable
fun ColoredBox(
    color: Color,
    width: Dp = Dp.Unspecified,
    height: Dp = Dp.Unspecified,
    text: String,
    alignment: Alignment,
    textAlign: TextAlign,
    onClick: () -> Unit

) {
    Box(
        modifier = Modifier
            .then(if (width != Dp.Unspecified) Modifier.width(width) else Modifier.fillMaxWidth())
            .then(if (height != Dp.Unspecified) Modifier.height(height) else Modifier.fillMaxHeight())
            .clip(RoundedCornerShape(12.dp))
            .background(color)
            .clickable { onClick() }
            .padding(8.dp),
        contentAlignment = alignment
    ) {
        Text(
            text = text,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            fontWeight = FontWeight.ExtraBold,
            textAlign = textAlign
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MenuPreview() {
    Project1appTheme {
        MenuScreen(rememberNavController())
    }
}