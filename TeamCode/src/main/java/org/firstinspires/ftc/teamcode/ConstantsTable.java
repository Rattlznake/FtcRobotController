package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.util.ReadWriteFile.readFile;
import static com.qualcomm.robotcore.util.ReadWriteFile.writeFile;

import java.io.File;
import java.util.ArrayList;

public class ConstantsTable {
    ArrayList<String> keys;
    ArrayList<String> values;

    public ConstantsTable() {
        keys = new ArrayList<String>();
        values = new ArrayList<String>();
    }

    public boolean isEmpty() {
        return keys.size() == 0 && values.size() == 0;
    }

    public String get(String key) {
        return values.get(keys.indexOf(key));
    }

    public String get(int idx) {
        return values.get(idx);
    }

    public void set(String key, String value) {
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
            return;
        }

        values.set(keys.indexOf(key), value);
    }

    public int size() {
        int keysSize = keys.size();
        int valuesSize = values.size();

        return Math.max(keysSize, valuesSize);
    }

    public static ConstantsTable constantTableFromString(String input) {
        ConstantsTable output = new ConstantsTable();

        StringBuilder stringBuffer = new StringBuilder();

        char keySeparatorCharacter = ':';
        char constantSeparatorCharacter = ',';

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (currentChar == keySeparatorCharacter) {
                output.keys.add(stringBuffer.toString());
                stringBuffer = new StringBuilder();
                continue;
            }
            if (currentChar == constantSeparatorCharacter || i == input.length() - 1) {
                if (i == input.length() - 1) stringBuffer.append(currentChar);
                output.values.add(stringBuffer.toString());
                stringBuffer = new StringBuilder();
                continue;
            }

            stringBuffer.append(currentChar);
        }

        return output;
    }

    public static ConstantsTable constantTableFromFile(File file) {
        String constTableString = readFile(file);

        return constantTableFromString(constTableString);
    }

    public static String stringFromConstantTable(ConstantsTable input) {
        String output = "";

        for (int i = 0; i < input.size(); i++) {
            output += input.keys.get(i) + ":" + input.values.get(i);

            if (i != input.size()-1) output += ",";
        }

        return output;
    }

    public void writeToFile(File file) {
        writeFile(file, stringFromConstantTable(this));
    }
}
