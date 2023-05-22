package projeto.lab05;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.StringJoiner;
import java.util.ArrayList;


public class ClientePJ extends Cliente {
	private final String CNPJ;
	private LocalDate dataFundacao;
	private ArrayList<Frota> listaFrotas;
	private int qtdeFuncionarios;
	
	//Metodo construtor:
	public ClientePJ(String nome, String endereco, String telefone, String email, String CNPJ, String dataFundacao, int qtdeFuncionarios) {
		// chama o construtor da superclasse
		super(nome, endereco, telefone, email);
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.CNPJ = CNPJ;
		this.dataFundacao = LocalDate.parse(dataFundacao, formatador);
		this.qtdeFuncionarios = qtdeFuncionarios;
	}

	//Getters e setters:
	
	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public ArrayList<Frota> getListaFrotas() {
		return listaFrotas;
	}

	public void setListaFrotas(ArrayList<Frota> listaFrota) {
		this.listaFrotas = listaFrota;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public int getQtdeFuncionarios() {
		return qtdeFuncionarios;
	}

	public void setQtdeFuncionarios(int qtdeFuncionarios) {
		this.qtdeFuncionarios = qtdeFuncionarios;
	}

	//toString:
	@Override
	public String toString() {
		return "ClientePJ [CNPJ=" + CNPJ + ", dataFundacao=" + dataFundacao + ", listaFrota=" + listaFrotas + "]";
	}	
	
	// Métodos:
	public boolean cadastrarFrota() {
		return true;
	}
	
	public boolean atualizarFrota() {
		return true;
	}
	
	public boolean listarVeiculosPorFrota() {
		return true;
	}
	
	//Método que retorna a quantidade de tempo passada após a fundação deste ClientePJ
	public int getIdade() {
		int idade;
		LocalDate fund = getDataFundacao();
		LocalDate now = LocalDate.now();
		idade = (int) fund.until(now, ChronoUnit.YEARS);
		
		return idade;
	}
	
	//Método que retorna a quantidade de veículos associados a este ClientePJ
	public int totalVeiculos() {
		int qtdVeiculos = 0;
		for(Frota frota : getListaFrotas()) {
			qtdVeiculos += frota.getListaVeiculos().size();
		}
		return qtdVeiculos;
	}
}


		