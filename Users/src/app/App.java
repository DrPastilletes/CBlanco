package app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    static Scanner sc = new Scanner(System.in);
    static String[][] usuaris = new String[7][20];
    static String[][] edats = new String[4][20];
    public static void main(String[] args) {     
        boolean sortir = false;
        for(int i=0;i<usuaris.length;i++){
            for(int j=0; j<usuaris[0].length;j++){
                usuaris[i][j] = " ";
            }
        }

        while (!sortir) {
            System.out.println("Quina acció vols fer?");
            System.out.println("---------------------");
            System.out.println("1. Afegir un nou usuari");
            System.out.println("2. Accedir amb un usuari ja existent");
            System.out.println("3. Buscar coincidencia");
            System.out.println("4. Sortir");
            int accio = Integer.parseInt(sc.nextLine());
            switch (accio) {
                case 1:
                System.out.println("Vols afegir un nou usuari! Endevant:");
                System.out.println("------------------------------------");
                infoUsuari(usuaris);
                break;
                case 2:
                System.out.println("Vols accedir amb un usuari! Endevant:");
                System.out.println("-------------------------------------");
                login(usuaris);
                break;
                case 3:
                System.out.println("Vols buscar una coincidencia! Endevant:");
                buscar(usuaris);
                break;
                case 4:
                System.out.println("Vagi bé! Fins la proxima.");
                sortir=true;
                break;
                default:
                System.out.println("Introdueix una operació valida!");                
            }

        }
    }
    public static String[][] infoUsuari(String[][] usuaris){
        int contador =0;
        boolean buit = false;
        while (!buit){
            for(int i=0; i<7; i++){
                if(usuaris[i][contador]!=null) {
                    contador++;
                }
            }
            buit=true;
        }
        System.out.println("Quin és el teu nom?");
        String dada = sc.nextLine();
        usuaris[0][contador] = dada;
        edats[0][contador] = dada;
        System.out.println("--------------------------------------------------");

        System.out.println("Quin és el teu cognom?");
        dada = sc.nextLine();
        usuaris[1][contador] = dada;
        System.out.println("--------------------------------------------------");
        edats[1][contador] = dada;

        System.out.println("Quina és la teva adreça?");
        dada = sc.nextLine();
        usuaris[2][contador] = dada;
        System.out.println("--------------------------------------------------");

        System.out.println("Quina és la teva població?");
        dada = sc.nextLine();
        usuaris[3][contador] = dada;
        System.out.println("--------------------------------------------------");
        edats[2][contador] = dada;

        System.out.println("Quin dia vas neixer?(dd/mm/aaaa)");
        dada = sc.nextLine();
        usuaris[4][contador] = dada;
        data(usuaris, contador);

        boolean repe = true;
        while(repe) {
            System.out.println("--------------------------------------------------");
            System.out.println("Quin nom d'usuari vols?");
            System.out.println("--------------------------------------------------");
            dada = sc.nextLine();
            repe = false;
            for(int x = 0; x < usuaris[0].length; x++){   
                if (dada.equalsIgnoreCase(usuaris[5][x])) {
                    repe = true;
                    System.out.println("--------------------------------------------------");
                    System.out.println("El nom d'usuari ja existeix!");
                    System.out.println("--------------------------------------------------");
                }
            }
            usuaris[5][contador] = dada;
        }        

        System.out.println("Quina és la teva contrasenya?");
        dada = sc.nextLine();
        while(!dada.matches("(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$)")) {
            System.out.println("La contrasenya que has introduit és incorrecte, no compleix els requisits minims!");
            dada = sc.nextLine();
        }
        usuaris[6][contador] = dada;
        System.out.println("--------------------------------------------------");

        return usuaris;
    }
    public static void login(String[][] usuaris) {
        System.out.println("Quin és el teu nom d'usuari?");
        String usuari = sc.nextLine();
        boolean trobat = false;
        int contador = 0;
        
        for (int i=0;i<20;i++) {
            if (usuaris[5][i].equals(usuari)) {
                break;
            }
            contador++;
        }
        while (!trobat){
            System.out.println("Quina és la teva contrasenya?");
            String contra = sc.nextLine();
            if (usuaris[6][contador].equals(contra)) {
                trobat=true;
            }
            else {
                System.out.println("La contrasenya introduida és incorrecte!");
            }
        }
        mostrarInfo(usuaris, contador);

    }

    public static void mostrarInfo(String[][] usuaris, int contador){
        System.out.println("Nom: "+usuaris[0][contador]);
        System.out.println("Cognom: "+usuaris[1][contador]);
        System.out.println("Adreça: "+usuaris[2][contador]);
        System.out.println("Població: "+usuaris[3][contador]);
        System.out.println("Data de naixement: "+usuaris[4][contador]);
        System.out.println("Nom d'usuari: "+usuaris[5][contador]);
        System.out.println("Contrasenya: "+usuaris[6][contador]);
    }

    public static void data(String[][] usuaris, int contador) {
        Date avui = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String[] dataAvui = format.format(avui).split("/");
        String[] dataUser = usuaris[4][contador].split("/");
        int edat = 0;
            if(Integer.parseInt(dataAvui[0]) >= Integer.parseInt(dataUser[0])) {
                edat = (Integer.parseInt(dataAvui[2])-Integer.parseInt(dataUser[2]));
                
            } else {
                edat = (Integer.parseInt(dataAvui[2])-Integer.parseInt(dataUser[2])-1);
                if(Integer.parseInt(dataAvui[1]) > Integer.parseInt(dataUser[1])) {
                edat++;
                }
            }
            edats[3][contador]=Integer.toString(edat);
            System.out.println(edat);
    }
    public static void buscar(String[][] usuaris) {
        String busqueda = sc.nextLine();
        Pattern p = Pattern.compile(busqueda);
        int contador = 0;
        for(int i=0;i<usuaris[0].length;i++){
            boolean igual = false;
            for(int j=0; j<usuaris.length;j++){
                Matcher m = p.matcher(usuaris[j][i]);
                contador= i;
                if (m.find() && !igual) {
                    System.out.println("Nom: "+usuaris[0][contador]);
                    System.out.println("Cognom: "+usuaris[1][contador]);
                    System.out.println("Adreça: "+usuaris[2][contador]);
                    System.out.println("Població: "+usuaris[3][contador]);
                    System.out.println("Data de naixement: "+usuaris[4][contador]);
                    System.out.println("Nom d'usuari: "+usuaris[5][contador]);
                    igual = true;
                }
            }
        }
    }
    public static void ordenarAsc(String[][] usuaris, String[][] edats){
        String nom = "";
        String cognom = "";
        String poblacio = "";
        String edat = "";
        for(int j = 1; j<(edats[0].length)+1;j++){
            for(int i = 1; i<(edats[0].length)+1;i++) {
                if (Integer.parseInt(edats[3][i])<=Integer.parseInt(edats[3][i+1])){
                    nom=edats[0][i+1];
                    cognom=edats[1][i+1];
                    poblacio=edats[2][i+1];
                    edat=edats[3][i+1];
                    edats[0][i+1]=edats[0][i];
                    edats[1][i+1]=edats[1][i];
                    edats[2][i+1]=edats[2][i];
                    edats[3][i+1]=edats[3][i];
                    edats[0][i]=nom;
                    edats[1][i]=cognom;
                    edats[2][i]=poblacio;
                    edats[3][i]=edat;
                }
            }
        }
        for(int i=0;i<edats[0].length;i++){
            for(int j=0; j<edats.length;j++){
                System.out.println("Nom: "+edats[0][i]);
                System.out.println("Cognom: "+edats[1][i]);
                System.out.println("Població: "+edats[2][i]);
                System.out.println("Edat: "+edats[3][i]);
            }
        }
    }
}