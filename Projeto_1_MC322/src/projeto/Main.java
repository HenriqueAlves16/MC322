package projeto;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new File("input/input.txt"));
			
			//Criando três veículos:
			Veiculo veiculo1 = criaVeiculo(scanner);
			Veiculo veiculo2 = criaVeiculo(scanner);
			Veiculo veiculo3 = criaVeiculo(scanner);
			
			//Criando clientes:
			//System.out.println("Crie um cliente do tipo 'PF' e outro do tipo 'PJ'");
			Cliente cliente1 = criaCliente(scanner);
			Cliente cliente2 = criaCliente(scanner);
			Cliente cliente3 = new ClientePF("Paulo", "Campinas", "07/12/2012", "PF", "Ensino Fundamental", "Masculino", "Baixa", "0", "07/10/1980");
			
			//Validação dos códigos:
			System.out.printf("O número do documento do cliente 1 é válido: %b\n", cliente1.validarDocumento());
			System.out.printf("O número do documento do cliente 2 é válido: %b\n\n", cliente2.validarDocumento());
		
			//Adicionando veículos:
			cliente1.adicionaVeiculo(veiculo1);
			cliente1.adicionaVeiculo(veiculo2);
			cliente1.adicionaVeiculo(veiculo3);
			cliente2.adicionaVeiculo(veiculo3);
			
			
			//Criando uma seguradora:
			Seguradora seguradora1 = criaSeguradora(scanner);
			
			//Cadastrando clientes na seguradora:
			seguradora1.cadastrarCliente(cliente1);
			seguradora1.cadastrarCliente(cliente2);
			seguradora1.cadastrarCliente(cliente3);
			
			//Removendo clientes da seguradora:
			seguradora1.visualizarClientes("PF");
			seguradora1.removerCliente("Paulo");
			seguradora1.visualizarClientes("PF");
			
			//Gerando sinistro:
			Sinistro sinistro1 = seguradora1.gerarSinistro("28/02/2022", "Limeira", veiculo3, cliente2);
			
			//Chamando os métodos listarCliente, visualizarSinistro e listarSinistros:
			seguradora1.listarClientes("PF");
			seguradora1.visualizarSinistro(cliente2.getNome());
			seguradora1.listarSinistros("PF");
			seguradora1.listarSinistros("PJ");
			seguradora1.visualizarClientes("PF");
			seguradora1.visualizarClientes("PJ");
			
			//Chamando os métodos toString de cada classe:
			System.out.println(seguradora1.toString() + "\n");
			System.out.println(cliente1.toString() + "\n");
			System.out.println(cliente2.toString() + "\n");
			System.out.println(veiculo1.toString() + "\n");
			System.out.println(sinistro1.toString() + "\n");
			//System.out.println(sinistro.toString());

			//Método que faz leitura de dados usando System.in:
			visualizaSeguradora(seguradora1);
			
			
		}	catch(FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
		}
		
	}
	
	public static Veiculo criaVeiculo(Scanner scanner) {
		//System.out.println("Digite a placa, marca, modelo e ano do veiculo, respectivamente:");
		
		String placa = scanner.nextLine();
		String marca = scanner.nextLine();
		String modelo = scanner.nextLine();
		int ano = scanner.nextInt();
		scanner.nextLine();
		
		Veiculo veiculo = new Veiculo(placa, marca, modelo, ano);
		return veiculo;
	}
	
	public static Cliente criaCliente(Scanner scanner) {
		//System.out.println("Digite o nome, endereço, data de licenca (aaaa-mm-dd) e tipo de cliente ('PF' ou 'PJ'), respectivamente.");
		String nome = scanner.nextLine();
		String endereco = scanner.nextLine();
		String dataLicenca = scanner.nextLine();
		String tipo = scanner.nextLine();
		
		if(tipo.equals("PF")) {
			//System.out.println("Digite a escolaridade, genero, classe economica, CPF e data de nascimento (aaaa-mm-dd) do cliente, respectivamente.");
			
			String educacao = scanner.nextLine();
			String genero = scanner.nextLine();
			String classeEconomica = scanner.nextLine();
			String cpf = scanner.nextLine();
			String dataNascimento = scanner.nextLine();
			
			ClientePF cliente = new ClientePF(nome, endereco, dataLicenca, tipo, educacao, genero, classeEconomica, cpf, dataNascimento);
			return cliente;
		}	else	{
			//System.out.println("Digite o CNPJ e data de fundacao (aaaa-mm-dd) da instituicao, respectivamente.");
			
			String cnpj = scanner.nextLine();
			String dataFundacao = scanner.nextLine();
			
			ClientePJ cliente = new ClientePJ(nome, endereco, dataLicenca, tipo, cnpj, dataFundacao);
			return cliente;
		}
	}
	
	public static Seguradora criaSeguradora(Scanner scanner) {
		//System.out.println("Digite o nome, telefone, email e endereco da seguradora, respectivamente.");
		
		String nome = scanner.nextLine();
		String telefone = scanner.nextLine();
		String email = scanner.nextLine();
		String endereco = scanner.nextLine();
		
		Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
		return seguradora;
	}
	
	public static void visualizaSeguradora(Seguradora seguradora) {
		System.out.println("Digite 'C' para visualizar os clientes.");
		System.out.println("Digite 'S' para visualizar os sinistros.");
		System.out.println("Digite 'E' para visualizar o email da seguradora.");
		System.out.println("Digite 'T' para visualizar o telefone da seguradora.");
		System.out.println("Digite 'F' para finalizar o modo visualização");
		
		Scanner scanner = new Scanner(System.in);
		char c = '\0';
		do {
			c = scanner.next().charAt(0);
			if(c == 'C') {
				seguradora.visualizarClientes("PF");
				seguradora.visualizarClientes("PJ");
			}	else if(c == 'S')	{
				System.out.println("Digite o nome do cliente cujos sinistros serão visualizados:");
				
				scanner.nextLine();
				String nome = scanner.nextLine();
				seguradora.visualizarSinistro(nome);
				

			}	else if(c == 'E')	{
				System.out.printf("O email da seguradora %s é: %s\n", seguradora.getNome(), seguradora.getEmail());				
			
			}	else if(c == 'T')	{
				System.out.printf("O telefone da seguradora %s é: %s\n", seguradora.getNome(), seguradora.getTelefone());
			}	
		}	while(c != 'F');
		scanner.close();
	}
	
	
	
	
	
	
}