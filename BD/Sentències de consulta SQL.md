### Categoria 1 - Consultes simples:

``1- Mostra els candidats que tenen mes de 25 anys``

SELECT nom AS Nom, cog1 AS Cognom, TIMESTAMPDIFF(YEAR,data_naixement,CURDATE()) AS Edat
FROM persones
WHERE TIMESTAMPDIFF(YEAR,data_naixement,CURDATE()) >= 25;


``2- Mostra tota l'informació de les comunitats autonomes que contenen en el seu nom Cat``

SELECT *
FROM comunitats_autonomes
WHERE nom LIKE '%Cat%';

``3- Mostra el nom complet de totes les persones``

SELECT CONCAT(nom, ' ',cog1 , ' ' ,cog2) AS Nom_complet
FROM persones
WHERE sexe='F';


``4- Recompte de vots en el mes actual``

SELECT COUNT(eleccio_id) as Numero_de_eleccions
FROM eleccions
WHERE MONTH(data) = 11; 

``5- Mostra la quantitat de candidats per cada provincia``

SELECT provincia_id AS Id_provincia, COUNT(*) AS Num_candidats
FROM candidats
GROUP BY candidat_id
HAVING candidat_id IS NOT NULL
ORDER BY Num_candidats;


### Categoria 2 - Consultes de combinacions:

``1- Mostra al numero total de candidats per cada municipi``

SELECT p.provincia_id, COUNT(c.candidat_id) AS num_candidats
FROM provincies p
INNER JOIN candidats c ON c.provincia_id = p.provincia_id
INNER JOIN municipis m ON m.provincia_id = p.provincia_id
GROUP BY m.provincia_id
ORDER BY COUNT(c.candidat_id);

	
``2- Cantidad de mujeres que se han presentado en 2022``

SELECT COUNT(p.sexe) AS "vots dones"
FROM persones p
INNER JOIN candidats c ON c.persona_id = p.persona_id
INNER JOIN candidatures ca ON ca.candidatura_id = c.candidatura_id
INNER JOIN eleccions e ON e.eleccio_id = ca.eleccio_id
WHERE p.sexe = 'F' AND e.any = 2019
GROUP BY p.sexe;

``3- Nombre personas que viven en x Municipio``

SELECT p.nom,p.cog1,p.cog2
FROM persones p
INNER JOIN candidats c ON c.persona_id = p.persona_id
INNER JOIN provincies pr ON pr.provincia_id = c.provincia_id
INNER JOIN municipis m ON m.provincia_id = pr.provincia_id
WHERE m.nom = 'Blanes';

``4- Mostra als candidats suplent de cada provincia``

SELECT CONCAT(per.nom,' ',per.cog1 ,' ',per.cog2) AS nom_suplent,per.persona_id AS Id_persona, prov.nom AS nom_provincia
FROM persones per
INNER JOIN candidats c ON c.persona_id = per.persona_id
INNER JOIN provincies prov ON prov.provincia_id = c.provincia_id
WHERE c.tipus = 'S';

`` 5- Cantidad de municipios que son de Catalunya``

SELECT m.nom
FROM municipis m
INNER JOIN provincies p ON p.provincia_id = m.provincia_id
INNER JOIN comunitats_autonomes ca ON ca.comunitat_aut_id = p.comunitat_aut_id
WHERE ca.nom = 'Cataluña';

### Categoria 3 - Subconsultes:
``1- Mostra les persones que han sigut candidates mes de 2 vegades``

SELECT persona_id AS Id_persona, CONCAT(nom,' ',cog1 ,' ',cog2) AS Nom_complet
FROM persones
WHERE persona_id IN ( SELECT candidat_id
		FROM candidats
		GROUP BY candidat_id
		HAVING COUNT(*) < 2);

``2- Mostra les persones que no son candidates``

SELECT persona_id, nom
   FROM persones
WHERE persona_id NOT IN (SELECT DISTINCT candidat_id
               FROM candidats
             WHERE candidat_id IS NOT NULL);

``3- Mostrar el nom de les provincies que tenene més vots``

SELECT p.nom nom_provincies, vc.vots
FROM vots_candidatures_prov vc
INNER JOIN provincies p ON p.provincia_id = vc.provincia_id
WHERE vc.vots = (SELECT MAX(vots)
           FROM vots_candidatures_prov);
``4- Mostra les id de les eleccions de l'any 2022``
SELECT eleccio_id AS id_eleccio, vots_emesos AS Vots_emesos
FROM eleccions_municipis
WHERE eleccio_id IN ( SELECT eleccio_id
FROM eleccions
WHERE YEAR(data) = '2019'
);
		
``5- Mostra les provincies i comunitats que tenen candidats Suplents``

SELECT p.provincia_id, p.nom AS nom_provincia, c.nom AS nom_comunitat
	FROM provincies p
	INNER JOIN comunitats_autonomes c ON c.comunitat_aut_id = p.comunitat_aut_id
WHERE p.provincia_id = (SELECT c.provincia_id
			FROM candidats c
			WHERE tipus = 'S');

### Categoria 4 - Recursivitat:

``Mostrara l'informació de municipis de tot aquell que tingui el codi_ine entre el 5 i el 10``

WITH RECURSIVE codi (n) AS (
SELECT 2
UNION
SELECT n + 1
	FROM codi
WHERE n < 7
)
SELECT n, m.*
	FROM codi a
	INNER JOIN municipis m ON m.codi_ine = a.n;
