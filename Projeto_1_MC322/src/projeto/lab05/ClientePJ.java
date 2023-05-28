package projeto.lab05;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.StringJoiner;
import java.util.ArrayList;
import java.util.Scanner;


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
	
	// Método que cadastra uma frota para na lista de frotas deste ClientePJ:
	public boolean cadastrarFrota(Frota frota) {
		ArrayList<Frota> listaFrotas = getListaFrotas();
		if(listaFrotas.contains(frota)) {
			System.out.println("A frota já está cadastrada.");
			return false;
		}
		
		listaFrotas.add(frota);
		setListaFrotas(listaFrotas);
		System.out.println("Frota cadastrada com sucesso!");

		return true;
	}
	
	
	
	public boolean atualizarFrota() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Selecione o número da operação que você deseja fazer na frota:");
		System.out.println("1 - Adicionar veículo");
		System.out.println("2 - Remover veículo");
		System.out.println("3 - Remover frota");

		int op = scanner.nextInt();
		scanner.nextLine();
		
		switch(op) {
			case 1:
				adicionar
				break;
			case 2:
				remover
				break;
			case 3:
				remover tudo
				break;
		}
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


		