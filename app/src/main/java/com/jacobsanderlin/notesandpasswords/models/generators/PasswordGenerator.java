package com.jacobsanderlin.notesandpasswords.models.generators;

import com.jacobsanderlin.notesandpasswords.models.utils.Helper;

import java.util.ArrayList;

public abstract class PasswordGenerator {

    private static ArrayList<PasswordGenerator> generators;

    public static void clear() {
        if (generators != null) generators.clear();
        else generators = new ArrayList<>();
    }

    public static boolean isEmpty() { return generators.isEmpty(); }

    public static void add(PasswordGenerator generator) { generators.add(generator); }

    public abstract String getChar();

    public static PasswordGenerator getRandomGenerator() {
        if (generators == null) {
            generators = new ArrayList<>();
            add(new LowerCaseGenerator());
        }

        if (generators.size() == 1) return generators.get(0);
        int randomIndex = Helper.randomVal(generators.size());
        return generators.get(randomIndex);
    }

    public static String generatePassword(int sizeOfPassword) {
        StringBuilder password = new StringBuilder();

        while (sizeOfPassword != 0) {
            password.append(getRandomGenerator().getChar());
            sizeOfPassword--;
        }

        return password.toString();
    }

}
