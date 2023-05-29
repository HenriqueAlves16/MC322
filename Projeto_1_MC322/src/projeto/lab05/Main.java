package projeto.lab05;

class Main {
	public static void main(String[] args) {
		
		// Criando três veículos:
		Veiculo veiculo1 = new Veiculo("ABC0001", "Volkswagen", "Gol", 1990);
		Veiculo veiculo2 = new Veiculo("DEF0002", "Fiat", "Uno",  2015);
		Veiculo veiculo3 = new Veiculo("GHI0003", "Chevrolet", "Ônix", 2020);
		Veiculo veiculo4 = new Veiculo("JKL0004", "Volkswagen", "Fusca",  2000);
		Veiculo veiculo5 = new Veiculo("MNO0005", "Toyota", "Corolla",  2017);
		Veiculo veiculo6 = new Veiculo("PQR0006", "Ford", "Mustang",  2022);

		// Criando clientes:

		Cliente cliente1 = new ClientePF("Abel", "Campinas", "0000-0000", "abel@theweekend.com", "394.790.330-89", "Masculino", "Ensino Médio", "31/10/1987");
		Cliente cliente2 = new ClientePJ("Banco dos Sonhos", "São Paulo", "1111-1111", "banco@sonhos.com", "24.808.514/0001-36", "12/12/2010", 100);
		Cliente cliente3 = new ClientePF("Paulo", "Rio de Janeiro", "3333-3333", "paulo@gmail.com", "658.105.880-77", "Masculino", "Ensino Fundamental", "07/10/1980");

		// Criando uma seguradora:
		Seguradora seguradora1 = new Seguradora("23.831.847/0001-13", "Protege Seguros", "3555-0402", "Porto Alegre", "protegeseguros@gmail.com");
		Seguradora.getListaSeguradoras().add(seguradora1);
		
		// Adicionando veículos:
		cliente1.adicionaVeiculo(veiculo1);
		cliente1.adicionaVeiculo(veiculo2);
		cliente1.adicionaVeiculo(veiculo3);
		
		cliente2.adicionaVeiculo(veiculo4);
		cliente2.adicionaVeiculo(veiculo5);

		cliente3.adicionaVeiculo(veiculo6);

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
		seguradora1.visualizarSinistrosPorCliente();
		Seguradora.visualizarClientesPorSeg();

		// Calculando a receita total da seguradora:
		seguradora1.calcularReceitaSeguradora();
		
		// Chamando o menu interativo. Note que ainda nenhuma seguradoraa foi cadastrada na lista de seguradoras
		Menu menu = new Menu();
	    menu.exibirMenuPrincipal();
	    System.out.println("Programa finalizado.");
	}
}
