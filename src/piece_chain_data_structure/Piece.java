/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piece_chain_data_structure;

/**
 *
 * @author Ashwitha Yadav T
 */
public class Piece {
    private int offset;
    private int length;

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
