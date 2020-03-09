package org.openmall.mall.ums.util;

import org.openmall.mall.security.util.SecurityUtil;
import org.openmall.mall.ums.domain.MemberDetails;
import org.openmall.mall.ums.model.UmsMember;

public class MemberSecurityUtil {
    public static UmsMember getCurrentMember() {
        MemberDetails memberDetails = (MemberDetails) SecurityUtil.getCurrentUserDetails();
        return memberDetails.getUmsMember();
    }
}
