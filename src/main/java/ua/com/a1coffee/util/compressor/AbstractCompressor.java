package ua.com.a1coffee.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;

import static ua.com.a1coffee.util.validator.ObjectValidator.isNotEmpty;

abstract class AbstractCompressor implements Compressor {

   
    @Override
    public String compress(final String source) {
        String result = "";
        if (isNotEmpty(source)) {
            final Compressor compressor = getCompressor();
            result = compressor.compress(source);
        }
        return result;
    }

   
    abstract Compressor getCompressor();
}
