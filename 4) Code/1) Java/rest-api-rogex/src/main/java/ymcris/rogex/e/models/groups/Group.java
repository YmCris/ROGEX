package ymcris.rogex.e.models.groups;

import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.e.models.users.User;

/**
 * The Group class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
public class Group {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String name;
    private int quantity;
    private int membersLimit;

    private List<User> groupMembers;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public Group(String name, int memberLimits, int quantity) {
        this.name = name;
        this.membersLimit = memberLimits;
        this.quantity = quantity;
        this.groupMembers = new ArrayList<>(6);
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        return StringUtils.isNotBlank(name)
                && membersLimit > 0
                && quantity > 0
                && quantity < 7;
    }

    // GETTERS -----------------------------------------------------------------
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMembersLimit() {
        return membersLimit;
    }

    public List<User> getGroupMembers() {
        return groupMembers;
    }

    // SETTERS -----------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setGroupMembers(List<User> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMembersLimit(int membersLimit) {
        this.membersLimit = membersLimit;
    }

}
