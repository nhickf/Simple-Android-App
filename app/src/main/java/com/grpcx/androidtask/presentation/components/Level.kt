package com.grpcx.androidtask.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.grpcx.androidtask.R
import com.grpcx.androidtask.domain.models.Level
import com.grpcx.androidtask.domain.models.States

@Composable
fun Level(
    level: Level
) {
    LevelImage(level = level)
}

@Composable
private fun LevelImage(level: Level) {
    ConstraintLayout {
        val (image, text, title, description) = createRefs()
        Image(
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(
                    width = 102.dp,
                    height = 86.dp
                ),
            painter = painterResource(id = R.drawable.level_icon),
            contentDescription = "level"
        )

        Box(
            modifier = Modifier
                .constrainAs(text) {
                    start.linkTo(image.start)
                    end.linkTo(image.end)
                    bottom.linkTo(image.bottom)
                    top.linkTo(image.bottom)
                }
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                text = "LEVEL ${level.level}"
            )
        }

        Text(
            modifier = Modifier.constrainAs(title) {
                start.linkTo(text.start)
                end.linkTo(text.end)
                top.linkTo(text.bottom, margin = 16.dp)
            },
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            lineHeight = 30.sp,
            fontSize = 18.sp,
            text = level.title
        )

        Text(
            modifier = Modifier.constrainAs(description) {
                start.linkTo(title.start)
                end.linkTo(title.end)
                bottom.linkTo(parent.bottom)
                top.linkTo(title.bottom)
            }.width(250.dp),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            maxLines = 2,
            text = level.description
        )

    }
}

@Composable
@Preview
fun PreviewLevel() {
    Level(
        Level(
            emptyList(),
            "test", "1", States.LOCKED, "tete"
        )
    )
}