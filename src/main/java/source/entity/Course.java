package source.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import java.util.Set;

@Entity
@Table(name = "course")
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Course extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private Integer status;

    @Column(name = "slug")
    private String slug;

    @Column(name = "is_best_seller")
    private Boolean isBestseller;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCourse> userCourses;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SectionItem> sectionItems;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseSection> courseSections;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseCategory> courseCategories;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseTeacher> courseTeachers;
}
