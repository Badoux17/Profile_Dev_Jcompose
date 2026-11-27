Profile Dev – Jetpack Compose

Écran de profil développeur réalisé avec Jetpack Compose et Material Design 3, à partir d'une première version basique et des notions vues dans les TP 4 à 7. Toutes les données sont locales.

L'écran est construit avec une LazyColumn. En haut, une Surface présente l'avatar (Image) avec un badge de disponibilité superposé grâce à une Box, suivi du nom et du rôle. Viennent ensuite la description, les compétences affichées dans une LazyRow avec le composable SkillChip, puis les projets affichés avec le composable ProjectCard (une Card avec une Row et une Icon). La zone de contact regroupe l'email, la localisation et la disponibilité dans des OutlinedTextField en lecture seule, et un bouton « Contacter le profil » termine l'écran.

Le thème définit une palette claire et une palette sombre, des formes arrondies et une typographie personnalisée. Les composants utilisent uniquement MaterialTheme.colorScheme, typography et shapes, ce qui permet à l'écran de s'adapter automatiquement au mode sombre. Trois previews permettent de vérifier le rendu clair, sombre et « non disponible ».

Par rapport à la première version, le thème est maintenant bien appliqué, la disponibilité est réellement utilisée, les tailles de texte codées en dur ont été remplacées par celles du thème, et les listes passent par des composants Lazy.

Les icônes nécessitent la dépendance material-icons-extended, ajoutée comme au TP4.


<img width="749" height="625" alt="image" src="https://github.com/user-attachments/assets/2763ecc5-5472-4b8b-9e93-2359d432bdad" />
<img width="749" height="625" alt="image" src="https://github.com/user-attachments/assets/4df15e90-b87a-4372-86f3-d00b21d990a2" />

Piton Léo M1AL

