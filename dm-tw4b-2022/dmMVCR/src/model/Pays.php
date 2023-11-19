<?php

    // Path: src/model/Pays.php

    /**
     * Classe representant un Pays
     * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya</a>
     * @version 1.0.0
     */

// =================== Début de la définition de la classe ===============================

     class Pays{

        protected string $nom, $capitale, $continent, $langueOfficielle, $monnaie, $img;
        protected int $superficie, $population;

        public function __construct(string $nom="", string $capitale="", string $continent ="", string $langueOfficielle="", int $superficie = 0, $population="", $monnaie="", string $img ="")
        {
            
            $this -> nom = $nom;
            
            $this -> capitale = $capitale;
            
            $this -> continent = $continent;
            
            $this -> langueOfficielle = $langueOfficielle;
            
            $this -> superficie = $superficie;

            $this -> population = $population;

            $this -> monnaie = $monnaie;
            
            $this -> img = $img;
        }

        /** Getters and Setters  */

        public function getNom():string
        {
            return $this -> nom;
        }

        public function setNom(string $nom)
        {
            if (!self::isChampValid($nom))
			    throw new Exception("Champ du nom INVALIDE !");
            $this -> nom = $nom ;
        }

        public function getCapitale():string
        {
            return $this -> capitale;
        }

        public function setCapitale(string $capitale)
        {
            if (!self::isChampValid($capitale))
			    throw new Exception("Champ de la capitale INVALIDE !");
            $this -> capitale = $capitale ;
        }

        public function getContinent() :string
        {
            return $this -> continent;
        }

        public function setContinent(string $continent)
        {
            if (!self::isChampValid($continent))
            {
			throw new Exception("Champ du continent INVALIDE !");
            }
            $this -> continent = $continent;
        }

        public function getSuperficie(): int
        {
            return $this -> superficie;
        }

        public function setSuperficie(int $superficie)
        {
            if (!self::isChampValid($superficie))
            {
			throw new Exception("Champ de la superficie INVALIDE !");
            }
            $this -> superficie = $superficie;
        }

        public function getLangue(): string
        {
            return $this -> langueOfficielle;
        }

        public function setLangue(string $langue)
        {
            if (!self::isChampValid($langue))
            {
			throw new Exception("Champ de langue INVALIDE !");
            }
            $this -> langueOfficielle = $langue;
        }

        public function getImg(): string
        {
            return $this -> img;
        }

        public function setImg(string $img)
        {
            if (!self::isImageValid($img))
            {
            throw new Exception("Champ de l'image INVALIDE !");
            }
            $this -> img = $img;
        }

        public function getPopulation(): int
        {
            return $this -> population;
        }

        public function setPopulation(int $population)
        {
            if (!self::isChampValid($population))
            {
            throw new Exception("Champ de la population INVALIDE !");
            }
            $this -> population = $population;
        }

        public function getMonnaie(): string
        {
            return $this -> monnaie;
        }

        public function setMonnaie(string $monnaie)
        {
            if (!self::isChampValid($monnaie))
            {
            throw new Exception("Champ de la monnaie INVALIDE !");
            }
            $this -> monnaie = $monnaie;
        }

        public static function isChampValid($champ) {
            return mb_strlen($champ, 'UTF-8') < 30 && $champ !== "" ;
        }

        public static function isImageValid($img) {
            return $img !== "" ;
        }

        public static function isSuperValid($sup) {
            return $sup >= 0 && $sup < 20000000 ;
        }

     }
    
 // =================== Fin de la définition de la classe ===============================


