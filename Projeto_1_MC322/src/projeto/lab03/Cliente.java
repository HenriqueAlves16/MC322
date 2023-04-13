package projeto.lab03;
import java.time.LocalDate;
import java.util.StringJoiner;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Cliente {
	
	private String nome; 
	private String endereco;
	private LocalDate dataLicenca;
	private String tipo;
	private LinkedList<Veiculo> listaVeiculos = new LinkedList<>();
	
	//Construtor da classe
	
	public Cliente (String nome, String endereco, String dataLicenca, String tipo) {
		DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.nome = nome;
		this.endereco = endereco;
		this.dataLicenca = LocalDate.parse(dataLicenca, formatterSTR);
		this.tipo = tipo;
	}
	
	//Getters e setters
	public String getNome()	{
		return nome;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public LocalDate getDataLicenca() {
		return dataLicenca;
	}

	public void setDataLicenca(LocalDate dataLicenca) {
		this.dataLicenca = dataLicenca;
	}

	public LinkedList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(LinkedList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	//toString:
	@Override
	public String toString() {
		StringJoiner str = new StringJoiner("\n");
		DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataLicencaFormatada = (getDataLicenca()).format(formatterSTR);
		str.add("Classe = Cliente");
		str.add("nome = " + nome);
		str.add("endereco = " + endereco);
		str.add("dataLicenca = " + dataLicencaFormatada);
		str.add("tipo = " + tipo);
		str.add("listaVeiculos :");
		for(Veiculo veiculo : listaVeiculos) {
			str.add(" ".repeat(4) + veiculo.toString());
		}
		return str.toString();
	}
	

	
	//Metodos:
	public void adicionaVeiculo(Veiculo veiculo) {
		listaVeiculos.add(veiculo);
	}
	
	public void visualizarVeiculos(Cliente cliente) {
		for(Veiculo veiculo: cliente.listaVeiculos) {
			System.out.println(veiculo.getModelo());
		}
	}
	
	public LinkedList<Veiculo> listaVeiculos(Cliente cliente) {
		return cliente.listaVeiculos;
	}
	
	public boolean validarDocumento() {
		if(this instanceof ClientePF) {
			return ((ClientePF) this).validarCPF();
		}	else 	{
			return ((ClientePJ) this).validarCNPJ();
		}
	}

	



	

	
}
