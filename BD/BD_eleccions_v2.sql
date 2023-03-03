ALTER TABLE provincies
	MODIFY COLUMN provincia_id	TINYINT UNSIGNED AUTO_INCREMENT,
	MODIFY COLUMN comunitat_aut_id	TINYINT UNSIGNED,
	MODIFY COLUMN codi_ine		CHAR(2);


ALTER TABLE municipis 
	MODIFY COLUMN codi_ine		CHAR(2),
	ADD CONSTRAINT uk_municipis_provincia_id UNIQUE (provincia_id),
   	ADD CONSTRAINT uk_municipis_districte UNIQUE (provincia_id);


ALTER TABLE eleccions_municipis 
    	DROP PRIMARY KEY,
	ADD CONSTRAINT pk_eleccions_municipis PRIMARY KEY (eleccio_id,municipi_id,num_meses,cens),
    	DROP CONSTRAINT uk_eleccions_municipis;
