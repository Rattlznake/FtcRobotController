package org.firstinspires.ftc.teamcode;

import java.io.File;

public class PodracerConstants {

    private final File constantsFile;
    private final ConstantsTable constantsTable;

    public PodracerConstants(File constantsFile) {
        this.constantsFile = constantsFile;
        constantsTable = ConstantsTable.constantTableFromFile(constantsFile);

        if (constantsTable.isEmpty()) initConstants();
    }

    void setPodracerConstant(String key, String value) {
        constantsTable.set(key, value);
    }

    String getPodracerConstant(String constantName) {
        return constantsTable.get(constantName);
    }

    void saveConstants() {
        constantsTable.writeToFile(constantsFile);
    }

    // Be careful with this! This will overwrite the data in the constants table
    void initConstants() {
        constantsTable.set("leftModifier","1");
        constantsTable.set("rightModifier","1");
    }
}
