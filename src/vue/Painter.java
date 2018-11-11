package vue;

import engine.GamePainter;
import model.Jeu;
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
	protected static final int ECHELLE = 100;
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
		crayon.setColor(Color.blue);
		crayon.fillOval(jeu.getHero().getPosX()*ECHELLE,jeu.getHero().getPosY()*ECHELLE,ECHELLE,ECHELLE);
	}

	public void drawPlateau(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		Plateau p = jeu.getPlateau();
		for(int i = 0; i <10; i++) {
			for(int j = 0; j <10; j++) {
				ECase type = p.getType(i,j);
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
		for(int i = 0; i <10; i++) {
			crayon.drawLine(i*ECHELLE,0,i*ECHELLE,10*ECHELLE);
		}
		for (int j = 0; j < 10; j++) {
			crayon.drawLine(0,j*ECHELLE,10*ECHELLE,j*ECHELLE);
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
