package ymcris.rogex.d.daos.sale;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ymcris.rogex.e.models.sale.Sale;
import ymcris.rogex.e.models.wallets.BanckType;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.h.utilities.exceptions.DAOException;

/**
 * The SaleDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 19, 2025
 */
public class SaleDAO extends GenericDAO<Sale> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_SALE
            = "INSERT INTO sale (videogame_price, sale_date, commission_percentage, "
            + "profit, user_email, wallet_name, wallet_banck, videogame_title, enterprise_name) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_EXISTS_SALE
            = "SELECT 1 FROM sale WHERE user_email = ? "
            + "AND videogame_title = ? AND enterprise_name = ?";

    private static final String SQL_GET_VIDEOGAME_SALE
            = "SELECT * FROM sale WHERE videogame_title = ? AND enterprise_name = ?";

    private static final String SQL_GET_ALL_USER_SALES
            = "SELECT * FROM sale WHERE user_email = ?";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public SaleDAO() {
        super(
                SQL_INSERT_SALE,
                SQL_EXISTS_SALE,
                SQL_GET_VIDEOGAME_SALE,
                null,
                SQL_GET_ALL_USER_SALES,
                null
        );
    }

    // SPECIFIC METHODS --------------------------------------------------------
    @Override
    public void createEntity(Sale sale) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement statement
                    = connection.prepareStatement(SQL_INSERT_SALE)) {

                statement.setDouble(1, sale.getVideogamePrice());
                statement.setDate(2, Date.valueOf(sale.getSaleDate()));
                statement.setDouble(3, sale.getCommissionPercentage());
                statement.setDouble(4, sale.getProfit());
                statement.setString(5, sale.getUserEmail());
                statement.setString(6, sale.getWalletName());
                statement.setString(7, sale.getWalletBanck().name());
                statement.setString(8, sale.getVideogameTitle());
                statement.setString(9, sale.getEnterpriseName());

                statement.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            throw new DAOException("Creating the sale in DB", e);
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, Sale sale
    ) {
        throw new UnsupportedOperationException("You cant edit a sale");
    }

    @Override
    protected Sale getEntity(ResultSet resultSet
    ) {
        try {
            return new Sale(
                    resultSet.getDouble("videogame_price"),
                    resultSet.getTimestamp("sale_date").toLocalDateTime().toLocalDate(),
                    resultSet.getDouble("commission_percentage"),
                    resultSet.getDouble("profit"),
                    resultSet.getString("user_email"),
                    resultSet.getString("videogame_title"),
                    resultSet.getString("enterprise_name"),
                    resultSet.getString("wallet_name"),
                    BanckType.valueOf(resultSet.getString("wallet_banck"))
            );
        } catch (SQLException e) {
            throw new DAOException("Getting sale in DB", e);
        }
    }

}
