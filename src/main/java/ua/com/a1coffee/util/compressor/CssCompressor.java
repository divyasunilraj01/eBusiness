package ua.com.a1coffee.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;
import com.googlecode.htmlcompressor.compressor.YuiCssCompressor;

public final class CssCompressor extends AbstractCompressor implements Compressor {

    private final Compressor compressor;

    
    public CssCompressor() {
        this.compressor = new YuiCssCompressor();
    }

   
    Compressor getCompressor() {
        return this.compressor;
    }
}
