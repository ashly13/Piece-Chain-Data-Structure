/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piece_chain_data_structure;

import java.util.*;

/**
 *
 * @author Ashwitha Yadav T
 */
public class PieceChain {
    
    private List<Piece> sequence;
    private List<Character> buffer;
    
    public PieceChain(){
        sequence = new LinkedList<>();
        buffer = new ArrayList<>();
    }
    
    /**
     * 
     * @param textOffset offset into the text and not the buffer
     * @param buf text to insert into the buffer
     */
    public void insert(int textOffset, char [] buf){
        
        // Add the input buffer to our universal buffer, note the offset 
        // and length to store in the piece chain
        int offset = buffer.size();
        int length = buf.length;
        for ( char c : buf ){
            buffer.add(c);
        }
        
        // Create a new piece
        Piece newPiece = new Piece(offset, length);
        
        // Find the piece that contains the offset provided
        // Traverse the linked list
        ListIterator<Piece> sequenceIterator = sequence.listIterator();
        
        // Check if the piece chain is empty
        if ( ! sequenceIterator.hasNext() ){
            sequenceIterator.add(newPiece);
            return;
        }
        
        // Inserting will involve splitting the current piece
        Piece prevPiece = null, nextPiece = null;
        Piece piece = null;
        
        int currTextOffset = 0;
        
        while ( sequenceIterator.hasNext() ){
            piece = sequenceIterator.next();
            currTextOffset += piece.getLength();
            
            if ( textOffset == (currTextOffset - piece.getLength()) ){
                // The text to be inserted is in the beginning of the current piece
                sequenceIterator.previous();
                sequenceIterator.add(newPiece);
                break;
            }
            else if ( textOffset < currTextOffset ){
                // The text to be inserted is in the middle of the current piece
                prevPiece = new Piece(piece.getOffset(), textOffset - ( currTextOffset - piece.getLength() ));
                nextPiece = new Piece(piece.getOffset() + prevPiece.getLength(), piece.getLength() - prevPiece.getLength());
                sequenceIterator.remove();
                sequenceIterator.add(prevPiece);
                sequenceIterator.add(newPiece);
                sequenceIterator.add(nextPiece);
                break;
            }
            else if ( textOffset == currTextOffset ){
                // The text to be inserted is in the end of the current piece
                sequenceIterator.add(newPiece);
                break;
            }
        }
        
    }
    
    @Override
    public String toString(){
        String result = "";
        ListIterator<Piece> sequenceIterator = sequence.listIterator();
        Piece piece;
        List sub;
        while ( sequenceIterator.hasNext() ){
            piece = sequenceIterator.next();
            sub = buffer.subList(piece.getOffset(), piece.getOffset() + piece.getLength());
            //System.out.println(piece.getOffset() + " " + piece.getLength());
            System.out.println(sub);
        }
        return result;
    }
    
}
