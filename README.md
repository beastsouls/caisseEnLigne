Comment utiliser le logiciel ???
Dans Eclipse, importer le projet depuis github avec le lien suivant
https://github.com/beastsouls/caisseEnLigne.git
Dû à un conflit avec le fichier caisse.html la manipulation suivante est nécessaire (en cas d’absence de ce fichier) :
Créer le fichier templates/caisse.html
Copier le contenu du fichier templates/test.html dans le fichier nouvellement créé (caisse.html)
Pour tester :
Lancer l’application : clic droit sur projet/Application => run as => java application
Ouvrir un navigateur et saisir l’url http://localhost:8080/index
Se connecter en tant que administrateur avec login : admin mdp : password
Ou en tant que caissier avec comme login user et comme mot de passe password.
La connexion en utilisateur simple ne proposant que l’utilisation de la caisse.
La connexion en tant qu’admin donne accès à toutes les fonctionnalités directement accessibles à partir du menu.
