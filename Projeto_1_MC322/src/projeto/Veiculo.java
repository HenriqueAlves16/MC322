package projeto;

public class Veiculo {
	private String placa;
	private String marca;
	private String modelo;
	
	//Construtor da classe
	public Veiculo(String placa, String marca, String modelo)	{
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
	}
	
	//Getters e setters
	public String getPlaca()	{
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	//toString
	public String toString() {
		return "Veiculo [placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + "]";
	}


}
