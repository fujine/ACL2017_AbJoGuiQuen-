package vue;

import engine.GamePainter;
import model.Jeu;
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
	protected static final int WIDTH = 440;
	private Image mur;
	private Image sol;
	private Image tp;
	private Image esc;
	protected static final int HEIGHT = 460;
	protected int echelle;
	protected static final int HEIGHTMENU = 20;
	protected int portee;
	protected int largeurEcran;
	private Jeu jeu;

	/**
	 * appelle constructeur parent
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
            sol = ImageIO.read(new File(getClass().getResource("/Ressources/sol.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            tp = ImageIO.read(new File(getClass().getResource("/Ressources/tp.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
            esc = ImageIO.read(new File(getClass().getResource("/Ressources/esc.png").toURI())).getScaledInstance(echelle,echelle,Image.SCALE_DEFAULT);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		drawPlateau(im);

		Graphics2D crayon = (Graphics2D) im.getGraphics();
		drawMenu(im,crayon);
		drawHero(im,crayon,jeu.getHero());
	}

	public void drawHero(BufferedImage im,  Graphics2D crayon, Hero h) {
		crayon.setColor(Color.blue);
		int posX = WIDTH/2 - echelle/2;
		int posY = (HEIGHT-HEIGHTMENU)/2;
		if(h.getCoord().x - portee < 0)
			posX = posX - (portee - h.getCoord().x)*echelle;
		else if(h.getCoord().x + portee >= jeu.getPlateau().getLargeur())
			posX = posX + (h.getCoord().x +2  - largeurEcran)*echelle;
		if(h.getCoord().y - portee < 0)
			posY -= (portee - h.getCoord().y)*echelle;
		else if(h.getCoord().y + portee >= jeu.getPlateau().getHauteur())
			posY = posY + (h.getCoord().y +2  - largeurEcran)*echelle;
		crayon.fillOval(posX,posY,echelle,echelle);
	}

	public void drawMonstre(BufferedImage im, Graphics2D crayon, Monstre m) {
		crayon.setColor(Color.green);
		int posX = WIDTH/2 - echelle/2;
		int posY = (HEIGHT-HEIGHTMENU)/2;
		if(m.getCoord().x - portee < 0)
			posX = posX - (portee - m.getCoord().x)*echelle;
		else if(m.getCoord().x + portee >= jeu.getPlateau().getLargeur())
			posX = posX + (m.getCoord().x +2  - largeurEcran)*echelle;
		if(m.getCoord().y - portee < 0)
			posY -= (portee - m.getCoord().y)*echelle;
		else if(m.getCoord().y + portee >= jeu.getPlateau().getHauteur())
			posY = posY + (m.getCoord().y +2  - largeurEcran)*echelle;
		crayon.fillOval(posX,posY,echelle,echelle);
	}

	public void drawMenu(BufferedImage im, Graphics2D crayon) {
		crayon.setColor(Color.black);
		crayon.drawString("Vie : " + Jeu.getInstance().getHero().getVie(),10,10);
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
						crayon.drawImage(mur,i*echelle,j*echelle+HEIGHTMENU,null);
						break;
					case PIEGE:
                        crayon.drawImage(sol,i*echelle,j*echelle+HEIGHTMENU,null);
						crayon.setColor(Color.red);
						break;
					case TRESOR:
						crayon.setColor(Color.yellow);
						break;
					case TELEPORTEUR:
                        crayon.drawImage(sol,i*echelle,j*echelle+HEIGHTMENU,null);
                        crayon.drawImage(tp,i*echelle,j*echelle+HEIGHTMENU,null);
						break;
					case VIE:
						crayon.setColor(Color.CYAN);
						break;
					case ESCALIER:
                        crayon.drawImage(sol,i*echelle,j*echelle+HEIGHTMENU,null);
                        crayon.drawImage(esc,i*echelle,j*echelle+HEIGHTMENU,null);
						break;
					default:
                        crayon.drawImage(sol,i*echelle,j*echelle+HEIGHTMENU,null);
				}

				//crayon.fillRect(i*echelle,j*echelle + HEIGHTMENU,echelle,echelle);
				for( Monstre m : jeu.getMonstres()) {
					if(m.getCoord().equals(new Point(x,y))){
						crayon.setColor(Color.green);
						crayon.fillOval(i*echelle,j*echelle + echelle/2,echelle,echelle);
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
