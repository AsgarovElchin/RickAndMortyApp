package asgarov.elchin.rickandmortyapp.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavHostController) {

    val topLevelRoutes = listOf(
        TopLevelRoute("Characters", AppRoute.CharacterRoute, Icons.Default.Home),
        TopLevelRoute("Episodes", AppRoute.EpisodeRoute, Icons.Default.PlayArrow),
        TopLevelRoute("Locations", AppRoute.LocationRoute, Icons.Default.LocationOn)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var selectedIndex by remember { mutableIntStateOf(0) }

    NavigationBar {
        topLevelRoutes.forEachIndexed { index, route ->
            NavigationBarItem(
                icon = { Icon(route.icon, contentDescription = route.name) },
                label = { Text(route.name) },
                selected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                    if (currentRoute != route.route.toString()) {
                        navController.navigate(route.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}