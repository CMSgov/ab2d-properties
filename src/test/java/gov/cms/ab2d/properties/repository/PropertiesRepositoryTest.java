package gov.cms.ab2d.properties.repository;

import gov.cms.ab2d.properties.AB2DPostgresqlContainer;
import gov.cms.ab2d.properties.model.Property;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Testcontainers
class PropertiesRepositoryTest {
    @Autowired
    private PropertiesRepository propertiesRepository;

    @Container
    private static final PostgreSQLContainer postgreSQLContainer = new AB2DPostgresqlContainer();

    @Test
    void testSave() {
        Property property = new Property();
        property.setKey("One");
        property.setValue("Two");
        Property savedProp = propertiesRepository.save(property);
        assertNotNull(savedProp);
        OffsetDateTime created = savedProp.getCreated();
        OffsetDateTime modified = savedProp.getModified();
        assertEquals(created.toInstant().toEpochMilli(), modified.toInstant().toEpochMilli(), 30);
        property.setValue("Three");
        Property nextSavedProp = propertiesRepository.save(property);
        assertNotNull(nextSavedProp);
        assertEquals(created, nextSavedProp.getCreated());
        assertNotEquals(modified, nextSavedProp.getModified());
    }
}
