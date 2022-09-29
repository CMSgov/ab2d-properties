package gov.cms.ab2d.properties.service;

import gov.cms.ab2d.properties.AB2DPostgresqlContainer;
import gov.cms.ab2d.properties.dto.PropertyDto;
import gov.cms.ab2d.properties.repository.PropertiesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Testcontainers
class PropertyServiceTest {
    @Autowired
    private PropertiesRepository propertiesRepository;

    @Container
    private static final PostgreSQLContainer postgreSQLContainer = new AB2DPostgresqlContainer();

    String key = "Hello";
    String value = "World";

    PropertyService service;

    @BeforeEach
    void init() {
        service = new PropertyServiceImpl(propertiesRepository);
        propertiesRepository.deleteAll();
    }

    @AfterEach
    void cleanUp() {
        propertiesRepository.deleteAll();
    }

    @Test
    void testTheDb() {
        List<PropertyDto> properties = service.getPropertiesDto();
        assertNotNull(properties);
        assertEquals(0, properties.size());
        assertNull(service.getProperty(null));
        assertNull(service.getProperty("NO_KEY"));
        assertNull(service.saveProperty(null, null));
        assertNull(service.saveProperty(null, value));
        PropertyDto savedProp = service.saveProperty(key, value);
        assertNotNull(savedProp);
        assertEquals(key, savedProp.getKey());
        assertEquals(value, savedProp.getValue());
        PropertyDto loadedProp = service.getProperty(key);
        assertEquals(key, loadedProp.getKey());
        assertEquals(value, loadedProp.getValue());
        properties = service.getPropertiesDto();
        assertNotNull(properties);
        assertEquals(1, properties.size());
        assertEquals(key, properties.get(0).getKey());
        assertEquals(value, properties.get(0).getValue());
        service.deleteProperty(key);
        properties = service.getPropertiesDto();
        assertNotNull(properties);
        assertEquals(0, properties.size());
    }

    @Test
    void testSaveAsSameAsInsert() {
        List<PropertyDto> properties = service.getPropertiesDto();
        assertNotNull(properties);
        assertEquals(0, properties.size());
        service.saveProperty(key, value);
        PropertyDto loadedProp = service.getProperty(key);
        loadedProp.setValue("New Value");
        service.saveProperty(loadedProp.getKey(), loadedProp.getValue());
        PropertyDto loadedProp2 = service.getProperty(key);
        assertEquals("New Value", loadedProp2.getValue());
        service.deleteProperty(key);
    }
}
