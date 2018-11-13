package model;

import model.factory.CaseFactory;
import model.factory.ObjetFactory;
import model.plateau.*;
import model.plateau.objet.ObjetPiege;
import model.plateau.objet.ObjetTresor;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LectureFichier {



    public static Plateau lireFichier(String txt) {

        Plateau plateau;
        try {
            boolean first = true;
            File f = new File(System.getProperty("user.dir") + File.separator + txt);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            Scanner sc = new Scanner(br);
            String line;
            String splitVirgule[];
            String[] splitTmp; // permet de faire les splits temporaires pour les teleporteurs etc
            int i=0;
            line = sc.nextLine();
            splitVirgule = line.split(",");

            ICase plat[][] = new ICase[splitVirgule.length][splitVirgule.length];

            while (sc.hasNextLine()){
                if (!first) {
                    line = sc.nextLine();
                    splitVirgule = line.split(",");
                }else{
                    first = false;
                }

                for(int j=0; j<splitVirgule.length ; j++){
                    if (splitVirgule[j].length() == 1){
                        switch (splitVirgule[j]){
                            case "M": plat[j][i] = CaseFactory.creerCase(ECase.MUR);
                                break;
                            case "O": plat[j][i] = CaseFactory.creerCase(ECase.SOL);
                                break;
                            case "P":  plat[j][i] = CaseFactory.creerCase(ECase.PIEGE);
                                break;
                            case "T": plat[j][i] = CaseFactory.creerCase(ECase.TRESOR);
                                break;
                            case "V":  plat[j][i] = CaseFactory.creerCase(ECase.VIDE);
                                break;
                        }
                    }else{

                        switch (""+splitVirgule[j].charAt(0)){
                            case "T" :
                                String x = ""+splitVirgule[j].charAt(2);
                                String y = ""+splitVirgule[j].charAt(4);
                                plat[j][i] = CaseFactory.creerCase(ECase.TELEPORTEUR, new Point(Integer.parseInt(x),Integer.parseInt(y)));
                                break;
                        }
                    }
                }
                i++;
            }
            plateau = new Plateau(plat);
            return plateau;

        } catch (FileNotFoundException e) {
            return new Plateau();
        }
    }

}
