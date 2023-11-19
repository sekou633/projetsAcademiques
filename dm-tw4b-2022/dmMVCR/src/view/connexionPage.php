<?php

?>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="styles/connexion.css" >
        <title> <?php echo $this->title ; ?> </title>
    </head>
    <body>
        <!-- Créer une page de connexion avec nom_User, Prenom_User, mot de pass et un boutton  -->
        <form action="squelette.php" method="POST">
            <div class="container">
                <div class="form">
                    <div class="form_title">Connexion</div>
                    <form class="form_inner" action="">
                        <div class="form_line">
                            <div class="form_block">
                                <h4 class="form_label">Nom</h4>
                                <input type="text" name="nom" class="form_input" placeholder="Nom">
                            </div>
                        </div>

                        <div class="form_line">
                            <div class="form_block">
                                <h4 class="form_label">Prenom</h4>
                                <input type="text" name="Prenom" class="form_input" placeholder="Prenom">
                            </div>
                        </div>

                        <div class="form_line">
                            <div class="form_block">
                                <h4 class="form_label">Mot de passe</h4>
                                <input type="text" name="pwd" class="form_input" placeholder="Password">
                            </div>
                        </div>
                        <button class="btn">Se connecter</button>

                    </form>
                </div>
            </div>
        </form>
    </body>
</html>