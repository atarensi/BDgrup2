<em>Aquesta carpeta conté l'estructura/modificacions i els selects de la Base de dades</em>

## BD_eleccions_v1.sql:
Com que els índexs ens donaven errors em decidid no afegir-los a l'estructura

## BD_eleccions_v2.sql:

__Alterem la taula de provincies__ 
1. provincia_id traiem NOT NULL
2. comunitat_aut_id traiem NOT NULL
3. codi_ine traiem NOT NULL

  
__Alterem la taula de municipis__ 
1. codi_ine traiem NOT NULL
2. Crear noves restriccións UNIQUE perque xxxx


__Alterem la taula d'eleccions municipis__
1. ADD CONSTRAINT pk_eleccions_municipis PRIMARY KEY (eleccio_id,municipi_id,num_meses,cens)
2. DROP CONSTRAINT uk_eleccions_municipis;
