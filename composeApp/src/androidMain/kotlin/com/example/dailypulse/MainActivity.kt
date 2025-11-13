package com.example.dailypulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dailypulse.articles.ArticlesViewModel
import com.example.dailypulse.screens.AboutScreen

class MainActivity : ComponentActivity() {
    private val articlesViewModel: ArticlesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        Platform().logSystemInfo()

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .safeContentPadding()
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        AppScaffold(articlesViewModel)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun AppAndroidPreview() {
    AppScaffold(ArticlesViewModel())
}