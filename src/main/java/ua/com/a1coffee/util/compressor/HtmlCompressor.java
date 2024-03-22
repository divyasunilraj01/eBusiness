package ua.com.a1coffee.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;

public final class HtmlCompressor extends AbstractCompressor implements Compressor {

   
    private final static boolean REMOVED_INTERTAG_SPACES = true;

   
    private final Compressor compressor;

   
    public HtmlCompressor() {
        compressor = createCompressor();
    }

    Compressor getCompressor() {
        return this.compressor;
    }

    
    private Compressor createCompressor() {
        final com.googlecode.htmlcompressor.compressor.HtmlCompressor compressor =
                new com.googlecode.htmlcompressor.compressor.HtmlCompressor();
        compressor.setRemoveIntertagSpaces(REMOVED_INTERTAG_SPACES);
        return compressor;
    }
}
