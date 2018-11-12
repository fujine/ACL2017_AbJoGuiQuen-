package vue;

import engine.GamePainter;
import model.Jeu;
import model.entites.Monstre;
import model.plateau.ECase;
import model.plateau.Plateau;

import java.awt.*;
import java.awt.image.BufferedImage;

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
	protected static final int WIDTH = 800;
	protected static final int HEIGHT = 820;
	protected int echelle;
	protected static final int HEIGHTMENU = 20;
	private Jeu jeu;

	/**
	 * appelle constructeur parent
	 *
	 * @param jeu
	 *            le jeutest a afficher
	 */
	public Painter(Jeu jeu) {
		this.jeu = jeu;
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		echelle = (HEIGHT-20) / jeu.getPlateau().getHauteur();
		drawPlateau(im);

		Graphics2D crayon = (Graphics2D) im.getGraphics();
		drawMenu(im,crayon);
		for (Monstre m : jeu.getMonstres())
			drawMonstre(im,crayon,m);
		crayon.setColor(Color.blue);
		crayon.fillOval(jeu.getHero().getCoord().x*echelle,jeu.getHero().getCoord().y*echelle + HEIGHTMENU,echelle,echelle);
	}

	public void drawMonstre(BufferedImage im, Graphics2D crayon, Monstre m) {
		crayon.setColor(Color.green);
		crayon.fillOval(m.getCoord().x*echelle,m.getCoord().y*echelle + HEIGHTMENU,echelle,echelle);
	}

	public void drawMenu(BufferedImage im, Graphics2D crayon) {
		crayon.setColor(Color.black);
		crayon.drawString("Vie : " + Jeu.getInstance().getHero().getVie(),10,10);
	}

	public void drawPlateau(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		Plateau p = jeu.getPlateau();
		for(int i = 0; i <jeu.getPlateau().getLargeur(); i++) {
			for(int j = 0; j <jeu.getPlateau().getHauteur(); j++) {
				ECase type = p.getType(new Point(i,j));
				switch (type) {
					case MUR:
						crayon.setColor(Color.black);
						break;
					case PIEGE:
						crayon.setColor(Color.red);
						break;
					case TRESOR:
						crayon.setColor(Color.yellow);
						break;
					case TELEPORTEUR:
						crayon.setColor(Color.pink);
						break;
					case VIE:
						crayon.setColor(Color.CYAN);
						break;
					default:
						crayon.setColor(Color.white);
				}
				crayon.fillRect(i*echelle,j*echelle + HEIGHTMENU,echelle,echelle);
			}
		}

		/*
		crayon.setColor(Color.blue);
		for(int i = 0; i <Plateau.LARGEUR; i++) {
			crayon.drawLine(i*ECHELLE,0 + HEIGHTMENU,i*ECHELLE,Plateau.HAUTEUR*ECHELLE + HEIGHTMENU);
		}
		for (int j = 0; j < Plateau.HAUTEUR; j++) {
			crayon.drawLine(0,j*ECHELLE + HEIGHTMENU,Plateau.LARGEUR*ECHELLE,j*ECHELLE + HEIGHTMENU);
		}
		*/
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
