package com.example.profile_dev_jcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profile_dev_jcompose.ui.theme.Profile_dev_jcomposeTheme

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
            Profile_dev_jcomposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EcranProfile(
                        profile = sampleProfile(),
                        onContactClick = {
                            // Pas de fonctionnement décidé
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

fun sampleProjects(): List<ProjectUi> {
    return listOf(
        ProjectUi(
            id = 1,
            title = "Profile Dev",
            description = "Application Android de présentation d'un profil développeur.",
            technologies = "Kotlin • Jetpack Compose • Material 3",
            year = "2026"
        ),
        ProjectUi(
            id = 2,
            title = "API de gestion de tâches",
            description = "API REST permettant de créer, modifier et suivre des tâches.",
            technologies = "Java • Spring Boot • PostgreSQL",
            year = "2025"
        ),
        ProjectUi(
            id = 3,
            title = "Script d'analyse de logs",
            description = "Outil en ligne de commande qui résume les erreurs d'un serveur.",
            technologies = "Python • Git",
            year = "2025"
        )
    )
}

fun sampleProfile(): ProfileUi {
    return ProfileUi(
        name = "Léo Piton",
        role = "Développeur fullstack",
        description = "Passionné par l'architecture logicielle depuis mes 5 ans...",
        skills = listOf("Kotlin", "Jetpack Compose", "Git", "Java", "Python"),
        projects = sampleProjects(),
        email = "leo.piton@example.com",
        location = "Paris, France",
        availabilityText = "Stage de 4 à 6 mois",
        isAvailable = true
    )
}

// Composant EcranProfile
@Composable
fun EcranProfile(
    profile: ProfileUi,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            EnteteProfile(
                name = profile.name,
                role = profile.role,
                isAvailable = profile.isAvailable
            )
        }
        item { SectionTitle(text = "À propos") }
        item {
            Text(
                text = profile.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        item { SectionTitle(text = "Compétences") }
        item { SkillRow(skills = profile.skills) }

        item { SectionTitle(text = "Projets") }
        items(
            items = profile.projects,
            key = { project -> project.id }
        ) { project ->
            ProjectCard(project = project)
        }
        item { SectionTitle(text = "Contact") }
        item {
            ContactCard(
                email = profile.email,
                location = profile.location,
                availabilityText = profile.availabilityText
            )
        }
        item {
            ActionButton(
                buttonText = "Contacter le profil",
                onClick = onContactClick
            )
        }
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
fun EnteteProfile(
    name: String,
    role: String,
    isAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AvatarAvecBadge(isAvailable = isAvailable)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = role,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
//Composant Card
@Composable
fun ContactCard(
    email: String,
    location: String,
    availabilityText: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            InfoField(label = "Email", value = email, icon = Icons.Default.Email)
            Spacer(modifier = Modifier.height(8.dp))
            InfoField(label = "Localisation", value = location, icon = Icons.Default.LocationOn)
            Spacer(modifier = Modifier.height(8.dp))
            InfoField(label = "Disponibilité", value = availabilityText, icon = Icons.Default.DateRange)
        }
    }
}

//Composant InfoChamps
@Composable
fun InfoField(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},   // Lecture seule : aucune modification
        readOnly = true,
        singleLine = true,
        label = { Text(text = label) },
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
        modifier = modifier.fillMaxWidth()
    )
}

//Composant Project Card
@Composable
fun ProjectCard(
    project: ProjectUi,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = project.year,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = project.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = project.technologies,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

//Composant Avatar
@Composable
fun AvatarAvecBadge(
    isAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = "Avatar du développeur",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        )
        Surface(
            modifier = Modifier.align(Alignment.BottomEnd),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surface
        ) {
            Icon(
                imageVector = if (isAvailable) Icons.Default.CheckCircle else Icons.Default.Close,
                contentDescription = if (isAvailable) "Disponible" else "Non disponible",
                tint = if (isAvailable) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                modifier = Modifier.size(28.dp)
            )
        }
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
// Composant SkillChip
@Composable
fun SkillChip(
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

// Composant SkillLigne
@Composable
fun SkillRow(
    skills: List<String>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(skills) { skill ->
            SkillChip(label = skill)
        }
    }
}

// Composant Titre
@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
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
        Icon(imageVector = Icons.Default.Email, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = buttonText,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

// Preview 1
@Preview(showBackground = true, name = "Profil - Light Theme")
@Composable
fun EcranProfileLightPreview() {
    Profile_dev_jcomposeTheme(darkTheme = false) {
        EcranProfile(profile = sampleProfile(), onContactClick = {})
    }
}
// Preview 2
@Preview(showBackground = true, name = "Profil - Dark Theme")
@Composable
fun EcranProfileDarkPreview() {
    Profile_dev_jcomposeTheme(darkTheme = true) {
        EcranProfile(profile = sampleProfile(), onContactClick = {})
    }
}
//Preview 3
@Preview(showBackground = true, name = "Profil - Non disponible")
@Composable
fun EcranProfileIndisponiblePreview() {
    Profile_dev_jcomposeTheme(darkTheme = false) {
        EcranProfile(
            profile = sampleProfile().copy(
                availabilityText = "Non disponible",
                isAvailable = false
            ),
            onContactClick = {}
        )
    }
}