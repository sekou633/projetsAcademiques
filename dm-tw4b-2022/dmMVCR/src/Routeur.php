<?php


/**
 * Class Routeur 
 * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya</a>
 * @version 1.0.0
 */

  // =================== Début de la définition de la classe ===============================

    class Routeur{
        //protected View $v;
        //protected Controller $c;
        //protected StockagePays $paysdb;

        

        public function __construct(StockagePays $paysdb)
        {
            $this->paysdb = $paysdb;
        }

    // ========================= Main pour l'exécution ==================================//
    //                            Affichage                                              //
    // ==================================================================================//
        public function main(): void 
        {   
            session_start();
            
            $feedback = key_exists('feedback', $_SESSION) ? $_SESSION['feedback'] : '';
            $_SESSION['feedback'] = '';

            $v = new View($this, $feedback);
            $c = new Controller($v, $this -> paysdb);


            /* Analyse de l'URL */
		$paysId = key_exists('pays', $_GET)? htmlspecialchars($_GET['pays'] ): null;
		$action = key_exists('action', $_GET)? htmlspecialchars($_GET['action']): null;
		if ($action === null) {
			/* Pas d'action demandée : par défaut on affiche
	 	 	 * la page d'accueil, sauf si une couleur est demandée,
	 	 	 * auquel cas on affiche sa page. */
			$action = ($paysId === null)? "accueil": "voir";
		}

		try {
			switch ($action) {
			case "voir":
				if ($paysId === null) {
					$v->makeUnknownActionPage();
				} else {
					$c->PaysPage($paysId);
				}
				break;
			
			case "recherche":
				// if($paysId === null){
				// 	$v->makeUnknownActionPage();
				// } else {
				$c->recherche();
				// }
				break;

			case "creerPays":
				$c->newPaysPage();
				break;

			case "sauvegardePays":
				$paysId = $c->saveNewPays($_POST);
				break;

			case "supprimer":
				if ($paysId === null) {
					$v->makeUnknownActionPage();
				} else {
					$c->suppression($paysId);
				}
				break;

			case "confirmerSuppression":
				if ($paysId === null) {
					$v->makeUnknownActionPage();
				} else {
					$c->confirmSuppression($paysId);
				}
				break;

			case "modifier":
				if ($paysId === null) {
					$v->makeUnknownActionPage();
				} else {
					$c->modifyPays($paysId);
				}
				break;

			case "sauverModifs":
				if ($paysId === null) {
					$v->makeUnknownActionPage();
				} else {
					$c->savePaysModifications($paysId, $_POST);
				}
				break;

			case "galerie":
				$c->showList();
				break;

			case "accueil":
				$v->makeHomePage();
				break;
			
			case "about":
				$v->information();
				break;

			default:
				/* L'internaute a demandé une action non prévue. */
				$v->makeUnknownActionPage();
				break;
			}
		} catch (Exception $e) {
			/* Si on arrive ici, il s'est passé quelque chose d'imprévu
	 	 	 * (par exemple un problème de base de données) */
			$v->makeUnexpectedErrorPage($e);
		}

		/* Enfin, on affiche la page préparée */
		$v->render();
	

        }
        
    // ======================== Obtenir l'URL des pays =========================
    /**
     * La fonction getPaysURL($id) permet d'obtenir l'URL de la page d'un pays
     * @param $id l'identifiant du pays
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya</a>
     * @version 1.0.0
     * @return string, ramène la page du pays dont l'identifiant correspondant à $id
     */
        public function getPaysURL($id)
        {
            return "pays.php?pays=$id" ;
        }

        /**
     * La fonction getPaysCreationURL($id) permet d'afficher la page du formulaire de création d'un pays
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya</a>
     * @version 1.0.0
     * @return string, ramène la page de creation d'un pays sur un formulaire
     */

    public function getPaysCreationURL()
    {
        return "pays.php?action=creerPays" ;
    }

       /**
     * La fonction getPaysSaveURL() permet de sauvegarder les données du formulaire de création d'un pays
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya</a>
     * @version 1.0.0
     * @return string, ramène la page de creation d'un pays sur un formulaire
     */

    public function getPaysSaveURL()
    {
        return "pays.php?action=sauvegardePays" ;
    }

    /* URL de la page d'accueil */
	public function homePage() {
		return "pays.php";
	}

    /* URL de la page avec tous les pays */
	public function allPaysPage() {
		return "pays.php?action=galerie";
	}

    
	public function getPaysDeletion($id):string {
		return "pays.php?pays=$id&amp;action=supprimer";
	}

    public function getPaysAskDeletionURL($id):string
    {
        return "pays.php?pays=$id&amp;action=confirmerSuppression";
    }

    /* URL de la page d'édition d'un pays  existant */
	public function paysModifPage($id) {
		return "pays.php?pays=$id&amp;action=modifier";
	}

    /* URL d'enregistrement des modifications sur un pays (champ 'action' du formulaire) */
	public function updateModifiedPays($id) {
		return "pays.php?pays=$id&amp;action=sauverModifs";
	}

	public function aboutPage()
	{
		return "pays.php?action=about";
	}

	public function recherchePage()
	{
		return "pays.php?action=recherche";
	}


	// POUR LES PROCHAINES VERSIONS DU SITE
	
	// public function contactPage()
	// {
	// 	return "pays.php?action=contact";
	// }

	// public function getPaysSearchURL($id)
	// {
	// 	return "pays.php?action=search&amp;pays=$id";
	// }

	

    public function POSTredirect($url, $feedback) {
		$_SESSION['feedback'] = $feedback;
		header("Location: ".htmlspecialchars_decode($url), true, 303);
		die;
	}

    

    }

// =================== Fin de la définition de la classe ===============================
  

