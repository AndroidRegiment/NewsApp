package com.androidregiment.newsapp.screen.webview

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


@Composable
fun WebViewScreen(
    url : String
) {

    val decodedUrl: String = URLDecoder.decode(url, StandardCharsets.UTF_8.toString())

    val context = LocalContext.current
    AndroidView(factory = {
        WebView(context).apply {
            webViewClient = WebViewClient()
            loadUrl(decodedUrl)
        }
    })

}