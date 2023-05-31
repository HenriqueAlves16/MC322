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
		this.listaFrotas = new ArrayList<Frota>();

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

	public String getDocumento() {
		return CNPJ;
	}

	public int getQtdeFuncionarios() {
		return qtdeFuncionarios;
	}

	public void setQtdeFuncionarios(int qtdeFuncionarios) {
		this.qtdeFuncionarios = qtdeFuncionarios;
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
	
	//Método que adiciona veículo na frota:
	public boolean adicionaVeiculoFrota(String code, Veiculo veiculo) {
		boolean adicionado = false;
		for(int i = 0; i < listaFrotas.size(); i++) {
			Frota frota = listaFrotas.get(i);
			ArrayList<Veiculo> listaVeiculosFrota = new ArrayList<Veiculo>(frota.getListaVeiculos());
			
			if(frota.getCode().equals(code)) {
				listaVeiculosFrota.add(veiculo);
				frota.setListaVeiculos(listaVeiculosFrota);
				return true;
			}
		}
		return false;
	}
	
	//Método que recebe o input para adicionar veículo em alguma frota
	public void leituraAdicionaVeiculo() {
		System.out.println("Escreva o código da frota e as informações do veículo que você deseja adicionar:");
		Scanner scanner = new Scanner(System.in);
		String code = scanner.nextLine();
		Veiculo veiculo = Veiculo.criaVeiculo();
		boolean sucesso = adicionaVeiculoFrota(code, veiculo);
		
		if(sucesso) {
			System.out.println("Veículo adicionado com sucesso!");
		}	else	{
			System.out.println("Falha ao adicionar veículo. Tente novamente.");
		}
	}
	
	//Método que remove veículo da frota:
	public boolean removeVeiculoFrota(String placa) {
		boolean removido = false;
		for(int i = 0; i < listaFrotas.size(); i++) {
			Frota frota = listaFrotas.get(i);
			ArrayList<Veiculo> listaVeiculosFrota = new ArrayList<Veiculo>(frota.getListaVeiculos());
			
			for(int j = 0; j < frota.getListaVeiculos().size(); j++) {
				Veiculo veiculo = frota.getListaVeiculos().get(j);
				if(veiculo.getPlaca().equals(placa)) {
					listaVeiculosFrota.remove(veiculo);
					frota.setListaVeiculos(listaVeiculosFrota);
					return true;
				}
			}
		}
		return false;
	}
	
	//Método que recebe o input para remover veículo de alguma frota
	public boolean leituraRemoveVeiculo() {
		System.out.println("Escreva a placa do veículo que você deseja remover:");
		Scanner scanner = new Scanner(System.in);
		String placa = scanner.nextLine();
		boolean sucesso = removeVeiculoFrota(placa);
		
		if(sucesso) {
			System.out.println("Veículo removido com sucesso!");
		}	else	{
			System.out.println("Falha ao remover veículo. Tente novamente.");
		}
		return sucesso;
	}
	
	//Método que remove veículo da frota:
	public boolean removeFrota(String code) {
		ArrayList<Frota> listaFrotas = getListaFrotas();
		
		for(int i = 0; i < listaFrotas.size(); i++) {
			Frota frota = listaFrotas.get(i);
			if(frota.getCode().equals(code)) {
				listaFrotas.remove(i);
				setListaFrotas(listaFrotas);
				return true;
			}
		}
		return false;
	}
		
	//Método que recebe o input para remover uma frota inteira:
	public boolean leituraRemoveFrota() {
		System.out.println("Escreva o código da frota que você deseja remover:");
		Scanner scanner = new Scanner(System.in);
		String code = scanner.nextLine();
		boolean sucesso = removeFrota(code);
		
		if(sucesso) {
			System.out.println("Frota removida com sucesso!");
		}	else	{
			System.out.println("Falha ao remover frota. Tente novamente.");
		}
		return sucesso;
	}
	
	public void atualizarFrota() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Selecione o número da operação que você deseja fazer na frota:");
		System.out.println("1 - Adicionar veículo");
		System.out.println("2 - Remover veículo");
		System.out.println("3 - Remover frota");

		int op = scanner.nextInt();
		scanner.nextLine();
		
		switch(op) {
			case 1:
				leituraAdicionaVeiculo();
				break;
			case 2:
				leituraRemoveVeiculo();
				break;
			case 3:
				leituraRemoveFrota();
				break;
		}
		
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
		try {
			for(Frota frota : getListaFrotas()) {
				qtdVeiculos += frota.getListaVeiculos().size();
			}
		} catch(NullPointerException e) {
			System.out.println("Nenhum veículo na lista de frotas do cliente " + getNome() + ".");
		}
		return qtdVeiculos;
	}
	
	//Método que retorna uma lista contendo todos os veículos de todas as frotas:
	public ArrayList<Veiculo> getListaVeiculos(){
		ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		for(Frota frota: getListaFrotas()) {
			listaVeiculos.addAll(frota.getListaVeiculos());
		}
		return listaVeiculos;
	}
	
	//Método que imprime todos os veículos do cliente:
	public void visualizarVeiculos() {
		for(Frota frota : getListaFrotas()) {
			System.out.println("* Frota " + frota.getCode() + ":");
			for(Veiculo veiculo : frota.getListaVeiculos()) {
				System.out.println("    - " + veiculo.toString());
			}
		}
	}
}


		