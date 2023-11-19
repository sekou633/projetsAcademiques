<?php

// Path: src/model/StockagePaysStub.php


/**
 * Classe qui permet de gérer le stockage des pays dans un fichier
 * @author <a href ="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
 * @version 1.0.0
 */

 class FichierStockagePays implements StockagePays{

    protected $db;

    public function __construct($file)
    {
        $res = file_exists($file);
        $this->db = new ObjectFileDB($file);
        if(!$res){
            $this->reinit();
        }
    }

    /** 
     * fonction qui permet d'ajouter un nouveau pays dans la base. Renvoie l'identifiant
	 * du pays ajouté. 
     * @author <a href = "mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya <a/>
     * @version 1.0.0
     * @param $pays, un nouveau pays
     * @return $id qui lui a été attribué
     * 
     */

	public function create(Pays $pays)
    {
        return $this->db->insert($pays);
	}

    /** 
     * fonction qui permet de retourner le pays d'identifiant $id
     * @author <a href = "mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya <a/>
     * @version 1.0.0
     * @param $id, l'identifiant du pays
     * @return // le pays d'identifiant $id ou null s'il n'existe pas
     * 
     */

    public function read($id) 
    {
		if ($this->db->exists($id)) {
			return $this->db->fetch($id);
        } else {
			return null;
        }
	}

    /** 
     * fonction qui permet de renvoyer un tableau id => Pays, contenant tous les pays de la base
     * @author <a href = "mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya <a/>
     * @version 1.0.0
     * @return // le tableau associatif contenant tous les pays de la base
     * 
     */

    public function readAll()
    {
		return $this->db->fetchAll();
	}


    /** 
     * fonction qui permet de mettre à jour un pays dans la base
     * @author <a href = "mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya <a/>
     * @version 1.0.0
     * @param $id, $pays
     * @return // True si la mise à jour effectuée, False sinon
     * 
     */

    public function update($id, Pays $pays): bool 
    {
		if ($this->db->exists($id)) 
        {
            $this->db->update($id, $pays);
			return true;
		}
		return false;
	}

    /** 
     * fonction qui permet de supprimer un pays dont l'id est passé en paramètre de la BD
     * @author <a href = "mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya <a/>
     * @version 1.0.0
     * @param $id
     * @return // True si la Suppression effectuée, False sinon
     * 
     */

    public function delete($id) : bool
    {
		if ($this->db->exists($id)) 
        {
			$this->db->delete($id);
			return true;
		}
		return false;
	}

    /** 
     * fonction qui permet de supprimer tous les pays de la BD
     * @author <a href = "mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya <a/>
     * @version 1.0.0
     * 
     */

	public function deleteAll() : void
    {
        $this->db->deleteAll();
	}

    public function reinit() : string
    {
        try
        {

            $tab = array
            (
                'guinee' => new Pays("Guinée", 'Conakry', 'Afrique', 'Français', 245857),
                'france' => new Pays('France', 'Paris', 'Europe','Français', 543940),
                'senegal' => new Pays('Senegal', 'Dakar', 'Afrique','Français', 196722),
                'japon' => new Pays('Japon', 'Tokyo', 'Asia','Japonais', 377915),
                'angleterre' => new Pays('Angleterre', 'London', 'Europe', 'Anglais', 243610),
            );

        $this -> deleteAll();

        foreach ($tab as $key => $pays){
            $this -> create($pays);
        }

        return "L'opération de Reinitialisation ok" ;

        }
        catch (Exception $e){
            return "L'opération de Reinitialisation a rencontré des erreurs";
        }
        
        
    }


 }


