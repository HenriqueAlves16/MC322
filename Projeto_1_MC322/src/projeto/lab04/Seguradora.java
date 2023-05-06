package projeto.lab04;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private LinkedList<Sinistro> listaSinistros;
	private ArrayList<Cliente> listaClientes;
	private static LinkedList<Seguradora> listaSeguradoras = new LinkedList<>();

	
	// Construtor
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaSinistros = new LinkedList<>();
		this.listaClientes = new ArrayList<>();
		
		if (listaSeguradoras == null) {
			listaSeguradoras = new LinkedList<>();
		}		
	}
	
	// Getters e setters
	public String getNome()	{
		return nome;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone (String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public LinkedList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(LinkedList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	public static LinkedList<Seguradora> getListaSeguradoras(){
		return listaSeguradoras;
	}

	//toString
	@Override
	public String toString() {
		StringJoiner str = new StringJoiner("\n");
		str.add("Classe = Seguradora");
		str.add("nome = " + nome);
		str.add("telefone = " + telefone);
		str.add("email = " + email);
		str.add("endereco = " + endereco);
		str.add("listaClientes:");
		for(Cliente cliente : listaClientes) {
			str.add(" ".repeat(4) + cliente.toString());
		}
		for(Sinistro sinistro : listaSinistros) {
			str.add(" ".repeat(4) + sinistro.toString());
		}
		return str.toString();
	}
	
	//Métodos:
	
	// Método que cadastra um novo cliente na seguradora
	public boolean cadastrarCliente(Cliente novoCliente) {
		novoCliente.setValorSeguro(calcularPrecoSeguroCliente(novoCliente));
		if (listaClientes.contains(novoCliente)) {
			System.out.println("O cliente " + novoCliente.getNome() + " já está cadastrado na seguradora " + this.getNome() + ".");
			return false;
		}
		listaClientes.add(novoCliente);
		System.out.println("O cliente " + novoCliente.getNome() + " foi cadastrado na seguradora " + this.getNome() + " com sucesso!");

		return true;
	}
	
	// Método que cadastra um veículo para um determinado cliente da seguradora:
	public void cadastrarVeiculo() {
		System.out.println("Digite o número do documento do cliente cujo carro será cadastrado.");
		String documento = Validacao.recebeDocumentoValido();
		Veiculo novoVeiculo = Veiculo.criaVeiculo();

		for (int i = 0; i < listaClientes.size(); i++) {
			if ((listaClientes.get(i) instanceof ClientePF
					&& (((ClientePF) listaClientes.get(i)).getCPF()).equals(documento))
					|| (listaClientes.get(i) instanceof ClientePJ
							&& (((ClientePJ) listaClientes.get(i)).getCNPJ()).equals(documento))) {

				LinkedList<Veiculo> novaListaVeiculos = ((listaClientes.get(i)).getListaVeiculos());
				novaListaVeiculos.add(novoVeiculo);

				(listaClientes.get(i)).setListaVeiculos(novaListaVeiculos);
			}
		}
	}
	
	// Método que cria uma seguradora a partir do input:
	public static Seguradora criaSeguradora() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome, telefone, email e endereço da seguradora, respectivamente:");

		String nome = scanner.nextLine();
		String telefone = scanner.nextLine();
		String email = scanner.nextLine();
		String endereco = scanner.nextLine();

		Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
		return seguradora;
	}

	// Método que cadastra uma nova seguradora:
	public static boolean cadastrarSeguradora() {
		Seguradora novaSeguradora = criaSeguradora();
		if (listaSeguradoras.contains(novaSeguradora)) {
			System.out.println("A seguradora " + novaSeguradora.getNome() + "já está cadastrada.");
			return false;
		}
		listaSeguradoras.add(novaSeguradora);
		System.out.println("A seguradora " + novaSeguradora.getNome() + " foi cadastrada com sucesso!");

		return true;
	}
	
	//Método que remove um cliente da lista de clientes. Se o nome do cliente dado na entrada for válido, retorna true; caso contrário, retorna false.
	public boolean excluirCliente() {
		System.out.println("Digite o documento do cliente que você deseja excluir");
		String documento = Validacao.recebeDocumentoValido();
		
		for(int i = 0; i < listaClientes.size(); i++) {
			Cliente cliente = listaClientes.get(i);
			
			// CPF com pontuação tem 14 dígitos
			if(documento.length() == 14) {
				if(cliente.getTipo().equals("PF") && (((ClientePF)cliente).getCPF()).equals(documento)) {
					listaClientes.remove(i);
					System.out.println("Cliente excluído com sucesso!");
					return true;
				}
			}	else	{
				if(cliente.getTipo().equals("PJ") && (((ClientePJ)cliente).getCNPJ()).equals(documento)) {
					listaClientes.remove(i);
					System.out.println("Cliente excluído com sucesso!");
					return true;
				}
			}
		}
		System.out.println("O cliente cujo documento é " + documento + " não está cadastrado na seguradora " + this.getNome() + ".");
		return false;
	}
	
	//Método que remove um veículo cadastrado na seguradora a partir de sua placa. Se a placa for válida, retorna true. Caso contrário, retorna false.
	public boolean excluirVeiculo() {
		Scanner scanner = new Scanner(System.in);
		String placaExcluir;
		
		System.out.println("Digite a placa do veículo que você deseja excluir:");
		placaExcluir = scanner.nextLine();
		
		for(int i = 0; i < listaClientes.size(); i++) {
			Cliente cliente = listaClientes.get(i);
			LinkedList<Veiculo> listaVeiculos = cliente.getListaVeiculos();
			for(int j = 0; j < listaVeiculos.size(); j++) {
				String placa = (listaVeiculos.get(j)).getPlaca();
				if(placa.equals(placaExcluir)) {
					listaVeiculos.remove(j);
					(listaClientes.get(i)).setListaVeiculos(listaVeiculos);
					System.out.println("Veículo excluído com sucesso!");
					return true;
				}
			}
		}
		System.out.println("O veículo cuja placa é" + placaExcluir + "não está cadastrado na seguradora" + this.getNome());
		return false;
	}
	
	//Método que remove um sinistro a partir de seu ID. Se o ID for válido, retorna True. Caso contrário, retorna false.
	public boolean excluirSinistro() {
		Scanner scanner = new Scanner(System.in);
		int IdExcluir;
		
		System.out.println("Digite o ID do sinistro que você deseja excluir:");
		IdExcluir = scanner.nextInt();
		
		for(int i = 0; i < listaSinistros.size(); i++) {
			if((listaSinistros.get(i)).getID() == IdExcluir) {
				listaSinistros.remove(i);
				System.out.println("Sinistro excluído com sucesso!");
				return true;	
			}
		}
		return false;
	}
	
	//Método que, dado um tipo de cliente "PF" ou "PJ" retorna uma lista com os clientes do tipo da entrada
	public ArrayList<Cliente> listarClientes(String tipoCliente){
		ArrayList<Cliente> listaTipo = new ArrayList<>();
		for(Cliente cliente : listaClientes) {
			if(tipoCliente.equals(cliente.getTipo())) {
				listaTipo.add(cliente);
			}
		}
		return listaTipo;
	}
	
	//Método que imprime todos os clientes de um dado tipo "PF" ou "PJ"
	public void visualizarClientesSeg() {
		int i = 1;
		Scanner scanner = new Scanner(System.in);
		String tipoCliente;
		
		//Lendo um tipo válido de cliente:
		System.out.println("Digite o tipo de cliente que você deseja visualizar ('PF', 'PJ' ou 'TODOS').");
		tipoCliente = scanner.nextLine();
		while(!tipoCliente.equals("PF") && !tipoCliente.equals("PJ") && !tipoCliente.equals("TODOS")) {
			System.out.println("Tipo de cliente inválido. Os tipos válidos são 'PF', 'PJ' e 'TODOS'. Digite novamente.");
		}
		
		for(Seguradora seguradora : listaSeguradoras) {
			//Caso de nenhum cliente cadastrado:
			if(seguradora.listaClientes.isEmpty()) {
				System.out.println("Nenhum cliente cadastrado na seguradora " + seguradora.getNome() + ".");
			}
						
			//Caso escolhido TODOS:
			if(tipoCliente.equals("TODOS")) {
				System.out.println("Lista de clientes da seguradora " + seguradora.getNome() + ":");
				for(Cliente cliente : seguradora.listaClientes) {
					System.out.println("* Cliente " + i++);
					System.out.println(cliente + "\n");
				}
				return;
			}
			
			//Caso escolhido 'PF' ou 'PJ':
			if(seguradora.listarClientes(tipoCliente).isEmpty()) {
				System.out.println("Nenhum cliente do tipo " + tipoCliente + " cadastrado na seguradora " + seguradora.getNome());
			}
			System.out.println("Lista de clientes do tipo " + tipoCliente + " da seguradora " + seguradora.getNome() + ":");
			for(Cliente cliente : listaClientes) {
				if(tipoCliente.equals(cliente.getTipo())) {
					System.out.println("* Cliente " + i++);
					System.out.println(cliente + "\n");
				}			
			}
		}
	}
	
	//Método que, a partir de um documento válido, encontra o cliente correspondente.
	public Cliente encontraCliente(String documento) {
		for(Cliente cliente : listaClientes) {
			if(cliente instanceof ClientePF && (((ClientePF) cliente).getCPF()).equals(documento)) {
				return cliente;
			} else if(cliente instanceof ClientePJ && (((ClientePJ) cliente).getCNPJ()).equals(documento)) {
				return cliente;
			}
		}
		System.out.println("Cliente não encontrado.");
		return null;
	}
	
	// Método que gera um sinistro:
	public Sinistro gerarSinistro() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite, respectivamente, a data, endereço, placa do veículo e número do documento do cliente relativos ao sinistro.");
		String data = scanner.nextLine();
		String endereco = scanner.nextLine();
		String placa = scanner.nextLine();
		String documento = scanner.nextLine();
		Cliente cliente = null;
		LinkedList<Veiculo> listaVeiculosCliente;
		Veiculo veiculo = null;
		
		for(int i = 0; i < listaClientes.size(); i++) {
			if(listaClientes.get(i) instanceof ClientePF) {
				ClientePF clienteAux = (ClientePF) listaClientes.get(i);
				if((clienteAux).getCPF().equals(documento)) {
					cliente = listaClientes.get(i);
				}
			} else {
				ClientePJ clienteAux = (ClientePJ) listaClientes.get(i);
				if((clienteAux).getCNPJ().equals(documento)) {
					cliente = listaClientes.get(i);
				}
			}
		}

		listaVeiculosCliente = cliente.getListaVeiculos();
		
		for(int i = 0; i < listaVeiculosCliente.size(); i++) {
			if((listaVeiculosCliente.get(i).getPlaca()).equals(placa)) {
				veiculo = listaVeiculosCliente.get(i);
			}
		}
		
		Sinistro novoSinistro = new Sinistro(data, endereco, this, veiculo, cliente);
		listaSinistros.add(novoSinistro);
		System.out.println("Sinistro gerado com sucesso!");
		return novoSinistro;
	}

	// Método que imprime todos os sinistros de um dado cliente
	public boolean visualizarSinistrosCliente() {
		boolean i = false;
		
		System.out.println("Digite o número do documento do cliente que você deseja visualizar os sinistros:");
		String documento = Validacao.recebeDocumentoValido();
		
		//CPF com pontuação tem 14 caracteres CNPJ tem 18.
		if(documento.length() == 14) {
			for(Sinistro sinistro : this.listarSinistros("PF")) {
				ClientePF cliente = (ClientePF)sinistro.getCliente();
				if(cliente.getCPF().equals(documento)) {
					System.out.println(sinistro);
					i = true;
				}
			}
		}	else	{
			for(Sinistro sinistro : listarSinistros("PJ")) {
				ClientePJ cliente = (ClientePJ)sinistro.getCliente();
				if(cliente.getCNPJ().equals(documento)) {
					System.out.println(sinistro);
					i = true;
				}
			}
		}
		if(!i) {
			System.out.println("Nenhum sinistro associado ao cliente em questão");
		}
		return i;
	}
	
	// Método que imprime todos os sinistros da seguradora:
	public void visualizarSinistrosSeguradora() {
		for(Sinistro sinistro : listaSinistros) {
			System.out.println(sinistro);
		}
	}
	
	//Método que, dado um tipo de cliente "PF" ou "PJ" retorna uma lista com todos os sinistros do tipo de cliente da entrada
	public ArrayList<Sinistro> listarSinistros(String tipoCliente){
		ArrayList<Sinistro> listaTipo = new ArrayList<>();
		
		for(Sinistro sinistro : listaSinistros) {
			if(tipoCliente.equals((sinistro.getCliente()).getTipo())) {
				listaTipo.add(sinistro);
			}
		}
		return listaTipo;
	}
	
	// Método que lista os veículos por cliente da seguradora:
	public void listaVeiculosCliente() {
		for (Cliente cliente : listaClientes) {
			System.out.println(cliente.getNome() + ": " + cliente.getListaVeiculos());
		}
	}
	
	// Método que lista todos os veículos cadastrados na seguradora:
	public void listaVeiculosSeguradora() {
		LinkedList<Veiculo> listaTodosVeiculos= new LinkedList<>();
		
		for(Cliente cliente : listaClientes) {
			for(Veiculo veiculo : cliente.getListaVeiculos()) {
				if(! listaTodosVeiculos.contains(veiculo)) {
					listaTodosVeiculos.add(veiculo);
				}
			}
		}
		
		if(listaTodosVeiculos.isEmpty()) {
			System.out.println("Nenhum veículo cadastrado na seguradora " + this.getNome() + ".");
		}	else	{
			System.out.println("Veículos cadastrados na seguradora " + this.getNome() + ":");
			for(Veiculo veiculo : listaTodosVeiculos) {
				System.out.println(veiculo);
			}
		}
		System.out.println("");
	}
	
	// Método que transfere os veículos de um cliente para outro.
	public void transferirSeguro() {
		String documentoClienteAntigo;
		String documentoClienteNovo;
		Cliente clienteAntigo;
		Cliente clienteNovo;
		LinkedList<Veiculo> novaLista = new LinkedList<>();
		int s = 0;
		
		System.out.println("Digite, respectivamente, o documento do dono antigo e novo dos veículos.");
		documentoClienteAntigo = Validacao.recebeDocumentoValido();
		documentoClienteNovo = Validacao.recebeDocumentoValido();
		
		clienteAntigo = encontraCliente(documentoClienteAntigo);
		clienteNovo = encontraCliente(documentoClienteNovo);
		novaLista = clienteNovo.getListaVeiculos();

		for(int i = 0; i < listaClientes.size(); i++) {
			Cliente clienteAtual = listaClientes.get(i);
			if(clienteAtual == clienteAntigo) {
				listaClientes.get(i).setListaVeiculos(null);
			}	else if(clienteAtual == clienteNovo) {
				for(Veiculo veiculo : clienteAntigo.getListaVeiculos()) {
					novaLista.add(veiculo);
				}
				listaClientes.get(i).setListaVeiculos(novaLista);
			}
		}
		
	}
	
	//Método que calcula um preço seguro para um determinado cliente:
	public double calcularPrecoSeguroCliente(Cliente cliente) {
		int qtdSinistros = getListaSinistros().size();
		double preco = cliente.calculaScore() * (1 + qtdSinistros);
		return preco;
	}
		
	//Método que calcula a receita da seguradora:
	public double calcularReceitaSeguradora() {
		double receita = 0;
		DecimalFormat formato = new DecimalFormat("0,00");
		String receitaFormatada;
		
		for(Cliente cliente : listaClientes) {
			receita += cliente.calculaScore();
		}
		
		receitaFormatada = formato.format(receita);
		System.out.println("A receita total da seguradora " + this.getNome() + " é R$" + receitaFormatada);
		return receita;
	}
	
}
