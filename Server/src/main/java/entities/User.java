package entities;

public class User {
    private String name;
    private String email;
    private String gradYear;
    private boolean isStudent;

    //constructors
    public User() {
    }

    public User(String email, String name, String gradYear, boolean isStudent) {
        super();
        this.name = name;
        this.email = email;
        this.gradYear = gradYear;
        this.isStudent = isStudent;
    }

    //getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringBuffer().append(getEmail()).toString();
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public String getGradYear() {
        return gradYear;
    }

    public void setGradYear(String gradYear) {
        this.gradYear = gradYear;
    }
}
