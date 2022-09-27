package gov.cms.ab2d.properties.service;

import gov.cms.ab2d.properties.dto.PropertyDto;

import java.util.List;

public interface PropertyService {
    List<PropertyDto> getPropertiesDto();
    PropertyDto getProperty(String key);
    PropertyDto saveProperty(String key, String value);
    void deleteProperty(String name);
}
