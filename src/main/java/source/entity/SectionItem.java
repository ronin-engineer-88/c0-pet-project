package source.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "section_item")
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class SectionItem extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;   // 'DOCUMENT'/'VIDEO'

    @Column(name = "status")
    private String status; // 'DRAFT'/ 'PUBLISH'

    @Column(name = "url")
    private String url;    // document/ video url

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_section_id")
    private CourseSection courseSection;
}

