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
		Veiculo novoVeiculo;

		// Encontrando o cliente com documento especificado e cadastrando um veículo a ele
		for (int i = 0; i < listaClientes.size(); i++) {
			Cliente clienteAtual = listaClientes.get(i);
			if ((clienteAtual instanceof ClientePF && (((ClientePF) clienteAtual).getCPF()).equals(documento))
					|| (clienteAtual instanceof ClientePJ && (((ClientePJ) clienteAtual).getCNPJ()).equals(documento))) {
				novoVeiculo = Veiculo.criaVeiculo();
				LinkedList<Veiculo> novaListaVeiculos = (clienteAtual.getListaVeiculos());
				novaListaVeiculos.add(novoVeiculo);

				(clienteAtual).setListaVeiculos(novaListaVeiculos);
				System.out.println("Veículo de placa " + novoVeiculo.getPlaca() + " cadastrado para o cliente de documento " + documento + " com sucesso!");
				this.calcularPrecoSeguroCliente(clienteAtual);
				return;
			}
		}
		System.out.println("Nenhum cliente cadastrado na seguradora " + this.getNome() + " com este documento.");
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
		System.out.println("Digite o documento do cliente que você deseja excluir:");
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
		
		// Encontrando e excluindo o veículo a partir de sua placa:
		for(int i = 0; i < listaClientes.size(); i++) {
			Cliente cliente = listaClientes.get(i);
			LinkedList<Veiculo> listaVeiculos = cliente.getListaVeiculos();
			for(int j = 0; j < listaVeiculos.size(); j++) {
				String placa = (listaVeiculos.get(j)).getPlaca();
				if(placa.equals(placaExcluir)) {
					listaVeiculos.remove(j);
					(listaClientes.get(i)).setListaVeiculos(listaVeiculos);
					System.out.println("Veículo excluído com sucesso!");
					this.calcularPrecoSeguroCliente(cliente);
					return true;
				}
			}
		}
		System.out.println("O veículo cuja placa é" + placaExcluir + "não está cadastrado na seguradora" + this.getNome());
		return false;
	}
	
	//Método que remove um sinistro a partir de seu ID. Se o ID for válido, retorna true. Caso contrário, retorna false.
	public boolean excluirSinistro() {
		Scanner scanner = new Scanner(System.in);
		int IdExcluir;
		
		System.out.println("Digite o ID do sinistro que você deseja excluir:");
		IdExcluir = scanner.nextInt();
		
		// Encontrando o sinistro a partir do ID e excluindo-o:
		for(int i = 0; i < listaSinistros.size(); i++) {
			if((listaSinistros.get(i)).getID() == IdExcluir) {
				Cliente cliente = listaSinistros.get(i).getCliente();
				listaSinistros.remove(i);
				System.out.println("Sinistro excluído com sucesso!");
				this.calcularPrecoSeguroCliente(cliente);
				return true;	
			}
		}
		System.out.println("ID inválido. Tente novamente.");
		return false;
	}
	
	//Método que retorna uma lista de clientes do tipo especificado "PF" ou "PJ"
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
	
	//Método que imprime todos os clientes de cada seguradora
	//Método que imprime todos os clientes de um dado tipo "PF" ou "PJ"
	public static void visualizarClientesPorSeg() {
		Scanner scanner = new Scanner(System.in);
		String tipoCliente;
		
		//Lendo um tipo válido de cliente:
		System.out.println("Digite o tipo de cliente que você deseja visualizar ('PF', 'PJ' ou 'TODOS').");
		tipoCliente = scanner.nextLine();
		while(!tipoCliente.equals("PF") && !tipoCliente.equals("PJ") && !tipoCliente.equals("TODOS")) {
			System.out.println("Tipo de cliente inválido. Os tipos válidos são 'PF', 'PJ' e 'TODOS'. Digite novamente.");
			tipoCliente = scanner.nextLine();
		}
		
		for(Seguradora seguradora : listaSeguradoras) {
			int i = 1;
			//Caso de nenhum cliente cadastrado:
			if(seguradora.listaClientes.isEmpty()) {
				System.out.println("Nenhum cliente cadastrado na seguradora " + seguradora.getNome() + ".");
				continue;
			}
						
			//Caso escolhido TODOS:
			if(tipoCliente.equals("TODOS")) {
				System.out.println("Lista de clientes da seguradora " + seguradora.getNome() + ":");
				for(Cliente cliente : seguradora.listaClientes) {
					System.out.println("* Cliente " + i++);
					System.out.println(cliente + "\n");
				}
				continue;
			}
			
			//Caso escolhido 'PF' ou 'PJ':
			if(seguradora.listarClientes(tipoCliente).isEmpty()) {
				System.out.println("Nenhum cliente do tipo " + tipoCliente + " cadastrado na seguradora " + seguradora.getNome());
				continue;
			}
			System.out.println("Lista de clientes do tipo " + tipoCliente + " da seguradora " + seguradora.getNome() + ":");
			for(Cliente cliente : seguradora.listaClientes) {
				if(tipoCliente.equals(cliente.getTipo())) {
					System.out.println("* Cliente " + i++);
					System.out.println(cliente + "\n");
				}			
			}
		}
	}
	
	//Método que, dado o documento de um cliente, retorna este cliente
	//Método que, a partir de um documento válido, encontra o cliente correspondente.
	public static Cliente encontraCliente(String documento) {
		for(Seguradora seguradora : listaSeguradoras) {
			for(Cliente cliente : seguradora.listaClientes) {
				if(cliente instanceof ClientePF && (((ClientePF) cliente).getCPF()).equals(documento)) {
					return cliente;
				} else if(cliente instanceof ClientePJ && (((ClientePJ) cliente).getCNPJ()).equals(documento)) {
					return cliente;
				}
			}
		}
		System.out.println("Cliente não encontrado.");
		return null;
	}
	
	// Método que gera um sinistro
	public Sinistro gerarSinistro() {
		//Lendo as informações:
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite, respectivamente, a data, endereço, número do documento do cliente e placa do veículo relativos ao sinistro.");
		String data = scanner.nextLine();
		String endereco = scanner.nextLine();
		String documento;
		String placa;
		Cliente cliente = null;
		Veiculo veiculo = null;
		LinkedList<Veiculo> listaVeiculosCliente;		
		
		// Encontrando o cliente a partir de seu documento:
		while(true) {
			boolean encontrado = false;
			documento = scanner.nextLine();
			for(int i = 0; i < listaClientes.size(); i++) {
				if(listaClientes.get(i) instanceof ClientePF) {
					ClientePF clienteAux = (ClientePF) listaClientes.get(i);
					if((clienteAux).getCPF().equals(documento)) {
						cliente = listaClientes.get(i);
						encontrado = true;
						break;
					}
				} else {
					ClientePJ clienteAux = (ClientePJ) listaClientes.get(i);
					if((clienteAux).getCNPJ().equals(documento)) {
						cliente = listaClientes.get(i);
						encontrado = true;
						break;						
					}
				}
			}
			if (encontrado) break;
			System.out.println("Nenhum cliente com documento " + documento + " cadastrado na seguradora " + this.getNome() + ". Digite novamente.");
		}

		// Encontrando o veículo a partir de sua placa:
		listaVeiculosCliente = cliente.getListaVeiculos();
		
		while(true) {
			boolean encontrado = false;
			placa = scanner.nextLine();
			for(int i = 0; i < listaVeiculosCliente.size(); i++) {
				if((listaVeiculosCliente.get(i).getPlaca()).equals(placa)) {
					veiculo = listaVeiculosCliente.get(i);
					encontrado = true;
					break;
				}
			}
			if(encontrado)	break;
			System.out.println("Nenhum veículo com placa " + placa + " cadastrado na seguradora " + this.getNome() + ". Digite novamente.");
		}
		
		//Gerando o sinistro:
		Sinistro novoSinistro = new Sinistro(data, endereco, this, veiculo, cliente);
		listaSinistros.add(novoSinistro);
		System.out.println("Sinistro gerado com sucesso!");
		
		//Atualizando o valor do seguro do cliente envolvido no sinistro:
		this.calcularPrecoSeguroCliente(cliente);
		
		return novoSinistro;
	}

	//Método que imprime os sinistros para cada cliente da seguradora
	// Método que imprime todos os sinistros de um dado cliente
	public void visualizarSinistrosPorCliente() {
		String documento;
		
		//Caso em que não há clientes cadastrados:
		if(listaClientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado na seguradora " + this.getNome());
			return;
		}
		
		if(listaSinistros.isEmpty()) {
			System.out.println("Nenhum sinistro cadastrado na seguradora " + this.getNome());
			return;
		}
		
		//Percorrendo os clientes da seguradora
		for(Cliente cliente : listaClientes) {
			boolean temSinistro = false;
			
			if(cliente instanceof ClientePF) {
				documento = ((ClientePF) cliente).getCPF();
			}	else	{
				documento = ((ClientePJ) cliente).getCNPJ();
			}
			
			//Percorrendo os sinistros e verificando se eles são relativos ao cliente em questão
			for(Sinistro sinistro : listaSinistros) {
				if(sinistro.getCliente() == cliente) {
					if(!temSinistro) {
						System.out.println("Lista de sinistros do cliente " + cliente.getNome() + " (" + documento + ")");
						temSinistro = true;
					}
					System.out.println("   ID: " + sinistro.getID());
					System.out.println("   Data: " + sinistro.getData());
					System.out.println("   Endereço: " + sinistro.getEndereco());
	                System.out.println("   Veículo: " + sinistro.getVeiculo());
				}
			}
			System.out.println("Nenhum sinistro cadastrado para o cliente " + cliente.getNome() + " (" + documento + ")");
		}
	}
	
	// Método que imprime todos os sinistros da seguradora:
	public static void visualizarSinistrosPorSeg() {
		for(Seguradora seguradora: listaSeguradoras) {
			// Caso de nenhum sinistro na seguradora:
			if(seguradora.listaSinistros.isEmpty()) {
				System.out.println("Nenhum sinistro cadastrado na seguradora " + seguradora.getNome());
				continue;
			}
			// Caso com sinistros na seguradora:
			System.out.println("Lista de sinistros da seguradora " + seguradora.getNome());
			for(Sinistro sinistro : seguradora.listaSinistros) {
				System.out.println(sinistro);
			}
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
			if(cliente instanceof ClientePF) {
				System.out.println(cliente.getNome() + "(" + ((ClientePF)cliente).getCPF() + ")" + ":");
				for(Veiculo veiculo : cliente.getListaVeiculos()) {
					System.out.println("   " + veiculo);
				}
			}	else	{
				System.out.println(cliente.getNome() + "(" + ((ClientePJ)cliente).getCNPJ() + ")" + ":");
				for(Veiculo veiculo : cliente.getListaVeiculos()) {
					System.out.println("   " + veiculo);
				}
			}
		}
	}
	// Método que lista todos os veículos cadastrados na seguradora:
	public static void listaVeiculosPorSeg() {
		for(Seguradora seguradora : listaSeguradoras) {
			LinkedList<Veiculo> listaVeiculosSeg = new LinkedList<>();
			for(Cliente cliente : seguradora.listaClientes) {
				for(Veiculo veiculo : cliente.getListaVeiculos()) {
					if(! listaVeiculosSeg.contains(veiculo)) {
						listaVeiculosSeg.add(veiculo);
					}
				}
			}
			
			if(listaVeiculosSeg.isEmpty()) {
				System.out.println("Nenhum veículo cadastrado na seguradora " + seguradora.getNome() + ".");
			}	else	{
				System.out.println("Veículos cadastrados na seguradora " + seguradora.getNome() + ":");
				for(Veiculo veiculo : listaVeiculosSeg) {
					System.out.println(veiculo);
				}
			}
			System.out.println("");
		}
	}
	
	// Método que transfere os veículos de um cliente para outro.
	public void transferirSeguro() {
		String documentoClienteAntigo;
		String documentoClienteNovo;
		Cliente clienteAntigo;
		Cliente clienteNovo;
		LinkedList<Veiculo> antigaLista;
		LinkedList<Veiculo> novaLista;
		
		System.out.println("Digite, respectivamente, os documentos do antigo e novo dono dos veículos.");
		documentoClienteAntigo = Validacao.recebeDocumentoValido();
		documentoClienteNovo = Validacao.recebeDocumentoValido();
		
		clienteAntigo = encontraCliente(documentoClienteAntigo);
		clienteNovo = encontraCliente(documentoClienteNovo);
		
		//Construindo a lista de veículos de clienteNovo:
		novaLista = new LinkedList<>(clienteNovo.getListaVeiculos());
		novaLista.addAll(clienteAntigo.getListaVeiculos());
		clienteNovo.setListaVeiculos(novaLista);
		
		// Zerando a lista de clienteAntigo:
		antigaLista = new LinkedList<>(clienteAntigo.getListaVeiculos());
		antigaLista.clear();
		clienteAntigo.setListaVeiculos(antigaLista);
	
		// Atualizando os valores do seguro:
		clienteNovo.setValorSeguro(clienteNovo.getValorSeguro() + clienteAntigo.getValorSeguro());
		clienteAntigo.setValorSeguro(0);
		
		System.out.println("Veículos transferidos de " + clienteAntigo.getNome() + " para " + clienteNovo.getNome() + " com sucesso!");
	}
	
	//Método que calcula um preço seguro para um determinado cliente:
	public double calcularPrecoSeguroCliente(Cliente cliente) {
		int qtdSinistros = 0;
		
		// Contando quantos sinistros o cliente tem:
		for(Sinistro sinistro : listaSinistros) {
			if(sinistro.getCliente().equals(cliente)) {
				qtdSinistros++;
			}
		}
		
		// Calculando o preço
		double preco = cliente.calculaScore() * (1 + qtdSinistros);
		cliente.setValorSeguro(preco);
		return preco;
	}
		
	//Método que calcula a receita da seguradora:
	public double calcularReceitaSeguradora() {
		double receita = 0;
		DecimalFormat formato = new DecimalFormat("0.00");
		String receitaFormatada;
		
		for(Cliente cliente : listaClientes) {
			receita += this.calcularPrecoSeguroCliente(cliente);
		}
		
		receitaFormatada = formato.format(receita);
		System.out.println("A receita total da seguradora " + this.getNome() + " é R$" + receitaFormatada);
		return receita;
	}
	
}
