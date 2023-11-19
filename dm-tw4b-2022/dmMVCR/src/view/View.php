<?php
/**
 * Classe qui s'occupe de la vue pour afficher la page html du site.
 * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumouya </a>
 * @version 1.0.0
 */

 // =================== Début de la définition de la classe ===============================

class View{

    protected $menu;    // menu du site pour la page d'accueil
    protected string $title;    // titre de la page
    protected string $contents; //  contenu de la page
    protected Routeur $r;
    protected $feedback;


    public function __construct(Routeur $r, $feedback)
    {
        $this->title = '';
        $this->contents = '';
        $this->r = $r;
        $this->feedback = $feedback;
    }

    /**
     * Getters and Setters
     */

    public function getTitle() : string
    {
        return $this->title;
    }

    public function setTitle(string $title): void
    {
        $this->title = $title;
    }

    public function getContent() : string 
    {
        return $this->contents ;
    }

    public function setContent(string $contents): void
    {
        $this->contents = $contents ;
    }

    public function getRouteur() : Routeur
    {
        return $this->r;
    }

    public function setRouteur(Routeur $routeur): void
    {
        $this->r = $routeur;
    }
    
    /**
     * La fonction render() ne retourne rien
     * elle permet d'afficher une page
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumouya </a>
     * @version 1.0.0
     */

    public function render() : void

    {
        if ($this->title === null || $this->contents === null) 
        {
			$this->makeUnexpectedErrorPage();
		}
        include 'squelette.php';    // squelette de l'affichage pour réduire le code;
    }

    /**
     * La fonction makeTestPage() ne retourne rien
     * elle permet de construire une page
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumouya </a>
     * @version 1.0.0
     */

    public function makeTestPage() : void
    {
        $this->title = "Pays";
        $this->contents = "<strong>Gestion des pays</strong>";
    }

    /**
     * La fonction makePaysPage() est une version spécifique de makeTestPage
     * elle permet de construire la page des pays 
     * @param $pays l'instance du pays.
     * @param $id l'identifiant du pays.
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumouya </a>
     * @version 1.0.0
     * 
     */

    public function makePaysPage($id, Pays $pays) : void
    {
        $this->title = "Page sur " . $pays->getNom() . "<br/>";
        $this->contents .= "<div class='image'>";
        $this->contents .= "<figure>";
        $this->contents .= "<img src='../upload/". $pays->getImg() ."' alt='Drapeau de " . $pays->getNom() . "'/>";
        $this->contents .= "<figcaption>Drapeau de " . $pays->getNom(). "</figcaption>\n";
        $this->contents .= "</figure></div>";
        $this->contents .= "<div class='infopays'>";
        $this->contents .= "<strong>".$pays->getNom()."</strong>" . " est un pays situé en <strong>" . $pays->getContinent() . "</strong>";
        $this->contents .= ". Sa capitale est <strong>" . $pays->getCapitale() . "</strong>.<br/>";
        $this->contents .= "<br/>";
        $this->contents .= "Sa langue officielle est <strong>" . $pays->getLangue() . "</strong>". ".<br/><br/>";
        $this->contents .= "Sa superficie est de <strong>" . $pays->getSuperficie() . "</strong>". " km².<br/><br/>";
        $this->contents .= "Sa monnaie est <strong>" . $pays->getMonnaie() ."</strong>". ".<br/><br/>";
        $this->contents .= "Sa population est de <strong>" . $pays->getPopulation() ."</strong>". " habitants.<br/><br/>";
        $this->contents .= "</div>";
        $this->contents .= "<ul>\n";
		$this->contents .= '<li><a href="'.$this->r->paysModifPage($id).'">Modifier</a></li>'."\n";
		$this->contents .= '<li><a href="'.$this->r->getPaysAskDeletionURL($id).'">Supprimer</a></li>'."\n";
		$this->contents .= "</ul>\n";
        

    }

        /**
         * La fonction makeUnknownPaysPage() permet d'afficher une page d'erreur
         * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumouya </a>
         * @version 1.0.0
         */
    
