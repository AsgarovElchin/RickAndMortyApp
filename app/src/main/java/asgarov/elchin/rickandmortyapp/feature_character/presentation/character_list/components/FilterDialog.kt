package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun FilterDialog(
    title: String,
    filterOptions: Map<String, List<String>>,
    currentFilterValues: Map<String, String>,
    onDismiss: () -> Unit,
    onApply: (Map<String, String?>) -> Unit
) {
    var selectedValues by remember { mutableStateOf(currentFilterValues) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title, style = MaterialTheme.typography.titleMedium) },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                filterOptions.forEach { (category, options) ->
                    FilterSection(category, options, selectedValues[category].orEmpty()) {
                        selectedValues = selectedValues.toMutableMap().apply { put(category, it) }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onApply(selectedValues.mapValues { it.value.ifEmpty { null } })
                }
            ) {
                Text("Apply")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}