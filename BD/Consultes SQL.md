BEGIN;

-- CATEGORIA 1

**Categoria 1: 5 preguntes de consultes simples: inclou una sola taula, funcions,
funcions d'agregat o grups. (0,5 punts)**

-- Mostra els candidats que tenen mes de xxx anys

SELECT TIMESTAMPDIFF(YEAR,data_naixement,CURDATE()) AS Edat

	FROM persones
	
WHERE TIMESTAMPDIFF(YEAR,data_naixement,CURDATE()) >= 25;


-- Mostra els municipis que tenen en el nom xxx
SELECT *
	FROM municipis;

-- Mostra el nom complet dels candidats
SELECT CONCAT(nom, ' ',cog1 , ' ' ,cog2) AS Nom_complet
	FROM persones;

-- Recompte de vots al mes X
SELECT COUNT(eleccions_id) as vots
	FROM eleccions
WHERE MONTH (mes) = ?;

-- CATEGORIA 2
/**Categoria 2: 5 preguntes de consultes de combinacions de més d'una taula:
INNER JOINS, LEFT JOINS. (1 punt)*/

-- Mostra als candidats que han estat mes de xx vegades com a S = suplent








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
