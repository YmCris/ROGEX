package ymcris.rogex.b.services.groups;

import java.util.Optional;
import ymcris.rogex.c.dtos.groups.NewGroupRequest;
import ymcris.rogex.c.dtos.groups.UpdateGroupRequest;
import ymcris.rogex.d.daos.groups.GroupDAO;
import ymcris.rogex.d.daos.groups.MemberGroupDAO;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.e.models.groups.Group;
import ymcris.rogex.e.models.groups.MemberGroup;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;
import ymcris.rogex.h.utilities.exceptions.ObjectAlreadyExistsException;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

/**
 * The GroupService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
public class GroupService extends GenericService<Group> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public GroupService() {
        super(new GroupDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected Group createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewGroupRequest newGroupRequest = (NewGroupRequest) newObjectRequest;
        newGroupRequest.setPrimaryKeysSQLs(new String[]{newGroupRequest.getName()});
        newGroupRequest.setPrimaryKeysAdditionals(new String[]{newGroupRequest.getCreatorEmail()});

        Group group = new Group(
                newGroupRequest.getName(),
                6,
                1
        );

        userExits(newGroupRequest.getCreatorEmail());

        if (!group.isValid()) {
            throw new InvalidUserParametersException(
                    "Data sent to create the group isn't valid");
        }

        return group;
    }

    @Override
    protected void updateObject(Group entity,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        UpdateGroupRequest updateGroupRequest = (UpdateGroupRequest) updateObjectRequest;

        entity.setQuantity(updateGroupRequest.getQuantity());

        if (entity.getQuantity() > 6 || entity.getQuantity() <= 0) {
            throw new InvalidUserParametersException(
                    "Data sent to update the group isn't valid");
        }

    }

    @Override
    public Group insertObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException, ObjectAlreadyExistsException {

        NewGroupRequest newGroupRequest = (NewGroupRequest) newObjectRequest;
        Group group = (Group) extractObject(newObjectRequest);

        if (genericDAO.entityExists(newObjectRequest.getPrimaryKeysSQLs())) {

            throw new ObjectAlreadyExistsException("This already exists");

        }

        genericDAO.createEntity(group);
        UserDAO userDAO = new UserDAO();

        if (!userDAO.entityExists(new String[]{newGroupRequest.getCreatorEmail()})) {
            throw new InvalidUserParametersException("The user creator does'nt exists");
        }

        MemberGroupDAO memberGroupDAO = new MemberGroupDAO();
        System.out.println(newObjectRequest.getPrimaryKeysSQLs()[0]);
        System.out.println(newObjectRequest.getPrimaryKeysAdditionals()[0]);
        MemberGroup memberGroup = new MemberGroup(
                newObjectRequest.getPrimaryKeysAdditionals()[0],
                newObjectRequest.getPrimaryKeysSQLs()[0]
        );

        memberGroupDAO.createEntity(memberGroup);

        return group;
    }

    @Override
    public Group getEntity(String[] primaryKeys) throws ObjectNotFoundException {
        Optional<Group> entityOptional = genericDAO.getEntityByPrimaryKeys(primaryKeys);
        MemberGroupDAO memberGroupDAO = new MemberGroupDAO();

        if (entityOptional.isEmpty()) {
            throw new ObjectNotFoundException("This entity does'nt exists");
        }

        memberGroupDAO.addUsers(entityOptional.get());

        return entityOptional.get();
    }

    @Override
    public void deleteEntity(String[] primaryKeys)
            throws ObjectNotFoundException {

        MemberGroupDAO memberGroupDAO = new MemberGroupDAO();
        if (memberGroupDAO.entityExists(primaryKeys)) {
            throw new ObjectNotFoundException("You cant delete this group");
        }
        genericDAO.deleteEntity(primaryKeys);

    }

    public void userExits(String userEmail) throws InvalidUserParametersException {
        UserDAO userDAO = new UserDAO();

        if (!userDAO.entityExists(new String[]{userEmail})) {
            throw new InvalidUserParametersException("The user creator does'nt exists");

        }
    }

}
