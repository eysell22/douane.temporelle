package com.example.douanetemporelle;

class Cles {

    // num inscri; derniers 4 chiff carte etu
    public static final String MATRICULE = "9217";

    // num inscri + "_" nom
    public static String construire(String matricule, String nom) {
        return matricule + "_" + nom;
    }

//3)
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