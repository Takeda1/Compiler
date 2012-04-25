# change the directory and file names for your project below
# WARNING this shell file deletes all .class, .ast, and Lexer.java files in CS333/group/assignment

directory=$(pwd)

#changes these
group=ME
assignment=PA4
LEXER_NAME=Lexer.jflex
CUP_NAME=ycool.cup
MAIN_NAME=Main

CLASSPATH=".;./java_cup"
alias java="/c/Program\ Files/Java/jdk1.7.0_02/bin/java"

# create the CUP class named CUP_NAME
cd $directory/$assignment


# creating the java lexer file named LEXER_NAME
java -jar ../../JFlex.jar $LEXER_NAME

java java_cup.Main < $CUP_NAME

# compile the main class
/c/Program\ Files/Java/jdk1.7.0_02/bin/javac $MAIN_NAME.java

clear

java $MAIN_NAME "good/hello.cl" 
java $MAIN_NAME "good/assignments.cl"
java $MAIN_NAME "good/bool-comparison.cl"
java $MAIN_NAME "good/bigexpr.cl"
java $MAIN_NAME "good/calls-with-same-formal-name.cl"
java $MAIN_NAME "good/dispatch-override-dynamic.cl"
java $MAIN_NAME "good/dispatch-override-static.cl"
java $MAIN_NAME "good/shadow-let-let.cl"
java $MAIN_NAME "good/void-comparison.cl"
java $MAIN_NAME "good/case-order.cl"

rm Lexer.java
rm *.class