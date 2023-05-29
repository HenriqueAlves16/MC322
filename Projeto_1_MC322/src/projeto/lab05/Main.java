package projeto.lab05;
import java.util.ArrayList;

class Main {
	public static void main(String[] args) {
		
		// Criando três veículos:
		Veiculo veiculo1 = new Veiculo("ABC0001", "Volkswagen", "Gol", 1990);
		Veiculo veiculo2 = new Veiculo("DEF0002", "Fiat", "Uno",  2015);
		Veiculo veiculo3 = new Veiculo("GHI0003", "Chevrolet", "Ônix", 2020);
		Veiculo veiculo4 = new Veiculo("JKL0004", "Volkswagen", "Fusca",  2000);
		Veiculo veiculo5 = new Veiculo("MNO0005", "Toyota", "Corolla",  2017);
		Veiculo veiculo6 = new Veiculo("PQR0006", "Ford", "Mustang",  2022);
		Veiculo veiculo7 = new Veiculo("ABC1234", "Chevrolet", "Camaro", 2023);
		Veiculo veiculo8 = new Veiculo("DEF5678", "Toyota", "Corolla", 2022);
		Veiculo veiculo9 = new Veiculo("GHI9012", "Honda", "Civic", 2023);
		Veiculo veiculo10 = new Veiculo("JKL3456", "Volkswagen", "Golf", 2022);
		Veiculo veiculo11= new Veiculo("MNO7890", "BMW", "X5", 2023);

		// Criando clientes:

		ClientePJ cliente1 = new ClientePJ("Banco dos Sonhos", "São Paulo", "1111-1111", "banco@sonhos.com", "24.808.514/0001-36", "12/12/2010", 100);
		ClientePF cliente2 = new ClientePF("Abel", "Campinas", "0000-0000", "abel@theweekend.com", "394.790.330-89", "Masculino", "Ensino Médio", "31/10/1987");
		ClientePF cliente3 = new ClientePF("Paulo", "Rio de Janeiro", "3333-3333", "paulo@gmail.com", "658.105.880-77", "Masculino", "Ensino Fundamental", "07/10/1980");
		System.out.println(cliente1);
		System.out.println(cliente2);
		System.out.println(cliente3);
	
		// Criando uma seguradora:
		Seguradora seguradora1 = new Seguradora("23.831.847/0001-13", "Protege Seguros", "3555-0402", "Porto Alegre", "protegeseguros@gmail.com");
		Seguradora.getListaSeguradoras().add(seguradora1);
		System.out.println(seguradora1);
		
		// Cadastrando clientes na seguradora:
		seguradora1.cadastrarCliente(cliente1);
		seguradora1.cadastrarCliente(cliente2);
		seguradora1.cadastrarCliente(cliente3);
		System.out.println("");
		
		// Adicionando veículos para os ClientePF:		
		cliente2.cadastrarVeiculo(veiculo1);
		cliente2.cadastrarVeiculo(veiculo2);

		cliente3.cadastrarVeiculo(veiculo3);

		//Criando frotas:
		ArrayList<Veiculo> listaVeiculosFrota2 = new ArrayList<Veiculo>();
		ArrayList<Veiculo> listaVeiculosFrota3 = new ArrayList<Veiculo>();
		listaVeiculosFrota2.add(veiculo7);
		listaVeiculosFrota2.add(veiculo8);
		listaVeiculosFrota3.add(veiculo9);
		listaVeiculosFrota3.add(veiculo10);
		listaVeiculosFrota3.add(veiculo11);

		Frota frota1 = new Frota("frota1", veiculo6);
		Frota frota2 = new Frota("frota2", listaVeiculosFrota2);
		Frota frota3 = new Frota("frota3", listaVeiculosFrota3);
		System.out.println(frota1);
		System.out.println(frota2);
		System.out.println(frota3);

		//Criando seguros para os clientes:
		SeguroPJ seguro1 = new SeguroPJ("10/02/2015", "14/09/2015", seguradora1, frota2, cliente1);
		SeguroPJ seguro2 = new SeguroPJ("18/06/2016", "14/09/2018", seguradora1, frota3, cliente1);
		SeguroPF seguro3 = new SeguroPF("29/05/2023", "29/12/2024", seguradora1, veiculo1, cliente2);
		SeguroPF seguro4 = new SeguroPF("31/01/2020", "31/12/2025", seguradora1, veiculo3, cliente3);

		
		// Gerando sinistro:
		

		/*// Chamando os métodos listarCliente(), visualizarSinistro(), listarSinistros()
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
	    */
	}
}
