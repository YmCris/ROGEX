package ymcris.rogex.d.daos.system;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import ymcris.rogex.e.models.system.SystemConfig;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericSingletonDAO;

/**
 * The SystemConfigDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 15, 2025
 */
public class SystemConfigDAO extends GenericSingletonDAO<SystemConfig> {

    // CONSTATNS ---------------------------------------------------------------
    private static final String SQL_GET_CONFIG
            = "SELECT * FROM system_configuration WHERE id = 1";

    private static final String SQL_UPDATE_CONFIG
            = "UPDATE system_configuration "
            + "SET description = ?, global_commission_percentage = ? "
            + "WHERE id = 1";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public SystemConfigDAO() {
        super(
                SQL_GET_CONFIG,
                SQL_UPDATE_CONFIG
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public void updateEntity(String[] primaryKeys, SystemConfig systemConfig) {

        if (!systemConfig.isValid()) {
            throw new IllegalArgumentException("Invalid system configuration data");
        }

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_SINGLETON)) {

            statement.setString(1, systemConfig.getDescription());
            statement.setDouble(2, systemConfig.getGlobalCommissionPercentage());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating system configuration: " + e.getMessage());
        }
    }

    @Override
    protected SystemConfig createEntity(ResultSet resultSet) {
        try {
            SystemConfig systemConfig = new SystemConfig(
                    resultSet.getString("description"),
                    resultSet.getDouble("global_commission_percentage")
            );

            return systemConfig;

        } catch (SQLException e) {
            throw new RuntimeException("Error creating SystemConfig entity", e);
        }
    }

    public SystemConfig getSystemConfig() {
        return getSingleton().get();
    }
}
