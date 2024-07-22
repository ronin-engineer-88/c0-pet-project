package source.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course_teacher")
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CourseTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "is_assistant")
    private Boolean isAssistant;

    @Column(name = "join_at")
    private Date joinAt;
}
