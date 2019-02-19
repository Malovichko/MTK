/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statemachine;

import java.util.HashMap;

/**
 *
 * @author gents
 */
public class DirectedGraph {
    public HashMap<Integer, HashMap<String, Integer>> vertexMap = new HashMap<Integer, HashMap<String, Integer>>();
 
    public void addVertex(Integer vertexName) {
        vertexMap.put(vertexName, new HashMap<String, Integer>());
    }
 
    public Integer goEdge(Integer state, String condition) {
        HashMap<String, Integer> edges = vertexMap.get(state);
        if (!edges.containsKey(condition)) return -1;
        else return edges.get(condition);
    }
 
    public void addEdge(Integer state1, String condition, Integer state2) {
        if (!vertexMap.containsKey(state1)) addVertex(state1);
        HashMap<String, Integer> edges = vertexMap.get(state1);
        edges.put(condition, state2);
    }
}
