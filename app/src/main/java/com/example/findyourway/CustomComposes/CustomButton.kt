package com.example.findyourway.CustomComposes

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findyourway.R

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    loadingText: String = "Signing in...",
    icon: Int,
    progressIndicatorColor: Color = MaterialTheme.colors.primary,
    onClicked: () -> Unit
) {
    var clicked by remember { mutableStateOf(false) }
    val quicksand = Font(R.font.quicksand_medium)

    Box(
        modifier = modifier
            .clickable { clicked = !clicked }
            .background(Color.White),
    ) {
        Row(
            modifier = Modifier
                .width(265.dp)
                .height(40.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Google Button",
                tint = Color.Unspecified,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(30.dp))
            Text(
                text = if (clicked) loadingText else text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(quicksand)
            )
            if (clicked) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )
                onClicked()
            }
        }
    }
}