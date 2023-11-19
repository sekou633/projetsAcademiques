<?php 

/**
 * class qui impléments StockagePays pour stocker les données en base de données
 * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
 * @version 1.0.0
 */

class StockagePaysStub implements StockagePays {

    protected $db;

    public function __construct(){

        $this -> db = array
            (
                'guinee' => new Pays("Guinée", 'Conakry', 'Afrique', 'Français', 245857),
                'france' => new Pays('France', 'Paris', 'Europe','Français', 543940),
                'senegal' => new Pays('Senegal', 'Dakar', 'Afrique','Français', 196722),
                'japon' => new Pays('Japon', 'Tokyo', 'Asia','Japonais', 377915),
                'angleterre' => new Pays('Angleterre', 'London', 'Europe', 'Anglais', 243610),
            );
        
    }
    
    /** REDEFINITION */
    public function read($id)
    {
        if (key_exists($id, $this->db))
        {
            return $this -> db[$id] ;
        }

        return null ;
    }

    /** REDEFINITION */
    public function readAll()
    {
        return $this -> db;
    }

    /** REDEFINITION */
    public function create(Pays $pays)
    {

    }
    /** REDEFINITION */
    public function update($id, Pays $pays)
    {
        
    }
    /** REDEFINITION */
    public function delete($id)
    {
        
    }

}

