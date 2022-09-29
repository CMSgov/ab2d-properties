package gov.cms.ab2d.properties.controller;

import gov.cms.ab2d.properties.dto.PropertyDto;
import gov.cms.ab2d.properties.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PropertiesController {
    private PropertyService propertyService;

    @GetMapping("/properties")
    ResponseEntity<List<PropertyDto>> listProperties() {
        return new ResponseEntity<>(propertyService.getPropertiesDto(), HttpStatus.OK);
    }

    @GetMapping("/properties/{key}")
    ResponseEntity<PropertyDto> listProperty(@PathVariable String key) {
        PropertyDto prop = propertyService.getProperty(key);
        if (prop == null) {
            return new ResponseEntity<>(new PropertyDto(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(prop, HttpStatus.OK);
    }

    @PostMapping("/properties")
    ResponseEntity<PropertyDto> save(@RequestParam String key, @RequestParam String value) {
        return new ResponseEntity<>(propertyService.saveProperty(key, value), HttpStatus.OK);
    }

    @DeleteMapping("/properties/{key}")
    ResponseEntity<Boolean> delete(@PathVariable String key) {
        try {
            propertyService.deleteProperty(key);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
