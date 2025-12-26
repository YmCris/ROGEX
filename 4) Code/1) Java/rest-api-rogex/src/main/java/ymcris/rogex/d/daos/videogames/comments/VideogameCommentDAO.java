package ymcris.rogex.d.daos.videogames.comments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import ymcris.rogex.e.models.videogames.comments.VideogameComment;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.h.utilities.exceptions.DAOException;

/**
 * The VideogameCommentDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class VideogameCommentDAO extends GenericDAO<VideogameComment> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_VIDEOGAME_COMMENT
            = "INSERT INTO videogame_comment (videogame_title, enterprise_name, "
            + "user_email, comment_text, comment_date, parent_comment_id) "
            + "VALUES (?, ?, ?, ?, ?, ?);";

    private static final String SQL_EXISTS_VIDEOGAME_COMMENT_FROM_USER
            = "SELECT 1 FROM videogame_comment WHERE id = ?";

    private static final String SQL_GET_VIDEOGAME_COMMENT_FROM_USER
            = "SELECT * FROM videogame_comment WHERE id = ?";

    private static final String SQL_UPDATE_VIDEOGAME_COMMENT
            = "UPDATE videogame_comment SET comment_text = ? WHERE id = ?";

    private static final String SQL_GET_ALL_VIDEOGAMES_COMMENTS
            = "SELECT * FROM videogame_comment WHERE videogame_title = ? AND "
            + "enterprise_name = ? ORDER BY comment_date ASC";

    private static final String SQL_DELETE_VIDEOGAME_COMMENT
            = "DELETE FROM videogame_comment WHERE id = ?";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameCommentDAO() {
        super(
                SQL_INSERT_VIDEOGAME_COMMENT,
                SQL_EXISTS_VIDEOGAME_COMMENT_FROM_USER,
                SQL_GET_VIDEOGAME_COMMENT_FROM_USER,
                SQL_UPDATE_VIDEOGAME_COMMENT,
                SQL_GET_ALL_VIDEOGAMES_COMMENTS,
                SQL_DELETE_VIDEOGAME_COMMENT
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public void createEntity(VideogameComment comment) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_VIDEOGAME_COMMENT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, comment.getVideogameTitle());
            statement.setString(2, comment.getEnterpriseName());
            statement.setString(3, comment.getUserEmail());
            statement.setString(4, comment.getCommentText());
            statement.setTimestamp(5, Timestamp.valueOf(comment.getCommentDate()));

            if (comment.getParentCommentId() == null) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, comment.getParentCommentId());
            }

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new DAOException("Creating videogame comment", e);
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, VideogameComment comment) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_VIDEOGAME_COMMENT)) {

            statement.setString(1, comment.getCommentText());
            statement.setString(2, primaryKeys[0]);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Updatig videogame comment", e);
        }
    }

    @Override
    protected VideogameComment getEntity(ResultSet resultSet) {
        try {

            int parentId = resultSet.getInt("parent_comment_id");
            Integer parentCommentId = (resultSet.wasNull() ? null : parentId);

            VideogameComment comment = new VideogameComment(
                    resultSet.getString("videogame_title"),
                    resultSet.getString("enterprise_name"),
                    resultSet.getString("user_email"),
                    resultSet.getString("comment_text"),
                    resultSet.getTimestamp("comment_date").toLocalDateTime(),
                    resultSet.getInt("id"),
                    parentCommentId
            );

            return comment;

        } catch (SQLException e) {
            throw new DAOException("Getting videogame comment with RS", e);
        }
    }

}
