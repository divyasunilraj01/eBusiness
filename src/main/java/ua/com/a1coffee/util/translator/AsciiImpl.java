package ua.com.a1coffee.util.translator;

import static ua.com.a1coffee.util.validator.ObjectValidator.isNotEmpty;

public final class AsciiImpl implements Ascii {

    
    private final String value;

   
    public AsciiImpl(final String value) {
        this.value = value;
    }

   
    public AsciiImpl(final int value) {
        this(Integer.toString(value));
    }

    @Override
    public String to() {
        final String result;
        if (isNotEmpty(this.value)) {
            result = convertToAscii();
        } else {
            result = "";
        }
        return result;
    }

   
    @Override
    public String from() {
        String result;
        if (isNotEmpty(this.value)) {
            try {
                result = convertFromAscii();
            } catch (NumberFormatException ex) {
                result = "";
            }
        } else {
            result = "";
        }
        return result;
    }

    private String convertToAscii() {
        final char[] charArray = getValueChars();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            sb.append(charToInt(charArray[i]));
            if (i != charArray.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private String convertFromAscii() {
        final StringBuilder sb = new StringBuilder();
        for (String number : this.value.split(",")) {
            sb.append(numberToChar(number));
        }
        return sb.toString();
    }

   
    public String getValue() {
        return this.value;
    }

   
    private char[] getValueChars() {
        return this.value.toCharArray();
    }

    private int charToInt(final char character) {
        return (int) character;
    }

   
    private char numberToChar(final String number) {
        final int integer = Integer.parseInt(number);
        return intToChar(integer);
    }

   
    private char intToChar(final int number) {
        return (char) number;
    }
}
