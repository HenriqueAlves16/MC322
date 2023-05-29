package projeto.lab05;
import java.util.Scanner;

public class Veiculo {
	private String placa;
	private String marca;
	private String modelo;
	private int anoFabricacao;
	
	//Construtor da classe
	public Veiculo(String placa, String marca, String modelo, int anoFabricacao)	{
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
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
	
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	//toString
	public String toString() {
		return this.marca + " " + this.modelo + " " + this.anoFabricacao + " - " + this.placa;
	}

	//Método que cria veículo com input:
		public static Veiculo criaVeiculo() {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Digite a placa, marca, modelo e ano do veículo, respectivamente: ");
			
			String placa = scanner.nextLine();
			String marca = scanner.nextLine();
			String modelo = scanner.nextLine();
			int ano = scanner.nextInt();
			scanner.nextLine();
			Veiculo veiculo = new Veiculo(placa, marca, modelo, ano);
			return veiculo;
		}
}
