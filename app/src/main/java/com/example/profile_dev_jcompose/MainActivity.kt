package com.example.profile_dev_jcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ProjectUi(
    val id: Int,
    val title: String,
    val description: String,
    val technologies: String,
    val year: String
)

data class ProfileUi(
    val name: String,
    val role: String,
    val description: String,
    val skills: List<String>,
    val projects: List<ProjectUi>,
    val email: String,
    val location: String,
    val availabilityText: String,
    val isAvailable: Boolean
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EcranProfile(
                        name = "Léo Piton",
                        role = "Développeur fullstack",
                        description = "Passionnée l'architecture logiciel depuis mes 5 ans...",
                        skills = listOf("Kotlin", "Jetpack Compose", "Git", "Java", "Python"),
                        availabilityText = "Disponible pour un stage (4 à 6 mois)",
                        isAvailable = true,
                        modifier = Modifier.padding(innerPadding)
                    )
            }
        }
    }
}
// Composant EcranProfile
@Composable
fun EcranProfile(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    availabilityText: String,
    isAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        FicheProfile(
            name = name,
            role = role,
            description = description,
            skills = skills,
            availabilityText = availabilityText,
            isAvailable = isAvailable,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}
// Composant FicheProfile
@Composable
fun FicheProfile(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    availabilityText: String,
    isAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            EntêteProfile(
                name = name,
                role = role,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            InfoSecondaire(
                statusText = availabilityText,
                isAvailable = isAvailable,
                onClick = {
                    // Pas de fonctionnement décidé
                },
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "À propos",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = description,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Text(
                text = "Compétences",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            skills.forEach { skill ->
                Competence(
                    skillName = skill,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            ActionButton(
                buttonText = "Contacter le profil",
                onClick = {
                    // Pas de fonctionnement décidé
                },
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

// Composant EntêteProfile
@Composable
fun EntêteProfile(
    name: String,
    role: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Text(
            text = role,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

// Composant InfoSecondaire
@Composable
fun InfoSecondaire(
    statusText: String,
    isAvailable: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                role = Role.Button
                contentDescription = "Statut : $statusText. Appuyez pour vérifier le calendrier de disponibilité."
            }
            .clickable(onClick = onClick)
    ) {
        Text(
            text = statusText,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(12.dp)
        )
    }
}

// Composant Competence
@Composable
fun Competence(
    skillName: String,
    modifier: Modifier = Modifier
) {
    Surface(

        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "• $skillName",
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

// Composant ActionButton
@Composable
fun ActionButton(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = buttonText,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

// Preview 1
@Preview(showBackground = true, name = "Profile Junior")
@Composable
fun EcranProfilePreview1() {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            EcranProfile(
                name = "Alice Martin",
                role = "Développeuse Android Junior",
                description = "Passionnée par le développement mobile",
                skills = listOf("Kotlin", "Jetpack Compose", "Git", "Accessibilité"),
                availabilityText = "Disponible pour un stage",
                isAvailable = true,
                modifier = Modifier.padding(innerPadding)
            )
        }
}

// Preview 2
@Preview(showBackground = true, name = "Profile Senior")
@Composable
fun EcranProfilePreview2() {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            EcranProfile(
                name = "Thomas Dubois",
                role = "Développeur Full-Stack Senior",
                description = "Spécialiste architecture cloud",
                skills = listOf(
                    "TypeScript",
                    "Docker",
                    "PostgreSQL",
                    "CI/CD"
                ),
                availabilityText = "Non disponible",
                isAvailable = false,
                modifier = Modifier.padding(innerPadding)
            )
        }
}