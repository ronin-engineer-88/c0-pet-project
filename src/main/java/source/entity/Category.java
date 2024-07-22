package source.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "category")
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Category extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseCategory> courseCategories;
}
