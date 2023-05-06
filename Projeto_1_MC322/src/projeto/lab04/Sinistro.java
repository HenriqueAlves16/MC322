package projeto.lab04;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Sinistro {
	private LocalDate data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;
	final int ID;
	private static int n = 1;	
	
	//Construtor da classe
	
	
	public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
		DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.data = LocalDate.parse(data, formatterSTR);
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
		this.ID = n;
		n++;
	}
		
	//Getters e setters
	public LocalDate getData() {
		return data;
	}

	public void setData(String data) {
		this.data = LocalDate.parse(data);
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getID() {
		return ID;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	//toString
	@Override
	public String toString() {
		StringJoiner str = new StringJoiner("\n");
		DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = (data).format(formatterSTR);
		
		str.add("Classe: Sinistro");
		str.add("Data: " + dataFormatada);
		str.add("Endereco: " + endereco);
		str.add("Seguradora: " + seguradora.getNome());
		str.add("Veiculo: " + veiculo);
		str.add("Cliente: " + cliente);
		str.add("ID: " + ID);

		return str.toString();
	}
	
}
