package com.artemissoftware.pantracklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.artemissoftware.pantracklist.core.designsystem.theme.PanTracklistTheme
import com.artemissoftware.pantracklist.feature.album.presentation.albums.navigation.AlbumsGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PanTracklistTheme {
                AlbumsGraph()
            }
        }
    }
}
