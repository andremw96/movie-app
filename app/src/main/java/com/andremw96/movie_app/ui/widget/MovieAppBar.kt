package com.andremw96.movie_app.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andremw96.movie_app.R

@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.genre_list_title),
                color = Color.Gray,
            )
        }

        Divider(
            color = Color.LightGray,
            thickness = 2.dp
        )
    }
}
