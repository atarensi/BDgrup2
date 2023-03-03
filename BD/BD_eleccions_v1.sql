BEGIN;

DROP DATABASE IF EXISTS eleccions_generals_espanyoles;

CREATE DATABASE eleccions_generals_espanyoles;

USE eleccions_generals_espanyoles; 


CREATE TABLE comunitats_autonomes (
 	comunitat_aut_id 	TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
	nom					VARCHAR(45),
	codi_ine			CHAR(2) NOT NULL,
	CONSTRAINT pk_comunitats_autonomes PRIMARY KEY (comunitat_aut_id),
	CONSTRAINT uk_com_aut_codi_ine UNIQUE (codi_ine)    
);

CREATE TABLE provincies (
	provincia_id		TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
	comunitat_aut_id	TINYINT UNSIGNED NOT NULL,
	nom					VARCHAR(45),
	codi_ine			CHAR(2) NOT NULL,
	num_escons			TINYINT UNSIGNED COMMENT "Numero d'escons que li pertoquen a aquella provincia",
	-- INDEX idx_fk_provincies_comunitats_autonomes (comunitat_aut_id),
	CONSTRAINT pk_provincies PRIMARY KEY (provincia_id),
		CONSTRAINT  fk_provincies_comunitats_autonomes FOREIGN KEY (comunitat_aut_id) 
			REFERENCES comunitats_autonomes (comunitat_aut_id),
	CONSTRAINT uk_com_aut_codi_ine UNIQUE (codi_ine)
);

CREATE TABLE municipis (
	municipi_id			SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
	nom					VARCHAR(100),
	codi_ine			CHAR(3) NOT NULL,
	provincia_id		TINYINT UNSIGNED NOT NULL ,
	districte			CHAR(2),
	-- INDEX idx_fk_municipis_provincies1 (provincia_id),
	CONSTRAINT pk_municipis PRIMARY KEY (municipi_id),
		CONSTRAINT fk_municipis_provincies FOREIGN KEY (provincia_id) 
			REFERENCES provincies (provincia_id),
	CONSTRAINT uk_municipis_codi_ine UNIQUE (codi_ine)
);

CREATE TABLE eleccions (
	eleccio_id		TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
	nom				VARCHAR(45),
	data			DATE NOT NULL,
	any				YEAR GENERATED ALWAYS AS (YEAR(data)) NOT NULL COMMENT "Any el qual s'han celebrat les eleccions",
	mes				TINYINT GENERATED ALWAYS AS (MONTH(data)) NOT NULL COMMENT "El mes que s'han celebrat les eleccions",
    CONSTRAINT pk_eleccions PRIMARY KEY (eleccio_id),
	CONSTRAINT uk_eleccions_any_mes UNIQUE (any,mes), 
    CONSTRAINT uk_eleccions_data UNIQUE (data)
);

CREATE TABLE eleccions_municipis (
	eleccio_id			TINYINT UNSIGNED NOT NULL,
    municipi_id			SMALLINT UNSIGNED NOT NULL,
    num_meses			SMALLINT UNSIGNED,
    cens				INT UNSIGNED,
    vots_emesos			INT UNSIGNED COMMENT "Número total de vots realitzats en el municipi",
    vots_valids			INT UNSIGNED COMMENT "Número de vots es que tindran en compte: vots a candidatures + vots nuls",
    vots_candidatures	INT UNSIGNED COMMENT "Total de vots a les candidatures",
    vots_blanc			INT UNSIGNED,
    vots_nuls			INT UNSIGNED,
    -- INDEX idx_fk_eleccions_municipis_eleccions (eleccio_id),
    -- INDEX fk_eleccions_municipis_municipis (municipi_id),
    CONSTRAINT pk_eleccions_municipis PRIMARY KEY (eleccio_id,municipi_id),
		CONSTRAINT fk_eleccions_municipis_municipis FOREIGN KEY (municipi_id) 
			REFERENCES municipis (municipi_id),
        CONSTRAINT fk_eleccions_municipis_eleccions FOREIGN KEY (eleccio_id) 
			REFERENCES eleccions (eleccio_id),
	CONSTRAINT uk_eleccions_municipis UNIQUE (eleccio_id)
);

CREATE TABLE candidatures (
	candidatura_id			INT UNSIGNED NOT NULL AUTO_INCREMENT,
    eleccio_id				TINYINT UNSIGNED NOT NULL,
    codi_candidatura		CHAR(6),
    nom_curt				VARCHAR(50) COMMENT "Sigles de la candidatura",
    nom_llarg				VARCHAR(150) COMMENT "Nom llarg de la candidatura (denominació)",
    codi_acumulacio_provincia	CHAR(6) COMMENT "Codi de la candidatura d'acumulació a nivell provincial.",
    codi_acumulacio_ca			CHAR(6) COMMENT "Codi de la candidatura d'acumulació a nivell de comunitat autònoma",
    codi_acumulario_nacional	CHAR(6),
    -- INDEX idx_fk_eleccions_partits_eleccions (eleccio_id),
    CONSTRAINT pk_eleccions PRIMARY KEY (candidatura_id),
		CONSTRAINT fk_eleccions_partits_eleccions FOREIGN KEY (eleccio_id) 
			REFERENCES eleccions (eleccio_id),
	CONSTRAINT uk_eleccions_partits UNIQUE (eleccio_id,codi_candidatura)
);

