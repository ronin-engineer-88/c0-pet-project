package source.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = "user_name")
    private String username;

    @Column(name = "age")
    private Integer age;

    @Column(name = "status")
    private Integer status; // ACTIVE/INACTIVE/BLOCKED

    @OneToMany(mappedBy = "user")
    private Set<UserCourse> userCourses;
}
