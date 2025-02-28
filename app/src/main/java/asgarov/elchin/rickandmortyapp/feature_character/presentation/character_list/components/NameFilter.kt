package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun FilterBar(
    searchQuery: String,
    labelText: String = "Search",
    onSearchQueryChange: (String?) -> Unit,
    onFilterClick: (() -> Unit)? = null,
    debounceTimeMillis: Long = 700
) {
    var query by remember { mutableStateOf(searchQuery) }
    val coroutineScope = rememberCoroutineScope()
    var debounceJob by remember { mutableStateOf<Job?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { newText ->
                query = newText
                debounceJob?.cancel()
                debounceJob = debounce(coroutineScope, debounceTimeMillis) {
                    onSearchQueryChange(newText.takeIf { it.isNotBlank() })
                }
            },
            label = { Text(labelText) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1,
            shape = RoundedCornerShape(16.dp)
        )

        if (onFilterClick != null) {
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    debounceJob?.cancel()
                    onSearchQueryChange(query.takeIf { it.isNotBlank() })
                    onFilterClick()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Filter")
            }
        }
    }
}


private fun debounce(
    scope: CoroutineScope,
    delayMillis: Long,
    action: () -> Unit
): Job {
    return scope.launch {
        delay(delayMillis)
        action()
    }
}