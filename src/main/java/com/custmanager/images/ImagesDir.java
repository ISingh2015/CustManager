package com.custmanager.images;

import java.io.File;
import javax.swing.ImageIcon;
/**
 *
 * @author Inderjit
 */
public class ImagesDir {

    public static synchronized ImageIcon getImage(String istrAdditionalPath) {
        File file = new File("images\\" + istrAdditionalPath);
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        return icon;
    }

}
