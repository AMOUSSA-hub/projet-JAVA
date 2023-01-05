package fr.iutfbleau.projetIHM2022FI2.API;
import java.util.*;
/**
 * Un groupe
 */

public interface Groupe extends MonPrint {

    

    /**
     * Ajoute un étudiant. Se comporte comme add de l'interface Set.
     *
     * @return true iff e est ajouté
     */
    public boolean addEtudiant(Etudiant e);

    /**
     * Enlève un étudiant. Se comporte comme remove de l'interface Set.
     *
     * @return true iff e est enlevé
     */
    boolean removeEtudiant(Etudiant e);

    /**
     * Ajoute un sous-groupe. Se comporte comme add de l'interface Set.
     * vérifie que le groupe passé en argument a bien renseigné this comme son père.
     *
     * @return true iff g est ajouté
     */
    public boolean addSousGroupe(Groupe g);

    /**
     * Enlève un groupe. Se comporte comme remove de l'interface Set.
     *
     * @return true iff e est enlevé
     */
    public boolean removeSousGroupe(Groupe g);

    /**
     * permet de récupérer l'identifiant d'un groupe (référence interne sans intérêt irl).
     * @return l'identifiant.
     */
    public int getId();

    /**
     * permet de récupérer le nom d'un groupe (utile irl).
     * @return le nom.
     */
    public String getName();

    /**
     * permet de récupérer le nombre minimum d'étudiants souhaités dans le groupe.
     * @return le minimum souhaité
     */
    public int getMin();

    /**
     * permet de récupérer le nombre maximum d'étudiants souhaités dans un groupe.
     * @return le maximum souhaité
     */
    public int getMax();

    /**
     * permet de récupérer le nombre d'étudiants dans ce groupe.
     * @return le nombre de places prises (pas forcément limité entre Min et Max, mais c'est le but)
     */
    public int getSize();

    /**
     * permet de récupérer la nature du groupe
     * @return le type du groupe
     */
    public TypeGroupe getType();

    /**
     * permet de récupérer le groupe père
     * un groupe racine devrait retourner lui-même
     *
     * @return le père
     */
    public Groupe getPointPoint();

    /**
     * Potentiellement "vide"
     * @return l'ensemble de sous-groupes.
     */
    public Set<Groupe> getSousGroupes();

    /**
     * Potentiellement "vide"
     * @return l'ensemble des étudiants.
     */
    public Set<Etudiant> getEtudiants();

     /**
    * vérife si l'étudiant fait parti du groupe.
    * @param e etudiant que l'on veut tester
    * @return true si l'étudiant fait parti du groupe ou sinon false.
    */
    public boolean contain( Etudiant e);
    
    /**
     * @see MonPrint
     * NB. On n'utilise le mécanisme des méthodes par défaut pour donner du code dans une interface. C'est un petit peu laid et à contre-emploi mais pratique ici.
     *
     * NB2. On ne peut pas utiliser le toString de Objects
     * https://stackoverflow.com/questions/24016962/java8-why-is-it-forbidden-to-define-a-default-method-for-a-method-from-java-lan
     */ 
     public default String monPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append("Groupe " + getName() + " (" + getType() + ", capacité " + getMin() + " à " + getMax()  +", id " + getId()+")");
        sb.append("\n");
        // NB. getEtudiants() retourne un Set qui étend Iterable
        // On peut utiliser les mécanismes de boucle "moderne" de puis la version 8
        // pour se faciliter la vie quand le traitement est assez simple
        // voir https://docs.oracle.com/javase/8/docs/technotes/guides/language/foreach.html
        sb.append(" ** Étudiants **\n");
        for (Etudiant s: getEtudiants()){
            sb.append("\t _ "+ s.monPrint());
            sb.append("\n");
        }
        sb.append(" ** Sous Groupes **\n");
        for (Groupe g: getSousGroupes()){
            sb.append("\t _ "+ g.getName() + " (" + g.getType() + ", capacité " + g.getMin() + " à " + g.getMax()  +", id " + g.getId()+")");
            sb.append("\n");
        }
        return sb.toString();
    }

}
