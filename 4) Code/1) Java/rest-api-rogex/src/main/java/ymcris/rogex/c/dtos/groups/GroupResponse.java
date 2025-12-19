package ymcris.rogex.c.dtos.groups;

import java.util.List;
import ymcris.rogex.e.models.groups.Group;
import ymcris.rogex.e.models.users.User;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The GroupResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
public class GroupResponse implements GenericObjectResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String name;
    private List<User> users;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private int quantity;
    private int membersLimit;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public GroupResponse(Group group) {
        this.name = group.getName();
        this.users = group.getGroupMembers();
        this.quantity = group.getQuantity();
        this.membersLimit = group.getMembersLimit();
    }

    // GETTERS -----------------------------------------------------------------
    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMembersLimit() {
        return membersLimit;
    }

    // SETTERS -----------------------------------------------------------------
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setMembersLimit(int membersLimit) {
        this.membersLimit = membersLimit;
    }

}
