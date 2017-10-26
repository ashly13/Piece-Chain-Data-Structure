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
        PieceChain pc = new PieceChain();
        pc.insert(0, "A span of text.".toCharArray());
        pc.insert(2, "large ".toCharArray());
        pc.insert(8, "English ".toCharArray());
        pc.insert(0, " (English) ".toCharArray());
        pc.insert(29, " My bad!".toCharArray());
        pc.insert(36, ", truly".toCharArray());
        System.out.println("The Piece Chain before deleting : \n");
        System.out.println(pc);
        System.out.println("\n\n");
        pc.delete(2, 3);
        System.out.println("The Piece Chain after deleting : \n");
        System.out.println(pc);
        System.out.println("\n\n");
    }
    
}
