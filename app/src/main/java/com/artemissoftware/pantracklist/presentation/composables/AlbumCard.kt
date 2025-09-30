package com.artemissoftware.pantracklist.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.artemissoftware.pantracklist.R
import com.artemissoftware.pantracklist.core.designsystem.theme.PanTracklistTheme
import com.artemissoftware.pantracklist.core.designsystem.theme.dimension
import com.artemissoftware.pantracklist.core.designsystem.theme.spacing
import com.artemissoftware.pantracklist.domain.models.Album

@Composable
internal fun AlbumCard(
    album: Album,
    modifier: Modifier = Modifier
) {

    val request = ImageRequest
        .Builder(LocalContext.current)
        .data(album.thumbnailUrl)
        .error(R.drawable.ic_error_image)
        .placeholder(R.drawable.ic_image_placeholder)
        .crossfade(true)

    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacing.spacing0_5),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing2)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(MaterialTheme.dimension.cardImage),
                model = request
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Text(
                text = album.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlbumCardPreview() {
    PanTracklistTheme {
        AlbumCard(
            modifier = Modifier.fillMaxWidth(),
            album = Album(
                id = 1,
                title = "The flowers",
                thumbnailUrl = "https://placehold.co/150x150/92c952/white/png"
            )
        )
    }
}