CREATE TABLE persones (
	persona_id			INT UNSIGNED NOT NULL,
    nom					VARCHAR(30) ,
    cog1				VARCHAR(30),
    cog2				VARCHAR(30),
    sexe				ENUM('M','F') COMMENT "M=Masculí, F=Femení",
    data_naixement		DATE,
    dni					CHAR(10) NOT NULL,
    CONSTRAINT pk_persones PRIMARY KEY (persona_id),
	CONSTRAINT uk_candidats_dni UNIQUE (dni)
);

CREATE TABLE candidats (
	candidat_id	BIGINT 	UNSIGNED NOT NULL AUTO_INCREMENT,
    candidatura_id		INT UNSIGNED NOT NULL,
    persona_id			INT UNSIGNED,
    provincia_id		TINYINT UNSIGNED,
    num_ordre			TINYINT,
    tipus				ENUM('T','S'),
    -- INDEX fk_candidats_provincies1_idx (provincia_id),
    -- INDEX fk_candidats_persones1_idx (persona_id),
    -- INDEX fk_candidats_candidatures1_idx (candidatura_id),
    CONSTRAINT pk_candidats PRIMARY KEY (candidat_id),
		CONSTRAINT fk_candidats_provincies1 FOREIGN KEY (provincia_id) 
			REFERENCES provincies (provincia_id),
		CONSTRAINT fk_candidats_persones1 FOREIGN KEY (persona_id) 
			REFERENCES persones (persona_id),
		CONSTRAINT fk_candidats_candidatures1 FOREIGN KEY (candidatura_id) 
			REFERENCES candidatures (candidatura_id),
	CONSTRAINT uk_candidats_persona_cand UNIQUE (candidatura_id,persona_id)
);

CREATE TABLE vots_candidatures_ca (
	comunitat_autonoma_id	TINYINT UNSIGNED NOT NULL,
	candidatura_id			INT UNSIGNED NOT NULL,
	vots					INT UNSIGNED,
	-- INDEX fk_comunitats_autonomes_has_candidatures_candidatures1_idx (candidatura_id),
	-- INDEX fk_comunitats_autonomes_has_candidatures_comunitats_autonom_idx (comunitat_autonoma_id),
	CONSTRAINT pk_vots_candidatures_ca PRIMARY KEY (comunitat_autonoma_id,candidatura_id),
	CONSTRAINT fk_comunitats_autonomes_has_candidatures_comunitats_autonomes1 FOREIGN KEY (comunitat_autonoma_id) 
		REFERENCES comunitats_autonomes (comunitat_aut_id),
	CONSTRAINT fk_comunitats_autonomes_has_candidatures_candidatures1 FOREIGN KEY (candidatura_id) 
		REFERENCES candidatures (candidatura_id)
);

CREATE TABLE vots_candidatures_prov (
	provincia_id		TINYINT UNSIGNED NOT NULL,
	candidatura_id		INT UNSIGNED NOT NULL,
	vots				INT UNSIGNED COMMENT "Número de vots obtinguts per la candidatura",
	candidats_obtinguts	SMALLINT UNSIGNED COMMENT "Número de candidats obtinguts per la candidatura",
	-- INDEX fk_candidatures_provincies_candidatures1_idx (candidatura_id),
	CONSTRAINT pk_vots_candidatures_prov PRIMARY KEY (provincia_id,candidatura_id),
		CONSTRAINT fk_candidatures_provincies_provincies1 FOREIGN KEY (provincia_id) 
			REFERENCES provincies (provincia_id),
		CONSTRAINT fk_candidatures_provincies_candidatures1 FOREIGN KEY (candidatura_id) 
			REFERENCES candidatures (candidatura_id)
);

CREATE TABLE vots_candidatures_mun (
	eleccio_id			TINYINT UNSIGNED NOT NULL,
	municipi_id			SMALLINT UNSIGNED NOT NULL,
	candidatura_id		INT UNSIGNED NOT NULL,
	vots				INT UNSIGNED COMMENT "Número de vots obtinguts per la candidatura",
	-- INDEX fk_candidatures_municipis_candidatures1_idx (candidatura_id),
    	-- INDEX fk_candidatures_municipis_eleccions_municipis1_idx (eleccio_id,municipi_id),
	CONSTRAINT pk_vots_candidatures_mun PRIMARY KEY (eleccio_id,municipi_id,candidatura_id),
		CONSTRAINT fk_candidatures_municipis_candidatures1 FOREIGN KEY (candidatura_id) 
			REFERENCES candidatures (candidatura_id),
		CONSTRAINT fk_candidatures_municipis_eleccions_municipis1 FOREIGN KEY (eleccio_id,municipi_id) 
			REFERENCES eleccions_municipis (eleccio_id,municipi_id)
);

COMMIT;

