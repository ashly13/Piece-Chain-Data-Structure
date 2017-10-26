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
 *      by the piece chain.
 * 
 * @author Ashwitha Yadav T
 * @since October 26, 2017
 * @version 1.0
 */
public class PieceChain {
    
    private final List<Piece> sequence;
    private final List<Character> buffer;
    
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
        
        if ( textOffset < 0 ){
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
        // Traverse the linked list
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
    
    public void delete(int textOffset, int length){
        // Find the piece(s) that contains the offset provided
        // Traverse the linked list
        ListIterator<Piece> sequenceIterator = sequence.listIterator();
        
        // Check if the piece chain is empty or if delete length is less than 0
        if ( ! sequenceIterator.hasNext() || length <= 0 ){
            return;
        }
        
        // Deleting may involve splitting the current piece
        Piece prevPiece = null, nextPiece = null;
        Piece piece = null;
        
        int currTextOffset = 0;
        
        while ( sequenceIterator.hasNext() ){
            piece = sequenceIterator.next();
            currTextOffset += piece.getLength();
            
            // Make the text to be deleted always start from the beginning of a piece
            
            if ( textOffset > (currTextOffset - piece.getLength()) && textOffset < currTextOffset ){
                // The text to be deleted starts from the middle of the current piece
                // Transform it so it starts from the beginning
                prevPiece = new Piece(piece.getOffset(), textOffset - ( currTextOffset - piece.getLength() ));
                nextPiece = new Piece(piece.getOffset() + prevPiece.getLength(), piece.getLength() - prevPiece.getLength());
                
                // Remove the current piece and replace it with the new piece
                sequenceIterator.remove();
                sequenceIterator.add(prevPiece);
                sequenceIterator.add(nextPiece);
            }
            
            // Make the text to be deleted always stop at the end of a piece
            if ( ( textOffset + length ) < currTextOffset && ( textOffset + length) > ( currTextOffset - piece.getLength() ) ){
                // The text to be deleted stops at the middle of the current piece
                
                // Transform it so it stops at the end
                prevPiece = new Piece(piece.getOffset(), ( textOffset + length ) - ( currTextOffset - piece.getLength() ));
                nextPiece = new Piece(piece.getOffset() + prevPiece.getLength(), piece.getLength() - prevPiece.getLength());
                
                // Remove the current piece and replace it with the new pieces
                
                // The following two lines are used to prevent an 
                // IllegalStateException which occurs after successive
                // adds or removes without an intermediate call to
                // next() or previous()
                sequenceIterator.previous();
                sequenceIterator.next();
                
                sequenceIterator.remove();
                sequenceIterator.add(prevPiece);
                sequenceIterator.add(nextPiece);
                break;
            }
        }
        
        // Traverse the linked list again to remove all required pieces
        sequenceIterator = sequence.listIterator();
        currTextOffset = 0;
        
        while ( sequenceIterator.hasNext() ){
            piece = sequenceIterator.next();
            currTextOffset += piece.getLength();
            if ( ( currTextOffset - piece.getLength() ) == textOffset ){    // Starting piece
                sequenceIterator.remove();
            }
            else if( ( currTextOffset - piece.getLength() ) > textOffset && currTextOffset < ( textOffset + length ) ){
                // Middle piece
                sequenceIterator.remove();
            }
            else if( currTextOffset == ( textOffset + length ) ){ // End piece
                sequenceIterator.remove();
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
            System.out.println(sub);
        }
        return result;
    }
    
}
