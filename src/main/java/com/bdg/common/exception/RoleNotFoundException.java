package com.bdg.common.exception;

import com.bdg.common.enums.RoleType;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(RoleType roleType) {
        super("Role with type: {" + roleType + "} not found");
    }

    public RoleNotFoundException() {
        super("Role not found");
    }
}