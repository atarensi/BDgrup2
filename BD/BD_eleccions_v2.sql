ALTER TABLE provincies
	MODIFY COLUMN provincia_id	TINYINT UNSIGNED AUTO_INCREMENT,
	MODIFY COLUMN comunitat_aut_id	TINYINT UNSIGNED,
	MODIFY COLUMN codi_ine		CHAR(2);

ALTER TABLE municipis 
	MODIFY COLUMN codi_ine		CHAR(7);

        
ALTER TABLE persones
MODIFY COLUMN dni CHAR(10);

INSERT INTO eleccions (eleccio_id, nom, data)
	VALUES(1,'Eleccions generals espanya 2020','2019-11-24');

