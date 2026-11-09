package com.example.douanetemporelle;

public class Cles {

    // matricule : e5er 4 nwamer mel carte
    public static final String MATRICULE = "9217";

    // fonction statique tebni el clé bel matricule "_" esm el champ
    public static String construire(String matricule, String nom) {
        return matricule + "_" + nom;
    }

//clés 7adhrin bech nesta3louhom
    public static String nomVoyageur(String matricule) {
        return construire(matricule, "NOM_VOYAGEUR");
    }
    public static String anneeNaissance(String matricule) {
        return construire(matricule, "ANNEE_NAISSANCE");
    }
    public static String anneeDestination(String matricule) {
        return construire(matricule, "ANNEE_DESTINATION");
    }
    public static String motif(String matricule) {
        return construire(matricule, "MOTIF");
    }
    public static String verdict(String matricule) {
        return construire(matricule, "VERDICT");
    }
}