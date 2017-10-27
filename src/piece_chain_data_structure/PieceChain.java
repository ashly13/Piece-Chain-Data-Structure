package piece_chain_data_structure;

import java.util.*; // For ArrayList and LinkedList

/**
 * The actual implementation of the piece chain data structure using LinkedList 
 * and ArrayList from Java Collections Framework. 
 * 
 * The piece chain is a LinkedList with each "piece" containing an offset 
 * and length into the physical buffer which is an ArrayList. 
 * 
 * The pieces are inserted in the order of their appearance in the actual text. 
 * 
 * The actual text sequences are inserted at the end of the ArrayList so the 
 * data structure is very efficient for insertion.
 * 
 * Physical buffer = ArrayList used to store the text sequences
 * Logical buffer  = The actual order in which text must appear (maintained
 *      by the piece chain)
 * 
 * @author Ashwitha Yadav T
 * @since October 26, 2017
 * @version 1.0
 */
public class PieceChain {
    
    private final List<Piece> sequence;
    private final List<Character> buffer;
    
    /**
     * Default Constructor
     */
    public PieceChain(){
        sequence = new LinkedList<>();
        buffer = new ArrayList<>();
    }
    
    /**
     * Inserts the character sequence at the specified offset
     * 
     * @param textOffset offset into the logical buffer where insertion
     *                      will happen
     * @param buf text to insert into the buffer
     */
    public void insert(int textOffset, char [] buf){
        
        // Offset cannot be before the beginning or after the end
        if ( textOffset < 0 || textOffset > buffer.size() ){
            return;
        }
        
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
        // by traversing the linked list
        // The only "inefficient" part of a piece chain
        ListIterator<Piece> sequenceIterator = sequence.listIterator();
        
        // Check if the piece chain is empty
        if ( ! sequenceIterator.hasNext() ){
            sequenceIterator.add(newPiece);
            return;
        }
        
        // Inserting will involve splitting the current piece
        Piece prevPiece, nextPiece, piece;
        
        // Logical offset into the universal buffer is calculated as the
        // cumulative sum of the lengths of all pieces
        int currTextOffset = 0;
        
        while ( sequenceIterator.hasNext() ){
            piece = sequenceIterator.next();
            currTextOffset += piece.getLength();
            
            if ( textOffset == (currTextOffset - piece.getLength()) ){
                // The text to be inserted is in the beginning of the 
                // current piece
                sequenceIterator.previous();
                sequenceIterator.add(newPiece);
                break;
            }
            else if ( textOffset < currTextOffset ){
                // The text to be inserted is in the middle of the 
                // current piece
                piece.setLength(textOffset - ( currTextOffset - piece.getLength() ));
                nextPiece = new Piece(piece.getOffset() + piece.getLength()
                        , piece.getLength() - piece.getLength());
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
    
    /**
     * Deletes the character sequence at the specified offset
     * of the given length
     * 
     * @param textOffset offset into the logical buffer
     * @param length length of text to be deleted
     */
    public void delete(int textOffset, int length){
        // Find the piece(s) that contains the offset provided
        // Traverse the linked list
        ListIterator<Piece> sequenceIterator = sequence.listIterator();
        
        // Check if the piece chain is empty or if delete length is less than 0
        if ( ! sequenceIterator.hasNext() || length <= 0 ){
            return;
        }
        
        // Deleting may involve splitting the current piece
        Piece prevPiece, nextPiece, piece;
        
        int currTextOffset = 0;
        int oldLength;
        
        while ( sequenceIterator.hasNext() ){
            piece = sequenceIterator.next();
            currTextOffset += piece.getLength();
            
            if ( textOffset == (currTextOffset - piece.getLength()) ){
                // The text to be deleted starts from the beginning of the 
                // current piece - Done
                
                // See if it stops in this piece itself
                if ( ( textOffset + length ) > currTextOffset ){    // Done
                    // The sequence does not stops in this piece
                    // So it can be deleted
                    sequenceIterator.remove();
                }
                else if( ( textOffset + length ) == currTextOffset ){   // Done
                    // The sequence stops at the end of this piece
                    // So it can be deleted
                    sequenceIterator.remove();
                    // End of sequence, break for more efficiency
                    break;
                }
                else{   // Done
                    // The sequence stops at the middle of this piece
                    // So it must be split and then deleted
                    oldLength = piece.getLength();
                    piece.setLength((textOffset + length) - (currTextOffset - piece.getLength()));
                    sequenceIterator.remove();
                    nextPiece = new Piece(piece.getOffset() + piece.getLength()
                            , oldLength - piece.getLength());
                    
                    sequenceIterator.add(nextPiece);
                    // End of sequence, break for more efficiency
                    break;
                }
            }
            else if ( textOffset > (currTextOffset - piece.getLength()) &&
                    textOffset < currTextOffset ){
                // The text to be deleted starts from the middle of the 
                // current piece - Done
                
                // Split the current piece
                oldLength = piece.getLength();
                piece.setLength(textOffset - ( currTextOffset - piece.getLength() ));
                nextPiece = new Piece(piece.getOffset() + piece.getLength()
                        , oldLength - piece.getLength());
                
                sequenceIterator.add(nextPiece);
                piece = sequenceIterator.previous();
                
                // See if it stops in this piece itself
                if ( ( textOffset + length ) > currTextOffset ){    // Done
                    // The sequence does not stops in this piece
                    // So it can be deleted
                    sequenceIterator.remove();
                }
                else if( ( textOffset + length ) == currTextOffset ){   // Done
                    // The sequence stops at the end of this piece
                    // So it can be deleted
                    sequenceIterator.remove();
                    // End of sequence, break for more efficiency
                    break;
                }
                else{   // Done
                    // The sequence stops at the middle of this piece
                    // So it must be split and then deleted
                    oldLength = piece.getLength();
                    piece.setLength((textOffset + length) - (currTextOffset - piece.getLength()));
                    sequenceIterator.remove();
                    nextPiece = new Piece(piece.getOffset() + piece.getLength()
                            , oldLength - piece.getLength());
                    
                    sequenceIterator.add(nextPiece);
                    // End of sequence, break for more efficiency
                    break;
                }
            }
            else if( (currTextOffset - piece.getLength()) > textOffset
                    && currTextOffset < ( textOffset + length )){
                // The current piece is between start and stop of the sequence
                // Done
                sequenceIterator.remove();
            }
            else if ( (currTextOffset - piece.getLength()) > textOffset
                    && currTextOffset > ( textOffset + length ) ){
                // The sequence stops in the middle of the 
                // current piece - Done
                oldLength = piece.getLength();
                piece.setLength((textOffset + length) - (currTextOffset - piece.getLength()));
                sequenceIterator.remove();
                nextPiece = new Piece(piece.getOffset() + piece.getLength()
                        , oldLength - piece.getLength());

                sequenceIterator.add(nextPiece);
                // End of sequence, break for more efficiency
                break;
            }
            else if ( (currTextOffset - piece.getLength()) > textOffset
                    && currTextOffset == ( textOffset + length ) ){
                // The sequence stops at the end of the 
                // current piece - Done
                sequenceIterator.remove();
                // End of sequence, break for more efficiency
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
            sub = buffer.subList(piece.getOffset(), 
                    piece.getOffset() + piece.getLength());
            System.out.println("\t" + sub);
        }
        return result;
    }
    
}
