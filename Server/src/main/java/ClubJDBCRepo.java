import java.util.Collection;
import java.util.HashMap;

public class ClubJDBCRepo implements IClubRepo{
    private HashMap<String, Club> clubMap;  //Key is name of club

    public ClubJDBCRepo() { clubMap = new HashMap<>(); }

    @Override
    public void addClub(Club club) {
        //TODO: Retrieve all data from the database
    }

    @Override
    public Collection<Club> getClubs() { return clubMap.values(); }

    @Override
    public Club getClub(String name) { return clubMap.get(name); }

    @Override
    public Club editClub(Club edit) throws Exception{
        //TODO: Update the club in the database as well as the HashMap
        try {
            if (edit.getName() == null)
                throw new Exception("Name cannot be blank");

            Club toEdit = clubMap.get(edit.getName());

            if(toEdit == null)
                throw new Exception("Club not found");

            if(edit.getLocation() != null) {
                toEdit.setLocation(edit.getLocation());
            }
            if(edit.getClubEmail() != null) {
                toEdit.setClubEmail(edit.getClubEmail());
            }

            if(edit.getDescription() != null) {
                toEdit.setDescription(edit.getDescription());
            }

            return toEdit;

        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean deleteClub(String name) {
        //TODO: SQL Command that deletes club from db here and return true if properly deleted, false otherwise
        //TODO: Delete the club from the HashMap
        clubMap.remove(name);
        return true;
    }

    @Override
    public boolean clubExist(String name) { return clubMap.containsKey(name); }
}
