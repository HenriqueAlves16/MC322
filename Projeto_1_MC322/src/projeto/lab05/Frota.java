package projeto.lab05;
import java.util.LinkedList;

public class Frota {
	private String code;
	private LinkedList<Veiculo> listaVeiculos;
	
	//Construtor:
	Frota(String code, Veiculo veiculo){
		this.code = code;
		listaVeiculos = new LinkedList<Veiculo>();
		this.adicionarVeiculo();
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
	
	//Método que adiciona um veículo na lista de veículos:
	public boolean adicionarVeiculo() {
		return true;
	}
	
	//Método que remove um veículo da lista de veículos:
	public boolean removeVeiculo() {
		return true;
	}
}
