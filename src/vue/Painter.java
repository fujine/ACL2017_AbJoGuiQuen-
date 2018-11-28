package vue;

import engine.GamePainter;
import model.Jeu;
import model.entites.*;
import model.plateau.ECase;
import model.plateau.Plateau;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import static model.entites.Direction.*;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 *
 */
public class Painter implements GamePainter {

	/**
	 * La taille des cases
	 */
	private Image mur;
	private Image murBas;
	private Image sol;
	private Image tp;
	private Image esc;
    private Image escBas;
	private Image attaque;
	private Image vie;
	private Image tresor;
	private Image[] hero = new Image[4];
	private Image[] sque = new Image[4];
	private Image[] fantome = new Image[4];
	private Image[] coeur = new Image[10];
	private Image mort;
	private int compteur = 0;
	private int posXatt;
	private int posYatt;
    public static final int WIDTH = 880;
	public static final int HEIGHT = 913;
	protected int echelle;
	protected int taille;
	protected static final int HEIGHTMENU = 33;
	protected int portee;
	protected int largeurEcran;
	private Jeu jeu;
	private int[] limiteEcran = new int[2];
	private Camera cam;
	private HashMap<Point,Image> foreground;

	/**
	 * appelle constructeur parentq
	 *
	 * @param jeu
	 *            le jeutest a afficher
	 */
	public Painter(Jeu jeu) {
		this.jeu = jeu;
		this.cam = new Camera();
        largeurEcran = 11;
        portee = (largeurEcran-1)/2;
        echelle = (WIDTH) / largeurEcran;
        taille = Jeu.TAILLE;
        try {
			mur = ImageIO.read(new File("/Ressources/mur.png")).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
			murBas = ImageIO.read(new File("/Ressources/murbas.png")).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            sol = ImageIO.read(new File("/Ressources/sol.png")).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            tp = ImageIO.read(new File("/Ressources/tp.png")).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            esc = ImageIO.read(new File("/Ressources/esc.png")).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            escBas = ImageIO.read(new File("/Ressources/escBas.png")).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
			hero[0] = ImageIO.read(new File("/Ressources/herobas.png")).getScaledInstance(taille,taille,Image.SCALE_DEFAULT);;
			hero[1] = ImageIO.read(new File("/Ressources/herohaut.png")).getScaledInstance(taille,taille,Image.SCALE_DEFAULT);
			hero[2] = ImageIO.read(new File("/Ressources/herogauche.png")).getScaledInstance(taille,taille,Image.SCALE_DEFAULT);
			hero[3] = ImageIO.read(new File("/Ressources/herodroite.png")).getScaledInstance(taille,taille,Image.SCALE_DEFAULT);
            sque[0] = ImageIO.read(new File("/Ressources/squelettebas.png")).getScaledInstance(taille,taille,Image.SCALE_DEFAULT);
            sque[1] = ImageIO.read(new File("/Ressources/squelettehaut.png")).getScaledInstance(taille,taille,Image.SCALE_DEFAULT);
            sque[2] = ImageIO.read(new File("/Ressources/squelettegauche.png")).getScaledInstance(taille,taille,Image.SCALE_DEFAULT);
            sque[3] = ImageIO.read(new File("/Ressources/squelettedroite.png")).getScaledInstance(taille,taille,Image.SCALE_DEFAULT);
			fantome[0] = ImageIO.read(new File("/Ressources/fantomebas.png")).getScaledInstance(taille,taille,Image.SCALE_DEFAULT);
			fantome[1] = ImageIO.read(new File("/Ressources/fantomehaut.png")).getScaledInstance(taille,taille,Image.SCALE_DEFAULT);
			fantome[2] = ImageIO.read(new File("/Ressources/fantomegauche.png")).getScaledInstance(taille,taille,Image.SCALE_DEFAULT);
			fantome[3] = ImageIO.read(new File("/Ressources/fantomedroit.png")).getScaledInstance(taille,taille,Image.SCALE_DEFAULT);
            attaque = ImageIO.read(new File("/Ressources/explosion.png")).getScaledInstance(taille,taille,Image.SCALE_DEFAULT);
            vie = ImageIO.read(new File("/Ressources/vie.png")).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            tresor = ImageIO.read(new File("/Ressources/tresor.png")).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            mort = ImageIO.read(new File("/Ressources/GameOver.png")).getScaledInstance(HEIGHT,HEIGHT+HEIGHTMENU,Image.SCALE_DEFAULT);
            for (int i = 0; i< 10; i++){
                int num = i+1;
                coeur[i] = ImageIO.read(new File("/Ressources/coeur"+ num +".png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
	    cam.setLimites(Jeu.getInstance().getHero());

        Graphics2D crayon = (Graphics2D) im.getGraphics();

	    if(Jeu.getInstance().estMort()) {
	        crayon.drawImage(mort,0,0,null);
	        return;
        }
		drawPlateau(im);

		drawMonstre(im,crayon);
		drawHero(im,crayon,jeu.getHero());
        drawMenu(im,crayon);
	}

	public void drawHero(BufferedImage im,  Graphics2D crayon, Hero h) {
		crayon.setColor(Color.blue);
		int posX = (largeurEcran/2) * echelle ;
		int posY = (HEIGHT - HEIGHTMENU)/2 - echelle/2 + HEIGHTMENU ;
		if(h.getCoord().x - portee * Jeu.ECHELLE < 0) {
            posX = h.getCoord().x ;
        }else if(jeu.getHero().getCoord().x + (portee+1) *echelle > jeu.getPlateau().getLargeur())
			posX = largeurEcran*echelle - jeu.getPlateau().getLargeur() + h.getCoord().x ;
		if(h.getCoord().y - portee * Jeu.ECHELLE < 0)
			posY = h.getCoord().y + HEIGHTMENU;
		else if(jeu.getHero().getCoord().y + (portee+1) * echelle > jeu.getPlateau().getHauteur())
			posY = largeurEcran*echelle - jeu.getPlateau().getLargeur() + h.getCoord().y +HEIGHTMENU;
		switch (h.getDir()) {
			case BAS:
                crayon.drawImage(hero[0],posX,posY,null);
                break;
            case HAUT:
                crayon.drawImage(hero[1],posX,posY,null);
                break;
            case GAUCHE:
                crayon.drawImage(hero[2],posX,posY,null);
                break;
            case DROITE:
                crayon.drawImage(hero[3],posX,posY,null);
                break;
		}
		//crayon.drawRect(h.getCoord().x,h.getCoord().y+HEIGHTMENU,taille,taille);
		//crayon.drawRect(h.getSurfaceCollision().x,h.getSurfaceCollision().y + HEIGHTMENU, h.getSurfaceCollision().width,h.getSurfaceCollision().height);

        if(jeu.getHero().getAttaque()) {
            switch (h.getDir()) {
                case GAUCHE:
                    posX -= Jeu.TAILLE;
                    break;
                case DROITE:
                    posX+= Jeu.TAILLE;
                    break;
                case HAUT:
                    posY-= Jeu.TAILLE;
                    break;
                case BAS:
                    posY+= Jeu.TAILLE;
                    break;
            }
            crayon.drawImage(attaque, posX, posY, null);
            posXatt = posX;
            posYatt = posY;
            jeu.getHero().setAttaque(false);
            compteur++;
        } else if (compteur != 0){
        	if (compteur > 5)
        		compteur = 0;
        	else {
        		crayon.drawImage(attaque, posXatt,posYatt,null);
        		compteur++;
			}
		}

	}

	public void drawMenu(BufferedImage im, Graphics2D crayon) {
        crayon.setColor(Color.black);
        crayon.fillRect(0,0,880,33);
        crayon.drawImage(coeur[Jeu.getInstance().getHero().getVie()-1],0,0,null);

	}

	public void drawMonstre(BufferedImage im, Graphics2D crayon) {
	    Jeu mod = Jeu.getInstance();
	    int posX;
	    int posY;
        for (Monstre m : mod.getMonstres()) {
        	Image[] tab = null;
        	if(m instanceof Chevalier)
        		tab = sque;
        	else if (m instanceof Fantome)
        		tab = fantome;
            if (cam.dansCadre(m.getCoord())) {
                posX = m.getCoord().x - cam.getLimXInf();
                posY = m.getCoord().y - cam.getLimYInf();

                switch (m.getDir()) {
                    case BAS:
                        crayon.drawImage(tab[0],posX,posY + HEIGHTMENU,null);
                        break;
                    case HAUT:
                        crayon.drawImage(tab[1],posX,posY + HEIGHTMENU,null);
                        break;
                    case GAUCHE:
                        crayon.drawImage(tab[2],posX,posY + HEIGHTMENU,null);
                        break;
                    case DROITE:
                        crayon.drawImage(tab[3],posX,posY + HEIGHTMENU,null);
                        break;
                }
                //crayon.drawRect(posX,posY+HEIGHTMENU,taille,taille);
            }
        }
    }

	public void drawPlateau(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		int decalageX = 0;
		int decalageY = 0;
		Plateau p = jeu.getPlateau();
		for(int i = 0; i <=largeurEcran; i++) {
			for(int j = 0; j <=largeurEcran; j++) {
				int x = jeu.getHero().getCoord().x + (i - portee) * Jeu.ECHELLE;
				int y = jeu.getHero().getCoord().y + (j - portee) * Jeu.ECHELLE;
				if(jeu.getHero().getCoord().x - portee* Jeu.ECHELLE < 0) {
					x = i * Jeu.ECHELLE;
				}else if(jeu.getHero().getCoord().x + (portee+1) *echelle > jeu.getPlateau().getLargeur()) {
					x = jeu.getPlateau().getLargeur() - echelle- (largeurEcran -1- i)*Jeu.ECHELLE;
				}
				else
                    decalageX = jeu.getHero().getCoord().x % Jeu.ECHELLE;
				if(jeu.getHero().getCoord().y - portee*Jeu.ECHELLE < 0) {
					y = j * Jeu.ECHELLE;
				}else if(jeu.getHero().getCoord().y + (portee+1) * echelle > jeu.getPlateau().getHauteur()) {
					y = jeu.getPlateau().getHauteur()-echelle  - (largeurEcran - j -1)*Jeu.ECHELLE;
				} else
                    decalageY = jeu.getHero().getCoord().y % Jeu.ECHELLE;
				
				ECase type = p.getType(new Point(x,y));
				int posX = i*echelle -decalageX;
				int posY = j*echelle+HEIGHTMENU-decalageY;
				switch (type) {
					case MUR:
						if(!p.horsPlateau(new Point(x,y+Jeu.ECHELLE)) && p.getType(new Point(x,y+Jeu.ECHELLE)) != ECase.MUR)
							crayon.drawImage(murBas,posX,posY,null);
						else
							crayon.drawImage(mur,posX,posY,null);
						break;
					case PIEGE:
                        crayon.drawImage(sol,posX,posY,null);
						crayon.setColor(Color.red);
						break;
					case TRESOR:
                        crayon.drawImage(sol,posX,posY,null);
                        crayon.drawImage(tresor,posX,posY,null);
						break;
					case TELEPORTEUR:
                        crayon.drawImage(sol,posX,posY,null);
                        crayon.drawImage(tp,posX,posY,null);
						break;
					case VIE:
                        crayon.drawImage(sol,posX,posY,null);
                        if(p.getCase(new Point(x,y)).getObjet().getInfo()>0)
                            crayon.drawImage(vie,posX,posY,null);
						break;
					case ESCALIER:
                        crayon.drawImage(sol,posX,posY,null);
                        if(Jeu.getInstance().getIndex() > Jeu.getInstance().getPlateau().getCase(new Point(x,y)).getObjet().getInfo())
                            crayon.drawImage(escBas,posX,posY,null);
                        else
                            crayon.drawImage(esc,posX,posY,null);
						break;
					default:
                        crayon.drawImage(sol,posX,posY,null);
				}

				//crayon.fillRect(i*echelle,j*echelle + HEIGHTMENU,echelle,echelle);
                /*
				for( Monstre m : jeu.getMonstres()) {
					if(m.getCoord().equals(new Point(x,y))){
					    posX = i*echelle + x%echelle;
					    posY = j*echelle + x%echelle;
                        switch (m.getDir()) {
                            case BAS:
                                crayon.drawImage(sque[0],posX,posY + HEIGHTMENU,null);
                                break;
                            case HAUT:
                                crayon.drawImage(sque[1],posX,posY + HEIGHTMENU,null);
                                break;
                            case GAUCHE:
                                crayon.drawImage(sque[2],posX,posY + HEIGHTMENU,null);
                                break;
                            case DROITE:
                                crayon.drawImage(sque[3],posX,posY + HEIGHTMENU,null);
                                break;
                        }
					}
				}*/
			}
		}


		/*crayon.setColor(Color.blue);
		for(int i = 0; i <jeu.getPlateau().getLargeur(); i++) {
			crayon.drawLine(i*echelle,0 + HEIGHTMENU,i*echelle,jeu.getPlateau().getHauteur()*echelle + HEIGHTMENU);
		}
		for (int j = 0; j < jeu.getPlateau().getHauteur(); j++) {
			crayon.drawLine(0, j * echelle + HEIGHTMENU, jeu.getPlateau().getLargeur()* echelle, j * echelle + HEIGHTMENU);
		}*/

	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
