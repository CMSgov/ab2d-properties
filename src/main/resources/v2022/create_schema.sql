-- Create the new schema
CREATE SCHEMA IF NOT EXISTS property;

-- Create new table if it doesn't already exist
CREATE TABLE IF NOT EXISTS property.properties (like public.properties including all);

-- Create a sequence if it doesn't already exist
CREATE SEQUENCE IF NOT EXISTS property.property_sequence
    AS integer OWNED BY property.properties.id;

-- Update the sequence value to the greatest value of the existing properties
SELECT setval('property.property_sequence', max(id)) FROM property.properties;

-- Let Quicksight query this table if it ever wants to
GRANT SELECT ON property.properties TO ab2d_analyst;
