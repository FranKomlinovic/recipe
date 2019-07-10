package hr.brocom.recept.model;

public class UserDto {
    private String  nickname;
    private String  mail;
    private String  address;
    private Boolean active;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
        return "UserDto{" + "nickname='" + nickname + '\'' + ", mail='" + mail + '\'' + ", address='" + address + '\'' + ", active=" + active + '}';
    }
}


