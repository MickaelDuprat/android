package fr.greta.expensemanager;

/**
 * Created by Arsen Kubatyan on 17/07/2017.
 */

class MesClients {

    private String userFullname;

   // private String userCodePostal;
    private int userId;



    public MesClients(String userFullname,/* String userCodePostal,*/ int userId) {
        this.userFullname = userFullname;
       // this.userCodePostal = userCodePostal;
        this.userId = userId;
    }




    public String getFullName() {
        return this.userFullname;
    }


    //public String getCodePostal() {
     //   return this.userCodePostal;
    //}

    public int getClientId() {
        return userId;
    }

}
