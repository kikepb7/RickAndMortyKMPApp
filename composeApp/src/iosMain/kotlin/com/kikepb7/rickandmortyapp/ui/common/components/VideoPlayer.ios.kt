package com.kikepb7.rickandmortyapp.ui.common.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView

@Composable
actual fun VideoPlayer(modifier: Modifier, url: String) {
    val webView = remember { WKWebView() }

    UIKitView(
        modifier = modifier,
        factory = {
            val container = UIView().apply {
                autoresizingMask = UIViewAutoresizingFlexibleWidth or UIViewAutoresizingFlexibleHeight
            }

            webView.apply {
                autoresizingMask = UIViewAutoresizingFlexibleWidth or UIViewAutoresizingFlexibleHeight
                loadRequest(NSURLRequest(NSURL(string = url)))
            }

            container.addSubview(webView)
            container
        },
        update = { views ->
            views.subviews().firstOrNull { it is WKWebView }
        }
    )
}