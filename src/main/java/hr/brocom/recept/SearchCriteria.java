package hr.brocom.recept;

import lombok.Data;

@Data
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;

    public SearchCriteria(final String key, final String operation, final Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
