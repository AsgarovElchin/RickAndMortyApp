package asgarov.elchin.rickandmortyapp.core.presentation

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import asgarov.elchin.rickandmortyapp.CharacterDetailRoute
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character

@Composable
fun CharacterListDialog(
    title: String,
    characters:List<Character>,
    onDismiss: () -> Unit,
    navController: NavController
){
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
        },
        text = {
            LazyColumn(
                modifier = Modifier.heightIn(max = 400.dp)
            ) {
                items(characters) { character ->
                    DialogItem(character = character){
                        selectedCharacter ->
                        navController.navigate(CharacterDetailRoute(selectedCharacter.id))
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = { onDismiss() }) {
                Text("Close")
            }
            })
}