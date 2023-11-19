<!DOCTYPE html>
<html lang = "fr">

    <head>
      <meta charset="UTF-8" >
      <link rel = "stylesheet" href ="styles/stylecreateform.css" >
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
      <title class="titre"> <?php echo $this->title ; ?> </title>

    </head>

    <body>

      <nav class="menu">
        <ul>
          <?php
            foreach ($this->getMenu() as $key => $value) {
              echo '<li><a href="'.$value.'">'.$key.'</a></li>';
            }
          ?>
        </ul>
      </nav>
      <?php if ($this->feedback !== '') { ?>
	      <div class="feedback"> <?php echo $this->feedback; ?> </div>
      <?php } ?>
      <main>
      <h1>    <?php  echo $this->title  ?> </h1><br>
        <?php echo $this->contents ; ?>
      </main>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>

  </html>

