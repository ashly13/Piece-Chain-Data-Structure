package piece_chain_data_structure;

/**
 * Representation of a "piece" in a piece chain
 * 
 * A "piece" is a sequence of text that was inserted during a particular edit
 * ( an edit that started at a cursor position )
 * 
 * @author Ashwitha Yadav T
 * @since October 26
 * @version 1.0
 */
public class Piece {
    private int offset;
    private int length;

    /**
     * Parameterized Constructor
     * 
     * @param offset offset into the physical buffer
     * @param length length of the text sequence in the physical buffer
     */
    public Piece(int offset, int length) {
        this.offset = offset;
        this.length = length;
    }
    
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
}
