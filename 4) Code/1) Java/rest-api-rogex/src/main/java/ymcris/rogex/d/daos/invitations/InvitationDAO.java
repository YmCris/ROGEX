package ymcris.rogex.d.daos.messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import ymcris.rogex.e.models.messages.Message;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.h.utilities.exceptions.DAOException;

/**
 * The MessageDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 23, 2025
 */
public class MessageDAO extends GenericDAO<Message> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_MESSAGE
            = "";
    private static final String SQL_EXISTS_MESSAGE
            = "";
    private static final String SQL_GET_MESSAGE
            = "";
    private static final String SQL_UPDATE_MESSAGE
            = "";
    private static final String SQL_GET_ALL_MESSAGE_OF_CONVERSATION
            = "";
    private static final String SQL_DELETE_MESSAGE
            = "";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public MessageDAO() {
        super(
                SQL_INSERT_MESSAGE,
                SQL_EXISTS_MESSAGE,
                SQL_GET_MESSAGE,
                SQL_UPDATE_MESSAGE,
                SQL_GET_ALL_MESSAGE_OF_CONVERSATION,
                SQL_DELETE_MESSAGE
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public void createEntity(Message message) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_ENTITY)) {

            statement.setString(1, message.getMessageText());
            statement.setTimestamp(2, Timestamp.valueOf(message.getSentDate()));
            statement.setBytes(3, message.getMultimedia());
            statement.setString(4, message.getSenderEmail());
            statement.setString(5, message.getReceiverEmail());

            statement.executeUpdate();

        } catch (Exception e) {
            throw new DAOException("Creating message with the message object", e);
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, Message entity) {
        throw new UnsupportedOperationException("YOU CAN'T UPDATE A MESSAGE.");
    }

    @Override
    protected Message createEntity(ResultSet resultSet) {
        try {

            return new Message(
                    resultSet.getString("message_text"),
                    resultSet.getTimestamp("sent_date").toLocalDateTime(),
                    resultSet.getBytes("multimedia"),
                    resultSet.getString("sender_email"),
                    resultSet.getString("receiver_email")
            );

        } catch (SQLException e) {
            throw new DAOException("Creting entity with the result set", e);
        }

    }
}
