package projeto.lab04;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.StringJoiner;

public class ClientePF extends Cliente {
	private String educacao;
	private String genero;
	private String classeEconomica;
	final String CPF;
	private LocalDate dataNascimento;
	
	//Metodo construtor:
	public ClientePF(String nome, String endereco, String dataLicenca, String tipo, String educacao, 
			String genero, String classeEconomica, String cpf, String dataNascimento) {
		// chama o construtor da superclasse
		super(nome, endereco, dataLicenca, tipo);
		
		DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
		this.CPF = cpf;
		this.dataNascimento = LocalDate.parse(dataNascimento, formatterSTR);
	}

	//Getters e setters:
	public String getCPF() {
		return CPF;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = LocalDate.parse(dataNascimento);
	}
	
	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getClasseEconomica() {
		return classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}
	
	public int getIdade() {
		int idade;
		LocalDate nasc = getDataNascimento();
		LocalDate now = LocalDate.now();
		idade = (int) nasc.until(now, ChronoUnit.YEARS);
		
		return idade;
	}
	
	//toString:
	@Override
	public String toString() {
		StringJoiner str = new StringJoiner("\n");
		DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataLicencaFormatada = (getDataLicenca()).format(formatterSTR);
		String dataNascimentoFormatada = (dataNascimento).format(formatterSTR);
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
			
	// Método que calcula o score de um ClientePF
	@Override
	public double calculaScore() {
		int quantidadeCarros = getListaVeiculos().size();
		double fator;
		double valor;
		
		if(getIdade() < 31) {
			fator = CalcSeguro.FATOR_18_30.getF();
		}	else if(getIdade() < 61) {
			fator = CalcSeguro.FATOR_30_60.getF();
		}	else	{
			fator = CalcSeguro.FATOR_60_90.getF();
		}
		valor = CalcSeguro.VALOR_BASE.getF() * fator * quantidadeCarros;
		return valor;
	}
}


		