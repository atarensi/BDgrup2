<em>Aquesta carpeta conté l'estructura/modificacions i els selects de la Base de dades</em>

## BD_eleccions_v1.sql:
Com que els índexs ens donaven errors em decidid no afegir-los a l'estructura <br>

__Taula eleccions__ <br>
Per que el anys i els mesos es generin automaticament a la taula he fet que agafin el any/mes de la columna data

## BD_eleccions_v2.sql:

__Alterem la taula de provincies__ 
1. provincia_id traiem NOT NULL
2. comunitat_aut_id traiem NOT NULL
3. codi_ine traiem NOT NULL

  
__Alterem la taula de municipis__ 
1. codi_ine traiem NOT NULL
2. codi_ine	el canviem el màxim de caracters a 7 
3. Crear noves restriccións UNIQUE


__Alterem la taula d'eleccions municipis__
1. Modifiquem la PRIMARY KEY perquè inclogui eleccio_id, municipi_id, num_meses i cens

__Alterem la taula de persones__ 
1. dni traiem NOT NULL
