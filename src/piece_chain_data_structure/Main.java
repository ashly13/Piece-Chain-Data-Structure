package piece_chain_data_structure;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Ashwitha Yadav T
 * @since October 26
 * @version 1.0
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //demonstrate();
        measurePerformance(10000);
    }
    
    /**
     * Demonstrate insertion and deletion in a piece chain
     */
    public static void demonstrate(){
        PieceChain pc = new PieceChain();
        System.out.println("A demonstation of Piece Chain Data Structure");
        System.out.println("\n------------------Insertion------------------");
        
        System.out.println("\n");
        
        // Insertion when piece chain is empty
        String buffer = "A span of text.";
        int pos = 0;
        System.out.println("After inserting '" + buffer + "' at position " + pos);
        pc.insert(pos, buffer.toCharArray());
        System.out.println(pc);
        
        // Insertion in the middle of a piece
        buffer = "large ";
        pos = 2;
        System.out.println("After inserting '" + buffer + "' at position " + pos);
        pc.insert(pos, buffer.toCharArray());
        System.out.println(pc);
        
        // Insertion at the end of a piece
        buffer = "English ";
        pos = 8;
        System.out.println("After inserting '" + buffer + "' at position " + pos);
        pc.insert(pos, buffer.toCharArray());
        System.out.println(pc);
        
        // Insertion at the beginning of a piece
        buffer = "(or French) ";
        pos = 16;
        System.out.println("After inserting '" + buffer + "' at position " + pos);
        pc.insert(pos, buffer.toCharArray());
        System.out.println(pc);
        
        System.out.println("\n\n");
        
        System.out.println("\n------------------Deletion------------------");
        
        System.out.println("\n");
        
        // Deletion when sequence begins and ends in the middle of a piece
        int off = 11;
        int len = 5;
        System.out.println("After deleting from position " + off + " to position " + (off + len));
        pc.delete(off, len);
        System.out.println(pc);
        
        // Deletion when sequence begins in a piece and ends in another piece
        off = 1;
        len = 6;
        System.out.println("After deleting from position " + off + " to position " + (off + len));
        pc.delete(off, len);
        System.out.println(pc);
        
        // Deletion when sequence begins at the beginning of a piece and ends 
        // at the end of the same piece
        off = 2;
        len = 3;
        System.out.println("After deleting from position " + off + " to position " + (off + len));
        pc.delete(off, len);
        System.out.println(pc);
        
        System.out.println("\n\n");
    }
    
    /**
     * 
     * @param number number of times to execute insertion and deletion 
     */
    public static void measurePerformance(int number){
        
        System.out.println("\n\n---------------Performance Comparison of" + 
        " Piece Chain vs. ArrayList---------------\n");
        
        PieceChain pc = new PieceChain();
        List<Character> sequence = new ArrayList<>();
        char [][] text = { "A ".toCharArray(), "large".toCharArray(), "span ".toCharArray(), "of ".toCharArray(), "text. ".toCharArray() };
        int size = text.length;
        long startTime, endTime;
        
        // Insertion
        System.out.println("\nInsertion Comparison");
        
        // Insert into the piece chain
        startTime = System.currentTimeMillis();
        for ( int i = 0 ; i < number ; i++ ){
            pc.insert(i%size, text[i%size]);
        }
        endTime = System.currentTimeMillis();
        System.out.println("\n" + number + " insertions into the piece chain "
                + "took " + (endTime-startTime) + " milliseconds");
        //System.out.println(pc);
        
        
        // Insert into the ArrayList for a comparison
        startTime = System.currentTimeMillis();
        for ( int i = 0 ; i < number ; i++ ){
            int j = i%size ;
            for ( char c : text[i%size] ){
                sequence.add(j, c);
                j++;
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("\n" + number + " insertions into the ArrayList "
                + "took " + (endTime-startTime) + " milliseconds");
        //System.out.println(sequence);
        
        System.out.println("\n");
        
    }
    
}
