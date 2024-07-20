package source.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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
}
