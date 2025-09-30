package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;

public class PodracerConstants {
    private String constantFileName = "podracerConstants.txt";

    class ConstantTable {
        ArrayList<String> keys;
        ArrayList<String> values;

        ConstantTable() {
            keys = new ArrayList<String>();
            values = new ArrayList<String>();
        }
    }

    ConstantTable constantTableFromString(String input) {
        ConstantTable output = new ConstantTable();

        StringBuffer stringBuffer = new StringBuffer();

        char keySeparatorCharacter = ':';
        char constantSeparatorCharacter = ',';

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (currentChar == keySeparatorCharacter) {
                output.keys.add(stringBuffer.toString());
                stringBuffer = new StringBuffer();
                continue;
            }
            if (currentChar == constantSeparatorCharacter) {
                output.values.add(stringBuffer.toString());
                stringBuffer = new StringBuffer();
                continue;
            }

            stringBuffer.append(currentChar);
        }
    }

    void setPodracerConstant(String fileName) {

    }

    String getPodracerConstant(String fileName) {

    }
}
