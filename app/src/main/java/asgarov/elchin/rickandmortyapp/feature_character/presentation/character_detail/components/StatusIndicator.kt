package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatusIndicator(status: String) {
    val color = when (status) {
        "Alive" -> Color(0xFF4CAF50)
        "Dead" -> Color(0xFFF44336)
        else -> Color(0xFF9E9E9E)
    }

    Box(
        modifier = Modifier
            .size(10.dp)
            .clip(CircleShape)
            .background(color)
    )
}