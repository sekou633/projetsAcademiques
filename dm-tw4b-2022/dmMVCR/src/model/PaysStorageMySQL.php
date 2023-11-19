<?php

/**
 * @file PaysStorageMySQL.php
 * @brief Ce fichier contient la classe PaysStorageMySQL
 * @version 1.0.0
 * @date 2022-11-18
 * @author <a href ="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
 *
 *
 */

class PaysStorageMySQL implements StockagePays{

    private $pdo;

    public function __construct($pdo){
        $this->pdo = $pdo;
    }

    // public function setPDO($pdo){
    //     $this->pdo = $pdo;
    // }

    public function readAll(){
        $sql = "SELECT * FROM pays";
        $stmt = $this->pdo->query($sql);
        $stmt->execute();
        $rows = $stmt->fetchAll();
        $data = array();
        foreach($rows as $row => $column){
            $pId = $column['id'];
            $pb = new PaysBuilder($column);
            $pays = $pb->createPays();
            $data[$pId] = $pays;
        }
        return $data;
       // throw new Exception("Methode non implementee");

    }

    public function read($id)
    {
        $sql = "SELECT * FROM pays WHERE id = :id";
        $stmt = $this->pdo->prepare($sql);
        $data = array(":id" => $id);
        $stmt->execute($data);
        $row = $stmt->fetch();
        $pb = new PaysBuilder($row);
        $pays = $pb->createPays();

        // if($row){
        //     $pays = new Pays($row['id'], $row['nom_p'], $row['capitale_p'], $row['conti']);
        //     return $pays;
        // }
        return $pays;
        //throw new Exception("Methode non implementee");
    }

    public function create(Pays $pays)
    {
        $sql = "INSERT INTO `pays` 
                (
                    nom_p, 
                    capitale_p, 
                    continent_p, 
                    langue_p, 
                    superficie_p, 
                    population_p, 
                    monnaie_p, 
                    image_p
                ) 
                VALUES
                (
                    :nom_p, 
                    :capitale_p, 
                    :continent_p, 
                    :langue_p, 
                    :superficie_p, 
                    :population_p, 
                    :monnaie_p, 
                    :image_p
                )";
        $stmt = $this->pdo->prepare($sql);
        $data = array(
            ":nom_p" => $pays->getNom(),
            ":capitale_p" => $pays->getCapitale(),
            ":continent_p" => $pays->getContinent(),
            ":langue_p" => $pays->getLangue(),
            ":superficie_p" => $pays->getSuperficie(),
            ":population_p" => $pays->getPopulation(),
            ":monnaie_p" => $pays->getMonnaie(),
            ":image_p" => $pays->getImg()
        );
        $stmt->execute($data);
        $id = $this->pdo->lastInsertId();
        return $id;
        //throw new Exception("Méthode non implémentée");
    }

    public function update($id, Pays $pays)
    {
        // if (!key_exists($id, $this->readAll()))
        // {
        //     throw new Exception("Pays inexistant");
        // }
        $sql = "UPDATE `pays` SET 
                    nom_p = :nom_p, 
                    capitale_p = :capitale_p, 
                    continent_p = :continent_p, 
                    langue_p = :langue_p, 
                    superficie_p = :superficie_p,
                    population_p = :population_p,
                    monnaie_p = :monnaie_p, 
                    image_p = :image_p 
                WHERE id = :id";
        $stmt = $this->pdo->prepare($sql);
        $data = array(
            ":id" => $id,
            ":nom_p" => $pays->getNom(),
            ":capitale_p" => $pays->getCapitale(),
            ":continent_p" => $pays->getContinent(),
            ":langue_p" => $pays->getLangue(),
            ":superficie_p" => $pays->getSuperficie(),
            ":population_p" => $pays->getPopulation(),
            ":monnaie_p" => $pays->getMonnaie(),
            ":image_p" => $pays->getImg()
        );
        $stmt->execute($data);
        return $stmt->rowCount();
    }

    public function delete($id)
    {
        // if (!key_exists($id, $this->readAll()))
        // {
        //     throw new Exception("Pays inexistant");
        // }
        $sql = "DELETE FROM `pays` WHERE id = :id";
        $stmt = $this->pdo->prepare($sql);
        $data = array(":id" => $id);
        $stmt->execute($data);
        return $stmt->rowCount();
    }

}

