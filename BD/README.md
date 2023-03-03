<em>Aquesta carpeta conté l'estructura/modificacions i els selects de la Base de dades</em>

## BD_eleccions_v1.sql:
Com que els índexs ens donaven errors em decidid no afegir-los a l'estructura

## BD_eleccions_v2.sql:

__Alterem la taula de provincies__ 
1. provincia_id quitar NOT NULL
2. comunitat_aut_id quitar NOT NULL
3. codi_ine quitar NOT NULL

ALTER TABLE provincies
	MODIFY COLUMN provincia_id		TINYINT UNSIGNED AUTO_INCREMENT,
	MODIFY COLUMN comunitat_aut_id		TINYINT UNSIGNED,
	MODIFY COLUMN codi_ine			CHAR(2);
  
__Alterem la taula de municipis__ 
1. codi_ine quitar no null
2. Crear noves restriccións UNIQUE perque xxxx

ALTER TABLE municipis 
	MODIFY COLUMN codi_ine			CHAR(2),
	ADD CONSTRAINT uk_municipis_provincia_id UNIQUE (provincia_id),
   	ADD CONSTRAINT uk_municipis_districte UNIQUE (provincia_id);
    
__Alterem la taula d'eleccions municipis__
1. ADD CONSTRAINT pk_eleccions_municipis PRIMARY KEY (eleccio_id,municipi_id,num_meses,cens)
2. DROP CONSTRAINT uk_eleccions_municipis;

ALTER TABLE eleccions_municipis 
    	DROP PRIMARY KEY,
	ADD CONSTRAINT pk_eleccions_municipis PRIMARY KEY (eleccio_id,municipi_id,num_meses,cens),
    	DROP CONSTRAINT uk_eleccions_municipis;