    public function makeUnknownPaysPage() : void
    {
        $this->title = "Message d'erreur";
        $this->contents = " Ce Pays n'est pas dans la <strong> liste </strong>" ;
    }

    /**
     * la fonction makeUnexpectedErrorPage() permet d'afficher une page d'exception
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
     * @version 1.0.0
     */
    public function makeUnexpectedErrorPage(Exception $e=null) {
		$this->title = "Erreur";
		$this->content = "Une erreur inattendue s'est produite." . "<pre>" . var_export($e) . "</pre>";
	}

    /**
         * La fonction makeHomePage() permet d'afficher la page d'accueil du site
         * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumouya </a>
         * @version 1.0.0
         */

    public function makeHomePage():void
    {
        $this->title = "<mark> BIENVENUE SUR LE SITE DE GESTION DES PAYS</mark> ";
        $this->contents = "<strong> BONNE AVENTURE, PROFITER AU MAXIMUM !</strong>";

    }

    /**
         * La fonction makeListePage() permet d'afficher une page de liste de pays
         * @param $tableauInstances des pays
         * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumouya </a>
         * @version 1.0.0
         */

    
    public function makeListPage($tableauInstances, $error=False):void

    {
        $this->title = " AFFICHAGE DE LA LISTE ";
        //$this->contents = "";
        $this->contents .= "<form class=\"no-border\" action={$this->r->recherchePage()} method=\"POST\">";
        $this->contents .= "<input type=\"text\" id=\"search\" name=\"search\" placeholder=\"Rechercher un pays...\">";
        $this->contents .= "<button class=\"button_info\" type=\"submit\"> Rechercher dans la liste</button>";
        $this->contents .= "</form>";
        $this->contents .= "<p> DECOUVREZ LES DETAILS D'UN PAYS EN LE CLIQUANT DESSUS !</p>";
        if($error){
            $this->contents .= "<p style=\"text-align:center\"> CE PAYS N'EXISTE PAS DANS LA LISTE.</p>\n";
        }
        else
        {
        $this->contents .= "<ul class=\"listes\">";
        foreach ($tableauInstances as $key => $value)
        {
            $this->contents .= "<li> <a href =' " . $this->r->getPaysURL($key ) ."'>". $value->getNom() . "</a></li>" ;
        }

        $this->contents .= "</ul>";
        }
        
        
    }

    /**
     * La fonction makeDebugPage($variable) permet de faciliter le debugage
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumouya </a>
     * @version 1.0.0
     */

    public function makeDebugPage($variable) 
    {
        $this->title = 'Debug';
        $this->contents = '<pre>'.htmlspecialchars(var_export($variable, true)).'</pre>';
    }

    /* Une fonction pour échapper les caractères spéciaux de HTML,
	* car celle de PHP nécessite trop d'options. */
	public static function htmlesc($str) 
    {
		return htmlspecialchars($str,
			/* on échappe guillemets _et_ apostrophes : */
			ENT_QUOTES
			/* les séquences UTF-8 invalides sont
			* remplacées par le caractère �
			* au lieu de renvoyer la chaîne vide…) */
			| ENT_SUBSTITUTE
			/* on utilise les entités HTML5 (en particulier &apos;) */
			| ENT_HTML5,
			'UTF-8');
	}

    /**
     * la fonction makePaysCreationPage($pb) permet d'afficher le formulaire de création de pays
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
     * @version 1.0.0
     * @param $pb, l'identifiant du pays
     *
     */
    public function makePaysCreationPage(PaysBuilder $pb)
    {
         
        $this->title = " FORMULAIRE DE CREATION D'UN PAYS :  ";
       
        $nameRef = $pb->getNomRef();
        $capRef = $pb->getCapitaleRef();
        $contiRef = $pb->getContinentRef();
        $supRef = $pb->getSuperficieRef();
        $monRef = $pb->getMonnaieRef();
        $popRef = $pb->getPopulationRef();
        $imageRef = $pb->getImageRef();
        $langueRef = $pb->getLangueRef();

        ob_start();
        include "formulairecreation.php";
        $this->contents = ob_get_clean();

    }

