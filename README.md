Profile Dev – Jetpack Compose

Écran de profil développeur réalisé avec Jetpack Compose et Material Design 3. Cette branche reprend l'interface de la branche UI et la rend interactive avec les notions de gestion d'état vues dans les TP 8 à 11. Toutes les données restent locales.

L'écran affiche le nom, le rôle, un avatar avec un badge de disponibilité, une description, les compétences et la liste des projets. L'utilisateur peut rechercher un projet par son titre, une technologie ou sa description, ajouter ou retirer un projet des favoris, et choisir d'afficher tous les projets ou seulement les favoris. Le nombre de projets affichés se met à jour automatiquement. Un bouton permet aussi d'afficher ou de masquer les coordonnées de contact.

L'état principal de l'écran est géré par ProfileViewModel. Il regroupe dans ProfileUiState le texte de recherche, le filtre favoris, les identifiants des projets favoris et la liste affichée. L'état modifiable est privé dans un MutableStateFlow, et l'interface observe sa version publique en StateFlow. À chaque action de l'utilisateur, le ViewModel produit un nouvel état avec update et copy.

ProfileContainer relie le ViewModel à Compose : il récupère le ViewModel, collecte l'état avec collectAsState() et transmet les valeurs et les callbacks à EcranProfile. EcranProfile reste stateless, il affiche seulement ce qu'il reçoit. L'affichage des coordonnées est un petit état local gardé dans ContactSection avec rememberSaveable, car il ne concerne que l'interface.

Trois previews utilisent directement l'écran stateless, sans ViewModel : une preview claire avec tous les projets et aucun favori, une preview sombre avec une recherche active et le filtre favoris activé, et une preview avec un profil non disponible et une recherche sans résultat.

Le projet nécessite les dépendances material-icons-extended et lifecycle-viewmodel-compose.
