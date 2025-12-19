package ymcris.rogex.c.dtos.groups.members;

import ymcris.rogex.e.models.groups.MemberGroup;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The MemberGroupResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 19, 2025
 */
public class MemberGroupResponse implements GenericObjectResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String groupName;
    private String memberEmail;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public MemberGroupResponse(MemberGroup memberGroup) {
        this.groupName = memberGroup.getFamilyGroupName();
        this.memberEmail = memberGroup.getGroupMemberEmail();
    }

    // GETTERS -----------------------------------------------------------------
    public String getGroupName() {
        return groupName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    // SETTERS -----------------------------------------------------------------
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

}
