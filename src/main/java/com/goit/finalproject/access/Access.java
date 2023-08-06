package com.goit.finalproject.access;

public enum Access {
    PUBLIC,
    PRIVATE;

    public static Access getAccess(String accessType) {
        Access access = null;
        if (accessType.equalsIgnoreCase("private")) {
            access = Access.PRIVATE;
        }
        if (accessType.equalsIgnoreCase("public")) {
            access = Access.PUBLIC;
        }
        return access;
    }
}
