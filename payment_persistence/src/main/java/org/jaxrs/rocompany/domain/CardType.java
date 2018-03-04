package org.jaxrs.rocompany.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum CardType {
    @JsonProperty("electron")
    ELECTRON(0),

    @JsonProperty("visa")
    VISA(1),

    @JsonProperty("mastercard")
    MASTER_CARD(2);

    @JsonIgnore
    private final int type;

    CardType(final int type) {
        this.type = type;
    }

    @JsonIgnore
    public CardType valueOf(final int type) {
        for (CardType card : values()) {
            if (type == card.type) {
                return card;
            }
        }
        throw new IllegalArgumentException("Invalid type " + type);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