    /**
     * la fonction makePaysModifPage() permet d'afficher le formulaire de modification de pays
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
     * @version 1.0.0
     * @param $id, l'identifiant du pays
     * @param $pb, pays à modifier
     */
    public function makePaysModifPage($id, PaysBuilder $pb) 
    {
        $this->title = " FORMULAIRE DE MODIFICATION D'UN PAYS : ";

        $nameRef = $pb->getNomRef();
        $capRef = $pb->getCapitaleRef();
        $contiRef = $pb->getContinentRef();
        $supRef = $pb->getSuperficieRef();
        $langueRef = $pb->getLangueRef();
        $monRef = $pb->getMonnaieRef();
        $popRef = $pb->getPopulationRef();
        $imageRef = $pb->getImageRef();
        
        ob_start();
        include "formulairemodif.php";
        $this->contents = ob_get_clean();
        
	}
    

    // public function erreurPageSuperficie():void
    // {
    //     $this->title = "<h1> PAGE D'ERREUR (SUPERFICIE NÉGATIVE) </h1>" . "<br/>";
    //     $this->contents = "<strong> VEUILLEZ ENTRER UNE SUPERFICIE POSITIVE </strong>";
    // }

    // public function champManquant():void
    // {
    //     $this->title = "<h1> PAGE D'ERREUR (UN CHAMP EST VIDE) </h1>" . "<br/>";
    //     $this->contents = "<strong> VEUILLEZ REMPLIR TOUS LES CHAMPS ! </strong>";
    // }
     /**
     *          ============ Fonctions Utilitaires ============================
     */

    public function makeUnknownActionPage() {
		$this->title = " Erreur ";
		$this->contents = "<strong> LA PAGE DEMANDÉE N'EXISTE PAS. </strong>";
	}

    public function makeSuppressionPage($id, Pays $pays)
    {   
        $nom = self::htmlesc($pays->getNom());

        $this->title = " Suppression du pays <mark> $nom </mark>";
        $this ->contents = "le pays << $nom >> va être supprimé";
        $this->contents .= '<form action="'.$this->r->getPaysDeletion($id).'" method="POST">'."\n";
		$this->contents .= "<button>Confirmer</button>\n</form>\n";
    }

    public function makePaysSupprimerPage() {
		$this->title = "Suppression effectuée";
		$this->contents = "<p>Le pays a été correctement supprimé.</p>";
	}
    protected function getMenu() {
		return array(
			"ACCUEIL" => $this->r->homePage(),
			"LISTE DES PAYS" => $this->r->allPaysPage(),
            "AJOUTER UN PAYS" => $this->r->getPaysCreationURL(),
            "A PROPOS" => $this->r->aboutPage(),
            // "CONTACT" => $this->r->contactPage(),
		);
	}

    public function information():void
    {
        $this->title = " INFORMATIONS ";
        ob_start();
        include 'formulaire_a_propos.php';
        $this->contents = ob_get_clean();
    }

    public function displayPaysCreationSuccess($id)
    {
       // $this->title = "<h1> PAGE DE REDIRECTION </h1>";
        $this->r-> POSTredirect($this->r-> getPaysURL($id), "Le pays a été correctement ajouté");
    }
    public function displayPaysCreationFaillure()
    {
		$this->r->POSTredirect($this->r->getPaysCreationURL(), "Erreurs dans le formulaire.");
	}

    public function displayPaysDeletedPage() 
    {
		$this->r->POSTredirect($this->r->allPaysPage(), "Le Pays a bien été supprimé !");
	}

    public function displayPaysModifiedPage($id) 
    {
		$this->r->POSTredirect($this->r->getPaysURL($id), "Le Pays a bien été modifié !");
	}

    public function displayPaysmodifFaillure($id) 
    {
		$this->r->POSTredirect($this->r->paysModifPage($id), "Erreurs dans le formulaire.");
	}

    public function connexionPage():void
    {
        $this->title = " CONNEXION ";
        ob_start();
        include 'connexionPage.php';
        $this->contents = ob_get_clean();
    }

}


 // =================== Fin de la définition de la classe ===============================


