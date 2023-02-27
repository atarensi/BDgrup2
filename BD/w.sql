-- Mostra als candidats suplent de cada provincia
SELECT CONCAT(per.nom,' ',per.cog1 ,' ',per.cog2) AS nom_suplent,per.persona_id AS Id_persona, prov.nom AS nom_provincia
	FROM persones per
	INNER JOIN candidats c ON c.persona_id = per.persona_id
	INNER JOIN provincies prov ON prov.provincia_id = c.provincia_id
WHERE c.tipus = 'S';


-- CATEGORIA 3

-- Categoria 3: 5 preguntes fent ús de subconsultes (1 punt)
-- Mostra les persones que no son candidates
SELECT persona_id, nom
	FROM persones
WHERE persona_id NOT IN (SELECT DISTINCT candidat_id
								FROM candidats
							WHERE candidat_id IS NOT NULL);
                                
-- Mostra les provincies i comunitats que tenen candidats Suplents
SELECT p.provincia_id, p.nom AS nom_provincia, c.nom AS nom_comunitat
	FROM provincies p 
	INNER JOIN comunitats_autonomes c ON c.comunitat_aut_id = p.comunitat_aut_id
    WHERE p.provincia_id = (SELECT c.provincia_id
								FROM candidats c
                            WHERE tipus = 'S');


-- Mostra les persones que han sigut candidates mes de 2 vegades 
-- mal
SELECT persona_id AS Id_persona, CONCAT(nom,' ',cog1 ,' ',cog2) AS Nom_complet
	FROM persones
WHERE persona_id IN ( SELECT candidat_id
						FROM candidats
					GROUP BY candidat_id
					HAVING COUNT(*) < 2);
                    
