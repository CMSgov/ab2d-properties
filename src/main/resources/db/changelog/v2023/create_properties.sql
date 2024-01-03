CREATE SEQUENCE IF NOT EXISTS property.hibernate_sequence START WITH 1 INCREMENT BY 1;

INSERT INTO property.properties (id, key, value)
VALUES ((select nextval('property.hibernate_sequence')), 'hpms.ingest.engaged', 'engaged')
ON CONFLICT (key) DO UPDATE SET value = 'engaged';

INSERT INTO property.properties (id, key, value)
VALUES ((select nextval('property.hibernate_sequence')), 'pcp.core.pool.size', 10)
ON CONFLICT (key) DO UPDATE SET value = 10;

INSERT INTO property.properties (id, key, value)
VALUES ((select nextval('property.hibernate_sequence')), 'pcp.max.pool.size', 150)
ON CONFLICT (key) DO UPDATE SET value = 150;

INSERT INTO property.properties (id, key, value)
VALUES ((select nextval('property.hibernate_sequence')), 'pcp.scaleToMax', 900)
ON CONFLICT (key) DO UPDATE SET value = 900;

INSERT INTO property.properties (id, key, value)
VALUES ((select nextval('property.hibernate_sequence')), 'maintenance.mode', false)
ON CONFLICT (key) DO UPDATE SET value = false;

INSERT INTO property.properties (id, key, value)
VALUES ((select nextval('property.hibernate_sequence')), 'worker.engaged', 'engaged')
ON CONFLICT (key) DO UPDATE SET value = 'engaged';

INSERT INTO property.properties (id, key, value)
VALUES ((select nextval('property.hibernate_sequence')), 'coverage.update.discovery', 'idle')
ON CONFLICT (key) DO UPDATE SET value = 'idle';

INSERT INTO property.properties (id, key, value)
VALUES ((select nextval('property.hibernate_sequence')), 'coverage.update.queueing', 'idle')
ON CONFLICT (key) DO UPDATE SET value = 'idle';

INSERT INTO property.properties (id, key, value)
VALUES ((select nextval('property.hibernate_sequence')), 'ZipSupportOn', false)
ON CONFLICT (key) DO UPDATE SET value = false;


INSERT INTO property.properties(id, key, value, created, modified)
VALUES (nextval('property.hibernate_sequence'), 'coverage.update.override', false, current_timestamp, current_timestamp)
ON CONFLICT (key) DO UPDATE SET value = false;

INSERT INTO property.properties(id, key, value, created, modified)
VALUES (nextval('property.hibernate_sequence'), 'coverage.update.months.past', 3, current_timestamp, current_timestamp)
ON CONFLICT (key) DO UPDATE SET value = 3;

INSERT INTO property.properties(id, key, value, created, modified)
VALUES (nextval('property.hibernate_sequence'), 'coverage.update.stuck.hours', 24, current_timestamp, current_timestamp)
ON CONFLICT (key) DO UPDATE SET value = 24;

SELECT setval('property.property_sequence', (SELECT max(id) FROM property.properties)+1);

