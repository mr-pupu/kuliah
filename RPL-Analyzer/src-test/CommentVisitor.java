/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ziez
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.LineComment;

public class CommentVisitor extends ASTVisitor {

     CompilationUnit compilationUnit;
     private String[] source;

     public CommentVisitor(CompilationUnit compilationUnit, String[] source) {

          super();
          this.compilationUnit = compilationUnit;
          this.source = source;
     }

     @Override
     public boolean visit(LineComment node) {

          int startLineNumber = compilationUnit.getLineNumber(node.getStartPosition()) - 1;
          String lineComment = source[startLineNumber].trim();

          System.out.println(lineComment);

          return true;
     }

     /**
      *
      * @param node
      * @return
      */
     @Override
     public boolean visit(BlockComment node) {

          int startLineNumber = compilationUnit.getLineNumber(node.getStartPosition()) - 1;
          int endLineNumber = compilationUnit.getLineNumber(node.getStartPosition() + node.getLength()) - 1;

          StringBuffer blockComment = new StringBuffer();

          for (int lineCount = startLineNumber; lineCount <= endLineNumber; lineCount++) {

               String blockCommentLine = source[lineCount].trim();
               blockComment.append(blockCommentLine);
               if (lineCount != endLineNumber) {
                    blockComment.append("\n");
               }
          }

          System.out.println(blockComment.toString());

          return true;
     }

     @Override
     public void preVisit(ASTNode node) {
     }

     public static String readFileToString(String filePath) throws IOException {
          StringBuilder fileData = new StringBuilder(1000);
          BufferedReader reader = new BufferedReader(new FileReader(filePath));

          char[] buf = new char[10];
          int numRead = 0;
          while ((numRead = reader.read(buf)) != -1) {
               // System.out.println(numRead);
               String readData = String.valueOf(buf, 0, numRead);
               fileData.append(readData);
               buf = new char[1024];
          }

          reader.close();

          return fileData.toString();
     }

     public static void main(String[] args) throws IOException {
     }
}
