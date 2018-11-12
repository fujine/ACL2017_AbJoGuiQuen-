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
	 * la taille des cases
	 */
	protected static final int WIDTH = 1000;
	protected static final int HEIGHT = 1000;
	protected static final int ECHELLE = HEIGHT / Plateau.HAUTEUR;
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
		drawPlateau(im);
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		for (Monstre m : jeu.getMonstres())
			drawMonstre(im,crayon,m);
		crayon.setColor(Color.blue);
		crayon.fillOval(jeu.getHero().getCoord().x*ECHELLE,jeu.getHero().getCoord().y*ECHELLE,ECHELLE,ECHELLE);
	}

	public void drawMonstre(BufferedImage im, Graphics2D crayon, Monstre m) {
		crayon.setColor(Color.green);
		crayon.fillOval(m.getCoord().x*ECHELLE,m.getCoord().y*ECHELLE,ECHELLE,ECHELLE);
	}

	public void drawPlateau(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		Plateau p = jeu.getPlateau();
		for(int i = 0; i <Plateau.LARGEUR; i++) {
			for(int j = 0; j <Plateau.HAUTEUR; j++) {
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
					default:
						crayon.setColor(Color.white);
				}
				crayon.fillRect(i*ECHELLE,j*ECHELLE,ECHELLE,ECHELLE);
			}
		}

		crayon.setColor(Color.blue);
		for(int i = 0; i <Plateau.LARGEUR; i++) {
			crayon.drawLine(i*ECHELLE,0,i*ECHELLE,Plateau.HAUTEUR*ECHELLE);
		}
		for (int j = 0; j < Plateau.HAUTEUR; j++) {
			crayon.drawLine(0,j*ECHELLE,Plateau.LARGEUR*ECHELLE,j*ECHELLE);
		}
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
