INSERT INTO property.properties(id, key, value, created, modified)
VALUES (nextval('property.hibernate_sequence'), 'OptOutOn', 'false', current_timestamp, current_timestamp);

--rollback DELETE FROM properties WHERE key = 'OptOutOn';