package fr.greta.expensemanager;

/**
 * Created by Arsen Kubatyan on 24/07/2017.
 */

public class MesNotesDeFrais {

    private String AssocierNDF;

    // private String userCodePostal;
    private int notedefraisId;



    public MesNotesDeFrais(String AssocierNDF,/* String userCodePostal,*/ int notedefraisId) {
        this.AssocierNDF = AssocierNDF;
        // this.AssocierNDF = userCodePostal;
        this.notedefraisId = notedefraisId;
    }




    public String getAssocierNDF() {
        return this.AssocierNDF;
    }


    //public String getCodePostal() {
    //   return this.userCodePostal;
    //}

    public int getNoteDeFraisId() {
        return notedefraisId;
    }

}
