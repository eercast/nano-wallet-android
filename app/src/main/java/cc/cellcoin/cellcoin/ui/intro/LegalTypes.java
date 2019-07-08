package cc.cellcoin.cellcoin.ui.intro;

import cc.cellcoin.cellcoin.R;

/**
 * View Pager types
 */

public enum LegalTypes {
    DISCLAIMER("Disclamer"), EULA("EULA"), PRIVACY("Privacy");

    private String name;

    LegalTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
