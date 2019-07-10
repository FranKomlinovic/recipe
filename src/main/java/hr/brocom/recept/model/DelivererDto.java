package hr.brocom.recept.model;

public class DelivererDto {
    private String code;
    private String first_name;
    private String last_name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "DelivererDto{" + "code='" + code + '\'' + ", first_name='" + first_name + '\'' + ", last_name='" + last_name + '\'' + '}';
    }
}
