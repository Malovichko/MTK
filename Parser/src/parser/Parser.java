/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.IOException;
import java.io.StringReader;
import static java.lang.Math.pow;
import java.util.Scanner;

/**
 *
 * @author gents
 */
public class Parser {
    private Scanner scanner;
    private String text;
    private StringReader reader;
    private Lexer lexer;
    private Lexeme current_lexeme;
    private boolean end;
    
    public Parser() {
        scanner = new Scanner(System.in);
        text = scanner.next();
        reader = new StringReader(text);
        scanner.close();
        try {
            lexer = new Lexer(reader);
            get_lexeme();
            int answer = parseExpr();
            reader.close();
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Parser();
    }

    private int parseExpr() throws IOException {
        int temp = parseTerm();
        while ((current_lexeme.getType() == LexemeType.PLUS) || (current_lexeme.getType() == LexemeType.MINUS)) {
            if (current_lexeme.getType() == LexemeType.PLUS) {
                get_lexeme();
                temp += parseTerm();
            } else if (current_lexeme.getType() == LexemeType.MINUS) {
                get_lexeme();
                temp -= parseTerm();
            }
        }
        return  temp;
    }

    private int parseTerm() throws IOException {
        int temp = parseFactor();
        while ((current_lexeme.getType() == LexemeType.MULT) || (current_lexeme.getType() == LexemeType.DIV)) {
            if (current_lexeme.getType() == LexemeType.MULT) {
                get_lexeme();
                temp *= parseFactor();
            } else if (current_lexeme.getType() == LexemeType.DIV) {
                get_lexeme();
                int temp2 = parseFactor();
                if (temp2 == 0) {
                    throw new IOException("Exception in Parser - Division by 0");
                }
                temp /= temp2;
            }
        }
        return temp;
    }

    private int parseFactor() throws IOException {
        int temp = parsePower();
        if (current_lexeme.getType() == LexemeType.POW) {
            get_lexeme();
            return (int) pow(temp, parseFactor());
        } else {
            return temp;
        }
    }

    private int parsePower() throws IOException {
        if (current_lexeme.getType() == LexemeType.MINUS) {
            get_lexeme();
            return -parseAtom();
        }
        return parseAtom();
    }

    private int parseAtom() throws IOException {
        if (current_lexeme.getType() == LexemeType.OP_BR) {
            get_lexeme();
            int temp = parseExpr();
            if (current_lexeme.getType() != LexemeType.CL_BR) {
                throw new IOException("Exception in Parser - Missed closing bracket");
            }
            get_lexeme();
            return temp;
        }
        if (current_lexeme.getType() == LexemeType.NUM) {
            int temp = current_lexeme.getNumber();
            get_lexeme();
            return temp;
        }
        throw new IOException("Exception in Parser - Bad data");
    }

    private void get_lexeme() throws IOException {
        if (!end) {
            current_lexeme = lexer.getLexeme();
            if (current_lexeme.getType() == LexemeType.EOF) {
                end = true;
            }
            if (current_lexeme.getType() == LexemeType.UNKN) {
                end = true;
                throw new IOException("Exception in Lexer - Unknown symbol");
            }
        }
    }    
}
