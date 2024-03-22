package ua.com.a1coffee.util.translator;


public final class Translator {

    
    private Translator(){
    }

   
    public static String fromCyrillicToLatin(final String value) {
        return new ToLatinImpl(value).fromCyrillic();
    }

  
    public static String toAscii(final String value) {
        return new AsciiImpl(value).to();
    }

   
    public static String fromAscii(final String value) {
        return new AsciiImpl(value).from();
    }

    public static String toAscii(final int value) {
        return new AsciiImpl(value).to();
    }

    public static String fromAscii(final int value) {
        return new AsciiImpl(value).from();
    }
}
