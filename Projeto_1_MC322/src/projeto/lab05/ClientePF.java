package projeto.lab05;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.StringJoiner;
import java.util.ArrayList;

public class ClientePF extends Cliente {
	private final String CPF;
	private String genero;
	private String educacao;
	private LocalDate dataNascimento;
	private ArrayList<Veiculo> listaVeiculos;
	
	//Metodo construtor:
	public ClientePF(String nome, String endereco, String telefone, String email, String CPF, String genero, String educacao, String dataNascimento) {
		// chama o construtor da superclasse
		super(nome, endereco, telefone, email);
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.CPF = CPF;
		this.genero = genero;
		this.educacao = educacao;
		this.dataNascimento = LocalDate.parse(dataNascimento, formatador);
		this.listaVeiculos = new ArrayList<Veiculo>();
	}

	//Getters e setters:
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	public String getCPF() {
		return CPF;
	}
	
	public int getIdade() {
		int idade;
		LocalDate nasc = getDataNascimento();
		LocalDate now = LocalDate.now();
		idade = (int) nasc.until(now, ChronoUnit.YEARS);
		
		return idade;
	}
	
	//toString:
	/*@Override
	public String toString() {
		StringJoiner str = new StringJoiner("\n");
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String dataNascimentoFormatada = (dataNascimento).format(formatador);
		str.add("Classe = ClientePF");
		str.add("nome = " + getNome());
		str.add("endereco = " + getEndereco());
		str.add("dataLicenca = " + dataLicencaFormatada);
		str.add("tipo = " + getTipo());
		str.add("educacao = " + educacao);
		str.add("genero = " + genero);
		str.add("classeEconomica = " + classeEconomica);
		str.add("CPF = " + CPF);
		str.add("dataNascimento = " + dataNascimentoFormatada);
		str.add("listaVeiculos :");
		for(Veiculo veiculo : getListaVeiculos()) {
			str.add(" ".repeat(4) + veiculo.toString());
		}
		return str.toString();
	}
*/
	// Métodos:
	public boolean cadastrarVeiculo() {
		return true;
	}
	
	public boolean removerVeiculo() {
		return true;
	}
}


		