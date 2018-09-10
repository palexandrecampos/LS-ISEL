package pt.isel.ls.domain;

public abstract class Person {

    private int UserID;
    private String nameUser;
    private String emailUser;

    public Person(int UserID, String nameUser, String emailUser) {
        this.UserID = UserID;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

}
