package gov.cms.ab2d.properties.service;

import gov.cms.ab2d.properties.dto.PropertyDto;
import gov.cms.ab2d.properties.model.Property;
import gov.cms.ab2d.properties.repository.PropertiesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {
    private PropertiesRepository propertiesRepository;

    public List<PropertyDto> getPropertiesDto() {
        List<Property> properties = propertiesRepository.findAll();
        return properties.stream()
                .map(p -> new PropertyDto(p.getKey(), p.getValue())).toList();
    }

    @Override
    public PropertyDto getProperty(String key) {
        Optional<Property> propOptional = propertiesRepository.findByKey(key);
        return propOptional.map(property -> new PropertyDto(key, property.getValue())).orElse(null);
    }

    public PropertyDto saveProperty(String key, String value) {
        if (!StringUtils.hasText(key) || !StringUtils.hasText(value)) {
            log.error("Invalid key/value combination: " + key + "/" + value);
            return null;
        }
        Optional<Property> propOptional = propertiesRepository.findByKey(key);
        Property found;
        if (propOptional.isPresent()) {
            found = propOptional.get();
            found.setValue(value);
        } else {
            found = new Property();
            found.setKey(key);
            found.setValue(value);
        }
        Property saved = propertiesRepository.save(found);
        return new PropertyDto(saved.getKey(), saved.getValue());
    }

    public void deleteProperty(String name) {
        Optional<Property> propOptional = propertiesRepository.findByKey(name);
        propOptional.ifPresent(property -> propertiesRepository.deleteById(property.getId()));
    }
}
