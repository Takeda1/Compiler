import java.util.*;

/*
 * Eric Brodersen and Mark Harder
 * CS333 Topics in Computint - Compilers
 * Programming Assignment 3 - CUP Parser
 * 2012.03.15
 *
 * Node.java
 * To compile:
 * $ javac Node.java
 *
 * A class to represent a node. Contains a name, a line number, a
 * possible counting option, and an array list of child nodes
 */

public class Node {
  // the name of the node
  public String name;
  // holds the line number
  public int line;
  // child nodes
  public ArrayList<Node> nodes;
  // if the child-count needs to be printed
  public boolean count;

  // constructors
  public Node(String n, int l, ArrayList<Node> ns) {
    name = n;
    line = l;
    nodes = ns;
  }

  public Node(String n, int l, ArrayList<Node> ns, boolean c) {
    name = n;
    line = l;
    nodes = ns;
    count = c;
  }
}
