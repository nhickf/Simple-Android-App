package com.grpcx.androidtask.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grpcx.androidtask.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    ConstraintLayout(
        modifier = Modifier
            .windowInsetsPadding(TopAppBarDefaults.windowInsets)
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp
            )
            .wrapContentHeight()
    ) {
        val (icon, progressBar, percentage, title, profile, textIcon, fire) = createRefs()

        Image(
            modifier = Modifier
                .size(40.dp)
                .constrainAs(icon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                },
            painter = painterResource(id = R.drawable.top_bar_icon),
            contentDescription = ""
        )

        Text(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(icon.top)
                start.linkTo(icon.end, margin = 8.dp)
                bottom.linkTo(progressBar.top, margin = 8.dp)
            },
            fontWeight = FontWeight.Medium,
            lineHeight = 18.sp,
            fontSize = 16.sp,
            text = "Taming Temper"
        )

        LinearProgressIndicator(
            modifier = Modifier
                .constrainAs(progressBar) {
                    start.linkTo(icon.end, margin = 8.dp)
                    top.linkTo(title.bottom)
                    end.linkTo(title.end)
                    bottom.linkTo(icon.bottom, margin = 8.dp)
                    width = Dimension.fillToConstraints
                }
                .clip(RoundedCornerShape(8.dp)),
            progress = { 0.3f }
        )


        Text(
            modifier = Modifier.constrainAs(percentage) {
                top.linkTo(progressBar.top)
                start.linkTo(progressBar.end, margin = 8.dp)
                bottom.linkTo(progressBar.bottom)
            },
            text = "3%",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            lineHeight = 15.sp,
            fontSize = 10.sp
        )

        ElevatedButton(
            modifier = Modifier
                .constrainAs(profile) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .size(40.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            onClick = { }
        ) {
            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "profile"
            )
        }

        Image(
            modifier = Modifier
                .constrainAs(fire) {
                    top.linkTo(textIcon.top)
                    end.linkTo(textIcon.start)
                    bottom.linkTo(textIcon.bottom)
                }
                .size(24.dp),
            painter = painterResource(id = R.drawable.fire_icon),
            contentDescription = "fire"
        )

        Text(
            modifier = Modifier.constrainAs(textIcon) {
                end.linkTo(profile.start, margin = 16.dp)
                top.linkTo(profile.top)
                bottom.linkTo(profile.bottom)
            },
            fontSize = 16.sp,
            lineHeight = 26.sp,
            fontWeight = FontWeight.Medium,
            text = "0",
            color = MaterialTheme.colorScheme.primary
        )

    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    MainTopBar()
}