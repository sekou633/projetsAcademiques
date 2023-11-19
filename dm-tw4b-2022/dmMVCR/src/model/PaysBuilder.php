<?php

// Path: src/model/PaysBuilder.php


/**
 * Class qui permet de manipuler le formulaire à travers ses fonctions 
 * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
 * @version 1.0.0
 */

class PaysBuilder{

    private const NOM_REF = "nom_p";
    private const CAPITALE_REF = "capitale_p";
    private const CONTINENT_REF = "continent_p";
    private const LANGUE_REF = "langue_p";
    private const SUPERFICIE_REF = "superficie_p";
    private const POPULATION_REF = "population_p";
    private const MONNAIE_REF = "monnaie_p";
    private const IMAGE_REF = "image_p";


    protected $data;
    protected $errors;

    public function __construct($data=null)
    {
        if ($data === null)
        {
            $data = array(
                self::NOM_REF => "",
                self::CAPITALE_REF => "",
                self::CONTINENT_REF => "",
                self::LANGUE_REF => "",
                self::SUPERFICIE_REF => 0,
                self::POPULATION_REF => "",
                self::MONNAIE_REF => "",
                self::IMAGE_REF => ""
            );
        }
        $this -> data = $data;
        $this -> errors = array();
    }

    /* Renvoie une nouvelle instance de PaysBuilder avec les données
 	 * modifiables du pays passé en argument. */
	public static function buildFromPays(Pays $pays) {
		return new PaysBuilder(array(
			self::NOM_REF => $pays->getNom(),
			self::CAPITALE_REF => $pays->getCapitale(),
            self::CONTINENT_REF => $pays->getContinent(),
            self::LANGUE_REF => $pays->getLangue(),
            self::SUPERFICIE_REF => $pays->getSuperficie(),
            self::POPULATION_REF => $pays->getPopulation(),
            self::MONNAIE_REF => $pays->getMonnaie(),
            self::IMAGE_REF => $pays->getImg(),

		));
	}

    /**  
     * GETTERS 
     */


    // public function getError()
    // {
    //     return $this -> error;
    // }

    public function getNomRef():string
    {
        return self::NOM_REF;
    }

    public function getCapitaleRef():string
    {
        return self::CAPITALE_REF;
    }

    public function getContinentRef():string
    {
        return self::CONTINENT_REF;
    }

    public function getLangueRef():string
    {
        return self::LANGUE_REF;
    }

    public function getSuperficieRef():string
    {
        return self::SUPERFICIE_REF;
    }

    public function getPopulationRef():string
    {
        return self::POPULATION_REF;
    }

    public function getMonnaieRef():string
    {
        return self::MONNAIE_REF;
    }

    public function getImageRef():string
    {
        return self::IMAGE_REF;
    }

    /* Crée une nouvelle instance de Pays avec les données
	 * fournies. Si toutes ne sont pas présentes, une exception
	 * est lancée. 
     *  
     */

