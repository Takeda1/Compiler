/*
 * Eric Brodersen and Mark Harder
 * CS333 Topics in Computint - Compilers
 * Programming Assignment 3 - CUP Parser
 * 2012.03.15
 *
 * Token.java
 * To compile:
 * $ javac Token.java
 *
 * A class to represent a token. Contains a value and a line number
 */

public class Token {
  // the line number
  public int line;
  // the value
  public Object value;

  // constructors
  public Token(int line) {
    this.line = line;
  }

  public Token(int line, Object value) {
    this.line = line;
    this.value = value;
  }
}
