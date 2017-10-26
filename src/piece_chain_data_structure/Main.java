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
        char [] buffer = "A span of text.".toCharArray();
        pc.insert(0, buffer);
        buffer = "large ".toCharArray();
        pc.insert(2, buffer);
        buffer = "English ".toCharArray();
        pc.insert(8, buffer);
        buffer = " My bad!".toCharArray();
        pc.insert(29, buffer);
        System.out.println("The text stored in the piece chain : \n");
        System.out.println(pc);
        System.out.println("\n\n");
    }
    
}
