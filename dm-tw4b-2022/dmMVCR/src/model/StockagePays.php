<?php 

// Path: src/model/StockagePays.php

/**
 * Interface qui permet de stocker les pays
 * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya</a>
 * @version 1.0.0
 */

 interface StockagePays{


    /**
     * fonction qui renvoie l'instance d'un pays qui a pour identifiant $id passé en paramètre
     * @author <a href="mailto:sdoumbouya633@gmail.com>" Sekou Doumbouya </a>
     * @version 1.0.0
     * @param $id, l'identifiant du pays
     * @return // pays ou null si $id n'existe pas dans $_GET ;
     */

     public function read($id);

     /**
     * fonction qui renvoie la liste de tous les pays existant dans la BD
     * @author <a href="mailto:sdoumbouya633@gmail.com>" Sekou Doumbouya </a>
     * @version 1.0.0
     * @return // tableau associatif contenant tous les pays de la BD ;
     */

     public function readAll();


     /** 
     * fonction qui permet d'ajouter un nouveau pays dans la base. Renvoie l'identifiant
	 * du pays ajouté. 
     * @author <a href = "mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya <a/>
     * @version 1.0.0
     * @param $pays, un nouveau pays
     * @return $id qui lui a été attribué
     * 
     */

    public function create(Pays $pays);

     /** 
     * fonction qui permet de mettre à jour un pays dans la base
     * @author <a href = "mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya <a/>
     * @version 1.0.0
     * @param $id, $pays
     * @return // True si la mise à jour effectuée, False sinon
     * 
     */

     public function update($id, Pays $pays);

     /** 
     * fonction qui permet de supprimer un pays dont l'id est passé en paramètre de la BD
     * @author <a href = "mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya <a/>
     * @version 1.0.0
     * @param $id
     * @return // True si la Suppression effectuée, False sinon
     * 
     */

     public function delete($id);

     /** 
     * fonction qui permet de supprimer tous les pays de la BD
     * @author <a href = "mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya <a/>
     * @version 1.0.0
     * 
     */

     //public function deleteAll();


 }



