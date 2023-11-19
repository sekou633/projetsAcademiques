<!DOCTYPE html>

<html>

    <head>
      <meta charset="utf-8">
      <link rel = "stylesheet" href ="styles/stylecreateform.css" >
      
      <!-- <title>  //echo $this -> title ; </title> -->
    </head>

    <!-- <body>  -->
        
        <form enctype="multipart/form-data" action="<?php  echo $this->r->getPaysSaveURL() ?> " method="POST">

              
            <fieldset> 
                <legend> AJOUTER VOTRE PAYS </legend><br>
            
            <label> * NOM DU PAYS : 
                <input  type="text" name="<?= $nameRef ?>" value="<?= self::htmlesc($pb->getData($nameRef)) ?>" placeholder="nom du pays"/>
                <?php 
                    $err = $pb->getErrors($nameRef);
                    if ($err !== null)
                    {
                        echo ' <span class="error">'.$err.'</span>';
                    }
 
                ?>
            </label><br>

            <label> * CAPITALE DU PAYS : 
                <input  type="text" name="<?= $capRef ?>" value="<?= self::htmlesc($pb->getData($capRef)) ?>" placeholder="capitale du pays"  />
                <?php 
                    $err = $pb->getErrors($capRef);
                    if ($err !== null)
                    {
                        echo ' <span class="error">'.$err.'</span>';
                    }
 
                ?>
            </label><br>

            <label> * CONTINENT DU PAYS :
                <input  type="text" name="<?= $contiRef ?>" value="<?= self::htmlesc($pb->getData($contiRef)) ?>" placeholder="continent du pays" />
                <?php 
                    $err = $pb->getErrors($contiRef);
                    if ($err !== null)
                    {
                        echo ' <span class="error">'.$err.'</span>';
                    }
 
                ?>
            </label><br>

            <label> * SUPERFICIE DU PAYS :
                <input  type="number" name="<?= $supRef ?>" value="<?= self::htmlesc($pb->getData($supRef)) ?>" placeholder="superficie du pays(nombre positif)"  />
                <?php 
                    $err = $pb->getErrors($supRef);
                    if ($err !== null)
                    {
                        echo ' <span class="error">'.$err.'</span>';
                    }
 
                ?>
            </label><br>

            <label>* LANGUE OFFICIELLE DU PAYS :
                <input type="text" name="<?= $langueRef ?>" value="<?= self::htmlesc($pb->getData($langueRef)) ?>" placeholder="langue officielle du pays"  />
                <?php 
                    $err = $pb->getErrors($langueRef);
                    if ($err !== null)
                    {
                        echo ' <span class="error">'.$err.'</span>';
                    }
 
                ?>
            </label><br>

            <label> * MONNAIE DU PAYS :
                <input type="text" name="<?= $monRef ?>" value="<?= self::htmlesc($pb->getData($monRef)) ?>" placeholder="monnaie du pays"  />
                <?php 
                    $err = $pb->getErrors($monRef);
                    if ($err !== null)
                    {
                        echo ' <span class="error">'.$err.'</span>';
                    }
 
                ?>
            </label><br>

            <label> * POPULATION DU PAYS :
                <input type="number" name="<?= $popRef ?>" value="<?= self::htmlesc($pb->getData($popRef)) ?>" placeholder="population du pays(nombre positif)"  />
                <?php 
                    $err = $pb->getErrors($popRef);
                    if ($err !== null)
                    {
                        echo ' <span class="error">'.$err.'</span>';
                    }
 
                ?>
            </label><br>

            <label>* IMAGE DU PAYS :
                <input type="file" name="<?= $imageRef ?>" value="<?= self::htmlesc($pb->getData($imageRef)) ?>" placeholder="Une image pour le pays"  />
                <?php 
                    $err = $pb->getErrors($imageRef);
                    if ($err !== null)
                    {
                        echo ' <span class="error">'.$err.'</span>';
                    }
 
                ?>
            </label><br>
            
            

            <section><br>
                <input type = "reset" name = "reset" value="ANNULER LA CREATION" />
                <input type = "submit" name = "submite" value="VALIDER LA CREATION" />
            </section>

            </fieldset>
        <footer>
            <p> les champs precédés de <strong>(*)</strong> sont obligatoires </p>
        </footer>
        </form>
       
    <!-- </body>  -->

  </html>

