package ua.com.a1coffee.util.translator;

import static ua.com.a1coffee.util.validator.ObjectValidator.isNotEmpty;


public final class ToLatinImpl implements ToLatin {

   
    private final String value;

    public ToLatinImpl(final String value) {
        this.value = value;
    }

   
    @Override
    public String fromCyrillic() {
        final String result;
        if (isNotEmpty(this.value)) {
            result = convert(this.value);
        } else {
            result = "";
        }
        return result;
    }

   
    private String convert(final String value) {
        final StringBuilder sb = new StringBuilder();
        for (char ch : convertToChars(value)) {
            sb.append(translate(ch));
        }
        return sb.toString().replaceAll("__", "_");
    }

   
    private char[] convertToChars(final String value) {
        return value.toLowerCase().toCharArray();
    }

   
    private String translate(final char ch) {
        final String result;
        switch (ch) {
        case 'а':
            result = "a";
            break;
        case 'б':
            result = "b";
            break;
        case 'в':
            result = "v";
            break;
        case 'г':
            result = "g";
            break;
        case 'д':
            result = "d";
            break;
        case 'е':
            result = "e";
            break;
        case 'ё':
            result = "je";
            break;
        case 'ж':
            result = "zh";
            break;
        case 'з':
            result = "z";
            break;
        case 'і':
        case 'и':
            result = "i";
            break;
        case 'й':
            result = "y";
            break;
        case 'к':
            result = "k";
            break;
        case 'л':
            result = "l";
            break;
        case 'м':
            result = "m";
            break;
        case 'н':
            result = "n";
            break;
        case 'о':
            result = "o";
            break;
        case 'п':
            result = "p";
            break;
        case 'р':
            result = "r";
            break;
        case 'с':
            result = "s";
            break;
        case 'т':
            result = "t";
            break;
        case 'у':
            result = "u";
            break;
        case 'ф':
            result = "f";
            break;
        case 'х':
            result = "kh";
            break;
        case 'ц':
            result = "c";
            break;
        case 'ч':
            result = "ch";
            break;
        case 'ш':
            result = "sh";
            break;
        case 'щ':
            result = "jsh";
            break;
        case 'ъ':
            result = "hh";
            break;
        case 'ы':
            result = "ih";
            break;
        case 'ь':
            result = "jh";
            break;
        case 'э':
            result = "eh";
            break;
        case 'ю':
            result = "yu";
            break;
        case 'я':
            result = "ja";
            break;
        case ' ':
        case '.':
        case ',':
        case '!':
        case '?':
        case '/':
        case '\\':
            result = "_";
            break;
        case '(':
        case ')':
        case '{':
        case '}':
        case '[':
        case ']':
        case '&':
            result = "";
            break;
        default:
            result = String.valueOf(ch);
        }
        return result;
    }
}
