package gov.cms.ab2d.properties.service;

import gov.cms.ab2d.properties.dto.PropertyDto;
import gov.cms.ab2d.properties.model.Property;
import gov.cms.ab2d.properties.repository.PropertiesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private PropertiesRepository propertiesRepository;

    public List<PropertyDto> getPropertiesDto() {
        List<Property> properties = propertiesRepository.findAll();
        return properties.stream()
                .map(p -> new PropertyDto(p.getKey(), p.getValue())).collect(Collectors.toList());
    }

    @Override
    public PropertyDto getProperty(String key) {
        Optional<Property> propOptional = propertiesRepository.findByKey(key);
        if (propOptional.isPresent()) {
            return new PropertyDto(key, propOptional.get().getValue());
        }
        return null;
    }

    public PropertyDto saveProperty(String key, String value) {
        Optional<Property> propOptional = propertiesRepository.findByKey(key);
        Property found;
        if (propOptional.isPresent()) {
            found = propOptional.get();
            found.setValue(value);
            found.setModified(OffsetDateTime.now());
        } else {
            found = new Property();
            found.setCreated(OffsetDateTime.now());
            found.setModified(OffsetDateTime.now());
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
