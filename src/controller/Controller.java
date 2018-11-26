package controller;

import engine.Cmd;
import engine.GameController;
import model.Jeu;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 *
 */
public class Controller implements GameController {

	/**
	 * commande en cours
	 */
	private ArrayList<Cmd> listAction;
	private boolean attaque = false;

	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public Controller() {
		this.listAction = new ArrayList<Cmd>();
		this.listAction.add(Cmd.IDLE);
	}

	public ArrayList<Cmd> getListAction() {
		return listAction;
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
			// si on appuie sur 'q',commande joueur est gauche
			case 'q':
			case 'Q':
				if (!this.listAction.contains(Cmd.LEFT)){
					this.listAction.add(Cmd.LEFT);
				}
				if (this.listAction.contains(Cmd.IDLE)){
					this.listAction.remove(Cmd.IDLE);
				}
				break;
			case 'z':
			case 'Z':
				if (!this.listAction.contains(Cmd.UP)){
					this.listAction.add(Cmd.UP);
				}
				if (this.listAction.contains(Cmd.IDLE)){
					this.listAction.remove(Cmd.IDLE);
				}
				break;
			case 's':
			case 'S':
				if (!this.listAction.contains(Cmd.DOWN)){
					this.listAction.add(Cmd.DOWN);
				}
				if (this.listAction.contains(Cmd.IDLE)){
					this.listAction.remove(Cmd.IDLE);
				}
				break;
			case 'd':
			case 'D':
				if (!this.listAction.contains(Cmd.RIGHT)){
					this.listAction.add(Cmd.RIGHT);
				}
				if (this.listAction.contains(Cmd.IDLE)){
					this.listAction.remove(Cmd.IDLE);
				}
				break;
			case 'f':
			case 'F':
				if (!this.listAction.contains(Cmd.END)){
					this.listAction.add(Cmd.END);
				}
				if (this.listAction.contains(Cmd.IDLE)){
					this.listAction.remove(Cmd.IDLE);
				}
				break;
			case ' ':
				if (Jeu.getInstance().getHero().okAttaque()) {
					this.attaque = true;
					Jeu.getInstance().getHero().attaquer();
				}
				break;
		}
	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyChar()) {
			// si on appuie sur 'q',commande joueur est gauche
			case 'q':
			case 'Q':
				this.listAction.remove(Cmd.LEFT);
				if (this.listAction.size() == 0){
					this.listAction.add(Cmd.IDLE);
				}
				break;
			case 'z':
			case 'Z':
				this.listAction.remove(Cmd.UP);
				if (this.listAction.size() == 0){
					this.listAction.add(Cmd.IDLE);
				}
				break;
			case 's':
			case 'S':
				this.listAction.remove(Cmd.DOWN);
				if (this.listAction.size() == 0){
					this.listAction.add(Cmd.IDLE);
				}
				break;
			case 'd':
			case 'D':
				this.listAction.remove(Cmd.RIGHT);
				if (this.listAction.size() == 0){
					this.listAction.add(Cmd.IDLE);
				}
				break;
			case 'f':
			case 'F':
				this.listAction.remove(Cmd.END);
				if (this.listAction.size() == 0){
					this.listAction.add(Cmd.IDLE);
				}
				break;
			case ' ':
				this.attaque = false;
				break;
		}
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public Cmd getCommand() {
		if (!this.attaque)
			return listAction.get(0);
		else
			return Cmd.ATTAQUE;

	}
}
