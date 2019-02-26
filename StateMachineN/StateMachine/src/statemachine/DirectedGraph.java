/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statemachine;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author gents
 */
public class DirectedGraph {
    public HashMap<Integer, HashMap<String, Stack<Integer>>> vertexMap = new HashMap<Integer, HashMap<String, Stack<Integer>>>();
    public Stack<Integer> stack = new Stack<Integer>();
    
    public DirectedGraph() {
        stack.push(1);
    }
 
    public void addVertex(Integer vertexName) {
        vertexMap.put(vertexName, new HashMap<String, Stack<Integer>>());
    }
    
    public void goEdge(String condition) {
        try {
            int curstate = stack.pop();
            goEdge(condition);
            HashMap<String, Stack<Integer>> edges = vertexMap.get(curstate);
//            System.out.println(vertexMap);
            if (!edges.containsKey(condition)) return;
            Stack<Integer> s = edges.get(condition);
            for (Iterator<Integer> iterator = s.iterator(); iterator.hasNext();) {
               Integer stackel = iterator.next();
//               System.out.println(stackel);
               stack.push(stackel);
            }
            //else return edges.get(condition);
        } catch (Exception e) {
            
        }
        return;
    }
 
    public void addEdge(Integer state1, String condition, Integer state2) {
        if (!vertexMap.containsKey(state1)) addVertex(state1);
        HashMap<String, Stack<Integer>> edges = vertexMap.get(state1);
        if (!vertexMap.get(state1).containsKey(condition)) vertexMap.get(state1).put(condition, new Stack<Integer>());
        edges.get(condition).push(state2);
        //System.out.println(vertexMap.entrySet());
    }
    
}