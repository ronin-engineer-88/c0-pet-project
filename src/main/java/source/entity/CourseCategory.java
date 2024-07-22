package source.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "course_category")
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CourseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
