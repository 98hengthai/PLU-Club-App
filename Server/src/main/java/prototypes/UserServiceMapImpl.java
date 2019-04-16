package prototypes;

import entities.User;

import java.util.Collection;
import java.util.HashMap;

public class UserServiceMapImpl implements UserService {
    @Override
    public void addUser(User user) {

    }

    @Override
    public Collection<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public User editUser(User user) throws UserException {
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public boolean userExist(String id) {
        return false;
    }
//    private HashMap<String, User> userMap;
//
//    public UserServiceMapImpl() {
//        userMap = new HashMap<>();
//    }
//
//    @Override
//    public void addUser(User user) {
//        userMap.put(user.getId(), user);
//    }
//
//    @Override
//    public Collection<User> getUsers() {
//        return userMap.values();
//    }
//
//    @Override
//    public User getUser(String id) {
//        return userMap.get(id);
//    }
//
//    @Override
//    public User editUser(User forEdit) throws UserException {
//        try {
//            if (forEdit.getId() == null)
//                throw new UserException("ID cannot be blank");
//
//            User toEdit = userMap.get(forEdit.getId());
//
//            if (toEdit == null)
//                throw new UserException("entities.User not found");
//
//            if (forEdit.getEmail() != null) {
//                toEdit.setEmail(forEdit.getEmail());
//            }
//            if (forEdit.getName() != null) {
//                toEdit.setName(forEdit.getName());
//            }
//            if (forEdit.getLastName() != null) {
//                toEdit.setLastName(forEdit.getLastName());
//            }
//            if (forEdit.getId() != null) {
//                toEdit.setId(forEdit.getId());
//            }
//
//            return toEdit;
//        } catch (Exception ex) {
//            throw new UserException(ex.getMessage());
//        }
//    }
//
//    @Override
//    public void deleteUser(String id) {
//        userMap.remove(id);
//    }
//
//    @Override
//    public boolean userExist(String id) {
//        return userMap.containsKey(id);
//    }

}