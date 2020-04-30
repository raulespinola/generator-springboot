package <%= packageName %>.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class <%= entityName %> {

    private int <%= entityVarName %>Id;
    private String field1;
    private String field2;
}
