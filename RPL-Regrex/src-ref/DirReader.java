import java.io.File;
import java.util.*;
import javax.swing.JFileChooser;

// Directory Recursion Exercises
// Names:
//
// Date: February 25, 2011


public class DirReader
{
    private static final JFileChooser ourChooser = new JFileChooser(
        System.getProperties().getProperty("user.dir"));

    /**
     * Brings up chooser for user to select a directory
     * @return File for user selected directory, null if nothing chosen
     */
    public File getDirectory(){
        ourChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int retval = ourChooser.showOpenDialog(null);
        
        if (retval == JFileChooser.APPROVE_OPTION){
            return ourChooser.getSelectedFile();	
        }
        return null;
    }

    
    /**
     * Return list of all files in directory
     * 
     * @param dir starting directory
     * @return list of File in dir (not including dir)
     */
    public List<File> directoryList (File dir)
    {
        return Arrays.asList(dir.listFiles());
    }


    /**
     * Recursively descends directory and returns a list of all 
     * files reachable from directory dir
     * 
     * @param dir starting directory
     * @return list of all files in the the folder dir
     */
    public List<File> recursiveList (File dir)
    {
        List<File> results = new ArrayList<File>();
        List<File> files = directoryList(dir);
        for (File current : files)
        {
            // TODO: add file or add list of files in sub-directory
        }
        return results;
    }

    /**
     * Recursively descend directory up until a certain depth
     * 
     * @param dir starting directory
     * @param depth maximum number of subfolders to list,
     *              0 would be just the current file
     * @return list of all files in directory
     */
    public List<File> recursiveList (File dir, int depth)
    {
        List<File> results = new ArrayList<File>();
        // TODO: add file or add list of files in sub-directory 
        //       up to a given depth
        return results; 
    }

    public long recursiveSize (File dir)
    {
        long sum = 0;
        List<File> files = directoryList(dir);
        for (File current : files)
        {
            // TODO: add file's or sub-directory's size to sum
        }
        return sum;
    }


    public long recursiveMax (File dir)
    {
        long max = 0;
        List<File> files = directoryList(dir);
        for (File current : files) {
            // TODO: determine max total size from current file's
            //       or sub-directory's size
        }
        return max;
    }

    /**
     * Visit this directory and all the subdirectories in it. For each file in
     * this directory, print information about it
     * 
     * @param dir starting directory
     * @param level specifies the level of indentation
     */
    private void printDirectory (File dir, int level)
    {
        List<File> files = directoryList(dir);
	    for (File current : files)
        {
            // TODO: print contents indented by level spaces
        }
    }

    /**
     * Print all files contained in the directory and directories within,
     * with each file indented by the number of tabs equal to its level
     * 
     * @param dir starting directory
     */
    public void printDirectory (File dir)
    {
        System.out.println(dir.getName());
        printDirectory(dir, 1);
    }

    /**
     * Return a string of spaces/tabs.
     * 
     * @param count specifies how many tabs in returned string
     * @return string of count 'tabs'
     */
    private String tab (int count)
    {
        String s = "";
        for (int k = 0; k < count; k++)
            s += "  ";
        return s;
    }

    public static void main (String[] args)
    {
        final int DEPTH = 3;
        DirReader reader = new DirReader();
        File dir = reader.getDirectory();

        // 0. List Directory
        List<File> list = reader.directoryList(dir);
        System.out.println("0:  Directory listing:");
        System.out.println(list);
        System.out.println(); 

        // 1. list Directory recursively
        System.out.println("1: Recursive listing");
        System.out.println(reader.recursiveList(dir));
        System.out.println(); 

        // 2. depth Limited Recursive
        System.out.println("2: Listing up to depth " + DEPTH);
        System.out.println(reader.recursiveList(dir, DEPTH));
        System.out.println(); 

        // 3. depth Limited Recursive
        System.out.println("3: Size of directory");
        System.out.println(reader.recursiveSize(dir));
        System.out.println(); 

        // 4. depth Limited Recursive
        System.out.println("4: Max file size");
        System.out.println(reader.recursiveMax(dir));
        System.out.println(); 

        // 5. printDirectory
        System.out.println("5: Printing directory Tree");
        reader.printDirectory(dir);
        System.out.println(); 
    }
}
