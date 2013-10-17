
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ziez
 */
public class Parsing{

    public static void main(String[] args) {
        File f=new  File("/home/ziez/Documents/laboratory/java/desktop/geany");
        readRecursiveFile(f);
    }

    private static void readRecursiveFile(File f) {
        File[] files=f.listFiles(new FilterByteCode());
        if(null==files){
            files=new File[0];
        }
        for(File curr: files){
             
             if (!curr.isDirectory()){
                  System.out.println(curr.getName());
             }
          
            readRecursiveFile(curr);
        }
    }
}//end parsing

class FilterByteCode implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        if(null!=pathname && pathname.getName().endsWith(".java")){
            return  acceptByteCode(pathname);
        }else{
            return (null!=pathname && pathname.exists() && pathname.isDirectory());
        }
    }//accept

    private boolean acceptByteCode(File dir) {
        boolean res = (null != dir && dir.exists() && dir.isFile());
        return res;
    }

}//FilterByteCode 