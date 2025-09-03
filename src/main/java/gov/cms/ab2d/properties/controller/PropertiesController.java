package gov.cms.ab2d.properties.controller;

import gov.cms.ab2d.properties.dto.PropertyDto;
import gov.cms.ab2d.properties.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import lombok.extern.slf4j.Slf4j;


@RestController
@AllArgsConstructor
@Slf4j
public class PropertiesController {
    private PropertyService propertyService;

    private static final ResponseEntity HTTP_200 = new ResponseEntity<>(HttpStatusCode.valueOf(200));

    @GetMapping("/health")
    ResponseEntity<List<PropertyDto>> listProperties() {
        log.info("Returning HTTP 200 with empty response for health()");
        return HTTP_200;
        //return new ResponseEntity<>(propertyService.getPropertiesDto(), HttpStatus.OK);
    }

    @GetMapping("/properties")
    ResponseEntity<List<PropertyDto>> listProperties() {
        log.info("Returning HTTP 200 with empty response for listProperties()");
        return HTTP_200;
        //return new ResponseEntity<>(propertyService.getPropertiesDto(), HttpStatus.OK);
    }

    @GetMapping("/properties/{key}")
    ResponseEntity<PropertyDto> listProperty(@PathVariable String key) {
        log.info("Returning HTTP 200 with empty response for listProperty()");

        return HTTP_200;
//        PropertyDto prop = propertyService.getProperty(key);
//        if (prop == null) {
//            return new ResponseEntity<>(new PropertyDto(), HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(prop, HttpStatus.OK);
    }

    @PostMapping("/properties")
    ResponseEntity<PropertyDto> save(@RequestParam String key, @RequestParam String value) {
        log.info("Returning HTTP 200 with empty response for save()");

        return HTTP_200;

        //return new ResponseEntity<>(propertyService.saveProperty(key, value), HttpStatus.OK);
    }

    @DeleteMapping("/properties/{key}")
    ResponseEntity<Boolean> delete(@PathVariable String key) {
        log.info("Returning HTTP 200 with empty response for delete()");

        return HTTP_200;
//        try {
//            propertyService.deleteProperty(key);
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
//        }
    }
}
