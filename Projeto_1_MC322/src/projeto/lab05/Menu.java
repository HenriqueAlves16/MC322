package projeto.lab05;

import java.util.Scanner;

public class Menu {
	
	Seguradora seguradoraAtiva = null;
	
	//Verifica se alguma seguradora está cadastrada. Se não houver nenhuma, cadastra uma.
	public void verificaSeguradoraCadastrada() {
		if(Seguradora.getListaSeguradoras().isEmpty()) {
			System.out.println("Nenhuma seguradora cadastrada. Cadastre uma seguradora para começar.");
			Seguradora.cadastrarSeguradora();
		}
	}
	
	// Definindo uma seguradora ativa:
	public Seguradora definirSeguradoraAtiva() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o nome da seguradora com a qual você deseja fazer as operações: ");
		String nomeSeguradora = scanner.nextLine();
			
		for(Seguradora seguradora : Seguradora.getListaSeguradoras()) {
			if((seguradora.getNome()).equals(nomeSeguradora)) {
				System.out.println("Seguradora " + nomeSeguradora + " definida como ativa com sucesso!");
				return seguradora;
			}
		}
		
		System.out.println("Não há nenhuma seguradora cadastrada com este nome.");
		return null;
	}
	
	// Menu principal:
	public void exibirMenuPrincipal() {
		MenuOperacoes operacao;
		int c = -1;
		
		//Verifica se existe alguma seguradora cadastrada para fazer as operações:
		verificaSeguradoraCadastrada();
		
		//Verifica se alguma seguradora cadastrada está definida como ativa para fazer as operações:
		while (seguradoraAtiva == null) {
			seguradoraAtiva = definirSeguradoraAtiva();
		}
		
		//Imprime e executa o menu:
		do {
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("O que você deseja fazer?");
			System.out.println("1 - Cadastro");
			System.out.println("2 - Listar");
			System.out.println("3 - Excluir");
			System.out.println("4 - Gerar sinistro");
			System.out.println("5 - Transferir seguro");
			System.out.println("6 - Calcular a receita da seguradora");
			System.out.println("7 - Alterar a seguradora ativa");
			System.out.println("0 - Sair");
	
			c = scanner.nextInt();
			scanner.nextLine();
			operacao = MenuOperacoes.busca(c);
			principal(operacao);
		} while(operacao != MenuOperacoes.SAIR);
	}

	public void principal(MenuOperacoes operacao) {
		if(operacao == null) {
			System.out.println("Opção inválida!");
			return;
		}
		
		switch (operacao) {
			case CADASTRAR:
				exibirSubMenuCadastro();
				break;
			case LISTAR:
				exibirSubMenuListar();
				break;
			case EXCLUIR:
				exibirSubMenuExcluir();
				break;
			case GERAR_SINISTRO:
				seguradoraAtiva.gerarSinistro();
				break;
			case TRANSFERIR_SEGURO:
				seguradoraAtiva.transferirSeguro();
				break;
			case CALCULAR_RECEITA_SEGURADORA:
				seguradoraAtiva.calcularReceitaSeguradora();
				break;
			case ALTERAR_SEGURADORA_ATIVA:
				seguradoraAtiva = definirSeguradoraAtiva();
			case SAIR:
				break;
			default:
				System.out.println("Opção inválida!");
		}
	}
	
	// SUBMENU CADASTRO:
	public void exibirSubMenuCadastro() {
		MenuOperacoes operacao;
		int d;
		
		do {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Qual tipo de cadastro você deseja realizar?");
			System.out.println("1 - Cliente");
			System.out.println("2 - Veículo");
			System.out.println("3 - Seguradora");
			System.out.println("4 - Voltar");
	
			d = scanner.nextInt();
			scanner.nextLine();
			d += 10;
			
			operacao = MenuOperacoes.busca(d);
			
			cadastrar(operacao);
		} while(operacao != MenuOperacoes.VOLTAR_CADASTRO);
	}

	public void cadastrar(MenuOperacoes operacao) {
		if(operacao == null) {
			System.out.println("Opção inválida!");
			return;
		}
		
		switch (operacao) {
			case CADASTRAR_CLIENTE:
				seguradoraAtiva.cadastrarCliente(Cliente.criaCliente());
				break;
			case CADASTRAR_VEICULO:
				seguradoraAtiva.cadastrarVeiculo();
				break;
			case CADASTRAR_SEGURADORA:
				Seguradora.cadastrarSeguradora();
				break;
			case VOLTAR_CADASTRO:
				break;
			default:
				System.out.println("Opção inválida!");
		}
	}

	//SUBMENU LISTAR
	public void exibirSubMenuListar() {
		int e;
		MenuOperacoes operacao;
		
		do {
			Scanner scanner = new Scanner(System.in);
	
			System.out.println("O que você deseja listar?");
			System.out.println("1 - Clientes por seguradora");
			System.out.println("2 - Sinistros por seguradora");
			System.out.println("3 - Sinistros por cliente");
			System.out.println("4 - Veículos por cliente");
			System.out.println("5 - Veículos por seguradora");
			System.out.println("6 - Voltar");
	
			e = scanner.nextInt();
			scanner.nextLine();
			e += 20;
			
			operacao = MenuOperacoes.busca(e);
					
			listar(operacao);
			
		} while(operacao != MenuOperacoes.VOLTAR_LISTAR); 
	}

	public void listar(MenuOperacoes operacao) {
		if(operacao == null) {
			System.out.println("Opção inválida!");
			return;
		}
		
		switch (operacao) {
		case LISTAR_CLIENTE_POR_SEG:
			Seguradora.visualizarClientesPorSeg();
			break;
		case LISTAR_SINISTROS_POR_SEG:
			Seguradora.visualizarSinistrosPorSeg();
			break;
		case LISTAR_SINISTROS_POR_CLIENTE:	
			seguradoraAtiva.visualizarSinistrosPorCliente();
			break;
		case LISTAR_VEICULOS_POR_CLIENTE:
			seguradoraAtiva.listaVeiculosCliente();
			break;
		case LISTAR_VEICULOS_POR_SEG:
			Seguradora.listaVeiculosPorSeg();
			
			break;
		case VOLTAR_LISTAR:
			break;
		default:
			System.out.println("Opção inválida!");
		}
	}

	//SUBMENU EXCLUIR:
	public void exibirSubMenuExcluir() {
		int f;
		MenuOperacoes operacao = null;
		
		do {
			Scanner scanner = new Scanner(System.in);
	
			System.out.println("O que você deseja excluir?");
			System.out.println("1 - Cliente");
			System.out.println("2 - Veículo");
			System.out.println("3 - Sinistro");
			System.out.println("4 - Voltar");
	
			f = scanner.nextInt();
			scanner.nextLine();
			f += 30;
			
			operacao = MenuOperacoes.busca(f);
			
			excluir(operacao);
			

		} while(operacao != MenuOperacoes.VOLTAR_EXCLUIR);
	}

	public void excluir(MenuOperacoes operacao) {
		if(operacao == null) {
			System.out.println("Opção inválida!");
			return;
		}
		
		switch (operacao) {
		case EXCLUIR_CLIENTE:
			seguradoraAtiva.excluirCliente();
			break;
		case EXCLUIR_VEICULO:
			seguradoraAtiva.excluirVeiculo();
			break;
		case EXCLUIR_SINISTRO:
			seguradoraAtiva.excluirSinistro();
			break;
		case VOLTAR_EXCLUIR:
			break;
		default:
			System.out.println("Opção inválida!");
		}
	}
	
}
