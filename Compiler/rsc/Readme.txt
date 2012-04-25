Eric Brodersen
Mark Harder

To run the parser and generate the output for PCSpim, run the batch file Z:CS333\ME\TestPA4.bat

That will create programs named file_name.cl-ast. Open these in PCSpim. The starting point of all the programs is Main..new, which should be at the bottom of the code portion of PCSpim, right above the Kernel code

Our lexer is a jflex file named Lexer.jflex.
Our parser is a cup file named ycool.cup.
The parser creates an ASM which is handled by Main.java, which creates the asm output.
Node.java, Token.java, and Vtable.java all assist in creating the tokens, AST, and asm output.

Pass
  hello.cl
  assignments.cl
  bool-comparison.cl
  bigexpr.cl
  calls-with-same-formal-name.cl
  case-order.cl
  dispatch-override-dynamic.cl
  shadow-let-let.cl
  void-comparison.cl
  dispatch-override-static.cl
  



Fail
  alias.cl
  in-int.cl
  newbasic.cl
  copy-self-dispatch.cl
  eval-order-self.cl
  let-nested.cl
  shadow-attr-case.cl
  typename.cl
  init-order-super.cl
  book-list-static-dynamic-dispatch.cl
  bool-assigned-in-let.cl
  huge-case-in-big-loop.cl
  new-self-init.cl
  one-nested-exp-with-789-arith-ops.cl
  receiver-class-hard-to-determine.cl
  scoping.cl
  big-prog-with-many-exprs.cl