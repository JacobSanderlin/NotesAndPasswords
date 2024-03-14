package com.jacobsanderlin.notesandpasswords.models.generators;

import com.jacobsanderlin.notesandpasswords.models.utils.Helper;

public class NumericGenerator extends PasswordGenerator {

    private static final char CHAR_A = '0';
    private static final char CHAR_Z = '9';

    @Override
    public String getChar() { return String.valueOf((char) (Helper.randomChar(CHAR_A, CHAR_Z))); }
}
