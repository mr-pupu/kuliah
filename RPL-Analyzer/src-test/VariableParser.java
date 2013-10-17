
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class VariableParser {

     // use ASTParse to parse string
     public static void parse(String str) {
          ASTParser parser = ASTParser.newParser(AST.JLS3);
          parser.setSource(str.toCharArray());
          parser.setKind(ASTParser.K_COMPILATION_UNIT);

          final CompilationUnit cu = (CompilationUnit) parser.createAST(null);


          cu.accept(new ASTVisitor() {
               @Override
               public boolean visit(VariableDeclarationFragment node) {
                    SimpleName name = node.getName();

                    System.out.println("Global Variabel :\t" + name);
                    return false; // do not continue

               }

               @Override
               public boolean visit(VariableDeclarationStatement node) {

                    for (Iterator iter = node.fragments().iterator(); iter
                            .hasNext();) {
                         VariableDeclarationFragment fragment = (VariableDeclarationFragment) iter
                                 .next();

                         System.out.println("Local Variabel :\t"
                                 + fragment.getName());

                    }

                    return false; // prevent that SimpleName is interpreted as
                    // reference
               }

               public boolean visit(SingleVariableDeclaration node) {
                    System.out.println("Variable Parameter :\t" + node.getName());
                    return true;

               }

               public boolean visit(CompilationUnit node) {


                    List comments = node.getCommentList();

                    ListIterator iterator = comments.listIterator();
                    while (iterator.hasNext()) {
                         Object object = iterator.next();
                         if (object instanceof Javadoc) {
                              String comment = "" + object;

                              System.out.println("" + object);
                         }

                         if (object instanceof LineComment) {

                              Comment c = (LineComment) object;


                              System.out.println("" + object);
                         }


                    }




                    return true;
               }
          });

     }

     // read file content into a string
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

     // loop directory to get file list
     public static void ParseFilesInDir() throws IOException {

          String dirPath = "data/";

          File root = new File(dirPath);
          // System.out.println(root.listFiles());
          File[] files = root.listFiles();
          String filePath = null;

          for (File f : files) {
               filePath = f.getAbsolutePath();
               if (f.isFile()) {
                    parse(readFileToString(filePath));
               }
          }
     }

     public static void ParseFiles(String filePath) throws IOException {

          parse(readFileToString(filePath));

     }

     public static void main(String[] args) throws IOException {
          ParseFiles("data/Test.java");
     }
}