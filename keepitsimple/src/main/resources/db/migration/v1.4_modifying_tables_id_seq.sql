CREATE SEQUENCE term_collection_id_seq START WITH 1 INCREMENT BY 1;

ALTER TABLE account
    ALTER COLUMN id SET DEFAULT nextval('account_id_seq');

ALTER TABLE term
    ALTER COLUMN id SET DEFAULT nextval('term_id_seq');

ALTER TABLE term_version
    ALTER COLUMN id SET DEFAULT nextval('term_version_id_seq');

ALTER TABLE term_collection
    ALTER COLUMN id SET DEFAULT nextval('term_collection_id_seq');