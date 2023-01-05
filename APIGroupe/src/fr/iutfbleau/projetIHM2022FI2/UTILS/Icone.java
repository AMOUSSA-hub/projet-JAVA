package fr.iutfbleau.projetIHM2022FI2.UTILS;

import javax.swing.ImageIcon;

import java.awt.*;;
/**
 * stock les images.
 */
public class Icone  {
    
     static ClassLoader loader = Thread.currentThread().getContextClassLoader();

public static ImageIcon GROUP = new ImageIcon(Toolkit.getDefaultToolkit().getImage(loader.getResource("res/group.png")));

   
    
}
