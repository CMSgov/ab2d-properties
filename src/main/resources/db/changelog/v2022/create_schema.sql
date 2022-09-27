-- Create the new schema
CREATE SCHEMA IF NOT EXISTS property;

-- Create new table if it doesn't already exist
CREATE TABLE IF NOT EXISTS property.properties
(
    id bigint NOT NULL,
    key character varying(80) NOT NULL,
    value character varying(255) NOT NULL,
    created timestamp without time zone,
    modified timestamp without time zone,
    CONSTRAINT pk_properties PRIMARY KEY (id),
    CONSTRAINT uc_properties_key UNIQUE (key)
);

-- Create a sequence if it doesn't already exist
CREATE SEQUENCE IF NOT EXISTS property.property_sequence
    AS integer OWNED BY property.properties.id;
