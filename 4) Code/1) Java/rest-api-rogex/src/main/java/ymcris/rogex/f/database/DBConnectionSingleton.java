package ymcris.rogex.f.database;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * The DBConnectionSingleton class is the class responsible for use the pool
 * connections in tomcat and use the java drive manager
 *
 * @author YmCris
 * @see Connection
 * @since Dec 11, 2025
 */
public class DBConnectionSingleton {

    // CONSTANTS ---------------------------------------------------------------
    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "rogex";
    private static final String USER_NAME = "admindba";
    private static final String PASSWORD = "12345";
    private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;

    private static DBConnectionSingleton instance;

    // REFERENCE VARIABLES -----------------------------------------------------    
    private DataSource datasource;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    private DBConnectionSingleton() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            PoolProperties p = new PoolProperties();
            p.setUrl(URL);
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername(USER_NAME);
            p.setPassword(PASSWORD);
            p.setJmxEnabled(true);
            p.setTestWhileIdle(false);
            p.setTestOnBorrow(true);
            p.setValidationQuery("SELECT 1");
            p.setTestOnReturn(false);
            p.setValidationInterval(30000);
            p.setTimeBetweenEvictionRunsMillis(30000);
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMaxWait(10000);
            p.setRemoveAbandonedTimeout(60);
            p.setMinEvictableIdleTimeMillis(30000);
            p.setMinIdle(10);
            p.setLogAbandoned(true);
            p.setRemoveAbandoned(true);
            p.setJdbcInterceptors(
                    "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                    + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
            datasource = new DataSource(p);
            datasource.setPoolProperties(p);
        } catch (ClassNotFoundException ex) {
            System.getLogger(DBConnectionSingleton.class.getName())
                    .log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    // SPECIFIC METHODS --------------------------------------------------------
    /**
     * Method responsible for return the connection to the DB
     * @return connection
     */
    public Connection getConnection() {
        try {
            return datasource.getConnection();
        } catch (SQLException ex) {
            System.getLogger(DBConnectionSingleton.class.getName())
                    .log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    /**
     * Method responsible for return the unique instance of this class
     * @return Class instance to ger the connection
     */
    public static DBConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new DBConnectionSingleton();
        }
        return instance;
    }

}
