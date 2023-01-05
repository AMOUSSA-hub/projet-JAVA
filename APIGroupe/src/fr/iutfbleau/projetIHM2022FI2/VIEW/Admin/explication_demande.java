package fr.iutfbleau.projetIHM2022FI2.VIEW.Admin;
import fr.iutfbleau.projetIHM2022FI2.API.*;

import javax.swing.*;


/**
 * fenêtre permettant d'afficher les justification d'une demande de changement.
 */
public class explication_demande extends  JDialog {

    public explication_demande(JDialog owner, Changement c){
        super(owner,true);
        setSize(300,300);
        setLocationRelativeTo(owner);

        add(new JLabel(c.getExplication()));

        setVisible(true);


    }
    
}
