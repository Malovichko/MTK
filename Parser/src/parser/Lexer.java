/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author gents
 */
public class Lexer {
    private final StringReader reader;
    private char current;
    private boolean eof = false;
    
    public Lexer(StringReader reader) throws IOException {
        this.reader = reader;
        readNext();
    }
    
    public Lexeme getLexeme() throws IOException {
        Lexeme lexeme;
        int number = 0;
        delSpaces();
        if (eof) {
            lexeme = new Lexeme(LexemeType.EOF, 0);
            return lexeme;
        }
        switch (current) {
            case ('+'):
                lexeme = new Lexeme(LexemeType.PLUS, 0);
                readNext();
                break;
            case ('-'):
                lexeme = new Lexeme(LexemeType.MINUS, 0);
                readNext();
                break;
            case ('*'):
                lexeme = new Lexeme(LexemeType.MULT, 0);
                readNext();
                break;
            case ('/'):
                lexeme = new Lexeme(LexemeType.DIV, 0);
                readNext();
                break;
            case ('^'):
                lexeme = new Lexeme(LexemeType.POW, 0);
                readNext();
                break;
            case ('('):
                lexeme = new Lexeme(LexemeType.OP_BR, 0);
                readNext();
                break;
            case (')'):
                lexeme = new Lexeme(LexemeType.CL_BR, 0);
                readNext();
                break;
            case ('0'):
            case ('1'):
            case ('2'):
            case ('3'):
            case ('4'):
            case ('5'):
            case ('6'):
            case ('7'):
            case ('8'):
            case ('9'):
                while (current - '0' >= 0 && current - '0' <= 9) {
                    number *= 10;
                    number += Character.getNumericValue(current);
                    readNext();
                }
                lexeme = new Lexeme(LexemeType.NUM, number);
                break;
            default:
                lexeme = new Lexeme(LexemeType.UNKN, 0);
                break;
        }
        return lexeme;
    }

    private void delSpaces() throws IOException {
        while (current == ' ') {
            readNext();
        }
    }

    private void readNext() throws IOException {
        int num = reader.read();
        current = (char) num;
        if (num == -1) {
            eof = true;
        }
    }
}
