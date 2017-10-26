package piece_chain_data_structure;

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
        demonstrate();
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
    }
    
}
