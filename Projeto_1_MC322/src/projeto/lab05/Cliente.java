package projeto.lab05;
import java.util.Scanner;
import java.util.ArrayList;

public abstract class Cliente {
	
	private String nome; 
	private String telefone;
	private String endereco;
	private String email;
	
	//Construtor da classe
	
	public Cliente (String nome, String endereco, String telefone, String email) {
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}
	
	//Getters e setters:
	
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
	
	//toString:
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + ", email=" + email + "]";
	}

	
	// Metodos:

	//Método que cria um cliente a partir do input:
	public static Cliente criaCliente() {
		Scanner scanner = new Scanner(System.in);
		Cliente cliente = null;
		System.out.println("Digite o nome, endereço, data de licença (dd/mm/aaaa) e tipo de cliente ('PF' ou 'PJ'), respectivamente:");

		String nome = scanner.nextLine();
		String endereco = scanner.nextLine();
		String telefone = scanner.nextLine();
		String email = scanner.nextLine();
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

			cliente = new ClientePF(nome, endereco, telefone, email, cpf, genero, educacao, dataNascimento);
		//Caso tipo == "PJ"
		} else {
			System.out.println("Digite o CNPJ, a data de fundação (dd/mm/aaaa) e a quantidade de funcionários da instituição, respectivamente:");

			String cnpj = scanner.nextLine();
			String dataFundacao = scanner.nextLine();
			int qtdFuncionarios = scanner.nextInt();
			scanner.nextLine();
			
			cliente = new ClientePJ(nome, endereco, telefone, email, cnpj, dataFundacao, qtdFuncionarios);
		}
		return cliente;
	}
		
	//Método que, dado um cliente PF ou PJ, retorna seu documento 
	public abstract String getDocumento();
	
	//Método que retorna uma lista de veículos associada ao cliente:
	public abstract ArrayList<Veiculo> getListaVeiculos();
	
	//Método que imprime os veículos do cliente:
	public abstract void visualizarVeiculos();
	
	
	
}
