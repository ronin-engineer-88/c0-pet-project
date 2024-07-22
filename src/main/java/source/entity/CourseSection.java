package source.entity;

import lombok.*;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "course_section")
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CourseSection extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "slug")
    private String slug;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "courseSection", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SectionItem> sectionItems;
}
