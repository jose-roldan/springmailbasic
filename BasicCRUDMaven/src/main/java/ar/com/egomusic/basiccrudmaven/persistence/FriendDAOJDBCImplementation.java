package ar.com.egomusic.basiccrudmaven.persistence;

import ar.com.egomusic.basiccrudmaven.model.Friend;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FriendDAOJDBCImplementation implements FriendDAO {

    private final Object lockOfTheConexion = new Object();
    private Connection conexion = null;
    private static FriendDAOJDBCImplementation persistenceManager = null;
    private static final Logger logger = Logger.getLogger(FriendDAOJDBCImplementation.class.getName());

    FriendDAOJDBCImplementation() {
    }

    public static FriendDAOJDBCImplementation getJDBCPersistenceManager() {
        if (persistenceManager == null) {
            persistenceManager = new FriendDAOJDBCImplementation();
        }
        return persistenceManager;
    }

    @Override
    public boolean setUp(String url, String driver, String user, String password) {
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, "O driver para o banco de dados não encontrado", ex);
            return false;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Falha ao abrir conexão com o banco de dados", ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean disconnect() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Conexão de banco de dados não fechada", ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean createFriend(Friend friend) {
        String query = "insert into\"FRIENDS\" values(?,?,?,?,?,?)";
        PreparedStatement statement;
        try {
            synchronized (lockOfTheConexion) {
                statement = conexion.prepareStatement(query);
            }
            statement.setString(1, friend.getName());
            statement.setString(2, friend.getAddress());
            statement.setString(3, friend.getPhone());
            statement.setString(4, friend.getComments());
            statement.setInt(5, friend.getAge());
            statement.setInt(6, friend.getFriendshipLevel());
            statement.execute();
            return true;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE,  "Erro ao crear o amigo", ex);
            return false;
        }
    }

    @Override
    public boolean deleteFriend(String name) {
        String query = "delete from \"FRIENDS\" where NAME = ?";
        PreparedStatement statement;
        try {
            synchronized (lockOfTheConexion) {
                statement = conexion.prepareStatement(query);
            }
            statement.setString(1, name);
            statement.execute();
            return true;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao deletar o amigo", ex);
            return false;
        }
    }

    @Override
    public boolean updateFriend(String name, Friend friend) {
        String query = null;
        query = "update \"FRIENDS\" SET NAME =?,ADDRESS = ?,PHONE = ?,AGE = ?,"
                + " FRIENDSHIP_LEVEL = ?, COMMENTS = ? where NAME = ?";
        PreparedStatement statement;
        try {
            synchronized (lockOfTheConexion) {
                statement = conexion.prepareStatement(query);
            }
            statement.setString(1, friend.getName());
            statement.setString(2, friend.getAddress());
            statement.setString(3, friend.getPhone());
            statement.setInt(4, friend.getAge());
            statement.setInt(5, friend.getFriendshipLevel());
            statement.setString(6, friend.getComments());
            statement.setString(7, name);
            statement.execute();
            return true;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao atualizar um amigo", ex);
            return false;
        }
    }

    @Override
    public Friend readFriend(String name) {
        String query = null;
        query = "select * from \"FRIENDS\" where NAME =?";
        PreparedStatement statement;
        ResultSet resultSet = null;
        Friend friend = null;
        try {
            synchronized (lockOfTheConexion) {
                statement = conexion.prepareStatement(query);
            }
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                friend = new Friend();
                friend.setAddress(resultSet.getString("ADDRESS"));
                friend.setComments(resultSet.getString("COMMENTS"));
                friend.setPhone(resultSet.getString("PHONE"));
                friend.setName(resultSet.getString("NAME"));
                friend.setAge(resultSet.getInt("AGE"));
                friend.setFriendshipLevel(resultSet.getInt("FRIENDSHIP_LEVEL"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Falha ao recuperar um amigo", ex);
            friend = null;

        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, "Error al cerrar la conexon a la base de datos", ex);
                }
            }
        }
        return friend;
    }

    public void setConnection(Connection conexion) {
        synchronized (lockOfTheConexion) {
            this.conexion = conexion;
        }
    }
}