package projeto.lab05;
import java.time.LocalDate;
import java.util.StringJoiner;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class Cliente {
	
	private String nome; 
	private String endereco;
	private LocalDate dataLicenca;
	private String tipo;
	private double valorSeguro;
	private LinkedList<Veiculo> listaVeiculos;
	
	
	//Construtor da classe
	
	public Cliente (String nome, String endereco, String dataLicenca, String tipo) {
		DateTimeFormatter formatterSTR1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.nome = nome;
		this.endereco = endereco;
		this.dataLicenca = LocalDate.parse(dataLicenca, formatterSTR1);
		this.tipo = tipo;
		this.valorSeguro = 0;
		listaVeiculos = new LinkedList<>();
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
	
	public double getValorSeguro() {
		return valorSeguro;
	}
	
	public void setValorSeguro(double valorSeguro) {
		this.valorSeguro = valorSeguro;
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
	

	
	// Metodos:

	//Método que cria um cliente a partir do input:
	public static Cliente criaCliente() {
		Scanner scanner = new Scanner(System.in);
		Cliente cliente = null;
		System.out.println("Digite o nome, endereço, data de licença (dd/mm/aaaa) e tipo de cliente ('PF' ou 'PJ'), respectivamente:");

		String nome = scanner.nextLine();
		String endereco = scanner.nextLine();
		String dataLicenca = scanner.nextLine();
		String tipo = scanner.nextLine();
		
		// Caso em que o tipo é inválido
		while(!tipo.equals("PF") && !tipo.equals("PJ")) {
			System.out.println("Tipo inválido. O tipo de cliente deve ser 'PF' ou 'PJ'. Digite novamente.");
			tipo = scanner.nextLine();
		}
		
		// Caso tipo == "PF"
		if (tipo.equals("PF")) {
			System.out.println("Digite a escolaridade, gênero, classe econômica, CPF e data de nascimento (dd/mm/aaaa) do cliente, respectivamente:");

			String educacao = scanner.nextLine();
			String genero = scanner.nextLine();
			String classeEconomica = scanner.nextLine();
			String cpf = Validacao.recebeDocumentoValido();
			String dataNascimento = scanner.nextLine();

			cliente = new ClientePF(nome, endereco, dataLicenca, tipo, educacao, genero, classeEconomica, cpf, dataNascimento);
		//Caso tipo == "PJ"
		} else {
			System.out.println("Digite o CNPJ, a data de fundação (dd/mm/aaaa) e a quantidade de funcionários da instituição, respectivamente:");

			String cnpj = scanner.nextLine();
			String dataFundacao = scanner.nextLine();
			int qtdFuncionarios = scanner.nextInt();
			scanner.nextLine();
			
			cliente = new ClientePJ(nome, endereco, dataLicenca, tipo, cnpj, dataFundacao, qtdFuncionarios);
		}
		return cliente;
	}
	
	// Método que adiciona um veículo na lista de veículos de um cliente
	public boolean adicionaVeiculo(Veiculo veiculo) {
		if(listaVeiculos.contains(veiculo)) {
			System.out.println("O veículo de placa " + veiculo.getPlaca() + " já está cadastrado na lista de veículos de " + this.getNome() + ".");
			return false;
		}
		listaVeiculos.add(veiculo);
		System.out.println("O veículo de placa " + veiculo.getPlaca() + "foi adicionado com sucesso na lista de veículos do cliente " + this.getNome() + "!");
		return true;
	}
	
	//Método que imprime todos os veículos de um dado cliente
	public void visualizarVeiculos(Cliente cliente) {
		for(Veiculo veiculo: cliente.listaVeiculos) {
			System.out.println(veiculo.getModelo());
		}
	}
	
	//Método que retorna uma lista com todos os veículos de um dado cliente
	public LinkedList<Veiculo> listaVeiculos(Cliente cliente) {
		return cliente.listaVeiculos;
	}
	
	// Método que, dado um cliente PF ou PJ, retorna seu documento 
	public static String encontraDocumento(Cliente cliente) {
		String documento;
		if(cliente instanceof ClientePF) {
			documento = ((ClientePF)cliente).getCPF();
		}	else	{
			documento = ((ClientePJ)cliente).getCNPJ();
		}
		return documento;
	}
	
	//Método abstrato que calcula o score de um cliente PF ou PJ
	public abstract double calculaScore();
	
	
}
