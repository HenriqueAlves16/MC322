package projeto.lab04;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;


public class ClientePJ extends Cliente {
	final String CNPJ;
	private LocalDate dataFundacao;
	int qtdeFuncionarios;
	
	//Metodo construtor:
	public ClientePJ(String nome, String endereco, String dataLicenca, String tipo, String cnpj, String dataFundacao, int qtdeFuncionarios) {
		// chama o construtor da superclasse
		super(nome, endereco, dataLicenca, tipo);
		
		DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.CNPJ = cnpj;
		this.dataFundacao = LocalDate.parse(dataFundacao, formatterSTR);
		this.qtdeFuncionarios = qtdeFuncionarios;
	}

	//Getters e setters:
	public String getCNPJ() {
		return CNPJ;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}


	public void setDataFundacao(String dataFundacao) {
		this.dataFundacao = LocalDate.parse(dataFundacao);
	}
	
	public int getQtdeFuncionarios() {
		return qtdeFuncionarios;
	}
	
	public void setQtdeFuncionarios(int qtd) {
		this.qtdeFuncionarios = qtd;
	}
	
		
	//toString:
	@Override
	public String toString() {
		StringJoiner str = new StringJoiner("\n");
		DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataLicencaFormatada = (getDataLicenca()).format(formatterSTR);
		String dataFundacaoFormatada = (dataFundacao).format(formatterSTR);
		str.add("Classe = ClientePJ");
		str.add("nome = " + getNome());
		str.add("endereco = " + getEndereco());
		str.add("dataLicenca = " + dataLicencaFormatada);
		str.add("tipo = " + getTipo());
		str.add("CNPJ = " + CNPJ);
		str.add("dataFundacao = " + dataFundacaoFormatada);
		str.add("listaVeiculos :");
		for(Veiculo veiculo : getListaVeiculos()) {
			str.add(" ".repeat(4) + veiculo.toString());
		}
		return str.toString();

	}	
	
	// Método que calcula o score de um ClientePJ
	@Override
		public double calculaScore() {
			int quantidadeCarros = getListaVeiculos().size();
			double valor;
			
			valor = CalcSeguro.VALOR_BASE.getF() * (1 + (getQtdeFuncionarios()) / 100) * quantidadeCarros;
			setValorSeguro(valor);
			return valor;
		}
}


		