ALTER TABLE provincies
	MODIFY COLUMN provincia_id	TINYINT UNSIGNED AUTO_INCREMENT,
	MODIFY COLUMN comunitat_aut_id	TINYINT UNSIGNED,
	MODIFY COLUMN codi_ine		CHAR(2);


ALTER TABLE municipis 
	MODIFY COLUMN codi_ine		CHAR(5),
	ADD CONSTRAINT uk_municipis_provincia_id UNIQUE (municipi_id,provincia_id,codi_ine);


ALTER TABLE eleccions_municipis 
    	DROP PRIMARY KEY,
	ADD CONSTRAINT pk_eleccions_municipis PRIMARY KEY (eleccio_id,municipi_id,num_meses,cens),
    	DROP CONSTRAINT uk_eleccions_municipis;
        
ALTER TABLE persones
MODIFY COLUMN dni CHAR(10);

INSERT INTO eleccions (eleccio_id, nom, data)
	VALUES(1,'Eleccions generals espanya 2020','2019-11-24');
