/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author gents
 */
public class Lexeme {
    private LexemeType type;
    private final int number;
    
    public Lexeme(LexemeType type, int number) {
        this.type = type;
        this.number = number;
    }

    public LexemeType getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }
}
