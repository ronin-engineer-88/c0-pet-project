package source.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SectionStatusConstant {

    DRAFT("DRAFT"),
    PUBLISH("PUBLISH"),
            ;

    private final String value;
}
