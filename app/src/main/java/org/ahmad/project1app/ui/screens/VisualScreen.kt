package org.ahmad.project1app.ui.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.ahmad.project1app.R
import org.ahmad.project1app.ui.theme.Project1appTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun VisualScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                title = {
                    Text(
                        text = stringResource(R.string.menu_3dvisual),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // Navigasi kembali
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = stringResource(R.string.menu)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White) // Ubah warna latar belakang menjadi putih
        ) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            (context.resources.displayMetrics.heightPixels * 0.7).toInt()
                        )

                        webChromeClient = WebChromeClient()
                        webViewClient = object : WebViewClient() {
                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                Log.d("SketchfabViewer", "WebView page loaded")
                            }

                            override fun onReceivedError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                error: WebResourceError?
                            ) {
                                super.onReceivedError(view, request, error)
                                Log.e("SketchfabViewer", "WebView error: ${error?.description}")
                            }
                        }

                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true
                        settings.loadWithOverviewMode = true
                        settings.useWideViewPort = true
                        settings.mediaPlaybackRequiresUserGesture = false
                        settings.allowContentAccess = true
                        settings.allowFileAccess = true

                        val embedHtml = """
                            <html>
                            <head>
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <style>
                                    body, html {
                                        margin: 0;
                                        padding: 0;
                                        background-color: #ffffff; /* Sesuaikan background iframe jika perlu */
                                        height: 100%;
                                        width: 100%;
                                        overflow: hidden;
                                    }
                                    iframe {
                                        width: 100%;
                                        height: 100%;
                                        border: none;
                                    }
                                </style>
                            </head>
                            <body>
                                <iframe title="Respirator system Anatomy" frameborder="0" allowfullscreen mozallowfullscreen="true"
                                webkitallowfullscreen="true" allow="autoplay; fullscreen; xr-spatial-tracking"
                                xr-spatial-tracking execution-while-out-of-viewport execution-while-not-rendered web-share
                                src="https://sketchfab.com/models/e167afeba10a4711a8d6b0b59358cc44/embed?autostart=1&ui_theme=dark&ui_controls=0&ui_infos=0"></iframe>
                            </body>
                            </html>
                        """.trimIndent()

                        loadDataWithBaseURL(
                            "https://sketchfab.com",
                            embedHtml,
                            "text/html",
                            "UTF-8",
                            null
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Respirator system Anatomy",
                    color = Color.Black, // Sesuaikan warna teks jika perlu
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = "3D Model",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = Color.LightGray, // Sesuaikan warna surface jika perlu
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text(
                            text = "by 3D4SCI",
                            color = Color.Black, // Sesuaikan warna teks di dalam surface jika perlu
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))

                // Anda bisa menambahkan kembali Column "Let's modul view" jika dibutuhkan
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun VisualScreenPreview() {
    Project1appTheme {
        ARScreen(rememberNavController())
    }
}