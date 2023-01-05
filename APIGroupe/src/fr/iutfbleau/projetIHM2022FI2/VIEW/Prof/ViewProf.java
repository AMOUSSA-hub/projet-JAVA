package fr.iutfbleau.projetIHM2022FI2.VIEW.Prof;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;


/**
 * fenêtre d'accueil du Prof
 */
public class ViewProf extends JFrame {
	
    private static DefaultMutableTreeNode root;
    private static JPanel pan_groupe ;
    public static  JButton demande_de_changement;
    public static JFrame fenetre;
    public static JPanel menu_etudiant = new JPanel();  
    public static Map<String,Integer> group_map = new HashMap<String,Integer>();
    public static CardLayout gestionnaire = new CardLayout();
    public static JTree arbre ;
    public static MyAbstractGroupeFactory bd ;
    

	public ViewProf(MyAbstractGroupeFactory magf) {
        super("Professeur");
        bd = magf;
        JPanel center_pan = new JPanel();
        JPanel dashboard_pan = new JPanel();
        JPanel south_pan = new JPanel();
        pan_groupe = new JPanel();
        JPanel search_pan = new JPanel();


        JTextField search_bar = new JTextField("entrez le nom d'un étudiant");
        search_bar.setPreferredSize(new Dimension(150,20));

        center_pan.setLayout(gestionnaire);
        JButton search = new JButton("rechercher");
        search.addActionListener(new Observateur_recherche(search_bar,bd,center_pan,gestionnaire));

        pan_groupe.setLayout(gestionnaire);
		setLocation(50, 50);
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        
       
        dashboard_pan.setLayout(new GridLayout(1,2));
        
        menu_etudiant.setLayout(gestionnaire);
      
        arbre =  display_group(new DefaultMutableTreeNode(bd.getPromotion().getName()),1);

        search_pan.setBackground(new Color(116, 208, 241));
        search_pan.add(search_bar, BorderLayout.CENTER);
        search_pan.add(search,BorderLayout.EAST);
        pan_groupe.add(arbre);
        dashboard_pan.add(new JScrollPane(pan_groupe),BorderLayout.WEST);
        dashboard_pan.add(new JScrollPane(menu_etudiant),BorderLayout.WEST);

        center_pan.add(dashboard_pan,"home");

        south_pan.setBackground(new Color(116, 208, 241));

        add(search_pan,BorderLayout.NORTH);
        add(center_pan,BorderLayout.CENTER);
        add(south_pan,BorderLayout.SOUTH);

		setVisible(true);
    }

    

    
    public static void display_etudiant (Groupe groupe_selected,String titre_diapo){

         JPanel diapositive_etudiant = new JPanel();


        Set<Etudiant> list_etu =  groupe_selected.getEtudiants();
        Iterator<Etudiant> iterator = list_etu.iterator();

        while(iterator.hasNext()){

            JPanel info_etudiant = new JPanel();
            info_etudiant.setBorder(BorderFactory.createLineBorder(Color.black));
            Etudiant a = iterator.next();
            info_etudiant.add(new JLabel(a.getNom()+ " "+  a.getPrenom() ));
            diapositive_etudiant.add(info_etudiant);
        }



            
            diapositive_etudiant.setLayout(new GridLayout(list_etu.size(),3));
            

        menu_etudiant.add(diapositive_etudiant,groupe_selected.getId()+"");
        
    }

    
    
    
    
    public static JTree  display_group( DefaultMutableTreeNode node,int id_groupe){
  
        DefaultMutableTreeNode parent = node;

        String path = Utils.TreeNode_to_String(parent.getPath());

        MyGroupe g = bd.brain.get(id_groupe);

        g.setPath(path);

        group_map.put(path,id_groupe);
        display_etudiant(g,g.getPath());
     
        if(id_groupe == 1 ){
            root = parent;
        }

    
     
        Set<Groupe> list_groupe = g.getSousGroupes();
        Iterator<Groupe> iterator = list_groupe.iterator();


                   
            while(iterator.hasNext()){

                Groupe sous_groupe = iterator.next();
                DefaultMutableTreeNode fils = new DefaultMutableTreeNode(sous_groupe.getName());
               
                parent.add(fils);
                
                
               
                 display_group(fils,sous_groupe.getId());
                
                
            }   
  

            JTree arborescence_groupe = new JTree(root);
            arborescence_groupe.addTreeSelectionListener(new Observateur_arborescence(bd,group_map,gestionnaire,menu_etudiant));

      return arborescence_groupe;

    }

    public static void loadPanGroup(){

        group_map.clear();
       

        arbre = display_group(new DefaultMutableTreeNode(bd.getPromotion().getName()),1);

        
        pan_groupe.add(arbre);
        gestionnaire.next(pan_groupe);
        gestionnaire.show(menu_etudiant,Observateur_arborescence.group_selected.getId()+"");

        fenetre.setVisible(true);
    }
}
