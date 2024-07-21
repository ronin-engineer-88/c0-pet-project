package source.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SessionTypeConstant {

    DOCUMENT("DOCUMENT","document"),
    VIDEO("VIDEO","video url"),
    ;

    private final String type;
    private final String url;
}
