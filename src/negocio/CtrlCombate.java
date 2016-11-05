package negocio;

import java.io.Serializable;

import data.DataPersonaje;
import entidades.Personaje;
import utils.ApplicationException;

public class CtrlCombate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private DataPersonaje dataPersonaje;
	
	
	private Personaje jugador1;
	private Personaje jugador2;
	
	private Personaje jugadorAtaca;
	private Personaje jugadorRecibe;
	
	private boolean finCombate;
	
	public DataPersonaje getDataPersonaje() {
		return dataPersonaje;
	}
	public void setDataPersonaje(DataPersonaje dataPersonaje) {
		this.dataPersonaje = dataPersonaje;
	}
	public Personaje getJugadorAtaca() {
		return jugadorAtaca;
	}
	public void setJugadorAtaca(Personaje jugadorAtaca) {
		this.jugadorAtaca = jugadorAtaca;
	}
	public Personaje getJugadorRecibe() {
		return jugadorRecibe;
	}
	public void setJugadorRecibe(Personaje jugadorRecibe) {
		this.jugadorRecibe = jugadorRecibe;
	}
	public void setJugador1(Personaje jugador1) {
		this.jugador1 = jugador1;
	}
	public void setJugador2(Personaje jugador2) {
		this.jugador2 = jugador2;
	}
	public void setFinCombate(boolean finCombate) {
		this.finCombate = finCombate;
	}
	public Personaje getJugador1() {
		return jugador1;
	}
	public Personaje getJugador2() {
		return jugador2;
	}
	public boolean getFinCombate() {
		return finCombate;
	}
	
	
	
	public CtrlCombate() {
		dataPersonaje=new DataPersonaje();
	}
	
	
	
	public void nuevoCombate(Personaje j1, Personaje j2) throws ApplicationException {
		jugador1=dataPersonaje.getByName(j1);
		jugador2=dataPersonaje.getByName(j2);
		if(jugador1==null || jugador2==null) {
			throw new ApplicationException("Jugador no existe");
		}
		if(jugador1.equals(jugador2)) {
			throw new ApplicationException("Los jugadores seleccionados deben ser distintos");
		}
		jugadorAtaca=jugador1;
		jugadorRecibe=jugador2;
		finCombate=false;
	}
	
	private void cambiarTurno() {
		Personaje aux=jugadorAtaca;
		jugadorAtaca=jugadorRecibe;
		jugadorRecibe=aux;
	}
	
	public Personaje getTurno() {
		return jugadorAtaca;
	}
	
	public void atacar(int puntos) throws ApplicationException {
		if(finCombate) {
			throw new ApplicationException("El combate ha finalizado");
		}
		if(puntos>jugadorAtaca.getEnergiaRestante()) {
			throw new ApplicationException("Los puntos para el ataque superan la cantidad de puntos disponibles");
		}
		jugadorAtaca.realizarAtaque(puntos);
		jugadorRecibe.recibirAtaque(puntos);
		if(jugadorRecibe.getVidaRestante()<=0) {
			finCombate=true;
			jugadorAtaca.recibirPremio();
			dataPersonaje.update(jugadorAtaca);
		}
		if(!finCombate) {
			cambiarTurno();
		}
	}
	
	public void defender() throws ApplicationException {
		if(finCombate) {
			throw new ApplicationException("El combate ha finalizado");
		}
		jugadorAtaca.defiende();
		cambiarTurno();
	}

}
