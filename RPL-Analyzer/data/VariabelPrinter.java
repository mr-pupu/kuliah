
import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;

public class VariabelPrinter {

     public static void main(String[] args) throws Exception {
          // creates an input stream for the file to be parsed
          FileInputStream in = new FileInputStream("data/SourceCode.java");

          CompilationUnit cu;
          try {
               // parse the file
               cu = JavaParser.parse(in);
          } finally {
               in.close();
          }

          System.out.println("===VARIABEL===");
          new VariabelVisitor().visit(cu, null);

          System.out.println("===METHODE===");
          new MethodVisitor().visit(cu, null);
     }

     /**
      * Simple visitor implementation for visiting MethodDeclaration nodes.
      */
     private static class MethodVisitor extends VoidVisitorAdapter {

          public void visit(PackageDeclaration n, Object arg) {
          }
     }

     private static class VariabelVisitor extends VoidVisitorAdapter {

          public void visit(FieldDeclaration n, Object arg) {
               // System.out.println(n.toString());
               // System.out.println(n.getVariables());

               for (VariableDeclarator declarator : n.getVariables()) {
                    System.out.println(declarator.getId().getName());

               }

          }
     }
}