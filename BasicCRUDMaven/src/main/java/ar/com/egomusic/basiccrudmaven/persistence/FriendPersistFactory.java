package ar.com.egomusic.basiccrudmaven.persistence;

public class FriendPersistFactory {

    public static FriendDAO getFriendDAO(String persistenceMechanism) {
        if (persistenceMechanism.equals("file")) {
            return FriendDAOFileImplementation.getJDBCPersistenceManager();
        } else if (persistenceMechanism.equals("JDBC")) {
            return FriendDAOJDBCImplementation.getJDBCPersistenceManager();
        } else if (persistenceMechanism.equals("pool")) {
            return FriendDAOPoolImplementation.getFriendDAOPoolImplementation();
        } else {
            return null;
        }
    }
}
