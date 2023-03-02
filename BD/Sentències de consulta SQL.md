### Categoria 1 - Consultes simples:

``1- Mostra els candidats que tenen mes de 25 anys``

SELECT nom AS Nom, cog1 AS Cognom, TIMESTAMPDIFF(YEAR,data_naixement,CURDATE()) AS Edat <br>
&emsp;&emsp; FROM persones <br>
WHERE TIMESTAMPDIFF(YEAR,data_naixement,CURDATE()) >= 25;  
<br>


``2- Mostra tota l'informació de les comunitats autonomes que contenen en el seu nom Cat``

SELECT *<br>
&emsp;&emsp; FROM comunitats_autonomes<br>
WHERE nom LIKE '%Cat%';  
<br>

``3- Mostra el nom complet de totes les persones``

SELECT CONCAT(nom, ' ',cog1 , ' ' ,cog2) AS Nom_complet <br>
&emsp;&emsp; FROM persones <br>
WHERE sexe='F'; 
<br>

``4- Recompte de vots al mes maig``

SELECT COUNT(eleccions_id) as Vots <br>
&emsp;&emsp; FROM eleccions <br>
WHERE mes = 5; 
<br>

``5- Mostra la quantitat de candidats per cada provincia``

SELECT provincia_id AS Id_provincia, COUNT(*) AS Num_candidats <br>
&emsp;&emsp; FROM candidats <br>
GROUP BY candidat_id <br>
HAVING candidat_id IS NOT NULL <br>
ORDER BY Num_candidats; 
<br>


### Categoria 2 - Consultes de combinacions:

``1- Mostra al numero total de candidats per cada municipi``

SELECT p.provincia_id, COUNT(c.candidat_id) AS num_candidats <br>
&emsp;&emsp; FROM provincies p <br>
&emsp;&emsp; INNER JOIN candidats c ON c.provincia_id = p.provincia_id <br>
&emsp;&emsp; INNER JOIN municipis m ON m.provincia_id = p.provincia_id <br>
GROUP BY m.provincia_id <br>
ORDER BY COUNT(c.candidat_id); 
<br>
	
``2- Cantidad de mujeres que se han presentado en 2022``

SELECT COUNT(p.sexe) AS "vots dones" <br>
&emsp;&emsp; FROM persones p <br>
&emsp;&emsp; INNER JOIN candidats c ON c.persona_id = p.persona_id <br>
&emsp;&emsp; INNER JOIN candidatures ca ON ca.candidatura_id = c.candidatura_id <br>
&emsp;&emsp; INNER JOIN eleccions e ON e.eleccio_id = ca.eleccio_id <br>
WHERE p.sexe = 'F' AND e.any = 2022 <br>
GROUP BY p.sexe; 
<br>

``3- Nombre personas que viven en x Municipio``

SELECT p.nom,p.cog1,p.cog2 <br>
&emsp;&emsp; FROM persones p <br>
&emsp;&emsp; INNER JOIN candidats c ON c.persona_id = p.persona_id <br>
&emsp;&emsp; INNER JOIN provincies pr ON pr.provincia_id = c.provincia_id <br>
&emsp;&emsp; INNER JOIN municipis m ON m.provincia_id = pr.provincia_id <br>
WHERE m.nom = 'Blanes';
<br>

``4- Mostra als candidats suplent de cada provincia``

SELECT CONCAT(per.nom,' ',per.cog1 ,' ',per.cog2) AS nom_suplent,per.persona_id AS Id_persona, prov.nom AS nom_provincia <br>
&emsp;&emsp; FROM persones per <br>
&emsp;&emsp; INNER JOIN candidats c ON c.persona_id = per.persona_id <br>
&emsp;&emsp; INNER JOIN provincies prov ON prov.provincia_id = c.provincia_id <br>
WHERE c.tipus = 'S'; 
<br>

`` 5- Cantidad de municipios que son de Catalunya``

SELECT m.nom <br>
&emsp;&emsp; FROM municipis m <br>
&emsp;&emsp; INNER JOIN provincies p ON p.provincia_id = m.provincia_id <br>
&emsp;&emsp; INNER JOIN comunitats_autonomes ca ON ca.comunitat_aut_id = p.comunitat_aut_id <br>
WHERE ca.nom = 'Catalunya'; 
<br>

### Categoria 3 - Subconsultes:
``1- Mostra les persones que han sigut candidates mes de 2 vegades``

SELECT persona_id AS Id_persona, CONCAT(nom,' ',cog1 ,' ',cog2) AS Nom_complet <br>
&emsp;&emsp; FROM persones<br>
WHERE persona_id IN ( SELECT candidat_id<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; FROM candidats <br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; GROUP BY candidat_id <br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; HAVING COUNT(*) < 2); 
<br>

``2- Mostra les persones que no son candidates``

SELECT persona_id, nom <br>
&emsp;&emsp; FROM persones <br>
WHERE persona_id NOT IN (SELECT DISTINCT candidat_id <br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; FROM candidats <br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; WHERE candidat_id IS NOT NULL);
<br>

``3- Mostrar el nom de les provincies que tenene més vots``

SELECT p.nom nom_provincies, vc.vots <br>
&emsp;&emsp; FROM vots_candidatures_prov vc <br>
&emsp;&emsp; INNER JOIN provincies p ON p.provincia_id = vc.provincia_id <br>
WHERE vc.vots = (SELECT MAX(vots) <br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; FROM vots_candidatures_prov); 

``4- Mostra les id de les eleccions de l'any 2022``
SELECT eleccio_id AS id_eleccio, vots_emesos AS Vots_emesos <br>
&emsp;&emsp; FROM eleccions_municipis <br>
WHERE eleccio_id IN ( SELECT eleccio_id <br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; FROM eleccions <br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; WHERE YEAR(data) = 2022
		
``5- Mostra les provincies i comunitats que tenen candidats Suplents``

SELECT p.provincia_id, p.nom AS nom_provincia, c.nom AS nom_comunitat <br>
&emsp;&emsp; FROM provincies p  <br>
&emsp;&emsp; INNER JOIN comunitats_autonomes c ON c.comunitat_aut_id = p.comunitat_aut_id <br>
WHERE p.provincia_id = (SELECT c.provincia_id <br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; FROM candidats c <br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; WHERE tipus = 'S');

### Categoria 4 - Recursivitat:

``Mostrara l'informació de municipis de tot aquell que tingui el codi_ine entre el 5 i el 10``

WITH RECURSIVE codi (n) AS (<br>
SELECT 5<br>
UNION<br>
SELECT n + 1<br>
&emsp;&emsp; FROM codi<br>
WHERE n <= 10<br>
)<br>
SELECT n, m.*<br>
&emsp;&emsp; FROM codi a<br>
&emsp;&emsp; INNER JOIN municipis1 m ON m.codi_ine = a.n;
