package gov.cms.ab2d.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponseDto {
    private int statusCode;
    private String description;
}
