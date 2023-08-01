package ar.com.egomusic.basiccrudmaven.persistence;

import ar.com.egomusic.basiccrudmaven.model.Friend;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FriendDAOFileImplementation implements FriendDAO {

    private Map<String, Friend> friendMap = new HashMap<String, Friend>();
    private String fileName;
    private static FriendDAOFileImplementation persistenceManager = null;

    private FriendDAOFileImplementation() {
    }

    public static FriendDAO getJDBCPersistenceManager() {
        if (persistenceManager == null) {
            persistenceManager = new FriendDAOFileImplementation();
        }
        return persistenceManager;
    }

    @Override
    public boolean setUp(String url, String driver, String user, String password) {
        this.fileName = url;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return true;
            }
            InputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            int numberOfPersons = (Integer) objectInputStream.readObject();
            for (int i = 0; i < numberOfPersons; i++) {
                Friend person = (Friend) objectInputStream.readObject();
                friendMap.put(person.getName(), person);
            }
            objectInputStream.close();
        } catch (Exception ex) {
            Logger.getLogger(FriendDAOFileImplementation.class.getName()).log(Level.SEVERE,
                    "Não foi possível carregar os dados do arquivo", ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean disconnect() {
        try {
            OutputStream outputStream = new FileOutputStream(new File(fileName));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(friendMap.size());
            for (Friend person : friendMap.values()) {
                objectOutputStream.writeObject(person);
            }
            objectOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(FriendDAOFileImplementation.class.getName()).log(Level.SEVERE,
                    "Não foi possível salvar os dados no arquivo", ex);
            return false;
        }
        return true;
    }

    @Override
    public synchronized boolean createFriend(Friend friend) {
        if (friendMap.containsKey(friend.getName())) {
            return false;
        } else {
            friendMap.put(friend.getName(), friend);
            return true;
        }
    }

    @Override
    public synchronized boolean deleteFriend(String name) {
        if (!friendMap.containsKey(name)) {
            return false;
        } else {
            friendMap.remove(name);
            return true;
        }
    }

    @Override
    public synchronized boolean updateFriend(String name, Friend friend) {
        if (!friendMap.containsKey(name)) {
            return false;
        } else {
            friendMap.put(friend.getName(), friend);
            return true;
        }
    }

    @Override
    public synchronized Friend readFriend(String name) {
        return friendMap.get(name);
    }
}
