/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statemachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gents
 */
public class StateMachine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File file_states = new File("States.txt");
        
        Scanner sc;
        DirectedGraph graph=new DirectedGraph();
        int i, state1, state2, end_states;
        String[] Split = null;
        String[] End_states = null;
        String str = null;
        
        try {
            sc = new Scanner(file_states);
            str = sc.nextLine();
            End_states = str.split(" ");
            while (sc.hasNextLine()) {
                str = sc.nextLine();
                Split = str.split(" ");
            
            state1 = Integer.parseInt(Split[0]);
            state2 = Integer.parseInt(Split[2]);
            graph.addEdge(state1, Split[1], state2);
            }
            //System.out.println(graph.vertexMap.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       try {
            FileReader str_char = new FileReader(new File("String.txt"));
            int ch, st;
            while ((ch = str_char.read()) != -1) {
                //System.out.println(Character.toString((char)ch));
                graph.goEdge(Character.toString((char)ch));
                //System.out.println(graph.stack.toString());
                //if (st == -1) throw new IllegalArgumentException("Error states");
            }
            while (!graph.stack.empty()) {
                st = graph.stack.pop();
//                System.out.println(st);
                for (i = 0; i < End_states.length; i++) {
                    if (st == Integer.parseInt(End_states[i])) {
                        System.out.println("End state " + st);
                        return;
                    }
                }
            }
//            for (i = 0; i < End_states.length; i++) {
//                if (st == Integer.parseInt(End_states[i])) {
//                    System.out.println("End state " + st);
//                    return;
//                }
//            }
            throw new IllegalArgumentException("Error string");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StateMachine.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception e){
           e.printStackTrace();
       }
}
}