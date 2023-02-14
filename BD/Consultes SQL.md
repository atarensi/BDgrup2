BEGIN;

### Categoria 1: 

``Mostra els candidats que tenen mes de xxx anys``

SELECT TIMESTAMPDIFF(YEAR,data_naixement,CURDATE()) AS Edat<br>
	FROM persones<br>
WHERE TIMESTAMPDIFF(YEAR,data_naixement,CURDATE()) >= 25;


``Mostra tota l'informació de municipis que tenen en el nom xxx``

SELECT *<br>
	FROM municipis<br>
WHERE nom = 'xxx';

``Mostra el nom complet dels candidats``

SELECT CONCAT(nom, ' ',cog1 , ' ' ,cog2) AS Nom_complet<br>
	FROM persones;

`` Recompte de vots al mes X``

SELECT COUNT(eleccions_id) as vots<br>
	FROM eleccions<br>
WHERE MONTH (mes) = ?;

``Mostra la quantitat de candidats per cada provincia``

SELECT c.provincia_id, COUNT(*) AS num_candidats <br>
	  FROM candidats c<br>
	GROUP BY c.candidat_id<br>
    HAVING c.candidat_id IS NOT NULL<br>
    ORDER BY num_candidats;

### Categoria 2:

``Mostra als candidats que han estat mes de xx vegades com a S = suplent``

**Cantidad de mujeres que se han presentado en 2022**
SELECT COUNT(p.sexe) AS "vots dones"
	FROM persones p
    INNER JOIN candidats c ON c.persona_id = p.persona_id
    INNER JOIN candidatures ca ON ca.candidatura_id = c.candidatura_id
    INNER JOIN eleccions e ON e.eleccio_id = ca.eleccio_id
WHERE p.sexe = 'F' AND e.any = 2022
GROUP BY p.sexe;



### Categoria 3:
``Mostra les persones que han sigut candidates mes de 2 vegades``
SELECT persona_id AS Id_persona, CONCAT(nom,' ',cog1 ,' ',cog2) AS Nom_complet<br>
	FROM persones<br>
WHERE persona_id IN (SELECT candidat_id<br>
						FROM candidats<br>
						GROUP BY candidat_id<br>
					HAVING COUNT(*) < 2);<br>






### Categoria 4:

``Mostrara l'informació de municipis de tot aquell que tingui el codi_ine entre el 5 i el 10``

WITH RECURSIVE codi (n) AS (<br>
SELECT 5<br>
UNION<br>
SELECT n + 1<br>
FROM codi<br>
WHERE n <= 10<br>
)<br>
SELECT n, m.*<br>
	FROM codi a<br>
    INNER JOIN municipis1 m ON m.codi_ine = a.n;


COMMIT;
