package projeto.lab05;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Frota {
	private String code;
	private LinkedList<Veiculo> listaVeiculos;
	
	//Construtor:
	Frota(String code, Veiculo veiculo){
		this.code = code;
		listaVeiculos = new LinkedList<Veiculo>();
		this.adicionarVeiculo(veiculo);
	}

	//gets e sets:
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LinkedList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(LinkedList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	//toString():
	@Override
	public String toString() {
		return "Frota [code=" + code + ", listaVeiculos=" + listaVeiculos + "]";
	}

	
	//Métodos:
	
	// Método que cadastra um veículo na lista de veículos da Frota:
	public boolean adicionarVeiculo(Veiculo veiculo) {
		if(listaVeiculos.contains(veiculo)) {
			System.out.println("O veiculo já está cadastrado na frota.");
			return false;
		}
		
		listaVeiculos.add(veiculo);
		setListaVeiculos(listaVeiculos);
		System.out.println("Veiculo cadastrado na frota com sucesso!");

		return true;
	}
	
	//Método que remove um veículo cadastrado nesta Frota a partir de sua placa. Se a placa for válida, retorna true. Caso contrário, retorna false.
	public boolean excluirVeiculo() {
		Scanner scanner = new Scanner(System.in);
		String placaExcluir;
		
		System.out.println("Digite a placa do veículo que você deseja excluir:");
		placaExcluir = scanner.nextLine();
		
		// Encontrando e excluindo o veículo a partir de sua placa:
		for(int j = 0; j < listaVeiculos.size(); j++) {
			String placa = (listaVeiculos.get(j)).getPlaca();
			if(placa.equals(placaExcluir)) {
				listaVeiculos.remove(j);
				setListaVeiculos(listaVeiculos);
				System.out.println("Veículo excluído com sucesso!");
				return true;
			}
		}
		
		System.out.println("O veículo cuja placa é" + placaExcluir + "não está cadastrado na frota" + this.getCode());
		return false;
	}

}
