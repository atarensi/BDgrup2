### Categoria 1 - Consultes simples:

``1- Mostra les persones que el seu dni acaba amb z``

SELECT nom AS Nom, cog1 AS Cognom, dni <br>
FROM persones <br>
WHERE dni RLIKE '.[Z]';

``2- Mostra tota l'informació de les comunitats autonomes que contenen en el seu nom Cat``

SELECT * <br>
FROM comunitats_autonomes <br>
WHERE nom LIKE '%Cat%';

``3- Mostra el nom complet de totes les persones``

SELECT CONCAT(nom, ' ',cog1 , ' ' ,cog2) AS Nom_complet <br>
FROM persones <br>
WHERE sexe='F';


``4- El nombre de les eleccions que hi ha el mes 11``

SELECT COUNT(eleccio_id) as Numero_de_eleccions <br>
FROM eleccions <br>
WHERE MONTH(data) = 11; 

`` 5- Mostra la quantitat de candidats per cada provincia ordenat per el numero de candidats``

SELECT provincia_id AS Id_provincia, COUNT(*) AS Num_candidats
FROM candidats
GROUP BY provincia_id
ORDER BY Num_candidats;


### Categoria 2 - Consultes de combinacions:

``1- Mostra al numero total de candidats per cada municipi``

SELECT p.provincia_id, COUNT(c.candidat_id) AS num_candidats <br>
FROM provincies p <br>
INNER JOIN candidats c ON c.provincia_id = p.provincia_id <br>
INNER JOIN municipis m ON m.provincia_id = p.provincia_id <br>
GROUP BY m.provincia_id <br>
ORDER BY COUNT(c.candidat_id);

	
``2- Cantidad de mujeres que se han presentado en 2022``

SELECT COUNT(p.sexe) AS "vots dones" <br>
FROM persones p <br>
INNER JOIN candidats c ON c.persona_id = p.persona_id <br>
INNER JOIN candidatures ca ON ca.candidatura_id = c.candidatura_id <br>
INNER JOIN eleccions e ON e.eleccio_id = ca.eleccio_id <br>
WHERE p.sexe = 'F' AND e.any = 2019 <br>
GROUP BY p.sexe;

``3- Nombre personas que viven en x Municipio``

SELECT p.nom,p.cog1,p.cog2 <br>
FROM persones p <br>
INNER JOIN candidats c ON c.persona_id = p.persona_id <br>
INNER JOIN provincies pr ON pr.provincia_id = c.provincia_id <br>
INNER JOIN municipis m ON m.provincia_id = pr.provincia_id <br>
WHERE m.nom = 'Blanes';

``4- Mostra als candidats suplent de cada provincia``

SELECT CONCAT(per.nom,' ',per.cog1 ,' ',per.cog2) AS nom_suplent,per.persona_id AS Id_persona, prov.nom AS nom_provincia <br>
FROM persones per <br>
INNER JOIN candidats c ON c.persona_id = per.persona_id <br>
INNER JOIN provincies prov ON prov.provincia_id = c.provincia_id <br>
WHERE c.tipus = 'S';

`` 5- Cantidad de municipios que son de Catalunya``

SELECT m.nom <br>
FROM municipis m <br>
INNER JOIN provincies p ON p.provincia_id = m.provincia_id <br>
INNER JOIN comunitats_autonomes ca ON ca.comunitat_aut_id = p.comunitat_aut_id <br>
WHERE ca.nom = 'Cataluña';

### Categoria 3 - Subconsultes:
``1- Mostra les persones que han sigut candidates mes de 2 vegades``

SELECT persona_id AS Id_persona, CONCAT(nom,' ',cog1 ,' ',cog2) AS Nom_complet <br>
FROM persones <br>
WHERE persona_id IN ( SELECT candidat_id <br>
		FROM candidats <br>
		GROUP BY candidat_id <br>
		HAVING COUNT(*) < 2);

``2- Mostra les persones que no son candidates``

SELECT persona_id, nom <br>
   FROM persones <br>
WHERE persona_id NOT IN (SELECT DISTINCT candidat_id <br>
               FROM candidats <br>
             WHERE candidat_id IS NOT NULL);

``3- Mostrar el nom de les provincies que tenene més vots``

SELECT p.nom nom_provincies, vc.vots <br>
FROM vots_candidatures_prov vc <br>
INNER JOIN provincies p ON p.provincia_id = vc.provincia_id <br>
WHERE vc.vots = (SELECT MAX(vots) <br>
           FROM vots_candidatures_prov);
	   
``4- Mostra les id de les eleccions de l'any 2022``
SELECT eleccio_id AS id_eleccio, vots_emesos AS Vots_emesos <br>
FROM eleccions_municipis <br>
WHERE eleccio_id IN ( SELECT eleccio_id <br>
FROM eleccions <br>
WHERE YEAR(data) = '2019'
);
		
``5- Mostra les provincies i comunitats que tenen candidats Suplents``

SELECT p.provincia_id, p.nom AS nom_provincia, c.nom AS nom_comunitat <br>
	FROM provincies p <br>
	INNER JOIN comunitats_autonomes c ON c.comunitat_aut_id = p.comunitat_aut_id <br>
WHERE p.provincia_id = (SELECT c.provincia_id <br>
			FROM candidats c <br>
			WHERE tipus = 'S'); <br>

### Categoria 4 - Recursivitat:

``Mostrara l'informació de municipis de tot aquell que tingui el codi_ine entre el 2 i el 7``

WITH RECURSIVE codi (n) AS ( <br>
SELECT 2 <br>
UNION <br>
SELECT n + 1 <br>
	FROM codi <br>
WHERE n < 7 <br>
) <br>
SELECT n, m.* <br>
	FROM codi a <br>
	INNER JOIN municipis m ON m.codi_ine = a.n;
