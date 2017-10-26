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
        System.out.println("The text stored in the piece chain before deleting : \n");
        System.out.println(pc);
        System.out.println("\n\n");
        pc.delete(1, 1);
        System.out.println("The text stored in the piece chain after deleting : \n");
        System.out.println(pc);
        System.out.println("\n\n");
    }
    
}
