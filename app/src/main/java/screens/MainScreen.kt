package com.mindu.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mindu.app.data.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController? = null) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    var username by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    // Obtener el nombre del usuario desde DataStore
    LaunchedEffect(Unit) {
        scope.launch {
            username = userPrefs.getUsername().first()
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Hola, $username 👋",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Bienvenida a MINDU.\nAquí puedes escribir, respirar y sanar 🌸",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}



