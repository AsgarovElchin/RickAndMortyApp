package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
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
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun AdvancedFilterDialog(
    currentFilter: CharacterFilter,
    onDismiss: () -> Unit,
    onApply: (CharacterFilter) -> Unit
) {
    var selectedStatus by remember { mutableStateOf(currentFilter.status.orEmpty()) }
    var selectedSpecies by remember { mutableStateOf(currentFilter.species.orEmpty()) }
    var selectedGender by remember { mutableStateOf(currentFilter.gender.orEmpty()) }

    val statusOptions = listOf("Alive", "Dead", "Unknown")
    val speciesOptions = listOf("Alien", "Animal", "Human", "Humanoid", "Mythological Creature", "Poopybutthole", "Robot", "Unknown")
    val genderOptions = listOf("Female", "Genderless", "Male", "Unknown")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Advanced Filters", style = MaterialTheme.typography.titleMedium) },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Status", style = MaterialTheme.typography.labelLarge)
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    mainAxisSpacing = 8.dp,
                    crossAxisSpacing = 8.dp
                ) {
                    statusOptions.forEach { option ->
                        FilterChip(
                            selected = selectedStatus == option,
                            onClick = {
                                selectedStatus = if (selectedStatus == option) "" else option
                            },
                            label = { Text(option) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Species", style = MaterialTheme.typography.labelLarge)
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    mainAxisSpacing = 8.dp,
                    crossAxisSpacing = 8.dp
                ) {
                    speciesOptions.forEach { option ->
                        FilterChip(
                            selected = selectedSpecies == option,
                            onClick = {
                                selectedSpecies = if (selectedSpecies == option) "" else option
                            },
                            label = { Text(option) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Gender", style = MaterialTheme.typography.labelLarge)
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    mainAxisSpacing = 8.dp,
                    crossAxisSpacing = 8.dp
                ) {
                    genderOptions.forEach { option ->
                        FilterChip(
                            selected = selectedGender == option,
                            onClick = {
                                selectedGender = if (selectedGender == option) "" else option
                            },
                            label = { Text(option) }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onApply(
                        currentFilter.copy(
                            status = if (selectedStatus.isBlank()) null else selectedStatus,
                            species = if (selectedSpecies.isBlank()) null else selectedSpecies,
                            gender = if (selectedGender.isBlank()) null else selectedGender
                        )
                    )
                }
            ) {
                Text("Apply")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
