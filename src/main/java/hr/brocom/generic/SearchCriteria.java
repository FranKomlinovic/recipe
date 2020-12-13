package hr.brocom.generic;

import lombok.Data;

@Data
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;

    /**
     * Constructor.
     */
    public SearchCriteria() {
        // Empty constructor so it can be used as @RequestBody.
    }

    public SearchCriteria(final String key, final String operation, final Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
