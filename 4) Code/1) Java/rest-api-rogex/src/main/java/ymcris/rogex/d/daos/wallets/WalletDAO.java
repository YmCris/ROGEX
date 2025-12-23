package ymcris.rogex.d.daos.wallets;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import ymcris.rogex.e.models.users.User;
import ymcris.rogex.e.models.wallets.Wallet;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.e.models.wallets.BanckType;
import ymcris.rogex.f.database.DBConnectionSingleton;

/**
 * The WalletDAO class is the class responsible for be the wallet dao
 *
 * @author YmCris
 * @since Dec 14, 2025
 */
public class WalletDAO extends GenericDAO<Wallet> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_WALLET
            = "INSERT INTO wallet (banck, name, fund, user_email) "
            + "VALUES (?, ?, ?, ?)";

    private static final String SQL_EXISTS_WALLET
            = "SELECT 1 FROM wallet WHERE name = ? AND banck = ?";

    private static final String SQL_GET_BY_PK
            = "SELECT * FROM wallet WHERE banck = ? AND name = ?";

    private static final String SQL_UPDATE_WALLET
            = "UPDATE wallet SET fund = ? WHERE banck = ? AND name = ?";

    private static final String SQL_GET_ALL_WALLETS
            = "SELECT * FROM wallet";

    private static final String SQL_DELETE_WALLET
            = "DELETE FROM wallet WHERE banck = ? AND name = ?";

    private static final String SQL_GET_WALLET_BY_EMAIL
            = "SELECT * FROM wallet WHERE user_email = ?";

    private static final String SQL_GET_WALLET_BY_EMAIL_AND_WALLET
            = "SELECT * FROM wallet WHERE user_email = ?";

    // CONSTRUCTOR -------------------------------------------------------------
    public WalletDAO() {
        super(
                SQL_INSERT_WALLET,
                SQL_EXISTS_WALLET,
                SQL_GET_BY_PK,
                SQL_UPDATE_WALLET,
                SQL_GET_ALL_WALLETS,
                SQL_DELETE_WALLET
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public void createEntity(Wallet wallet) {

        if (!wallet.isValid()) {
            throw new IllegalArgumentException("Invalid wallet data");
        }

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_ENTITY)) {

            statement.setString(1, wallet.getBanck().name());
            statement.setString(2, wallet.getName());
            statement.setDouble(3, wallet.getFund());
            statement.setString(4, wallet.getUser().getEmail());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error creating wallet: " + e.getMessage());
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, Wallet wallet) {

        try (
                Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_ENTITY)) {

            statement.setDouble(1, wallet.getFund());
            statement.setString(2, primaryKeys[0]);
            statement.setString(3, primaryKeys[1]);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating wallet: " + e.getMessage());
        }
    }

    public void updateEntity(String[] primaryKeys, Double less) {

        try (
                Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_ENTITY)) {

            statement.setDouble(1, less);
            statement.setString(2, primaryKeys[0]);
            statement.setString(3, primaryKeys[1]);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating wallet: " + e.getMessage());
        }
    }

    @Override
    protected Wallet getEntity(ResultSet resultSet) {
        try {
            Wallet wallet = new Wallet(
                    resultSet.getString("name"),
                    resultSet.getDouble("fund"),
                    BanckType.valueOf(resultSet.getString("banck"))
            );

            User user = new User(
                    null,
                    null,
                    null,
                    null,
                    resultSet.getString("user_email"),
                    null,
                    null,
                    true
            );

            wallet.setUser(user);

            return wallet;

        } catch (SQLException e) {
            throw new RuntimeException("Error creating wallet entity", e);
        }
    }

    public List<Wallet> getWalletsByUserEmail(String email) {

        List<Wallet> wallets = new ArrayList<>();

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_GET_WALLET_BY_EMAIL)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                wallets.add(getEntity(resultSet));
            }

        } catch (SQLException e) {
            System.out.println("Error getting wallets by user: " + e.getMessage());
        }

        return wallets;
    }

    public Wallet getWalletByUserEmailAndWallet(String email, String walletName,
            String walletBanck) {

        Wallet wallet = null;

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_GET_WALLET_BY_EMAIL_AND_WALLET)) {

            statement.setString(1, email);
            statement.setString(2, walletName);
            statement.setString(3, walletBanck);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                wallet = getEntity(resultSet);
            }

        } catch (SQLException e) {
            System.out.println("Error getting wallets by user: " + e.getMessage());
        }

        return wallet;
    }

}
