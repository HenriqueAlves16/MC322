package projeto.lab04;

class Main {
	public static void main(String[] args) {
		
		// Criando três veículos:
		/*Veiculo veiculo1 = new Veiculo("ABC0001", "Volkswagen", "Gol", 1990);
		Veiculo veiculo2 = new Veiculo("DEF0002", "Fiat", "Uno",  2015);
		Veiculo veiculo3 = new Veiculo("GHI000", "Chevrolet", "Ônix", 2020);

		Veiculo veiculo1 = Veiculo.criaVeiculo();
		Veiculo veiculo2 = Veiculo.criaVeiculo();
		Veiculo veiculo3 = Veiculo.criaVeiculo();

		// Criando clientes:
		Cliente cliente1 = Cliente.criaCliente();
		Cliente cliente2 = Cliente.criaCliente();
		
		Cliente cliente1 = new ClientePF("Abel", "Campinas", "26/12/2015", "PF", "Ensino Médio", "Masculino", "Alta", "394.790.330-89", "31/10/1987");
		Cliente cliente2 = new ClientePJ("Banco dos Sonhos", "São Paulo", "25/05/2002", "PJ", "24.808.514/0001-36", "12/12/2010", 100);
		Cliente cliente3 = new ClientePF("Paulo", "Campinas", "07/12/2012", "PF", "Ensino Fundamental", "Masculino","Baixa", "658.105.880-77", "07/10/1980");

		// Criando uma seguradora:
		//Seguradora seguradora1 = Seguradora.criaSeguradora();
		Seguradora seguradora1 = new Seguradora("Protege Seguros", "3555-0402", "protegeseguros@gmail.com", "Porto Alegre");
				
		// Adicionando veículos:
		cliente1.adicionaVeiculo(veiculo1);
		cliente1.adicionaVeiculo(veiculo2);
		cliente1.adicionaVeiculo(veiculo3);
		
		cliente2.adicionaVeiculo(veiculo2);
		cliente2.adicionaVeiculo(veiculo3);

		cliente3.adicionaVeiculo(veiculo3);

		// Cadastrando clientes na seguradora:
		seguradora1.cadastrarCliente(cliente1);
		seguradora1.cadastrarCliente(cliente2);
		seguradora1.cadastrarCliente(cliente3);
		System.out.println("");

		// Gerando sinistro:
		seguradora1.gerarSinistro();
		seguradora1.gerarSinistro();
		System.out.println("");

		// Chamando os métodos listarCliente(), visualizarSinistro(), listarSinistros()
		// e calcularReceita():
		seguradora1.listarClientes("PF");
		seguradora1.listarSinistros("PF");
		seguradora1.listarSinistros("PJ");
		seguradora1.visualizarSinistrosCliente();
		seguradora1.visualizarClientes();
		seguradora1.visualizarClientes();

		// Calculando a receita total da seguradora:
		System.out.println("A receita total da seguradora " + seguradora1.getNome() + " é " + seguradora1.calcularReceitaSeguradora());*/
		
		// Chamando o menu interativo
		Menu menu = new Menu();
	    menu.exibirMenuPrincipal();
	    System.out.println("Programa finalizado.");
		
	}
	
}
