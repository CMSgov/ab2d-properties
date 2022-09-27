package gov.cms.ab2d.properties.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto {
    @NotNull
    public String key;

    @NotNull
    public String value;
}
