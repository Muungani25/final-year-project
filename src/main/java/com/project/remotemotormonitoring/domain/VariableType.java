package com.project.remotemotormonitoring.domain;

import lombok.Getter;

@Getter
public enum VariableType {
    TEMP("TEMP"),
    VIB("VIB"),
    CURRENT("CURRENT");

    private final String displayName;

    VariableType(String displayName) {
        this.displayName = displayName;
    }

}
