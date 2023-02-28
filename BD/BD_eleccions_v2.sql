-- ???????????
-- Alterem la taula eleccions_municipis per que es generin les columnes
ALTER TABLE eleccions_municipis
MODIFY COLUMN vots_emesos INT UNSIGNED GENERATED ALWAYS AS (vots_valids + vots_blanc + vots_nuls) STORED,
MODIFY COLUMN vots_valids INT UNSIGNED GENERATED ALWAYS AS (vots_canditadures) STORED,
MODIFY COLUMN vots_candidatures	INT UNSIGNED GENERATED ALWAYS AS (vots_valids + vots_blanc) STORED;
