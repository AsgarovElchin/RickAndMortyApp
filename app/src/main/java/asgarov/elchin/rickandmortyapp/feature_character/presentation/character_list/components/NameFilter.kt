package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import asgarov.elchin.rickandmortyapp.feature_character.domain.use_case.CharacterFilter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun FilterBar(
    currentFilter: CharacterFilter,
    onNameFilterChange: (String?) -> Unit,
    onShowAdvancedFilters: () -> Unit
) {
    var name by remember { mutableStateOf(currentFilter.name.orEmpty()) }
    val coroutineScope = rememberCoroutineScope()
    var debounceJob by remember { mutableStateOf<Job?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { newText ->
                name = newText
                debounceJob?.cancel()

                debounceJob = coroutineScope.launch {
                    delay(700)
                    onNameFilterChange(if (name.isBlank()) null else name)
                }
            },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                debounceJob?.cancel()
                onNameFilterChange(if (name.isBlank()) null else name)
                onShowAdvancedFilters()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Filter")
        }
    }
}
