ALTER TABLE term
ADD COLUMN current_version_id BIGINT REFERENCES term_version(id) ON DELETE SET NULL;