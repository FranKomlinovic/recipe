package hr.brocom.recept.model;

public class UserDto {
    private String username;
    private String mail;
    private String address;
    private Boolean active;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserDto{" + "username='" + username + '\'' + ", mail='" + mail + '\'' + ", address='" + address + '\'' + ", active=" + active + '}';
    }
}


