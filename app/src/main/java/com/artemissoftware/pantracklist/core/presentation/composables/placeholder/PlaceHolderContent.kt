package com.artemissoftware.pantracklist.core.presentation.composables.placeholder
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.pantracklist.R
import com.artemissoftware.pantracklist.core.designsystem.theme.PanTracklistTheme
import com.artemissoftware.pantracklist.core.designsystem.theme.dimension
import com.artemissoftware.pantracklist.core.designsystem.theme.spacing
import com.artemissoftware.pantracklist.core.presentation.util.test.CoreTestTags
import com.artemissoftware.pantracklist.core.presentation.util.test.CoreTestTags.CORE_PLACEHOLDER_CONTENT

@Composable
fun PlaceHolderContent(
    message: String,
    @DrawableRes icon: Int = R.drawable.ic_album,
    onClick: (() -> Unit)? = null,
    buttonText: String = "",
) {
    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.8f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "",
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    Column(
        modifier = Modifier
            .testTag(CORE_PLACEHOLDER_CONTENT)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(MaterialTheme.dimension.iconSizeBig)
                .alpha(alphaAnimation),
        )
        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.spacing1_5)
                .alpha(alphaAnimation),
            text = message,
            style = MaterialTheme.typography.bodyMedium,
        )
        onClick?.let {
            Button(
                modifier = Modifier
                    .testTag(CoreTestTags.CORE_PLACEHOLDER_BUTTON),
                onClick = it
            ) {
                Text(text = buttonText)
            }
        }
    }
}

@Preview
@Composable
private fun PlaceHolderContentPreview() {
    PanTracklistTheme {
        PlaceHolderContent(
            message = "Internet Unavailable.",
        )
    }
}