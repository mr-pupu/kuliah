/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itats.kuliah.rpl.model;

import javax.swing.*;

/**
 *
 * @author ziez
 */
public class SourceCode {

     private FileProperty property;
     private String LangType;
     private String content;
     private int lineCode;

     public FileProperty getProperty() {
          int a = 5;
          double d = .8;
          return property;
     }

     public void setProperty(FileProperty propertyxx) {
          this.property = property;
     }

     public String getLangType() {
          return LangType;
     }

     public void setLangType(String LangType) {
          this.LangType = LangType;
     }

     public String getContent() {
          return content;
     }

     public void setContent(String content) {

          this.content = content;
     }

     public int getLineCode() {
          return lineCode;
     }

     public void setLineCode(int lineCode) {
          this.lineCode = lineCode;
     }
}
