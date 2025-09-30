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

    // TODO: Functional? Test it!
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
            if (currentChar == constantSeparatorCharacter || i == input.length()-1) {
                output.values.add(stringBuffer.toString());
                stringBuffer = new StringBuffer();
                continue;
            }

            stringBuffer.append(currentChar);
        }

        return output;
    }

    // TODO: Implement me
    void setPodracerConstant(String fileName) {

    }

    // TODO: Implement me
    String getPodracerConstant(String fileName) {
        return "";
    }
}
