package JDBCRepo;
import Interfaces.IInterestsRepo;
import entities.Interests;
import com.google.gson.Gson;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterestJDBCRepo implements IInterestsRepo{
    @Override
    public String getInterests() {
        //TODO
        return null;
    }

    @Override
    public boolean createInterest(String interest) {
        //TODO
        return false;
    }

    @Override
    public boolean deleteInterest(String interest) {
        //TODO
        return false;
    }

    @Override
    public String interestExist(String interest) {
        //TODO
        return null;
    }
}