	public function createPays() 
    {   
        // if (empty($this->data))
        
        if (
            !key_exists(self::NOM_REF, $this->data) || !key_exists(self::CAPITALE_REF, $this->data) ||
            !key_exists(self::CONTINENT_REF, $this->data) || !key_exists(self::LANGUE_REF, $this->data) ||
            !key_exists(self::SUPERFICIE_REF, $this->data) || !key_exists(self::POPULATION_REF, $this->data) ||
            !key_exists(self::MONNAIE_REF, $this->data) || !key_exists(self::IMAGE_REF, $this->data)
            ) 
            {
                throw new Exception("Données incomplètes");
            }
          else
          {
            $a = htmlspecialchars($this->data[self::NOM_REF]);
            $b = htmlspecialchars($this->data[self::CAPITALE_REF]);
            $c = htmlspecialchars($this->data[self::CONTINENT_REF]);
            $d = htmlspecialchars($this->data[self::LANGUE_REF]);
            $e = htmlspecialchars($this->data[self::SUPERFICIE_REF]);
            $f = htmlspecialchars($this->data[self::POPULATION_REF]);
            $g = htmlspecialchars($this->data[self::MONNAIE_REF]);
            $h = htmlspecialchars($this->data[self::IMAGE_REF]);

            return new Pays($a, $b, $c, $d, $e, $f, $g, $h);
              
          }

	}

    
    /**
     * la fonction isValid() permet de vérifier si les données saisies dans le formulaire sont valides
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
     * @version 1.0.0
     */
    public function isValid() 
    {
		$this->errors = array();

        $remplir = "VEUILLEZ REMPLIR CE CHAMP SVP !";
        $moins_de_30 = "CE CHAMP DOIT FAIRE MOINS DE 30 CARACTÈRES SVP !";
        $borne_superficie = "SUPERFICIE ENTRE 0 ET 20 MILLION (20 000 000) km² SVP !";

        // ====== TRAITEMENT POUR LES CHAMPS DE TYPE STRING ====================
		if (!key_exists(self::NOM_REF, $this->data) || $this->data[self::NOM_REF] === "")
			$this->errors[self::NOM_REF] = $remplir;
		else if (mb_strlen($this->data[self::NOM_REF], 'UTF-8') >= 30)
			$this->errors[self::NOM_REF] = $moins_de_30;

        if (!key_exists(self::CAPITALE_REF, $this->data) || $this->data[self::CAPITALE_REF] === "")
			$this->errors[self::CAPITALE_REF] = $remplir;
		else if (mb_strlen($this->data[self::CAPITALE_REF], 'UTF-8') >= 30)
			$this->errors[self::CAPITALE_REF] = $moins_de_30;
        
        if (!key_exists(self::CONTINENT_REF, $this->data) || $this->data[self::CONTINENT_REF] === "")
			$this->errors[self::CONTINENT_REF] = $remplir;
		else if (mb_strlen($this->data[self::CONTINENT_REF], 'UTF-8') >= 30)
			$this->errors[self::CONTINENT_REF] = $moins_de_30;

        if (!key_exists(self::LANGUE_REF, $this->data) || $this->data[self::LANGUE_REF] === "")
			$this->errors[self::LANGUE_REF] = $remplir;
		else if (mb_strlen($this->data[self::LANGUE_REF], 'UTF-8') >= 30)
			$this->errors[self::LANGUE_REF] = $moins_de_30;

        if (!key_exists(self::POPULATION_REF, $this->data) || $this->data[self::POPULATION_REF] === "")
            $this->errors[self::POPULATION_REF] = $remplir;
        else if (!is_numeric($this->data[self::POPULATION_REF]))
            $this->errors[self::POPULATION_REF] = "VEUILLEZ SAISIR UN NOMBRE SVP !";
        else if ($this->data[self::POPULATION_REF] < 0)
            $this->errors[self::POPULATION_REF] = "VEUILLEZ SAISIR UN NOMBRE POSITIF SVP !";


        if (!key_exists(self::MONNAIE_REF, $this->data) || $this->data[self::MONNAIE_REF] === "")
            $this->errors[self::MONNAIE_REF] = $remplir;
        else if (mb_strlen($this->data[self::MONNAIE_REF], 'UTF-8') >= 30)
            $this->errors[self::MONNAIE_REF] = $moins_de_30;

        

        

        // ============= TRAITEMENT POUR LE CHAMP DE TYPE INT ==========================

        if (!key_exists(self::SUPERFICIE_REF, $this->data))
			$this->errors[self::SUPERFICIE_REF] = $remplir;
		else if ($this->data[self::SUPERFICIE_REF] < 0 || $this->data[self::SUPERFICIE_REF] > 20000000)
			$this->errors[self::SUPERFICIE_REF] = $borne_superficie;

        // ============= TRAITEMENT POUR LE CHAMP DE TYPE IMAGE ==========================

            $path='../upload/';

            $tempory_name = $_FILES[self::IMAGE_REF]['tmp_name'];
    
            if(!is_uploaded_file($tempory_name))
            {
    
                $this->errors[self::IMAGE_REF] = "VEUILLEZ SELECTIONNER UNE IMAGE SVP !";
            }
            else{
    
                $name = $_FILES[self::IMAGE_REF]['name'];
    
                $extension = strrchr($name, '.');
    
                $extension = strtolower($extension);
    
                $extensions = array('.png', '.jpg', '.jpeg');
    
                if(!in_array($extension, $extensions))
                {
    
                    $this->errors[self::IMAGE_REF] = "LES EXTENSIONS AUTORISEES SONT : .png, .jpg, .jpeg";
                }
                else
                {
    
                    $name = md5(uniqid(rand(), true));
    
                    $name = $name.$extension;
    
                    $result = move_uploaded_file($tempory_name, $path.$name);
    
                    if(!$result)
                    {
                        $this->errors[self::IMAGE_REF] = "ERREUR D'UPLOAD !";
                    }
                    else
                    {
                        $this->data[self::IMAGE_REF] = $name;
                    }
                }

                return count($this->errors) === 0;

            }
            
        
		 
	}

    /* Renvoie la valeur d'un champ en fonction de la référence passée en argument. */
	public function getData($ref) {
		return key_exists($ref, $this->data)? $this->data[$ref]: '';
	}

    /* Renvoie les erreurs associées au champ de la référence passée en argument,
 	 * ou null s'il n'y a pas d'erreur.
 	 * Nécessite d'avoir appelé isValid() auparavant. */
	public function getErrors($ref) {
		return key_exists($ref, $this->errors)? $this->errors[$ref]: null;
	}

    /* Met à jour une instance de Pays avec les données
	 * fournies. */
	public function updatePays(Pays $p) 
    {
		if (key_exists(self::NOM_REF, $this->data))
			$p->setNom($this->data[self::NOM_REF]);

		if (key_exists(self::CAPITALE_REF, $this->data))
			$p->setCapitale($this->data[self::CAPITALE_REF]);

        if (key_exists(self::CONTINENT_REF, $this->data))
            $p->setContinent($this->data[self::CONTINENT_REF]);

        if (key_exists(self::LANGUE_REF, $this->data))
            $p->setLangue($this->data[self::LANGUE_REF]);
            
        if (key_exists(self::SUPERFICIE_REF, $this->data))
            $p->setSuperficie($this->data[self::SUPERFICIE_REF]);

        if (key_exists(self::POPULATION_REF, $this->data))
            $p->setPopulation($this->data[self::POPULATION_REF]);

        if (key_exists(self::MONNAIE_REF, $this->data))
            $p->setMonnaie($this->data[self::MONNAIE_REF]);
        
        if (key_exists(self::IMAGE_REF, $this->data))
            $p->setImg($this->data[self::IMAGE_REF]);
	}





}

