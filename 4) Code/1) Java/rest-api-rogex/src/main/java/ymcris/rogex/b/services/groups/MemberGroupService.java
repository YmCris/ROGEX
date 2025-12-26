package ymcris.rogex.b.services.groups;

import ymcris.rogex.c.dtos.groups.members.NewMemberGroupRequest;
import ymcris.rogex.d.daos.groups.GroupDAO;
import ymcris.rogex.d.daos.groups.MemberGroupDAO;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.e.models.groups.MemberGroup;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The MemberGroupService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 19, 2025
 */
public class MemberGroupService extends GenericService<MemberGroup> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public MemberGroupService() {
        super(new MemberGroupDAO());
    }

    // SPECIFIC METHODS --------------------------------------------------------
    @Override
    protected MemberGroup createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewMemberGroupRequest newMemberGroupRequest = (NewMemberGroupRequest) newObjectRequest;
        newMemberGroupRequest.setPrimaryKeysSQLs(new String[]{newMemberGroupRequest.getUserEmail(),newMemberGroupRequest.getGroupName()});
        UserDAO userDAO = new UserDAO();
        GroupDAO groupDAO = new GroupDAO();

        MemberGroup memberGroup = new MemberGroup(
                newMemberGroupRequest.getUserEmail(),
                newMemberGroupRequest.getGroupName()
        );

        if (!memberGroup.isValid()) {
            throw new InvalidUserParametersException(
                    "Invalid data sent to add a member group");
        }

        if (!userDAO.entityExists(new String[]{memberGroup.getGroupMemberEmail()})) {
            throw new InvalidUserParametersException(
                    "This user does'nt exists");
        }

        if (!groupDAO.entityExists(new String[]{memberGroup.getFamilyGroupName()})) {
            throw new InvalidUserParametersException(
                    "The group does'nt exists");
        }

        return memberGroup;

    }

    @Override
    protected void updateObject(MemberGroup entity,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {
        throw new UnsupportedOperationException("YOU CAN'T UPDATE THIS");
    }

}
