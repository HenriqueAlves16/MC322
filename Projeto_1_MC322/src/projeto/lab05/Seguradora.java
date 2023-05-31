package projeto.lab05;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class Seguradora {
	private final String CNPJ;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Seguro> listaSeguros;
	private static ArrayList<Seguradora> listaSeguradoras;

	
	// Construtor
	public Seguradora(String CNPJ, String nome, String telefone, String endereco, String email) {
		this.CNPJ = CNPJ;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaSeguros = new ArrayList<Seguro>();
		this.listaClientes = new ArrayList<Cliente>();
		
		if (listaSeguradoras == null) {
			listaSeguradoras = new ArrayList<Seguradora>();
		}		
	}
	
	// Getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}

	public static ArrayList<Seguradora> getListaSeguradoras() {
		return listaSeguradoras;
	}

	public static void setListaSeguradoras(ArrayList<Seguradora> listaSeguradoras) {
		Seguradora.listaSeguradoras = listaSeguradoras;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	//toString
	@Override
	public String toString() {
		return getNome() + " - " + getCNPJ();
	}
	
	//Métodos:
	
	//Método que, dado um tipo de cliente "PF" ou "PJ" retorna uma lista com os clientes do tipo da entrada:
	public ArrayList<Cliente> listarClientes(String tipoCliente){
		ArrayList<Cliente> listaTipo = new ArrayList<>();
		for(Cliente cliente : listaClientes) {
			if(tipoCliente.equals("PF") && cliente instanceof ClientePF) {
				listaTipo.add(cliente);
			}	else if(tipoCliente.equals("PJ") && cliente instanceof ClientePJ) {
				listaTipo.add(cliente);
			}
		}
		return listaTipo;
	}
			
	//Método que imprime todos os clientes de cada seguradora:
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
					System.out.println("* Cliente " + i++ + ":");
					System.out.println(cliente + "\n");
				}
				continue;
			}
			
			//Caso escolhido 'PF' ou 'PJ':
			if(seguradora.listarClientes(tipoCliente).isEmpty()) {
				System.out.println("Nenhum cliente do tipo " + tipoCliente + " cadastrado na seguradora " + seguradora.getNome() + ".");
				continue;
			}
			System.out.println("Lista de clientes do tipo " + tipoCliente + " da seguradora " + seguradora.getNome() + ":");
			for(Cliente cliente : seguradora.listarClientes(tipoCliente)) {
				System.out.println("* Cliente " + i++ + ":");
				System.out.println(cliente + "\n");
			}
		}
	}
	
	//Método que imprime todos os seguros por seguradora:
	public static void visualizarSegurosPorSeg() {
		Scanner scanner = new Scanner(System.in);
		String tipoSeguro;
		
		for(Seguradora seguradora : listaSeguradoras) {
			int i = 1;
			//Caso de nenhum cliente cadastrado:
			if(seguradora.listaClientes.isEmpty()) {
				System.out.println("Nenhum cliente cadastrado na seguradora " + seguradora.getNome() + ".");
				continue;
			}
						
			System.out.println("Lista de seguros da seguradora " + seguradora.getNome() + ":");
			for(Seguro seguro : seguradora.getListaSeguros()) {
				System.out.println("* Seguro " + i++ + ":");
				System.out.println(seguro + "\n");
			}			
		}
	}
	
	//Método que atualiza a frota de um ClientePJ:
	public void atualizarFrota(MenuOperacoes op) {
		if(getListaClientes().isEmpty()) {
			System.out.println("Nenhum cliente cadastrado na seguradora " + this.getNome() + ".");
			return;
		}
		
		System.out.println("Digite o CNPJ do cliente com quem você deseja operar:");
		Scanner scanner = new Scanner(System.in);
		String cnpj = Validacao.recebeDocumentoValido();
		Cliente cliente = encontraCliente(cnpj);
		try {
			ClientePJ clientePJ = (ClientePJ)cliente;
			switch(op) {
				case ATUALIZAR_FROTA:
					clientePJ.atualizarFrota();
					break;
				case CADASTRAR_FROTA:
					clientePJ.cadastrarFrota(Frota.criarFrota());
					break;
				default:
					System.out.println("Operação inválida.");
			}
		} catch(NullPointerException e) {
			return;
		}	catch(ClassCastException e) {
			System.out.println("CNPJ inválido.");
		}
	}
	
	//Método que cadastra um veículo para um cliente:
	public void cadastrarVeiculo() {
		System.out.println("Digite o documento do cliente com quem você deseja operar:");
		Scanner scanner = new Scanner(System.in);
		String documento = Validacao.recebeDocumentoValido();
		Cliente cliente = Seguradora.encontraCliente(documento);
		try {
			((ClientePF)cliente).cadastrarVeiculo(Veiculo.criaVeiculo());
		} catch (ClassCastException e) {
			System.out.println("Esta operação vale apenas para clientes do tipo pessoa física.");
			System.out.println("Para cadastrar um veículo em uma frota, acesse a operação 'Atualizar frota'.");
		}
	}
	
	//Método que encontra um veículo a partir de sua placa:
	public Veiculo encontraVeiculo(String placa, ClientePF cliente) {
		Veiculo veiculo = null;
		for(int i = 0; i < cliente.getListaVeiculos().size(); i++) {
			if((cliente.getListaVeiculos().get(i).getPlaca()).equals(placa)) {
				veiculo = cliente.getListaVeiculos().get(i);
				break;
			}
		}
		return veiculo;
	}
	
	//Método que encontra uma frota a partir de seu código:
	public Frota encontraVeiculo(String code, ClientePJ cliente) {
		Frota frota = null;
		for(int i = 0; i < cliente.getListaFrotas().size(); i++) {
			if((cliente.getListaFrotas().get(i).getCode()).equals(code)) {
				frota = cliente.getListaFrotas().get(i);
				break;
			}
		}
		return frota;
	}

	//Método que cadastra um seguro na lista de seguros deste Seguradora. Se o seguro já está cadastrado, retorna false. Retorna true caso contrário:
	public boolean cadastrarSeguro(Seguro seguro) {
		ArrayList<Seguro> listaSeguros = getListaSeguros();
		if(listaSeguros.contains(seguro)) {
			System.out.println("O seguro já está cadastrado.");
			return false;
		}
		
		listaSeguros.add(seguro);
		setListaSeguros(listaSeguros);
		System.out.println("Seguro cadastrado com sucesso!");
		
		return true;
	}
	
	//Método que gera um seguro a partir do input:
	public Seguro gerarSeguro() {
		Seguro seguro;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite as datas de início e fim (dd/mm/aaaa) do seguro e o número do documento do cliente titular do seguro.");
		String dataInicio = scanner.nextLine();
		String dataFim = scanner.nextLine();
		String documento = Validacao.recebeDocumentoValido();
		Cliente cliente = encontraCliente(documento);
		
		if(cliente instanceof ClientePF) {
			System.out.println("Digite a placa do veículo cujo seguro será feito");
			String placa = scanner.nextLine();
			Veiculo veiculo = encontraVeiculo(placa, (ClientePF) cliente);
			seguro = new SeguroPF(dataInicio, dataFim, this, veiculo, (ClientePF) cliente);
		}	else	{
			System.out.println("Digite o código da frota cujo seguro será feito");
			String code = scanner.nextLine();
			Frota frota = encontraVeiculo(code, (ClientePJ) cliente);
			seguro = new SeguroPJ(dataInicio, dataFim, this, frota, (ClientePJ) cliente);
		}
		
		cadastrarSeguro(seguro);
		return seguro;
	}

	//Método que remove um seguro da lista se seguros desta Seguradora. Se ele não está nela, retorna false; caso contrário, retorna true:
	public boolean cancelarSeguro(Seguro seguro) {
		ArrayList<Seguro> listaSeguros = getListaSeguros();
		if(!listaSeguros.contains(seguro))	return false;
		
		listaSeguros.remove(seguro);
		setListaSeguros(listaSeguros);
		
		return true;
	}
	
	//Método que exclui um seguro a partir de dados do input:
	public void leituraExcluirSeguro() {
		Scanner scanner = new Scanner(System.in);
		int id;
		
		System.out.println("Digite o ID do seguro que você deseja excluir:");
		try {
			id = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("O ID deve ser um inteiro.");
			return;
		}
		scanner.nextLine();
		
		Seguro seguro = encontraSeguro(id);
		if(cancelarSeguro(seguro)) {
			System.out.println("Seguro removido com sucesso!");
		}	else {
			System.out.println("Falha ao excluir o seguro.");
		}
	}
	
	//Método que exclui um veículo a partir de informações do input:
	public void excluirVeiculo() {
		if(getListaClientes().isEmpty()) {
			System.out.println("Nenhum cliente cadastrado na seguradora " + getNome() + ".");
			return;
		}
		
		System.out.println("Digite o CPF do cliente que você deseja excluir o veículo:");
		String cpf = Validacao.recebeDocumentoValido();
		Cliente cliente = encontraCliente(cpf);
		try {
			((ClientePF)cliente).excluirVeiculo();
		} catch (ClassCastException e) {
			System.out.println("Esta operação vale apenas para clientes do tipo pessoa física.");
			System.out.println("Para excluir um veículo de uma frota, acesse a operação 'Atualizar frota'.");
		} catch (NullPointerException e) {
			System.out.println("Cliente não encontrado.");
		}
		
		try {
			encontraSeguro(cpf).atualizaValorMensal();
		}	catch(NullPointerException e) {}
	
	}
	
	//Método que desautoriza um condutor em um seguro:
	public void excluirCondutor() {
		Scanner scanner = new Scanner(System.in);
		int id;
		
		System.out.println("Digite o CPF do condutor que será excluído:");
		String cpf = scanner.nextLine();
		
		System.out.println("Digite o ID do seguro relativo ao condutor que será excluído:");
		try {
			id = scanner.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("O ID deve ser um inteiro.");
			return;
		} finally {
			scanner.nextLine();
		}
		
		Seguro seguro = encontraSeguro(id);
		Condutor condutor = seguro.encontraCondutor(cpf);
		
		seguro.desautorizarCondutor(condutor);
		System.out.println("Condutor excluído com sucesso!");
	}
	
	//Método que encontra um seguro a partir de seu ID:
	public Seguro encontraSeguro(int ID) {
		for(int i = 0; i < listaSeguros.size(); i++) {
			if(listaSeguros.get(i).getID() == ID) {
				return listaSeguros.get(i);
			}
		}
		return null;
	}
	
	//Método que encontra um seguro a partir do documento do cliente:
	public Seguro encontraSeguro(String documento) {
		for(int i = 0; i < listaSeguros.size(); i++) {
			if(listaSeguros.get(i).getCliente().getDocumento().equals(documento)) {
				return listaSeguros.get(i);
			}
		}
		return null;
	}
	
	//Método que, a partir de informações do input, cadastra um condutor em um seguro:
	public void cadastrarCondutor() {
		Scanner scanner = new Scanner(System.in);
		int id;
		
		System.out.println("Digite o ID do seguro em que o condutor será cadastrado");
		try {
			id = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("O ID deve ser um inteiro.");
			return;
		}
		
		Seguro seguro = encontraSeguro(id);
		seguro.autorizarCondutor(Condutor.criaCondutor());
		System.out.println("Condutor cadastrado com sucesso!");
	}
	
	// Método que cadastra um novo cliente na seguradora
	public boolean cadastrarCliente(Cliente novoCliente) {
		if (listaClientes.contains(novoCliente)) {
			System.out.println("O cliente " + novoCliente.getNome() + " já está cadastrado na seguradora " + this.getNome() + ".");
			return false;
		}
		listaClientes.add(novoCliente);
		System.out.println("O cliente " + novoCliente.getNome() + " foi cadastrado na seguradora " + this.getNome() + " com sucesso!");

		return true;
	}
	
	//Método que cadastra um novo sinistro em um seguro da Seguradora, a partir de dados do input:
	public void cadastrarSinistro() {
		Scanner scanner = new Scanner(System.in);
		int IDSin;
		
		System.out.println("Digite o ID do seguro relacionado ao sinistro:");
		IDSin = scanner.nextInt();
		scanner.nextLine();
		
		Seguro seguro = encontraSeguro(IDSin);
		seguro.gerarSinistro();
	}
	
	//Método que remove um cliente da lista de clientes. Se o nome do cliente dado na entrada for válido, retorna true; caso contrário, retorna false.
	public boolean excluirCliente() {
		if(getListaClientes().isEmpty()) {
			System.out.println("Nenhum cliente cadastrado na seguradora " + getNome() + ".");
			return false;
		}
		
		System.out.println("Digite o documento do cliente que você deseja excluir:");
		String documento = Validacao.recebeDocumentoValido();
		
		for(int i = 0; i < listaClientes.size(); i++) {
			Cliente cliente = listaClientes.get(i);
			if(cliente.getDocumento().equals(documento)) {
				listaClientes.remove(i);
				System.out.println("Cliente excluído com sucesso!");
				return true;
			}
		}
		System.out.println("O cliente cujo documento é " + documento + " não está cadastrado na seguradora " + this.getNome() + ".");
		return false;
	}
	
	//Método que retorna uma lista com os seguros por cliente na seguradora:
	public ArrayList<Seguro> getSegurosPorCliente() {
		ArrayList<Seguro> listaSegurosPorCliente = new ArrayList<Seguro>();
		
		for (Cliente cliente : listaClientes) {
			String documento = cliente.getDocumento();
			
			for(Seguro seguro : getListaSeguros())  {
				if(seguro.getCliente().getDocumento().equals(documento)) {
					listaSegurosPorCliente.add(seguro);
				}	
			}
		}
		return listaSegurosPorCliente;
	}
	
	//Método que imprime os seguros por cliente na seguradora:
	public void visualizarSegurosCliente() {
		// Caso de nenhum cliente cadastrado
		if(listaClientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado na seguradora " + this.getNome() + ".\n");
			return;
		}
		
		//Caso de clientes cadastrados:
		System.out.println("Lista de seguros por cliente na seguradora " + getNome() + ":");
		for (Cliente cliente : listaClientes) {
			boolean temSeguro = false;
			String documento = cliente.getDocumento();
			
			System.out.println("* " + cliente + ":");
			
			for(Seguro seguro : getListaSeguros())  {
				if(seguro.getCliente().getDocumento().equals(documento)) {
					temSeguro = true;
					System.out.println(seguro);
				}
			}
			if(!temSeguro) {
				System.out.println("Nenhum seguro cadastrado para o cliente " + cliente);
			}
			System.out.println("");
		}
	}

	//Método que retorna uma lista com os sinistros por cliente da seguradora:
	public ArrayList<Sinistro> getSinistrosPorCliente(){
		ArrayList<Sinistro> listaSinistrosPorCliente = new ArrayList<Sinistro>();
		for(Cliente cliente : getListaClientes()) {
			String documento = cliente.getDocumento();
			for(Seguro seguro : getSegurosPorCliente()) {
				for(Sinistro sinistro : seguro.getListaSinistros()) {
					if(seguro.getCliente().getDocumento().equals(documento)) {
						listaSinistrosPorCliente.add(sinistro);
					}
				}
			}
		}
		return listaSinistrosPorCliente;
	}
	
	//Método que imprime os sinistros para cada cliente da seguradora
	public void visualizarSinistrosPorCliente() {
		ArrayList<Sinistro> listaSinistrosPorCliente = getSinistrosPorCliente();
		//Caso em que não há clientes cadastrados:
		if(listaClientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado na seguradora " + this.getNome() + ".");
			return;
		}
		
		//Caso em que nenhum cliente possui sinistro:
		if(listaSinistrosPorCliente.isEmpty()) {
			System.out.println("Nenhum sinistro para clientes da seguradora " + this.getNome() + ".");
			return;
		}
		
		//Caso em que há sinistros:
		System.out.println("Lista de sinistros por cliente da seguradora " + this.getNome() + ":\n");
		for(Cliente clienteSeguradora : getListaClientes()) {
			boolean temSinistro = false;
			String documentoClienteSeguradora = clienteSeguradora.getDocumento();
			System.out.println("* " + clienteSeguradora + ":");
			int i = 0;
			for(Sinistro sinistro : getSinistrosPorCliente()) {
				Seguro seguro = sinistro.getSeguro();
				Cliente clienteSinistro = seguro.getCliente();
				String documentoClienteSinistro = clienteSinistro.getDocumento();
				
				if(documentoClienteSeguradora.equals(documentoClienteSinistro)) {
					temSinistro = true;
					DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					String dataSinistroFormatada = (sinistro.getData()).format(formatterSTR);
					System.out.println("- Sinistro " + ++i + ":");
					System.out.println("   ID: " + sinistro.getID());
					System.out.println("   Data: " + dataSinistroFormatada);
					System.out.println("   Endereço: " + sinistro.getEndereco());
	                System.out.println("   Condutor: " + sinistro.getCondutor() + "\n");	
				}	
			}
			if(!temSinistro) {
				System.out.println("Nenhum sinistro cadastrado para o cliente " + clienteSeguradora + "\n");
			}
		}
	}
	
	//Método que imprime os condutores autorizados por seguro:
	public void visualizarCondutoresPorSeguro() {
		if(getListaSeguros().isEmpty()) {
			System.out.println("Nenhum seguro cadastrado na seguradora " + getNome() + ".");
			return;
		}
		for(Seguro seguro : getListaSeguros()) {
			seguro.visualizarListaCondutores();
		}
	}
	
	//Método que imprime os sinistros por seguro:
	public void visualizarSinistrosPorSeguro() {
		if(getListaSeguros().isEmpty()) {
			System.out.println("Nenhum seguro cadastrado na seguradora " + getNome() + ".");
			return;
		}
		for(Seguro seguro : getListaSeguros()) {
			seguro.visualizarListaSinistros();
		}
	}
	
	//Método que cria uma seguradora a partir do input:
	public static Seguradora criaSeguradora() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o CNPJ, nome, telefone, email e endereço da seguradora, respectivamente:");

		String cnpj = Validacao.recebeDocumentoValido();
		String nome = scanner.nextLine();
		String telefone = scanner.nextLine();
		String email = scanner.nextLine();
		String endereco = scanner.nextLine();

		Seguradora seguradora = new Seguradora(cnpj, nome, telefone, email, endereco);
		return seguradora;
	}

	//Método que cadastra uma seguradora na lista de seguradoras
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

	//Método que remove um sinistro a partir de seu ID. Se o ID for válido, retorna true. Caso contrário, retorna false.
	
	//Método que, a partir de informaçõe do input. exclui um sinistro:
	public boolean excluirSinistro() {
		Scanner scanner = new Scanner(System.in);
		int IdExcluir;
		System.out.println("Digite o ID do sinistro que você deseja excluir:");
		try {
			IdExcluir = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("O ID deve ser um inteiro.");
			return false;
		}
		
		// Encontrando o sinistro a partir do ID e excluindo-o:
		for(int i = 0; i < listaSeguros.size(); i++) {
			Seguro seguro = listaSeguros.get(i);
			for(int j = 0; j < seguro.getListaSinistros().size(); j++) {
				Sinistro sinistro = seguro.getListaSinistros().get(j);
				if(sinistro.getID() == IdExcluir) {
					seguro.getListaSinistros().remove(j);
					return true;
				}
			}
		}
		
		System.out.println("ID inválido. Tente novamente.");
		return false;
	}
	
	//Método que, a partir de um documento válido, encontra o cliente correspondente cadastrado em alguma seguradora.
	public static Cliente encontraCliente(String documento) {
		for(Seguradora seguradora : listaSeguradoras) {
			for(Cliente cliente : seguradora.listaClientes) {
				if(cliente.getDocumento().equals(documento)) {
					return cliente;
				}
			}
		}
		System.out.println("Cliente não encontrado.");
		return null;
	}

	
	//Método que imprime os sinistros por seguradora:
	public static void visualizarSinistrosPorSeg() {
		for(Seguradora seguradora: listaSeguradoras) {
			System.out.println("* Seguradora " + seguradora.getNome() + ":");
			seguradora.visualizarSinistrosPorCliente();
		}
	}

	//Método que retorna uma lista com os veículos associados à seguradora:
	public ArrayList<Veiculo> listarVeiculos() {
		ArrayList<Veiculo> listaVeiculosSeguradora = new ArrayList<Veiculo>();
		for(Cliente cliente : getListaClientes()) {
			listaVeiculosSeguradora.addAll(cliente.getListaVeiculos());
		}
		return listaVeiculosSeguradora;
	}

	// Método que imprime os veículos por cliente da seguradora:
	//Método que imprime os veículos por cliente cadastrado na seguradora:
	public void visualizarVeiculosPorCliente() {
		// Caso de nenhum cliente cadastrado
		if(listaClientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado na seguradora " + this.getNome() + ".\n");
			return;
		}
		
		//Caso de clientes cadastrados:
		for (Cliente cliente : listaClientes) {
			if(cliente.getListaVeiculos().isEmpty()) {
				System.out.println("Nenhum veículo cadastrado para o cliente " + cliente);
				return;
			}
			System.out.println("Veículos do cliente " + cliente + ":");
			cliente.visualizarVeiculos();
			System.out.println("");
		}
	}
	
	// Método que lista todos os veículos cadastrados na seguradora:
	
	//Método que imprime todos os veículos relacionados a cada seguradora
	public static void visualizarVeiculosPorSeg() {
		for(Seguradora seguradora : listaSeguradoras) {
			if(seguradora.listarVeiculos().isEmpty()) {
				System.out.println("Nenhum veículo cadaastrado na seguradora " + seguradora.getNome() + ".");
			}
			System.out.println("* Lista de veículos da seguradora " + seguradora.getNome() + ":");
			ArrayList<Veiculo> listaVeiculos = seguradora.listarVeiculos();
			for(Veiculo veiculo : listaVeiculos) {
				System.out.println("    - " + veiculo);
			}
			System.out.println("");
		}
	}
	
	//Método que calcula a receita total da seguradora:
	
	//Método que calcula a receita total da seguradora:
	//Método que calcula a receita total da seguradora
	public String calcularReceitaSeguradora() {
		double receita = 0;
		DecimalFormat formato = new DecimalFormat("0.00");
		String receitaFormatada;
		
		for(Seguro seguro : getListaSeguros()) {
			seguro.atualizaValorMensal();
			receita += seguro.getValorMensal();
		}
		
		receitaFormatada = formato.format(receita);
		return receitaFormatada;
	}
	
	public void atualizarValores() {
		for(int i = 0; i < listaSeguros.size(); i++) {
			listaSeguros.get(i).atualizaValorMensal();
		}		
	}
	
	//Método que imprime a receita da seguradora:
	public void visualizarReceita() {
		System.out.println("A receita total da seguradora " + this.getNome() + " é R$" + calcularReceitaSeguradora() + ".\n");
	}
	
}
