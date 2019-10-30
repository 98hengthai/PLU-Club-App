package entities;

public class UserInterest {
    private String userEmail;
    private String interestName;

    //Constructors
    public UserInterest(){}

    public UserInterest(String userEmail, String intName){
        this.userEmail = userEmail;
        this.interestName = intName;
    }

    //Getters and Setters
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }
}
