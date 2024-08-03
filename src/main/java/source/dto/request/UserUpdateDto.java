package source.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateDto {

    @NotNull(message = "Age cannot be null")
    private Integer age;

    @NotNull(message = "Status cannot be null")
    private Integer status; // ACTIVE/INACTIVE/BLOCKED

}
