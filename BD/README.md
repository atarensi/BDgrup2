
CREATE TABLE provincies
	num_escons		TINYINT UNSIGNED COMMENT "Numero d'escons que li pertoquen a aquella provincia",

CREATE TABLE eleccions (
any			YEAR GENERATED ALWAYS AS (YEAR(data)) NOT NULL COMMENT "Any el qual s'han celebrat les eleccions",
    mes			TINYINT GENERATED ALWAYS AS (MONTH(data)) NOT NULL COMMENT "El mes que s'han celebrat les eleccions",
    

CREATE TABLE eleccions_municipis (
    vots_emesos			INT UNSIGNED COMMENT "Número total de vots realitzats en el municipi",
    vots_valids			INT UNSIGNED COMMENT "Número de vots es que tindran en compte: vots a candidatures + vots nuls",
    vots_candidatures	INT UNSIGNED COMMENT "Total de vots a les candidatures",

CREATE TABLE candidatures (
    nom_curt			VARCHAR(50) COMMENT "Sigles de la candidatura",
    nom_llarg			VARCHAR(150) COMMENT "Nom llarg de la candidatura (denominació)",
    codi_acumulacio_provincia	CHAR(6) COMMENT "Codi de la candidatura d'acumulació a nivell provincial.",
    codi_acumulacio_ca			CHAR(6) COMMENT "Codi de la candidatura d'acumulació a nivell de comunitat autònoma",
    
CREATE TABLE persones (
    sexe			ENUM('M','F') COMMENT "M=Masculí, F=Femení",


