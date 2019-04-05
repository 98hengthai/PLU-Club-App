package entities;

public class ClubInterest {
    private String interestName;
    private String clubName;

    //Constructors
    public ClubInterest(){
    }

    public ClubInterest(String intName, String cName){
        this.interestName = intName;
        this.clubName = cName;
    }

    //Getters and Setters
    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }






}
