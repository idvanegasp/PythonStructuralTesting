package main;

import java.io.File;
import java.io.FileInputStream;

import classes.MyPythonTesterVisitor;
import classes.Python3Lexer;
import classes.Python3Parser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Python3Tester {

    public static void main(String[] args)throws Exception{

        System.setIn(new FileInputStream(new File("while_input.py")));
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        Python3Lexer lexer = new Python3Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Python3Parser parser = new Python3Parser(tokens);
        ParseTree tree = parser.file_input();


        MyPythonTesterVisitor<Object> loader = new MyPythonTesterVisitor<Object>();
        loader.visit(tree);

    }

}