<?php


/**
 * Classe Controleur qui s'occupe du changement des données/requêtes
 * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya</a>
 * @version 1.0.0
 * @date 2022-11-18
 *  
 */

// =================== Début de la définition de la classe ===============================

class Controller
{

    protected View $view;
    //protected $paysTab;
    protected PaysStorageMySQL $paysdb;
    protected $currentPaysBuilder;
    protected $modifPaysBuilder;



    public function __construct(View $v, PaysStorageMySQL $paysdb)
    {
        $this->view = $v;
        $this->paysdb = $paysdb;
        $this->currentPaysBuilder = key_exists('currentPaysBuilder', $_SESSION) ? $_SESSION['currentPaysBuilder'] : null;
        $this->modifPaysBuilder = key_exists('modifPaysBuilder', $_SESSION) ? $_SESSION['modifPaysBuilder'] : array();

        // $this -> paysTab = array
        // (
        //     'guinee' => new Pays("Guinée", 'Conakry', 'Afrique', 'Français', 245857),
        //     'france' => new Pays('France', 'Paris', 'Europe','Français', 543940),
        //     'senegal' => new Pays('Senegal', 'Dakar', 'Afrique','Français', 196722),
        //     'japon' => new Pays('Japon', 'Tokyo', 'Asia','Japonais', 377915),
        //     'angleterre' => new Pays('Angleterre', 'London', 'Europe', 'Anglais', 243610),
        // );

        // =========== Avant Transformation =============================
        // $this -> paysTab = array
        // (
        //     'guinee' => array('Guinée', 'Conakry'),
        //     'france' => array('France', 'Paris'),
        //     'cote_ivoire' => array('Côte d ivoire', 'Abidjan'),
        // );

    }

    public function __destruct()
    {
        $_SESSION['currentPaysBuilder'] = $this->currentPaysBuilder;
        $_SESSION['modifPaysBuilder'] = $this->modifPaysBuilder;
    }

    /**
     * Getters and Setters
     */

    public function getView(): View
    {
        return $this->view;
    }

    public function getPays()
    {
        return $this->paysTab;
    }        // if (empty($this->data))


    public function setView(View $view): void
    {
        $this->view = $view;
    }

    public function setPays(array $paysTab): void
    {
        $this->paysTab = $paysTab;
    }

    /**
     * La fonction showInformation($id) permet d'afficher toutes les informations
     * concernant le pays correspondant à l'Id $id
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
     * @version 1.0.0
     * @param $id : l'identifiant du pays
     */

    public function PaysPage($id)
    {

        $pays = $this->paysdb->read($id);

        if ($pays !== null) {
            $this->view->makePaysPage($id, $pays);
        } else {
            $this->view->makeUnknownPaysPage();
        }
    }

    /**
     * La fonction showList() permet d'afficher la liste des pays
     * @author <a href="mailto:sdoumbouya633@gmail.com>" Sekou Doumbouya </a>
     * @version 1.0.0
     *
     */

    public function showList(): void
    {
        $this->view->makeListPage($this->paysdb->readAll());
    }

    public function recherche()
    {
        $p = "";
        if (key_exists('search', $_POST)) {
            $p = $_POST['search'];
        }
        $pays = $this->paysdb->readAll();
        $result = array();
        foreach ($pays as $key => $value) {
            if (stripos($value->getNom(), $p) !== false) {
                $result[$key] = $value;
            }
        }
        $this->view->makeListPage($result, count($result) === 0);
    }

    /**
     * la fonction newPaysPage() permet d'afficher la page de création d'un nouveau pays
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
     * @version 1.0.0
     */

    public function newPaysPage()
    {
        if ($this->currentPaysBuilder === null) {
            $this->currentPaysBuilder = new PaysBuilder();
        }
        $this->view->makePaysCreationPage($this->currentPaysBuilder);
    }
    // public function showHomePage():void
    // {
    //     $this -> view -> makeHomePage();
    // }

