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
import java.net.URI;
import java.util.Scanner;

public class LectureFichier {



    public static Plateau lireFichier(URI uri) {

        Plateau plateau;
        try {
            boolean first = true;
            File f = new File(uri);
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
                        String[] splitRecupInfo = splitVirgule[j].split(";");
                        switch (splitRecupInfo[0]){
                            case "T" :
                                int x = Integer.parseInt(splitRecupInfo[1]);
                                int y = Integer.parseInt(splitRecupInfo[2]);
                                if (x < splitVirgule.length && y < splitVirgule.length) {
                                    plat[j][i] = CaseFactory.creerCase(ECase.TELEPORTEUR, new Point(x, y));
                                }else {
                                    plat[j][i] = CaseFactory.creerCase(ECase.SOL);
                                }
                                break;
                        }
                    }
                }
                i++;
            }
            plateau = new Plateau(plat);
            sc.close();
            return plateau;

        } catch (FileNotFoundException e) {
            return new Plateau();
        }
    }

}
