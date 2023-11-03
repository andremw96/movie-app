package com.andremw96.movie_app.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andremw96.movie_app.ui.theme.MovieAppTheme

@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .background(color = MovieAppTheme.colors.movieAppBarColor.backgroundColor)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (onBackClick != null) {
                IconButton(
                    onClick = { onBackClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MovieAppTheme.colors.movieAppBarColor.backButtonColor,
                    )
                }
            }

            Text(
                text = title,
                color = MovieAppTheme.colors.movieAppBarColor.textColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(48.dp))
        }

        Divider(
            color = MovieAppTheme.colors.movieAppBarColor.dividerColor,
            thickness = 2.dp
        )
    }
}

@Preview
@Composable
fun MovieAppBarPreview() {
    MovieAppTheme(darkTheme = true) {
        MovieAppBar(title = "title test") {

        }
    }
}
