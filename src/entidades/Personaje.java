package entidades;

import java.util.Random;

import utils.ApplicationException;



public class Personaje {
	
	public final static int PUNTOS_INICIO=200;
	public final static int MAX_DEFENSA=20;
	public final static int MAX_EVASION=80;
	public final static int PREMIO=10;
	
	private int id;
	private String nombre;
	private int vida;
	private int energia;
	private int defensa;
	private int evasion;
	private int puntosTotales;
	
	private int usoEnergia;
	private int danio;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public int getEnergia() {
		return energia;
	}
	public void setEnergia(int energia) {
		this.energia = energia;
	}
	public int getDefensa() {
		return defensa;
	}
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	public int getEvasion() {
		return evasion;
	}
	public void setEvasion(int evasion) {
		this.evasion = evasion;
	}
	public int getPuntosTotales() {
		return puntosTotales;
	}
	public void setPuntosTotales(int puntosTotales) {
		this.puntosTotales = puntosTotales;
	}
	
	public int getVidaRestante() {
		return vida-danio;
	}
	public int getEnergiaRestante() {
		return energia-usoEnergia;
	}
	
	
	public Personaje() {
		
	}
	
	
	
	public boolean isValid() throws ApplicationException {
		boolean valido=true;
		if(nombre.length()==0) {
			valido=false;
			throw new ApplicationException("Debe ingresar un nombre");
		}
		if(valido && vida<1 || energia<1 || defensa<1 || evasion<1) {
			valido=false;
			throw new ApplicationException("Los puntos asignados a los atributos deben ser mayores o iguales que 1");
		}
		if(valido && defensa>MAX_DEFENSA) {
			valido=false;
			throw new ApplicationException("Los puntos asignados a la defensa no pueden ser mayores que "+MAX_DEFENSA);
		}
		if(valido && evasion>MAX_EVASION) {
			valido=false;
			throw new ApplicationException("Los puntos asignados a la evasion no pueden ser mayores que "+MAX_EVASION);
		}
		if(vida+energia+defensa+evasion>puntosTotales) {
			valido=false;
			throw new ApplicationException("Los puntos asignados a los atributos superan la cantidad de puntos disponibles");
		}
		return valido;
	}
	
	
	
	public void realizarAtaque(int puntos) {
		usoEnergia+=puntos;
	}
	
	public void recibirAtaque(int puntos) {
		if(!evadeAtaque()) {
			danio+=puntos;
		}
	}
	
	public boolean evadeAtaque() {
		Random r=new Random();
		boolean evade=false;
		if((r.nextDouble()*100)<=evasion) {
			evade=true;
		}
		return evade;
	}
	
	public void defiende() {
		danio-=vida*defensa/250;
		usoEnergia-=energia*defensa/100;
		if(danio<0) {
			danio=0;
		}
		if(usoEnergia<0) {
			usoEnergia=0;
		}
	}
	
	public void recibirPremio() {
		puntosTotales+=PREMIO;
	}
	
	
	
	@Override
	public String toString() {
		return nombre;
	}
	
	@Override
	public boolean equals(Object p) {
		return p instanceof Personaje && ((Personaje) p).getId() == this.getId();		
	}

	
	
}
