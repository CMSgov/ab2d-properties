CREATE SEQUENCE IF NOT EXISTS property.hibernate_sequence START WITH 1 INCREMENT BY 1;

INSERT INTO property.properties (id, key, value)
VALUES ((select nextval('property.hibernate_sequence')), 'hpms.ingest.engaged', 'engaged'),
       ((select nextval('property.hibernate_sequence')), 'pcp.core.pool.size', 10),
       ((select nextval('property.hibernate_sequence')), 'pcp.max.pool.size', 150),
       ((select nextval('property.hibernate_sequence')), 'pcp.scaleToMax.time', 900),
       ((select nextval('property.hibernate_sequence')), 'maintenance.mode', 'false'),
       ((select nextval('property.hibernate_sequence')), 'worker.engaged', 'engaged'),
       ((select nextval('property.hibernate_sequence')), 'coverage.update.discovery', 'idle'),
       ((select nextval('property.hibernate_sequence')), 'coverage.update.queueing', 'idle'),
       ((select nextval('property.hibernate_sequence')), 'ZipSupportOn', 'false');

INSERT INTO property.properties(id, key, value, created, modified)
VALUES (nextval('property.hibernate_sequence'), 'coverage.update.override', 'false', current_timestamp, current_timestamp),
       (nextval('property.hibernate_sequence'), 'coverage.update.months.past', '3', current_timestamp, current_timestamp),
       (nextval('property.hibernate_sequence'), 'coverage.update.stuck.hours', '24', current_timestamp, current_timestamp);

SELECT setval('property.property_sequence', (SELECT max(id) FROM property.properties)+1);

