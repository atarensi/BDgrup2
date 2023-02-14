BEGIN;

### Categoria 1: 

``#### Mostra els candidats que tenen mes de xxx anys``

SELECT TIMESTAMPDIFF(YEAR,data_naixement,CURDATE()) AS Edat<br>
	FROM persones<br>
WHERE TIMESTAMPDIFF(YEAR,data_naixement,CURDATE()) >= 25;


``#### Mostra els municipis que tenen en el nom xxx``

SELECT *<br>
	FROM municipis;

``#### Mostra el nom complet dels candidats``

SELECT CONCAT(nom, ' ',cog1 , ' ' ,cog2) AS Nom_complet<br>
	FROM persones;

``#### Recompte de vots al mes X``

SELECT COUNT(eleccions_id) as vots<br>
	FROM eleccions<br>
WHERE MONTH (mes) = ?;

``#### Mostra la quantitat de candidats per cada provincia``

SELECT c.provincia_id, COUNT(*) AS num_candidats <br>
	  FROM candidats c<br>
	GROUP BY c.candidat_id<br>
    HAVING c.candidat_id IS NOT NULL<br>
    ORDER BY num_candidats;

### Categoria 2: 5 preguntes de consultes de combinacions de més d'una taula: INNER JOINS, LEFT JOINS. (1 punt)

``#### Mostra als candidats que han estat mes de xx vegades com a S = suplent``








-- CATEGORIA 3
-- Categoria 3: 5 preguntes fent ús de subconsultes (1 punt)







-- CATEGORIA 4
-- Categoria 4: 1 pregunta utilitzant WINDOW FUNCTIONS o recursivitat (1 punt)
WITH RECURSIVE codi (n) AS (
SELECT 1
UNION
SELECT n + 1
FROM codi
WHERE n < 10
)
SELECT n, m.*
	FROM codi a
    INNER JOIN municipis1 m ON m.codi_ine = a.n;


COMMIT;
