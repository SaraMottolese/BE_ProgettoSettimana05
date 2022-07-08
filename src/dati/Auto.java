package dati;

public class Auto {
	private int id;
	private String targa;
	private String marca;
	private String modello;
	
	
	/****************************	COSTRUTTORE		***************************************/
	
	public Auto() {}

	public Auto(int id, String targa, String marca, String modello) {
		this.id = id;
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
	}

	public Auto(String targa, String marca, String modello) {
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
	}
	
	/****************************	GETTERS E SETTERS	***************************************/

	public int getId() {
		return id;
	}

	public String getTarga() {
		return targa;
	}

	public String getMarca() {
		return marca;
	}

	public String getModello() {
		return modello;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}
	
	@Override
	public String toString() {
		String s="\ntarga: "+targa+" marca: "+ marca+" modello: "+ modello+"\n";
		return s;
	}

}
