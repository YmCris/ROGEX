package ymcris.rogex.d.daos.invitations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import ymcris.rogex.e.models.invitations.Invitation;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.h.utilities.exceptions.DAOException;

/**
 * The InvitationDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 23, 2025
 */
public class InvitationDAO extends GenericDAO<Invitation> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_INVITATION
            = "INSERT INTO invitation (invitation_text, sent_date, group_name, "
            + "sender_email, receiver_email) VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_EXISTS_INVITATION
            = "SELECT 1 FROM invitation WHERE group_name = ? AND receiver_email = ?";

    private static final String SQL_GET_INVITATION
            = "SELECT * FROM invitation WHERE group_name = ? AND receiver_email = ?";

    private static final String SQL_UPDATE_INVITATION
            = null;

    private static final String SQL_GET_ALL_USER_INVITATIONS
            = "SELECT * FROM invitation WHERE receiver_email = ?";

    private static final String SQL_DELETE_INVITATION
            = "DELETE FROM invitation WHERE group_name = ? AND receiver_email = ?";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public InvitationDAO() {
        super(
                SQL_INSERT_INVITATION,
                SQL_EXISTS_INVITATION,
                SQL_GET_INVITATION,
                SQL_UPDATE_INVITATION,
                SQL_GET_ALL_USER_INVITATIONS,
                SQL_DELETE_INVITATION
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public void createEntity(Invitation invitation) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_INVITATION)) {

            statement.setString(1, invitation.getInvitationText());
            statement.setTimestamp(2, Timestamp.valueOf(invitation.getSentDate()));
            statement.setString(3, invitation.getGroupName());
            statement.setString(4, invitation.getSenderEmail());
            statement.setString(5, invitation.getReceiverEmail());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Creating invitation with the Invitation object", e);
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, Invitation invitation) {
        throw new UnsupportedOperationException("YOU CAN'T UPDATE A INVITATION.");
    }

    @Override
    protected Invitation getEntity(ResultSet resultSet) {
        try {

            return new Invitation(
                    resultSet.getString("invitation_text"),
                    resultSet.getTimestamp("sent_date").toLocalDateTime(),
                    resultSet.getString("group_name"),
                    resultSet.getString("sender_email"),
                    resultSet.getString("receiver_email")
            );

        } catch (SQLException e) {
            throw new DAOException("Get Invitation with the result set", e);
        }

    }
}
