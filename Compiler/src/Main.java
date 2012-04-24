import java.io.*;
import java.util.regex.*;
import java.util.*;

/*
 * Eric Brodersen and Mark Harder
 * CS333 Topics in Computing - Compilers
 * Programming Assignment 3 - CUP Parser
 * 2012.03.15
 *
 * Main.java
 * To compile:
 * $ javac Main.java
 *
 * The main class which makes use of the parser created by the CUP
 * file ycool.cup and the lexer created by the jflex file Lexer.jflex.
 * This file takes a file from command line arguments, containing the
 * COOL programming language (with a filename extension of .cl) and
 * creates an AST which is returned by the parser. This tree is then
 * traversed, writing the output to a file with the same name as the
 * input file but with a filename extention of .cl-ast. This cl-ast
 * file contains the same output as the cool compiler with the --parse
 * flag.
 *
 * This program first takes the input file name and extracts useful
 * information from it such as the basename and calculates the
 * target output file name. Then it attempts to clear the output
 * file if it exists. If the supplied file does not end with the
 * filename extension of .cl then the program exits.
 *
 * Next a parser is created using the parser class parser.java created
 * by the CUP file. When this parser is created it is passed a lexer
 * using the lexer class Lexer.java created by the JFlex file. The parser
 * uses the built-in methods of the lexer such as next_token() to
 * extract meaningful tokens and use them to generate the tree.
 *
 * Finally the parser returns a symbol when the parse function is called.
 * This symbol holds all the information of the AST. The symbol contains a
 * node from the class Node.java as its value, which holds all the child
 * nodes in the tree. It is simple from that point to print the value of
 * the nodes as the tree is traversed
 */

public class Main {
  static String cName = "";
  static Integer memory = 1000;
  static Integer tempMem = 500;
  static Integer temp = 0;
  static Integer dataCounter = 1;
  static ArrayList<HashMap<String,Integer>> symTable = new ArrayList<HashMap<String,Integer>>();
  static String data = "\t.data\n\ntemp: .word 0\n";
  static String text = "\n\t.text\n\nIO..new:\n\tli $a0, 12\n\tli $v0, 9\n\tsyscall\n\tla $t0, IO_vtable\n\tsw $t0, 8($v0)\n\tjr $ra\n\nIO.out_string:\n\tlw $a0 0($sp)\n\tli $v0, 4\n\tsyscall\n\tjr $ra\n\nIO.out_int:\n\tlw $a0 0($sp)\n\tli $v0, 1\n\tsyscall\n\tjr $ra\n\nObject..new:\n\tjr $ra\n\nObject.copy:\n\tjr $ra\n";
  static ArrayList<Vtable> vtables = new ArrayList<Vtable>();
  
  // our output stream
  static BufferedWriter out;

