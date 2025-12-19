package ymcris.rogex.e.models.groups;

import org.apache.commons.lang3.StringUtils;

/**
 * The MemberGroup class is the class responsible for
 *
 * @author YmCris
 * @since Dec 19, 2025
 */
public class MemberGroup {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String groupMemberEmail;
    private String familyGroupName;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public MemberGroup(String groupMemberEmail, String familyGroupName) {
        this.groupMemberEmail = groupMemberEmail;
        this.familyGroupName = familyGroupName;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        return !StringUtils.isAnyBlank(
                groupMemberEmail,
                familyGroupName
        );
    }

    // GETTERS -----------------------------------------------------------------
    public String getGroupMemberEmail() {
        return groupMemberEmail;
    }

    public String getFamilyGroupName() {
        return familyGroupName;
    }

    // SETTERS -----------------------------------------------------------------
    public void setGroupMemberEmail(String groupMemberEmail) {
        this.groupMemberEmail = groupMemberEmail;
    }

    public void setFamilyGroupName(String familyGroupName) {
        this.familyGroupName = familyGroupName;
    }

}
