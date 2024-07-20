package source.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest extends BasicRequest {

    @Length(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotNull(message = "Age cannot be null")
    private Integer age;

    @NotNull(message = "Status cannot be null")
    private Integer status; // ACTIVE/INACTIVE/BLOCKED

}
