package projeto.lab04;

import java.time.LocalDate;
import java.util.LinkedList;

public class ClienteData {
	public String nome;
	public String endereco;
	public LocalDate dataLicenca;
	public String tipo;
	public double valorSeguro;
	public LinkedList<Veiculo> listaVeiculos;

	public ClienteData(LinkedList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
}