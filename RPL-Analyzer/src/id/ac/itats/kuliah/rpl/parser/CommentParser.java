package id.ac.itats.kuliah.rpl.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public final class CommentParser {

     private List<String> varGlobal;
     private List<String> varLocal;
     private List<String> varParameter;

     public CommentParser(String filePath) {
          varGlobal = new ArrayList<>();
          varGlobal.add("===VARIABEL GLOBAL ===");
          varLocal = new ArrayList<>();
          varLocal.add("===VARIABEL LOCAL ===");
          varParameter = new ArrayList<>();
          varParameter.add("===VARIABEL PARAMETER ===");
          try {
               parse(readFileToString(filePath));
          } catch (IOException ex) {
               Logger.getLogger(CommentParser.class.getName()).log(Level.SEVERE, null, ex);
          }
     }

     // use ASTParse to parse string
     public void parse(String str) {
          ASTParser parser = ASTParser.newParser(AST.JLS3);
          parser.setSource(str.toCharArray());
          parser.setKind(ASTParser.K_COMPILATION_UNIT);

          final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

          cu.accept(new ASTVisitor() {
               @Override
               public boolean visit(VariableDeclarationFragment node) {
                    SimpleName name = node.getName();
                    varGlobal.add("" + name);
                    //     System.out.println("Global Variabel :\t" + name);
                    return false; // do not continue

               }

               @Override
               public boolean visit(VariableDeclarationStatement node) {

                    for (Iterator iter = node.fragments().iterator(); iter
                            .hasNext();) {
                         VariableDeclarationFragment fragment = (VariableDeclarationFragment) iter
                                 .next();
                         varLocal.add("" + fragment.getName());
//                         System.out.println("Local Variabel :\t"
//                                 + fragment.getName());

                    }

                    return false; // prevent that SimpleName is interpreted as
                    // reference
               }

               @Override
               public boolean visit(SingleVariableDeclaration node) {
                    varParameter.add("" + node.getName());
//                    System.out.println("Variable Parameter :\t" + node.getName());
                    return true;

               }
          });

     }

     // read file content into a string
     public String readFileToString(String filePath) throws IOException {
          StringBuilder fileData = new StringBuilder(1000);
          try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
               char[] buf = new char[10];
               int numRead = 0;
               while ((numRead = reader.read(buf)) != -1) {
                    // System.out.println(numRead);
                    String readData = String.valueOf(buf, 0, numRead);
                    fileData.append(readData);
                    buf = new char[1024];
               }
          }

          return fileData.toString();
     }

     // loop directory to get file list
     public void ParseFilesInDir() throws IOException {

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

     public void ParseFiles(String filePath) throws IOException {

          parse(readFileToString(filePath));

     }

     public static void main(String[] args) {
          CommentParser parser = new CommentParser("data/Test.java");
          System.out.println("GLOBAL : " + parser.varGlobal);
          System.out.println("LOCAL : " + parser.varLocal);
          System.out.println("PARAMETER : " + parser.varParameter);
     }
}