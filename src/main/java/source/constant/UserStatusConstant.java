package source.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum UserStatusConstant {

    ACTIVE(1, "active"),
    INACTIVE(2, "inactive"),
    BLOCKED(3, "blocked"),
    ;

    private final int status;
    private final String name;

    private static final Map<Integer, UserStatusConstant> mappingValue = new HashMap<>();

    static {
        for (UserStatusConstant status : UserStatusConstant.values()) {
            mappingValue.put(status.getStatus(), status);
        }
    }

    public static UserStatusConstant fromValue(Integer status) {
        return mappingValue.getOrDefault(status, null);
    }
}
