package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun FilterSection(title: String, options: List<String>, selectedValue: String, onSelectionChange: (String) -> Unit) {
    Text(title, style = MaterialTheme.typography.labelLarge)
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp
    ) {
        options.forEach { option ->
            FilterChip(
                selected = selectedValue == option,
                onClick = { onSelectionChange(if (selectedValue == option) "" else option) },
                label = { Text(option) }
            )
        }
    }
}