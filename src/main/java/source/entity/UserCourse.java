package source.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "user_course")
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "register_at")
    private Date registerAt;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "review")
    private String review;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "payment_at")
    private Date paymentAt;
}
