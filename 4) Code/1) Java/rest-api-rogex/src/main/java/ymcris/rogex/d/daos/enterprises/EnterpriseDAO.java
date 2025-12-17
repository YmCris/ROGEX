package ymcris.rogex.d.daos.enterprises;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import ymcris.rogex.e.models.enterprise.Enterprise;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;

/**
 * The EnterpriseDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
public class EnterpriseDAO extends GenericDAO<Enterprise> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_ENTERPRISE
            = "INSERT INTO enterprise (name, description, specific_commission, "
            + "logo, cover, hidden_all_comments)"
            + " VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_EXISTS_ENTERPRISE
            = "SELECT 1 FROM enterprise WHERE name = ?";

    private static final String SQL_GET_ENTERPRISE_BY_NAME
            = "SELECT * FROM enterprise WHERE name = ?";

    private static final String SQL_UPDATE_ENTERPRISE
            = "UPDATE enterprise SET description = ?, specific_commission = ?,"
            + " logo = ?, cover = ?, hidden_all_comments = ? WHERE name = ?";

    private static final String SQL_GET_ALL_ENTERPRISES
            = "SELECT * FROM enterprise";

    private static final String SQL_DELETE_ENTERPRISE
            = "DELETE FROM enterprise WHERE name = ?";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public EnterpriseDAO() {
        super(
                SQL_INSERT_ENTERPRISE,
                SQL_EXISTS_ENTERPRISE,
                SQL_GET_ENTERPRISE_BY_NAME,
                SQL_UPDATE_ENTERPRISE,
                SQL_GET_ALL_ENTERPRISES,
                SQL_DELETE_ENTERPRISE
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public void createEntity(Enterprise enterprise) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_ENTERPRISE)) {

            statement.setString(1, enterprise.getName());
            statement.setString(2, enterprise.getDescription());

            if (enterprise.getSpecificCommission() != null) {
                statement.setDouble(3, enterprise.getSpecificCommission());
            } else {
                statement.setNull(3, Types.DECIMAL);
            }

            statement.setBytes(4, enterprise.getLogo());
            statement.setBytes(5, enterprise.getCover());
            statement.setBoolean(6, enterprise.getHiddenAllComments());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing creating entity (EnterpriseDAO)"
                    + "because " + e.getMessage());
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, Enterprise enterprise) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_ENTERPRISE)) {

            statement.setString(1, enterprise.getDescription());

            if (enterprise.getSpecificCommission() != null) {
                statement.setDouble(2, enterprise.getSpecificCommission());
            } else {
                statement.setNull(2, Types.DECIMAL);
            }

            statement.setBytes(3, enterprise.getLogo());
            statement.setBytes(4, enterprise.getCover());
            statement.setBoolean(5, enterprise.getHiddenAllComments());
            statement.setString(6, primaryKeys[0]);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing updatingEnterprise (EnterpriseDAO)"
                    + "because " + e.getMessage());
        }
    }

    @Override
    protected Enterprise createEntity(ResultSet resultSet) {
        try {
            Double comission = null;
            if (resultSet.getDouble("specific_commission") != 0) {
                comission = resultSet.getDouble("specific_commission");
            }
            return new Enterprise(
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    comission,
                    resultSet.getBoolean("hidden_all_comments"),
                    resultSet.getBytes("logo"),
                    resultSet.getBytes("cover")
            );

        } catch (SQLException ex) {
            System.out.println("An exception of type " + ex.getClass().getName()
                    + " occurred while performing creating entity (EnterpriseDAO)"
                    + "because " + ex.getMessage());
            throw new RuntimeException("Error creating enterprise", ex);
        }
    }

}
