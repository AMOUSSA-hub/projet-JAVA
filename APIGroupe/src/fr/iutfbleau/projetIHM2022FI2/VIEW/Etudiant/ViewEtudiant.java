package fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

/**
 * fenêtre d'accueil de l'Etudiant.
 */
public class ViewEtudiant extends JFrame {
	
    private static DefaultMutableTreeNode root;
    private static JPanel pan_groupe ;
    public static  JButton demande_de_changement;
    public static JFrame fenetre;
    public static JPanel menu_etudiant = new JPanel();  
    public static Map<String,Integer> group_map = new HashMap<String,Integer>();
    public static CardLayout gestionnaire = new CardLayout();
    public static JTree arbre ;
    public static MyAbstractGroupeFactory bd;
    public static MyAbstractChangementFactory demandes;
    public static Etudiant profil_actuelle;
    
    

	public ViewEtudiant(String nom, int id, MyAbstractGroupeFactory magf) {
        super(nom);
        profil_actuelle = new MyEtudiant(id); 
        System.out.println(profil_actuelle.getNom());
        bd = magf;

        JPanel north_pan = new JPanel();
        JPanel south_pan = new JPanel();
        pan_groupe = new JPanel();
        demande_de_changement = new JButton("Changer de groupe");
        JButton voir_les_demandes = new JButton("Voir les demandes");
        demandes = new MyAbstractChangementFactory(bd);

        pan_groupe.setLayout(gestionnaire);
		setLocation(50, 50);
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        north_pan.setPreferredSize(new Dimension(300,300));
        north_pan.setLayout(new GridLayout(1,2));
        
        menu_etudiant.setLayout(gestionnaire);
      
        arbre =  display_group(new DefaultMutableTreeNode(bd.getPromotion().getName()),1);

        pan_groupe.add(arbre);
        north_pan.add(pan_groupe);
        north_pan.add(new JScrollPane(pan_groupe));
        north_pan.add(new JScrollPane(menu_etudiant));
        
        demande_de_changement.addActionListener(new Observateur_MEG(this, profil_actuelle));
        voir_les_demandes.addActionListener(new Observateur_MEG(this, profil_actuelle));

        south_pan.setBackground(new Color(116, 208, 241));
        south_pan.add(demande_de_changement,BorderLayout.CENTER);
        south_pan.add(voir_les_demandes,BorderLayout.CENTER);

        add(north_pan);
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
            if(a.getId()== profil_actuelle.getId() ){

                JLabel capsule_etu = new JLabel(a.getNom()+ " "+  a.getPrenom());
                capsule_etu.setForeground(Color.BLUE);
                info_etudiant.add(capsule_etu);
            }
            else{
            info_etudiant.add(new JLabel(a.getNom()+ " "+  a.getPrenom()));
            }
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
