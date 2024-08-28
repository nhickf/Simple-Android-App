package com.grpcx.androidtask.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.grpcx.androidtask.R
import com.grpcx.androidtask.domain.models.Activity
import com.grpcx.androidtask.util.PdfPageDecoder.Companion.NUMBER_PDF_PAGES
import com.grpcx.androidtask.util.PdfPageDecoder.Companion.SPACE_BETWEEN_PAGES


@Composable
fun Activities(
    isLocked: Boolean,
    activity: Activity
) {

    ConstraintLayout(
        modifier = Modifier.size(160.dp)
    ) {

        val (image, icon, text) = createRefs()

        val imageToLoad = when (isLocked) {
            true -> activity.lockedIcon
            false -> activity.icon
        }

        SubcomposeAsyncImage(
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(100.dp),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .setParameter(NUMBER_PDF_PAGES, 1)
                .setParameter(SPACE_BETWEEN_PAGES, 20)
                .data("https:${imageToLoad.file.url}")
                .build(),
            loading = {
                CircularProgressIndicator(
                    Modifier.padding(24.dp)
                )
            },
            contentDescription = "activity",
        )

        CircularCheck(
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(image.top)
                    start.linkTo(image.end)
                    end.linkTo(image.end)
                    bottom.linkTo(image.top)
                    translationX = (-10).dp
                    translationY = (15).dp
                },
            isVisible = !isLocked
        )

        Text(
            modifier = Modifier.constrainAs(text) {
                top.linkTo(image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            fontSize = 14.sp,
            text = activity.title
        )
    }
}

@Composable
private fun CircularCheck(
    modifier: Modifier,
    isVisible: Boolean
) {

    if (isVisible) {
        Box(
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = CircleShape
                )
                .size(24.dp)
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(8.dp),
                painter = painterResource(id = R.drawable.check_icon),
                tint = Color.White,
                contentDescription = "check"
            )
        }
    }
}