    /**
     * la fonction saveNewPays() permet de sauvegarder un nouveau pays dans la base de données
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
     * @version 1.0.0
     * @param $data : les données du pays à créer
     * @return void
     *  
     */
    public function saveNewPays(array $data): void
    {
        //$this -> v -> makeDebugPage($data);
        //$p = new Pays($data["nompays"], $data["capitale"], $data["continent"], $data["langue"], $data["superficie"]);

        $this->currentPaysBuilder = new PaysBuilder($data);
        if ($this->currentPaysBuilder->isValid()) {
            $pays = $this->currentPaysBuilder->createPays();
            $pId = $this->paysdb->create($pays);
            $this->currentPaysBuilder = null;
            $this->view->displayPaysCreationSuccess($pId);
        } else {
            $this->view->displayPaysCreationFaillure();
        }
    }

    /**
     * La fonction confirmSuppression($pId) permet de supprimer un pays de la base de données
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
     * @param $pId, l'identifiant du pays à supprimer
     * @version 1.0.0
     * 
     */

    public function confirmSuppression($pId)
    {
        $liste = $this->paysdb->readAll();

        if (!key_exists($pId, $liste)) {
            $this->view->makeUnknownPaysPage();
        } else {
            $p = $this->paysdb->read($pId);
            $this->view->makeSuppressionPage($pId, $p);
        }
    }

    /**
     * La fonction Suppression($id) permet de supprimer le pays correspondant à l'Id $id
     * @author <a href="mailto:sdoumbouya633@gmail.com">Sekou Doumbouya </a>
     * @param $id : l'identifiant du pays
     * @version 1.0.0
     * 
     * 
     */

    public function suppression($pId)
    {
        /* L'utilisateur confirme vouloir supprimer
		* le pays. On essaie. */
        $this->paysdb->delete($pId);
        $this->view->displayPaysDeletedPage();
    }

    /**
     * la fonction modifyPays($pId) permet d'afficher la page de modification d'un pays existant dans la base de données
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
     * @param $pId, l'identifiant du pays à modifier
     * @version 1.0.0
     */

    public function modifyPays($pId)
    {
        if (key_exists($pId, $this->modifPaysBuilder)) {
            /* On a déjà un PaysBuilder pour ce pays */
            $this->view->makePaysModifPage($pId, $this->modifPaysBuilder[$pId]);
        } else {
            /* On récupère en BD le pays à modifier */

            $pays = $this->paysdb->read($pId);
            if ($pays === null) {
                $this->view->makeUnknownPaysPage();
            } else {
                /* Extraction des données modifiables */
                $p = PaysBuilder::buildFromPays($pays);
                /* Préparation de la page de formulaire */
                $this->view->makePaysModifPage($pId, $p);
            }
        }
    }

    /**
     * la fonction saveModifPays($pId, $data) permet de sauvegarder les modifications apportées à un pays existant dans la base de données
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
     * @param $pId, l'identifiant du pays à modifier
     * @param $data, les données du pays
     * @version 1.0.0
     */

    public function savePaysModifications($pId, array $data)
    {

        $builder = new PaysBuilder($data);
        // Get corresponding object from db
        $pays = $this->paysdb->read($pId);
        // data validation
        if ($builder->isValid()) {
            // if valid then update object
            $builder->updatePays($pays);
            // update object in db
            $ok = $this->paysdb->update($pId, $pays);
            //   if (!$ok)
            //     throw new Exception("Id does not exist");
            // unset the current update from the session  
            if (array_key_exists("modifPaysBuilder", $_SESSION)) unset($_SESSION["modifPaysBuilder"]);
            // Shows the view with a succes feedback
            return $this->view->displayPaysModifiedPage($pId);
            //return $this->view->makeMotorSportPage($id, $pays);
        }
        // else save current builder in session
        $_SESSION["modifPaysBuilder"] = $builder;
        // Display view with update error feedback
        $this->view->displayPaysmodifFaillure($pId);
        //$this->view->makeMotorSportCreationPage($builder, $id);


    }
}

    // =========== Fin de la définition de la class ==================================
