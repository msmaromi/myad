/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myad.model;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author FUJITSU
 */
public class MyCustomFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
//        only xml file
        return (f.isDirectory() || f.getAbsolutePath().endsWith(".xml"));
    }

    @Override
    public String getDescription() {
        return "AdsML Documents";
    }
}