  static public void main(String argv[]) {
    // the input file name
    String inputFile = argv[0];

    // exit if the file does not end in .cl
    if ( !( Pattern.compile("\\.cl$").matcher(inputFile).find() ) ) {
      System.out.println("You need a file ending in .cl to parse.");
      System.exit(0);
    }

    // get the basename and append -ast to it to acquire the output file name
    String outputFile = getBaseName(inputFile)+"-asm";

    /*
     * attempt to clear the output file by overwriting the contents of the
     * file with an empty string. This is done because text is appended
     * to the output file, so it can handle a text of infinite size and
     * run out of disk space before memory overflows.
     */
    try {
      BufferedWriter clear = new BufferedWriter( new FileWriter(outputFile) );
      clear.write("");
      clear.close();
    } catch (Exception e) {
      // if the program cannot clear the file, the program is exited
      System.out.println("Could not clear the file " + outputFile + ".");
      System.out.println(e.getMessage());
      System.exit(0);
    }

    // create the actual output stream which will append text to the
    // output file, which should now be empty
    try {
      out = new BufferedWriter( new FileWriter(outputFile,true) );
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    try {
      // create the parser, passing it 
      parser p = new parser(new Lexer(new FileReader(inputFile)));
      // save the symbol returned from the parse() method, which
      // represents the AST as tree
      java_cup.runtime.Symbol tree = p.parse();

      // the actual root of the tree
      Node n = (Node)tree.value;

      //symTable.add(new Map<String,Integer>());
      vtables.add(new Vtable("Object"));
      vtables.get(vtables.size()-1).put(".copy","Object");
      
      // print the root and traverse through the children
      //symtable.add(new Map<String,Integer>());
      vtables.add(new Vtable("IO"));
      vtables.get(vtables.size()-1).put(".out_string","IO");
      vtables.get(vtables.size()-1).put(".out_int","IO");
      inheritClass(vtables.get(vtables.size()-1),"Object");
      
      handleClass(n.nodes);
      text += "\nmain:\n\tjal Main..new\n\tlw $t0, 8($v0)\n\tlw $t1, " + Integer.toString(vtables.get(vtables.size()-1).locate("main")*4) + "($t0)\n\tjalr $t1\n\tli $v0, 10\n\tsyscall";
      
      for (int i = 0; i < vtables.size(); i++ ) {
        data += vtables.get(i).toString();
      }
      
      out.write(data);
      out.write(text);
    } catch (Exception e) {
      // if an error occurs, it is caught here
      System.out.println("--"+e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        // close the output file
        out.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  // a simple regular expression to return the basename of a
  // file given a directory path to the file
  private static String getBaseName( String fileName ) {
    String[] parts = fileName.split("\\/(?=[^\\/]+$)");
    return parts[parts.length-1];
  }

  private static void inheritClass(Vtable table, String className){
    for (int i = 0; i < vtables.size(); i++){
      if ( vtables.get(i).getTableName().equals(className)){
        table.inherit(vtables.get(i));
        return;
      }
    }
  }
  
  private static Integer searchSymTable(String identifier) {
    for (int i = symTable.size()-1; i >= 0; i--) {
      if (symTable.get(i).get(identifier) != null) {
        return symTable.get(i).get(identifier);
      }
    }
    return -1;
  }
  
  
  private static void handleClass( ArrayList<Node> nodes ) throws IOException {
      for ( int i = 0; i < nodes.size(); i++ ) {
        if (nodes.get(i) == null) continue;
  
        Node n = nodes.get(i);
       
        symTable.add(new HashMap<String,Integer>());
        vtables.add(new Vtable(n.name));
        
        text += "\n" + n.name + "..new:\n";
        text += "\tsw $ra, temp\n";
        text += "\tlw $t7, temp\n";
        text += "\tli $a0, 12\n";
        text += "\tli $v0, 9\n";
        text += "\tsyscall\n";
        text += "\tsw $v0, temp\n";
        text += "\tlw $t6, temp\n";
        text += "\tla $t0, " + n.name + "_vtable\n";
        text += "\tsw $t0, 8($v0)\n";
        
        int att = 0;
        for (int j = 0; j < n.nodes.get(0).nodes.get(0).nodes.size(); j++) {
          if (n.nodes.get(0).nodes.get(0).nodes.get(j).name == "attribute_init" || n.nodes.get(0).nodes.get(0).nodes.get(j).name == "attribute_no_init") {
            att++;
          }
        }
        if (att == 0) {
          text += "\tsw $t6, temp\n";
          text += "\tlw $v0, temp\n";
          text += "\tjr $t7\n";
        }
        
        cName = n.name;
        handleInherit( n.nodes );
        symTable.remove(symTable.size()-1);
      }
  }
  
  private static void handleInherit( ArrayList<Node> nodes ) throws IOException {
        if ( ((Node)nodes.get(0)).name == "no_inherits" ) {
          inheritClass(vtables.get(vtables.size()-1),"Object");
          handleFeatures(((Node)((ArrayList<Node>)((Node)nodes.get(0)).nodes).get(0)).nodes);
        } else {
            inheritClass(vtables.get(vtables.size()-1),nodes.get(0).name);
          handleFeatures(((Node)((ArrayList<Node>)((Node)nodes.get(0)).nodes).get(0)).nodes);
        }
  
  }
  
  private static void handleFeatures( ArrayList<Node> nodes ) throws IOException {
      //symTable.put(cName+"..new", 0);
    
      for ( int i = 0; i < nodes.size(); i++ ) {
        if (nodes.get(i) == null ) continue;
        
        Node n = nodes.get(i);
        if (n.name == "method") {
          vtables.get(vtables.size()-1).put("."+n.nodes.get(0).name,cName);
        }
      }
      
      int temp = 4;
      for ( int k = 0; k < nodes.size(); k++ ) {
        if (nodes.get(k) == null) continue;
        Node n = nodes.get(k);
        if (n.name == "method") {
          text += "\n" + cName;
          handleMethod( n.nodes );
        } else if (n.name == "attribute_init") {
          symTable.get(symTable.size()-1).put(n.nodes.get(0).name,memory);
          if (n.nodes.get(1).name == "integer") {
            memory += 4;
          } else if (n.nodes.get(1).name == "string") {
            memory += 4*n.nodes.get(1).name.length();
          } else {
            memory += 12;
          }
          
          Node e = handleExpression(n.nodes.get(2));
          if (e.name == "constructor") {
            text += "\tsw $v0, " + Integer.toString(memory-12) + "($sp)######\n";
          }
          text += "\tsw $t6, temp\n";
          text += "\tlw $v0, temp\n";
          text += "\tjr $t7\n";
        } else {
          System.out.println("'" + n.name + "'");
        }
        //symTable.put(cName + "." + n.nodes.get(0).name, temp);
        temp += 4;
      }
      
  }
  
    
  private static void handleMethod( ArrayList<Node> nodes ) throws IOException {
      symTable.add(new HashMap<String,Integer>());
      text += "." + ((Node)nodes.get(0)).name + ":\n";
      // arguments
      for (int i = 0; i < nodes.get(0).nodes.get(0).nodes.size(); i++) {
        Node arg = nodes.get(0).nodes.get(0).nodes.get(i);
        symTable.get(symTable.size()-1).put(arg.nodes.get(0).name,memory);
        text += "\tlw $t" + temp.toString() + ", " + Integer.toString(i*4) + "($sp)\n";
        text += "\tsw $t" + temp.toString() + ", " + memory+"($sp)\n";
        memory += 4;
      }
    
      text += "\tmove $t7, $v0\n";
      Node n = handleExpression( (Node)nodes.get(2) );
      text += "\tsw $t0, 0($sp)\n";
      text += "\tjr $ra\n";
      temp = 0;
      symTable.remove(symTable.size()-1);
  }
  
  private static Node handleExpression( Node node ) throws IOException {
    if (node.name == "dynamic_dispatch") {
      Node n1 = handleExpression(node.nodes.get(0));
      Node n2 = node.nodes.get(1);
      ArrayList<Node> args = handleArguments(node.nodes.get(2));
      Integer mem = 0;
      for (int i = 0; i < args.size(); i++) {
        if (args.get(i).name == "string") {
          data += "\narg" + dataCounter.toString() + ": .asciiz \"" + args.get(i).nodes.get(0).name + "\"\n";
          text += "\tla $t" + temp.toString() + ", arg" + dataCounter.toString() + "\n";
          text += "\tsw $t" + temp.toString() + ", " + mem.toString() + "($sp)\n";
          temp++;
          mem += 800;
          dataCounter++;
        } else if (args.get(i).name == "plus") {
                    text += "\tsw $s" + Integer.toString(temp) + ", temp\n";
	        text += "\tlw $t" + temp.toString() + ", temp\n";
        } else if (args.get(i).name != "") {
          text += "\tsw $s" + Integer.toString(temp-args.size()+i) + ", temp\n";
	        text += "\tlw $t" + temp.toString() + ", temp\n";
        }
        text += "\tsw $t" + temp.toString() + ", " + mem.toString() + "($sp)\n";
        mem += 4;
      }
      if (n1.name == "constructor") {
        text += "\tlw $t" + temp.toString() + ", 8($v0)\n";
      } else if (n1.name == "identifier") {
        text += "\tlw $t" + temp.toString() + ", " + searchSymTable(n1.nodes.get(0).name) + "($sp)\n";
        text += "\tlw $t" + temp.toString() + ", 8($t" + temp.toString() + ")\n";
      }
      
      int t = -2;
      for (int i = 0; i < vtables.size(); i++) {
        if ( vtables.get(i).locate(n2.name) >= 0) {
          t = vtables.get(i).locate(n2.name);
          break;
        }
      }
      t *= 4;
      //temp++;
      text += "\tlw $t" + temp.toString() + ", " + Integer.toString(t) + "($t" + Integer.toString(temp) + ")\n";
      text += "\tjalr $t" + temp.toString() + "\n";
      //temp++;
      return n2;
    } else if (node.name == "self_dispatch") {
      Integer mem = 0;
      Node n = node.nodes.get(0);
      ArrayList<Node> args = handleArguments(node.nodes.get(1));
      for (int i = 0; i < args.size(); i++){
        if (args.get(i).name == "string") {
          data += "\narg" + dataCounter.toString() + ": .asciiz \"" + args.get(i).nodes.get(0).name + "\"\n";
          text += "\tla $t" + temp.toString() + ", arg" + dataCounter.toString() + "\n";
          text += "\tsw $t" + temp.toString() + ", " + mem.toString() + "($sp)\n";
          temp++;
          mem += 800;
          dataCounter++;
          // hi
        } else if (args.get(i).name == "integer"){
          text += "\tli $t" + temp.toString() + ", " + args.get(i).nodes.get(0).name + "\n";
          text += "\tsw $t" + temp.toString() + ", temp\n";
          text += "\tlw $t" + temp.toString() + ", temp\n";
        } else if (args.get(i).name == "identifier"){
          text += "\tlw $t" + temp.toString() + ", " + searchSymTable(args.get(i).nodes.get(0).name) + "($sp)\n";
        } else if (args.get(i).name == "plus") {
          text += "\tsw $t" + Integer.toString(temp) + ", temp\n";
	        text += "\tlw $t" + temp.toString() + ", temp\n";
        } else if (args.get(i).name == "if") {
          text += "\tsw $t" + Integer.toString(temp) + ", temp\n";
	        text += "\tlw $t" + temp.toString() + ", temp\n";
        } else {
          text += "\tsw $s" + Integer.toString(temp-args.size()+i) + ", temp\n";
	        text += "\tlw $t" + temp.toString() + ", temp\n";
      	}
        text += "\tsw $t" + temp.toString() + ", " + mem.toString() + "($sp)\n";
        mem += 4;
      }
      
      int t = -1;
      for (int i = 0; i < vtables.size(); i++) {
        if ( vtables.get(i).getTableName() == cName) {
          t = vtables.get(i).locate(n.name);
        }
      }
      t *= 4;
      
      text += "\tlw $t" + temp.toString() + ", 8($t7)\n";
      text += "\tlw $t" + temp.toString() + ", " + Integer.toString(t) + "($t" + Integer.toString(temp) + ")\n";
      text += "\tjalr $t" + temp.toString() + "\n";
      text += "\tlw $s" + temp.toString() + ", 0($sp)\n";
      temp++;
      
      return node;
      /// assign
      ///
      ///
      ///
    } else if (node.name == "assign") {
      Node n = handleExpression(node.nodes.get(1));
      if (n.name == "integer"){
        text += "\tli $t" + temp.toString() + ", " + n.nodes.get(0).name + "\n";
        text += "\tsw $t" + temp.toString() + ", temp\n";
        text += "\tlw $t" + temp.toString() + ", temp\n";
      } else if (n.name == "times") {
        text += "\tsw $t" + temp.toString() + ", temp\n";
        text += "\tlw $t" + temp.toString() + ", temp\n";
      } else if (n.name == "plus") {
         //text += "\tsw $t" + temp.toString() + ", temp\t***\n";
        text += "\tsw $t" + temp.toString() + ", temp\n";
        text += "\tlw $t" + temp.toString() + ", temp\n";
      } else if (n.name == "identifier") {
        text += "\tlw $t" + temp.toString() + ", " + searchSymTable(n.nodes.get(0).name) + "($sp)\n";
      }
      text += "\tsw $t" + temp.toString() + ", " + searchSymTable(node.nodes.get(0).name) + "($sp)\n";
      return node;
    } else if (node.name == "constructor") {
      text += "\tjal " + node.nodes.get(1).name + "..new\n";
      return node;
    } else if (node.name == "initialize") {
      symTable.get(symTable.size()-1).put(node.nodes.get(0).name,memory);
      memory += 4;
      return node;
    } else if (node.name == "paren") {
      return handleExpression(node.nodes.get(0));
    } else if (node.name == "block") {
	    for (int i = 0; i < node.nodes.get(0).nodes.size(); i++) {
	      handleExpression(node.nodes.get(0).nodes.get(i));
	    }
      return node;
      /// LET
      ///
      ///
      ///
    } else if (node.name == "let") {
      Node n = node.nodes.get(0).nodes.get(0);
      symTable.add(new HashMap<String,Integer>());
      
      ArrayList<Node> al1 = new ArrayList<Node>();
      al1.add(n.nodes.get(1));
      handleExpression(new Node("initialize",-1,al1));
      ArrayList<Node> al3 = new ArrayList<Node>();
      al3.add(n.nodes.get(1));
      Node id = new Node("identifier",-1,al3);
      ArrayList<Node> al2 = new ArrayList<Node>();
      al2.add(n.nodes.get(1));
      al2.add(n.nodes.get(3));
      handleExpression(new Node("assign",-1,al2));
      
      handleExpression(node.nodes.get(1));
      
      symTable.remove(symTable.size()-1);
      return node;
    } else if (node.name == "if") {
      Node equality = handleExpression(node.nodes.get(0));
      if (equality.name == "le") {
        text += "\tbgt $t" + Integer.toString(temp-1) + ", $t" + Integer.toString(temp) + ", ELSE\n";
      }
      temp--;
      Node n1 = handleExpression(node.nodes.get(1));
      Integer mem = 0;
      if (Pattern.compile("\\d+").matcher(n1.name).matches()){
      } else if (n1.name == "string") {
        data += "\narg" + dataCounter.toString() + ": .asciiz \"" + n1.nodes.get(0).name + "\"\n";
        text += "\tla $t" + temp.toString() + ", arg" + dataCounter.toString() + "\n";
        mem += 800;
        dataCounter++;
      }
      text += "\tj FI\n";
      text += "ELSE:\n";
      mem = 0;
      Node n2 = handleExpression(node.nodes.get(2));
      if (Pattern.compile("\\d+").matcher(n2.name).matches()){
      } else if (n2.name == "string") {
        data += "\narg" + dataCounter.toString() + ": .asciiz \"" + n2.nodes.get(0).name + "\"\n";
        text += "\tla $t" + temp.toString() + ", arg" + dataCounter.toString() + "\n";
        mem += 800;
        dataCounter++;
      }
      text += "FI:\n";
	    return node;
    } else if (node.name == "le") {
      Node n1 = handleExpression(node.nodes.get(0));
      if (n1.name == "bool"){
        text += "\tli $t" + temp.toString() + ", " + n1.nodes.get(0).name + "\n";
        text += "\tsw $t" + temp.toString() + ", temp\n";
        text += "\tlw $t" + temp.toString() + ", temp\n";
        temp++;
      }
      Node n2 = handleExpression(node.nodes.get(1));
      if (n2.name == "bool"){
        text += "\tli $t" + temp.toString() + ", " + n2.nodes.get(0).name + "\n";
        text += "\tsw $t" + temp.toString() + ", temp\n";
        text += "\tlw $t" + temp.toString() + ", temp\n";
      }
      return node;
    } else if (node.name == "times") {
      Node left = handleExpression(node.nodes.get(0));
      Node right = handleExpression(node.nodes.get(1));
      if (left.name == "integer"){
        text += "\tli $t" + temp.toString() + ", " + left.nodes.get(0).name + "\n";
        text += "\tsw $t" + temp.toString() + ", temp\n";
      } else if (left.name == "times") {
        // do nothing
      } else if (left.name == "identifier") {
        text += "\tlw $t" + temp.toString() + ", " + searchSymTable(left.nodes.get(0).name) + "($sp)\n";
      }
      temp++;
      if (right.name == "integer"){
        text += "\tli $t" + temp.toString() + ", " + right.nodes.get(0).name + "\n";
      } else if (right.name == "times") {
        // do nothing
      } else if (right.name == "identifier") {
        text += "\tlw $t" + temp.toString() + ", " + searchSymTable(left.nodes.get(0).name) + "($sp)\n";
      }
      text += "\tmult $t" + Integer.toString(temp-1) + ", $t" + Integer.toString(temp) + "\n";
      temp--;
      text += "\tmflo $t" + temp.toString() + "\n";
      return node;
    } else if (node.name == "plus") {
      Node left = handleExpression(node.nodes.get(0));
      if (left.name == "assign") {
        text += "\tsw $t" + temp.toString() + ", " + Integer.toString(tempMem) + "($sp)\n";
      }
      if (left.name == "integer" || left.name == "identifier" || left.name == "assign") {
        tempMem += 4;
      }
      Node right = handleExpression(node.nodes.get(1));
      if (left.name == "integer" || left.name == "identifier"|| left.name == "assign") {
        tempMem -= 4;
      }  
      
      if (left.name == "integer"){
        text += "\tli $t" + temp.toString() + ", " + left.nodes.get(0).name + "\n";
        text += "\tsw $t" + temp.toString() + ", temp\n";
        text += "\tlw $t" + temp.toString() + ", temp\n";
      } else if (left.name == "identifier") {
        text += "\tlw $t" + temp.toString() + ", " + searchSymTable(left.nodes.get(0).name) + "($sp)\n";
      } else if (left.name == "assign") {
      } else {
        text += "\tlw $t" + temp.toString() + ", " + Integer.toString(tempMem) + "($sp)\n";
      }
      if (left.name != "assign") {
        text += "\tsw $t" + temp.toString() + ", " + tempMem.toString() + "($sp)\n";
      }
      //if (right.name != "integer" && right.name != "identifier") {
        tempMem += 4;
      //}
      if (right.name == "integer"){
        text += "\tli $t" + temp.toString() + ", " + right.nodes.get(0).name + "\n";
        text += "\tsw $t" + temp.toString() + ", temp\n";
        text += "\tlw $t" + temp.toString() + ", temp\n";
      } else if (right.name == "identifier") {
        text += "\tlw $t" + temp.toString() + ", " + searchSymTable(right.nodes.get(0).name) + "($sp)\n";
      } else {
        if (left.name == "integer" || left.name == "identifier" ) {
          text += "\tlw $t" + temp.toString() + ", " + Integer.toString(tempMem) + "($sp)\n";
        } else {
          text += "\tlw $t" + temp.toString() + ", " + Integer.toString(tempMem) + "($sp)\n";
        }
      }
      text += "\tsw $t" + temp.toString() + ", " + tempMem.toString() + "($sp)\n";
      text += "\tlw $t" + temp.toString() + ", " + Integer.toString(tempMem-4)+ "($sp)\n";
      temp++;
      text += "\tlw $t" + temp.toString() + ", " + Integer.toString(tempMem)+ "($sp)\n";
      text += "\tadd $t" + Integer.toString(temp-1) + ", $t" + Integer.toString(temp-1) + ", $t" + Integer.toString(temp) + "\n";
      temp--;
      text += "\tsw $t" + temp.toString() + ", temp\n";
      text += "\tlw $t" + temp.toString() + ", temp\n";
      text += "\tsw $t" + temp.toString() + ", " + Integer.toString(tempMem-4) + "($sp)\n";
      tempMem -= 4;
      return node;
    } else if (node.name == "integer"){
      return node;
    } else if (node.name == "string") {
      return node;
    } else if (node.name == "identifier") {
      return node;
    } else {
      return node;
    }
  }
  
  private static ArrayList<Node> handleArguments( Node node ) throws IOException {
    ArrayList<Node> al = new ArrayList<Node>();
    for (int i = 0; i < node.nodes.size(); i++) {
      al.add(handleExpression(node.nodes.get(i)));
    }
    return al;
  }

}
