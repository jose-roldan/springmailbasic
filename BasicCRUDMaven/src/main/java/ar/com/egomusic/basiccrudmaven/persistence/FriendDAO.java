package ar.com.egomusic.basiccrudmaven.persistence;

import ar.com.egomusic.basiccrudmaven.model.Friend;

public interface FriendDAO {

    public boolean createFriend(Friend friend);

    public boolean deleteFriend(String name);

    public Friend readFriend(String name);

    public boolean updateFriend(String name, Friend friend);

    public boolean setUp(String url, String driver, String user, String password);

    public boolean disconnect();
}
