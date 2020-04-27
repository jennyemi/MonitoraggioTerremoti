<!DOCTYPE html>
<html lang="en" >
    <script>
        function includeHTML() {
            var z, i, elmnt, file, xhttp;
            /*loop through a collection of all HTML elements:*/
            z = document.getElementsByTagName("*");
            for (i = 0; i < z.length; i++) {
                elmnt = z[i];
                /*search for elements with a certain atrribute:*/
                file = elmnt.getAttribute("w3-include-html");
                if (file) {
                    /*make an HTTP request using the attribute value as the file name:*/
                    xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (this.readyState == 4) {
                            if (this.status == 200) {
                                elmnt.innerHTML = this.responseText;
                            }
                            if (this.status == 404) {
                                elmnt.innerHTML = "Page not found.";
                            }
                            /*remove the attribute, and call this function once more:*/
                            elmnt.removeAttribute("w3-include-html");
                            includeHTML();
                        }
                    }
                    xhttp.open("GET", file, true);
                    xhttp.send();
                    /*exit the function:*/
                    return;
                }
            }
        }
        ;
    </script>
    <head>
        <meta charset="UTF-8">
        <title>Terremoti</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
        <link rel="stylesheet" href="Style/style.css">
        <link rel="stylesheet" href="Style/table.css">
        <link rel="stylesheet" href="Style/sidebar.css">

    </head>
    <body>
        <!-- partial:index.partial.html -->
        <nav class="nav">
            <div id="drop">
                <div>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
        </nav>
        <ul>
            <img src="1.jpg" height="175px" width="175px" style="border-radius: 100%; border:10px solid #fff;"></img>
            <!--
            <button type="submit" class="btn-custom-due" onclick="location.href = 'profilo.html'">Modifica Profilo</button>
            -->
            <br>
            <form action="Logout">
                <button type="submit" class="btn-custom"">Logout</button>
            </form>>
            <form action="Dati">
                <button type="submit" class="btn-custom-due"">Inserimento Dati</button>
            </form>
        </ul>

        <h1><strong>TERREMOTI</strong></h1>
        <p>Aggiornamenti sui terremoti in tutto il mondo</p>
        <form action="Menu">
            <select class="menuTendina" id="filtro" name="menu"">
                <option value="base">Seleziona Filtro</option>
                <option value="data">Data (Ultimo Mese) X</option>
                <option value="località">Località (Italia)</option>
                <option value="magnitudo">Magnitudo (>3)</option>
                <option value="profondità">Profondità (>10)</option>
            </select>
        </form>
        <nav class="lateral">
            <ul>
                <li class="azul"><a href="https://www.google.it/maps/place/Italia/@40.9571138,3.7280602,5z/data=!3m1!4b1!4m5!3m4!1s0x12d4fe82448dd203:0xe22cf55c24635e6f!8m2!3d41.87194!4d12.56738">Mappa<i class="fas fa-map" aria-hidden="true"></i></a></li>
                <!--
                <li class="azul"><a href="#">Cose<i class="fab fa-accessible-icon" aria-hidden="true"></i></a></li>
                <li class="azul"><a href="#">Cose<i class="fab fa-accessible-icon" aria-hidden="true"></i></a></li>
                <li class="azul"><a href="#">Cose<i class="fab fa-accessible-icon" aria-hidden="true"></i></a></li>
                -->
            </ul>
        </nav>

        <!--
         <form action="TableUpdate" method="post">
             <input type="submit" value="submit">
         </form>
        -->

        <div w3-include-html="dati.html"></div>
        <script>
            includeHTML();
        </script>

        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>
        <script  src="Js/script.js"></script>

    </body>
</html>
