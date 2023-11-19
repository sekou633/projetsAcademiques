<?php
/*
 * On indique que les chemins des fichiers qu'on inclut
 * seront relatifs au répertoire src.
 */

set_include_path("./src");

/* Inclusion des classes utilisées dans ce fichier */

require_once ('Routeur.php');
require_once ('view/View.php');
require_once ('control/Controller.php');
require_once ('model/Pays.php');
require_once ('model/StockagePays.php');
require_once ('model/StockagePaysStub.php');
require_once ('model/FichierStockagePays.php');
require_once ('model/PaysBuilder.php');
require_once ('model/PaysStorageMySQL.php');
require_once ('lib/ObjectFileDB.php');

require_once ('../../../private/mysql_config.php');



//$file = '../../tmp/pays_db';

//$result = file_exists($file);

//$paysdb = new StockagePaysStub();

$dsn = "mysql:host=". $MYSQL_HOST . ";port=". $MYSQL_PORT . ";dbname=" . $MYSQL_DB . ";charset=" . $MYSQL_CHARSET;
$user = $MYSQL_USERS;
$pass = $MYSQL_PASSWORD;
$pdo = new PDO($dsn, $user, $pass);
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$paysdb = new PaysStorageMySQL($pdo);

/*
 * Cette page est simplement le point d'arrivée de l'internaute
 * sur notre site. On se contente de créer un routeur
 * et de lancer son main.
 */

$router = new Routeur($paysdb);
$router->main();




