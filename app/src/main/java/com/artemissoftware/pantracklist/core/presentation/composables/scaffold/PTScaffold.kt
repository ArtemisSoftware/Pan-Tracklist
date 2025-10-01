package com.artemissoftware.pantracklist.core.presentation.composables.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.artemissoftware.pantracklist.R
import com.artemissoftware.pantracklist.core.designsystem.composables.loading.PTLoadingProgress
import com.artemissoftware.pantracklist.core.presentation.composables.placeholder.PlaceHolderContent
import com.artemissoftware.pantracklist.core.presentation.models.ErrorData

@Composable
fun PTScaffold(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    topBar: @Composable () -> Unit = {},
    error: ErrorData? = null,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier
                .then(modifier),
            topBar = topBar,
            content = {  innerPadding ->

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        content.invoke()
                    }
                }
            },
        )

        PTLoadingProgress(isLoading = isLoading)

        error?.let {
            PlaceHolderContent(
                icon = R.drawable.ic_error_image,
                message = it.message.asString(),
                onClick = it.onClick,
                buttonText = it.buttonText.asString(),
            )
        }
    }
}