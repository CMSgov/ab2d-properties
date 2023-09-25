CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

INSERT INTO property.properties (id, key, value)
VALUES ((select nextval('hibernate_sequence')), 'hpms.ingest.engaged', 'engaged'),
       ((select nextval('hibernate_sequence')), 'pcp.core.pool.size', 10),
       ((select nextval('hibernate_sequence')), 'pcp.max.pool.size', 150),
       ((select nextval('hibernate_sequence')), 'pcp.scaleToMax.time', 900),
       ((select nextval('hibernate_sequence')), 'maintenance.mode', 'false'),
       ((select nextval('hibernate_sequence')), 'worker.engaged', 'engaged'),
       ((select nextval('hibernate_sequence')), 'coverage.update.discovery', 'idle'),
       ((select nextval('hibernate_sequence')), 'coverage.update.queueing', 'idle'),
       ((select nextval('hibernate_sequence')), 'ZipSupportOn', 'false');

INSERT INTO property.properties(id, key, value, created, modified)
VALUES (nextval('hibernate_sequence'), 'coverage.update.override', 'false', current_timestamp, current_timestamp),
       (nextval('hibernate_sequence'), 'coverage.update.months.past', '3', current_timestamp, current_timestamp),
       (nextval('hibernate_sequence'), 'coverage.update.stuck.hours', '24', current_timestamp, current_timestamp)