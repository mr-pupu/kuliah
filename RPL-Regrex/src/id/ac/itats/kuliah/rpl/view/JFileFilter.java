/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itats.kuliah.rpl.view;

/**
 *
 * @author ziez
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class JFileFilter extends javax.swing.filechooser.FileFilter {
  protected String description;

  protected ArrayList exts = new ArrayList();

  public void addType(String s) {
    exts.add(s);
  }

  /** Return true if the given file is accepted by this filter. */
  @Override
  public boolean accept(File f) {
    // Little trick: if you don't do this, only directory names
    // ending in one of the extentions appear in the window.
    if (f.isDirectory()) {
      return true;

    } else if (f.isFile()) {
      Iterator it = exts.iterator();
      while (it.hasNext()) {
        if (f.getName().endsWith((String) it.next()))
          return true;
      }
    }

    // A file that didn't match, or a weirdo (e.g. UNIX device file?).
    return false;
  }

  /** Set the printable description of this filter. */
  public void setDescription(String s) {
    description = s;
  }

  /** Return the printable description of this filter. */
  @Override
  public String getDescription() {
    return description;
  }
}


