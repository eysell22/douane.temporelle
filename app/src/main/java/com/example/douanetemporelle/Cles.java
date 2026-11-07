package com.example.douanetemporelle;

class Cles {

    //1- رقم التسجيل: آخر 4 أرقام من بطاقة الطالب
    public static final String MATRICULE = "9217";

    // 2-دالة ثابتة تبني المفتاح: رقم التسجيل + "_" + الاسم
    public static String construire(String matricule, String nom) {
        return matricule + "_" + nom;
    }

//3-مفاتيح جاهزة للاستعمال
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