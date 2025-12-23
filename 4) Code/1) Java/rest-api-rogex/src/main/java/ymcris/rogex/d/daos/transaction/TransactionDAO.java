package ymcris.rogex.d.daos.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import ymcris.rogex.e.models.transaction.Transaction;
import ymcris.rogex.e.models.wallets.BanckType;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.h.utilities.exceptions.DAOException;

/**
 * The TransactionDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 20, 2025
 */
public class TransactionDAO extends GenericDAO<Transaction> {

    // CONSTANTS ---------------------------------------------------------------
    public static final String SQL_INSERT_TRANSACTION
            = "INSERT INTO wallet_transaction (transaction_date, amount, "
            + "wallet_name, description, wallet_banck, user_email) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SQL_EXISTS_TRANSACTION
            = "SELECT 1 FROM wallet_transaction WHERE wallet_name = ? AND "
            + "wallet_banck = ? AND user_email = ?";

    public static final String SQL_GET_TRANSACTION_FOR_USER
            = "SELECT * FROM wallet_transaction WHERE wallet_name = ? AND "
            + "wallet_banck = ? AND user_email = ?";

    public static final String SQL_UPDATE_TRANSACTION
            = null;

    public static final String SQL_GET_ALL_TRANSACTIONS_FOR_USER
            = "SELECT * FROM wallet_transaction WHERE user_email = ?";

    public static final String SQL_DELETE_TRANSACTION
            = null;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public TransactionDAO() {
        super(SQL_INSERT_TRANSACTION,
                SQL_EXISTS_TRANSACTION,
                SQL_GET_TRANSACTION_FOR_USER,
                SQL_UPDATE_TRANSACTION,
                SQL_GET_ALL_TRANSACTIONS_FOR_USER,
                SQL_DELETE_TRANSACTION
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public void createEntity(Transaction transaction) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement statement
                    = connection.prepareStatement(SQL_INSERT_TRANSACTION)) {

                statement.setTimestamp(1, Timestamp.valueOf(transaction.getTransactionDate()));
                statement.setDouble(2, transaction.getAmount());
                statement.setString(3, transaction.getWalletName());
                statement.setString(4, transaction.getDescription());
                statement.setString(5, transaction.getWalletBanck().name());
                statement.setString(6, transaction.getUserEmail());

                statement.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            throw new DAOException("Transaction error", e);
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, Transaction entity) {
        throw new UnsupportedOperationException("YOU CANT UPDATE A TRANSACTION.");
    }

    @Override
    protected Transaction createEntity(ResultSet resultSet) {
        try {
            return new Transaction(
                    resultSet.getTimestamp("transaction_date").toLocalDateTime(),
                    resultSet.getString("wallet_name"),
                    resultSet.getString("description"),
                    BanckType.valueOf(resultSet.getString("wallet_banck")),
                    resultSet.getString("user_email"),
                    resultSet.getDouble("amount")
            );
        } catch (SQLException ex) {
            throw new DAOException("Error creating transaction", ex);
        }
    }

}
