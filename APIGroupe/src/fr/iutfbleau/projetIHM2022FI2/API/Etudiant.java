package fr.iutfbleau.projetIHM2022FI2.API;
/**
 * Un étudiant
 */

public interface Etudiant extends MonPrint{



    /**
     * permet de récupérer l'identifiant de l'étudiant.
     * @return l'identifiant.
     */
    public int getId();

    /**
     * permet de récupérer 
     * @return le nom de l'étudiant.
     */
    public String getNom();

    /**
     * permet de récupérer
     * @return le prénom de l'étudiant
     */
    public String getPrenom();

    /**
     * @see MonPrint
     * NB. On n'utilise le mécanisme des méthodes par défaut pour donner du code dans une interface. C'est un petit peu laid et à contre-emploi mais pratique ici.
     */ 
    public default String monPrint() {
        return String.format("Nom " + getNom() + " Prenom " + getPrenom() + " (id="+getId()+")");
    }
}
