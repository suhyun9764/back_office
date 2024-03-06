package com.sparta.back_office.model.enums;

public enum Auth {
    MANAGER("ROLE_MANAGER"),
    STAFF("ROLE_STAFF");

    private final String authority;

    Auth(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public static class Authority{
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String STAFF = "ROLE_STAFF";
    }
}
