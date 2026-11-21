package com.nossahistoria.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Rose = Color(0xFFE88FA8)
private val RoseDark = Color(0xFFB95776)
private val Cream = Color(0xFFFFF8F5)
private val Ink = Color(0xFF49343B)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NossaHistoriaApp() }
    }
}

@Composable
fun NossaHistoriaApp() {
    var page by remember { mutableStateOf("home") }
    var showNewMemory by remember { mutableStateOf(false) }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = RoseDark,
            secondary = Rose,
            background = Cream,
            surface = Color.White,
            onBackground = Ink,
            onSurface = Ink
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Cream
        ) {
            Column {
                Header()

                Box(Modifier.weight(1f)) {
                    when (page) {
                        "home" -> HomeScreen {
                            showNewMemory = true
                        }
                        "memories" -> MemoriesScreen()
                        "letters" -> LettersScreen()
                        "profile" -> ProfileScreen()
                    }
                }

                NavigationBar(containerColor = Color.White) {
                    NavItem("Início", Icons.Default.Home, page == "home") {
                        page = "home"
                    }
                    NavItem("Memórias", Icons.Default.PhotoLibrary, page == "memories") {
                        page = "memories"
                    }
                    NavItem("Cartinhas", Icons.Default.Mail, page == "letters") {
                        page = "letters"
                    }
                    NavItem("Conta", Icons.Default.Person, page == "profile") {
                        page = "profile"
                    }
                }
            }

            if (showNewMemory) {
                NewMemoryDialog {
                    showNewMemory = false
                }
            }
        }
    }
}

@Composable
private fun Header() {
    Column(
        Modifier.fillMaxWidth()
            .background(Cream)
            .padding(horizontal = 22.dp, vertical = 18.dp)
    ) {
        Text(
            "NOSSO CANTINHO",
            color = RoseDark,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Nossa História",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = Ink
            )
            Spacer(Modifier.width(8.dp))
            Text("♥", color = Rose, fontSize = 22.sp)
        }
    }
}

@Composable
private fun HomeScreen(onNew: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF8DDE5)
                ),
                shape = RoundedCornerShape(26.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(22.dp)) {
                    Text(
                        "UM LUGAR SÓ NOSSO",
                        color = RoseDark,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Cada lembrança merece\num lugar especial.",
                        fontSize = 24.sp,
                        lineHeight = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Ink
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Fotos, palavras e pequenos momentos para guardar.",
                        color = Ink.copy(alpha = .75f),
                        fontSize = 14.sp
                    )
                    Spacer(Modifier.height(18.dp))
                    Button(
                        onClick = onNew,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = RoseDark
                        )
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(Modifier.width(6.dp))
                        Text("Guardar uma lembrança")
                    }
                }
            }
        }

        item { SectionTitle("O nosso espaço") }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                FeatureCard(
                    "Fotos", "Momentos em imagens",
                    Icons.Default.Photo, Modifier.weight(1f)
                )
                FeatureCard(
                    "Áudios", "Voz que vira memória",
                    Icons.Default.Mic, Modifier.weight(1f)
                )
            }
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                FeatureCard(
                    "Cartinhas", "Palavras guardadas",
                    Icons.Default.Favorite, Modifier.weight(1f)
                )
                FeatureCard(
                    "Nossa linha do tempo", "A história de vocês",
                    Icons.Default.CalendarMonth, Modifier.weight(1f)
                )
            }
        }

        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(Modifier.padding(18.dp)) {
                    Text(
                        "Um lembrete",
                        color = RoseDark,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "O mais bonito não é guardar tudo. É poder voltar e sentir a alegria de cada momento.",
                        color = Ink,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun MemoriesScreen() {
    EmptyState(
        Icons.Default.PhotoLibrary,
        "As lembranças de vocês",
        "As fotos e momentos compartilhados vão aparecer aqui."
    )
}

@Composable
private fun LettersScreen() {
    EmptyState(
        Icons.Default.Mail,
        "Cartinhas especiais",
        "Mensagens escritas com carinho terão seu próprio cantinho."
    )
}

@Composable
private fun ProfileScreen() {
    Column(
        Modifier.fillMaxSize().padding(22.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Conta de vocês", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(
            "Na próxima etapa vamos conectar duas contas privadas para que cada um entre no mesmo espaço compartilhado.",
            color = Ink.copy(alpha = .75f)
        )
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(18.dp)
        ) {
            Row(
                Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.CloudDone,
                    tint = RoseDark,
                    contentDescription = null
                )
                Spacer(Modifier.width(10.dp))
                Text("Sincronização na nuvem será configurada na etapa de contas.")
            }
        }
    }
}

@Composable
private fun EmptyState(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    body: String
) {
    Column(
        Modifier.fillMaxSize().padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            Modifier.size(76.dp)
                .background(Color(0xFFF8DDE5), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = RoseDark,
                modifier = Modifier.size(34.dp)
            )
        }
        Spacer(Modifier.height(18.dp))
        Text(title, fontSize = 21.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text(body, color = Ink.copy(alpha = .7f))
    }
}

@Composable
private fun FeatureCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(Modifier.padding(15.dp)) {
            Icon(
                icon,
                tint = RoseDark,
                modifier = Modifier.size(25.dp),
                contentDescription = null
            )
            Spacer(Modifier.height(12.dp))
            Text(title, fontWeight = FontWeight.Bold, color = Ink)
            Text(
                subtitle,
                color = Ink.copy(alpha = .65f),
                fontSize = 11.sp,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Ink
    )
}

@Composable
private fun RowScope.NavItem(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = { Icon(icon, contentDescription = label) },
        label = { Text(label) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = RoseDark,
            selectedTextColor = RoseDark,
            indicatorColor = Color(0xFFF8DDE5)
        )
    )
}

@Composable
private fun NewMemoryDialog(onDismiss: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nova lembrança ♥") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    "Este é o primeiro rascunho do formulário. Salvamento e anexos serão ligados à nuvem na próxima etapa."
                )
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = message,
                    onValueChange = { message = it },
                    label = { Text("Escreva algo especial") },
                    minLines = 3
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Fechar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
