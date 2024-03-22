package ua.com.a1coffee.util.generator;

import java.util.Random;

import static ua.com.a1coffee.util.validator.ObjectValidator.isNotEmpty;


public final class StringGenerator implements Generator<String> {

    private final static Random RANDOM;

  
    private final static char[] DEFAULT_PATTERN;

    private final static long DEFAULT_LENGTH;

   
    private final char[] pattern;

    private final long length;

  
    static {
        RANDOM = new Random();
        DEFAULT_PATTERN = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789").toCharArray();
        DEFAULT_LENGTH = 6;
    }

   
    public StringGenerator() {
        this(DEFAULT_PATTERN, DEFAULT_LENGTH);
    }

    public StringGenerator(final long length) {
        this(new char[] {}, length);
    }

   
    public StringGenerator(final char[] pattern) {
        this(pattern, 0);
    }

   
    public StringGenerator(final char[] pattern, final long length) {
        if (isNotEmpty(pattern)) {
            this.pattern = pattern;
        } else {
            this.pattern = DEFAULT_PATTERN;
        }
        this.length = (length > 0) ? length : DEFAULT_LENGTH;
    }

    @Override
    public String generate() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.length; i++) {
            sb.append(getRandomChar());
        }
        return sb.toString();
    }

   
    private char getRandomChar() {
        final int charNumber = getRandomPatternNumber();
        return this.pattern[charNumber];
    }

    private int getRandomPatternNumber() {
        final int bound = patternLength();
        return RANDOM.nextInt(bound);
    }

  
    private int patternLength() {
        return this.pattern.length;
    }
}
