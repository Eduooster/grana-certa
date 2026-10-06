package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain;



public enum PluggyWebhookEvent {
    ITEM_CREATED("item/created"),
    ITEM_UPDATED("item/updated"),
    ITEM_ERROR("item/error"),
    ITEM_WAITING_USER_INPUT("item/waiting_user_input"),
    ITEM_WAITING_USER_ACTION("item/waiting_user_action"),
    TRANSACTIONS_CREATED("transactions/created"),
    LOGIN_SUCCESS("item/login_succeeded"),
    UNKNOWN("unknown");

    private final String value;

    PluggyWebhookEvent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PluggyWebhookEvent fromValue(String value) {
        if (value == null) return UNKNOWN;
        for (PluggyWebhookEvent e : values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        return UNKNOWN;
    }
}