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
import asgarov.elchin.rickandmortyapp.feature_character.domain.use_case.CharacterFilter

@Composable
fun AdvancedFilterDialog(
    currentFilter: CharacterFilter,
    onDismiss: () -> Unit,
    onApply: (CharacterFilter) -> Unit
) {
    var selectedStatus by remember { mutableStateOf(currentFilter.status.orEmpty()) }
    var selectedSpecies by remember { mutableStateOf(currentFilter.species.orEmpty()) }
    var selectedGender by remember { mutableStateOf(currentFilter.gender.orEmpty()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Advanced Filters", style = MaterialTheme.typography.titleMedium) },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                FilterSection("Status", listOf("Alive", "Dead", "Unknown"), selectedStatus) {
                    selectedStatus = it
                }
                Spacer(modifier = Modifier.height(16.dp))
                FilterSection("Species", listOf("Alien", "Animal", "Human", "Humanoid", "Mythological Creature", "Poopybutthole", "Robot", "Unknown"), selectedSpecies) {
                    selectedSpecies = it
                }
                Spacer(modifier = Modifier.height(16.dp))
                FilterSection("Gender", listOf("Female", "Genderless", "Male", "Unknown"), selectedGender) {
                    selectedGender = it
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onApply(
                        currentFilter.copy(
                            status = selectedStatus.ifEmpty { null },
                            species = selectedSpecies.ifEmpty { null },
                            gender = selectedGender.ifEmpty { null }
                        )
                    )
                }
            ) { Text("Apply") }
        },
        dismissButton = { OutlinedButton(onClick = onDismiss) { Text("Cancel") } }
    )
}
