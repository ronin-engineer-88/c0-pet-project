package source.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teacher")
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Teacher extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Integer status; // 'ACTIVE/INACTIVE/BLOCKED'

    @Column(name = "age")
    private String age;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseTeacher> courseTeachers;
}
