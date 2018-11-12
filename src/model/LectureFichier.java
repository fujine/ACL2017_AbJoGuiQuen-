package model;

import model.plateau.*;
import model.plateau.objet.ObjetPiege;
import model.plateau.objet.ObjetTresor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LectureFichier {



    public static ICase[][] lireFichier(String txt) {


        try {
            boolean first = true;
            File f = new File(txt);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            Scanner sc = new Scanner(br);
            String line;
            String split[];
            String tmp[]; // permet de faire les splits temporaires pour les teleporteurs etc
            int i=0;
            line = sc.nextLine();
            split = line.split(",");
            ICase plateau[][] = new ICase[split.length][split.length];
            while (sc.hasNextLine()){
                if (!first) {
                    line = sc.nextLine();
                    split = line.split(",");
                }
                for(int j=0; j<split.length ; j++){
                    if (split[j].length() == 1){
                        switch (split[j]){
                            case "M": plateau[i][j] = new CaseMur();
                                break;
                            case "O": plateau[i][j] = new Case();
                                break;
                            case "P": plateau[i][j] = new Case(new ObjetPiege());
                                break;
                            case "T": plateau[i][j] = new Case(new ObjetTresor());
                                break;
                            case "V": plateau[i][j] = new CaseVide();
                                break;
                        }
                    }else{

                    }
                }
                i++;
            }

            return plateau;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
