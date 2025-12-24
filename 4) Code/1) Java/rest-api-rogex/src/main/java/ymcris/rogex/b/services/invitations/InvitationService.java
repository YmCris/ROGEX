package ymcris.rogex.b.services.invitations;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ymcris.rogex.c.dtos.invitations.NewInvitationRequest;
import ymcris.rogex.d.daos.groups.GroupDAO;
import ymcris.rogex.d.daos.groups.MemberGroupDAO;
import ymcris.rogex.d.daos.invitations.InvitationDAO;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.e.models.invitations.Invitation;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.DAOException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The InvitationService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 23, 2025
 */
public class InvitationService extends GenericService<Invitation> {

    // LOGGER ------------------------------------------------------------------
    private static final Logger logger
            = LoggerFactory.getLogger(InvitationService.class);

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public InvitationService() {
        super(new InvitationDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected Invitation createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        try {

            NewInvitationRequest request = (NewInvitationRequest) newObjectRequest;
            request.setPrimaryKeysSQLs(new String[]{request.getGroupName(), request.getReceiverEmail()});

            Invitation invitation = new Invitation(
                    request.getInvitationText(),
                    LocalDateTime.now(),
                    request.getGroupName(),
                    request.getSenderEmail(),
                    request.getReceiverEmail()
            );

            if (!invitation.isValid()) {
                throw new InvalidUserParametersException("Data sent is invalid to create an invitation");
            }

            //1. See if the users exists
            validateUsersExist(invitation.getSenderEmail(), invitation.getReceiverEmail());

            //2. See if the group exists
            validateGroupExist(invitation.getGroupName());

            //3. See if the senderUser is on the group
            validateSenderIsInGroup(invitation.getGroupName(), invitation.getSenderEmail());

            return invitation;

        } catch (DAOException e) {
            logger.error("Error executing {}", e.getOperation(), e);
            throw e;
        }

    }

    @Override
    protected void updateObject(Invitation invitation,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        throw new UnsupportedOperationException("YOU CAN'T UPDATE AN INVITATION");
    }

    // AUXILIAR METHODS --------------------------------------------------------
    private void validateUsersExist(String senderEmail, String receiverEmail)
            throws InvalidUserParametersException {

        UserDAO userDAO = new UserDAO();

        if (!userDAO.entityExists(new String[]{senderEmail})) {
            throw new InvalidUserParametersException("Sender user does'nt exist");
        }

        if (!userDAO.entityExists(new String[]{receiverEmail})) {
            throw new InvalidUserParametersException("Receiver user doesn't exist");
        }

    }

    private void validateGroupExist(String groupName)
            throws InvalidUserParametersException {

        GroupDAO groupDAO = new GroupDAO();

        if (!groupDAO.entityExists(new String[]{groupName})) {
            throw new InvalidUserParametersException("Group does'nt exist");
        }

    }

    private void validateSenderIsInGroup(String groupName, String senderEmail)
            throws InvalidUserParametersException {

        MemberGroupDAO memberGroupDAO = new MemberGroupDAO();

        if (!memberGroupDAO.entityExists(new String[]{senderEmail, groupName})) {
            throw new InvalidUserParametersException("Sender isn't in the group " + groupName);
        }

    }
}
