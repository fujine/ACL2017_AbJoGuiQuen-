package vue;

import engine.GamePainter;
import model.Jeu;
import model.entites.Direction;
import model.entites.Hero;
import model.entites.Monstre;
import model.plateau.ECase;
import model.plateau.Plateau;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

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
	protected static final int WIDTH = 880;
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
	private Image[] coeur = new Image[10];
	private Image mort;
	protected static final int HEIGHT = 900;
	protected int echelle;
	protected static final int HEIGHTMENU = 33;
	protected int portee;
	protected int largeurEcran;
	private Jeu jeu;

	/**
	 * appelle constructeur parentq
	 *
	 * @param jeu
	 *            le jeutest a afficher
	 */
	public Painter(Jeu jeu) {
		this.jeu = jeu;
        largeurEcran = 11;
        portee = (largeurEcran-1)/2;
        echelle = (WIDTH) / largeurEcran;
        try {
            mur = ImageIO.read(new File(getClass().getResource("/Ressources/mur.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
			murBas = ImageIO.read(new File(getClass().getResource("/Ressources/murbas.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            sol = ImageIO.read(new File(getClass().getResource("/Ressources/sol.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            tp = ImageIO.read(new File(getClass().getResource("/Ressources/tp.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            esc = ImageIO.read(new File(getClass().getResource("/Ressources/esc.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            escBas = ImageIO.read(new File(getClass().getResource("/Ressources/escBas.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
			hero[0] = ImageIO.read(new File(getClass().getResource("/Ressources/herobas.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
			hero[1] = ImageIO.read(new File(getClass().getResource("/Ressources/herohaut.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
			hero[2] = ImageIO.read(new File(getClass().getResource("/Ressources/herogauche.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
			hero[3] = ImageIO.read(new File(getClass().getResource("/Ressources/herodroite.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            sque[0] = ImageIO.read(new File(getClass().getResource("/Ressources/squelettebas.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            sque[1] = ImageIO.read(new File(getClass().getResource("/Ressources/squelettehaut.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            sque[2] = ImageIO.read(new File(getClass().getResource("/Ressources/squelettegauche.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            sque[3] = ImageIO.read(new File(getClass().getResource("/Ressources/squelettedroite.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            attaque = ImageIO.read(new File(getClass().getResource("/Ressources/explosion.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            vie = ImageIO.read(new File(getClass().getResource("/Ressources/vie.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            tresor = ImageIO.read(new File(getClass().getResource("/Ressources/tresor.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            mort = ImageIO.read(new File(getClass().getResource("/Ressources/GameOver.png").toURI())).getScaledInstance(HEIGHT,HEIGHT+HEIGHTMENU,Image.SCALE_DEFAULT);
            for (int i = 0; i< 10; i++){
                int num = i+1;
                coeur[i] = ImageIO.read(new File(getClass().getResource("/Ressources/coeur"+ num +".png").toURI()));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
	    if(Jeu.getInstance().estMort()) {
	        crayon.drawImage(mort,0,0,null);
	        return;
        }
		drawPlateau(im);
		drawMenu(im,crayon);
		drawHero(im,crayon,jeu.getHero());
	}

	public void drawHero(BufferedImage im,  Graphics2D crayon, Hero h) {
		crayon.setColor(Color.blue);
		int posX = WIDTH/2 - echelle/2;
		int posY = (HEIGHT - HEIGHTMENU)/2 - echelle/2 + HEIGHTMENU ;
		if(h.getCoord().x - portee < 0)
			posX = posX - (portee - h.getCoord().x)*echelle;
		else if(h.getCoord().x + portee >= jeu.getPlateau().getLargeur())
			posX = posX + (h.getCoord().x +portee - jeu.getPlateau().getLargeur()+1)*echelle;
		if(h.getCoord().y - portee < 0)
			posY -= (portee - h.getCoord().y)*echelle;
		else if(h.getCoord().y + portee >= jeu.getPlateau().getHauteur())
			posY = posY + (h.getCoord().y +portee - jeu.getPlateau().getHauteur()+1)*echelle;
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
		//crayon.drawRect(posX,posY,echelle,echelle);
	}

	public void drawMenu(BufferedImage im, Graphics2D crayon) {
		crayon.drawImage(coeur[Jeu.getInstance().getHero().getVie()-1],0,0,null);
	}

	public void drawPlateau(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		Plateau p = jeu.getPlateau();
		for(int i = 0; i <largeurEcran; i++) {
			for(int j = 0; j <largeurEcran; j++) {
				int x = jeu.getHero().getCoord().x + i - portee;
				int y = jeu.getHero().getCoord().y + j - portee;
				if(jeu.getHero().getCoord().x - portee < 0) {
					x = i;
				}else if(jeu.getHero().getCoord().x + portee >= jeu.getPlateau().getLargeur()) {
					x = jeu.getPlateau().getLargeur() - largeurEcran + i;
				}
				if(jeu.getHero().getCoord().y - portee < 0) {
					y = j;
				}else if(jeu.getHero().getCoord().y + portee >= jeu.getPlateau().getHauteur()) {
					y = jeu.getPlateau().getHauteur() - largeurEcran + j;
				}

				ECase type = p.getType(new Point(x,y));
				switch (type) {
					case MUR:
						if(!p.horsPlateau(new Point(x,y+1)) && p.getType(new Point(x,y+1)) != ECase.MUR)
							crayon.drawImage(murBas,i*echelle,j*echelle+HEIGHTMENU,null);
						else
							crayon.drawImage(mur,i*echelle,j*echelle+HEIGHTMENU,null);
						break;
					case PIEGE:
                        crayon.drawImage(sol,i*echelle,j*echelle+HEIGHTMENU,null);
						crayon.setColor(Color.red);
						break;
					case TRESOR:
                        crayon.drawImage(sol,i*echelle,j*echelle+HEIGHTMENU,null);
                        crayon.drawImage(tresor,i*echelle,j*echelle+HEIGHTMENU,null);
						break;
					case TELEPORTEUR:
                        crayon.drawImage(sol,i*echelle,j*echelle+HEIGHTMENU,null);
                        crayon.drawImage(tp,i*echelle,j*echelle+HEIGHTMENU,null);
						break;
					case VIE:
                        crayon.drawImage(sol,i*echelle,j*echelle+HEIGHTMENU,null);
                        if(p.getCase(new Point(x,y)).getObjet().getInfo()>0)
                            crayon.drawImage(vie,i*echelle,j*echelle+HEIGHTMENU,null);
						break;
					case ESCALIER:
                        crayon.drawImage(sol,i*echelle,j*echelle+HEIGHTMENU,null);
                        if(Jeu.getInstance().getIndex() > Jeu.getInstance().getPlateau().getCase(new Point(x,y)).getObjet().getInfo())
                            crayon.drawImage(escBas,i*echelle,j*echelle+HEIGHTMENU,null);
                        else
                            crayon.drawImage(esc,i*echelle,j*echelle+HEIGHTMENU,null);
						break;
					default:
                        crayon.drawImage(sol,i*echelle,j*echelle+HEIGHTMENU,null);
				}

				if(jeu.getHero().getAttaque() != null && jeu.getHero().getAttaque().equals(new Point(x,y))) {
					crayon.drawImage(attaque, i * echelle, j * echelle + HEIGHTMENU, null);
					jeu.getHero().setAttaque(null);
				}
				//crayon.fillRect(i*echelle,j*echelle + HEIGHTMENU,echelle,echelle);
				for( Monstre m : jeu.getMonstres()) {
					if(m.getCoord().equals(new Point(x,y))){
                        switch (m.getDir()) {
                            case BAS:
                                crayon.drawImage(sque[0],i*echelle,j*echelle + HEIGHTMENU,null);
                                break;
                            case HAUT:
                                crayon.drawImage(sque[1],i*echelle,j*echelle + HEIGHTMENU,null);
                                break;
                            case GAUCHE:
                                crayon.drawImage(sque[2],i*echelle,j*echelle + HEIGHTMENU,null);
                                break;
                            case DROITE:
                                crayon.drawImage(sque[3],i*echelle,j*echelle + HEIGHTMENU,null);
                                break;
                        }
					}
				}
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
