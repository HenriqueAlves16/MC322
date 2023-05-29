package projeto.lab05;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.StringJoiner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ClientePF extends Cliente {
	private final String CPF;
	private String genero;
	private String educacao;
	private LocalDate dataNascimento;
	private ArrayList<Veiculo> listaVeiculos;
	
	//Metodo construtor:
	public ClientePF(String nome, String endereco, String telefone, String email, String CPF, String genero, String educacao, String dataNascimento) {
		// chama o construtor da superclasse
		super(nome, endereco, telefone, email);
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.CPF = CPF;
		this.genero = genero;
		this.educacao = educacao;
		this.dataNascimento = LocalDate.parse(dataNascimento, formatador);
		this.listaVeiculos = new ArrayList<Veiculo>();
	}

	//Getters e setters:
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	public String getDocumento() {
		return CPF;
	}

	// Métodos:
	// Método que cadastra um veículo na lista de veículos deste ClientePF:
	public boolean cadastrarVeiculo(Veiculo veiculo) {
		ArrayList<Veiculo> listaVeiculos = getListaVeiculos();
		if(listaVeiculos.contains(veiculo)) {
			System.out.println("O veiculo já está cadastrado.");
			return false;
		}
		
		listaVeiculos.add(veiculo);
		setListaVeiculos(listaVeiculos);
		System.out.println("Veiculo cadastrado com sucesso!");

		return true;
	}
	
	//Método que cria um veículo a partir do input e o cadastra na lista de veículos deste ClientePF
	public Veiculo gerarVeiculo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite a placa, marca, modelo e ano do veiculo, respectivamente: ");
		
		String placa = scanner.nextLine();
		String marca = scanner.nextLine();
		String modelo = scanner.nextLine();
		int ano = scanner.nextInt();
		scanner.nextLine();
		Veiculo veiculo = new Veiculo(placa, marca, modelo, ano);
		
		cadastrarVeiculo(veiculo);
		return veiculo;
	}
	
	//Método que remove um veículo cadastrado para este ClientePF a partir de sua placa. Se a placa for válida, retorna true. Caso contrário, retorna false.
	public boolean excluirVeiculo() {
		Scanner scanner = new Scanner(System.in);
		String placaExcluir;
		
		System.out.println("Digite a placa do veículo que você deseja excluir:");
		placaExcluir = scanner.nextLine();
		
		// Encontrando e excluindo o veículo a partir de sua placa:
		for(int j = 0; j < listaVeiculos.size(); j++) {
			String placa = (listaVeiculos.get(j)).getPlaca();
			if(placa.equals(placaExcluir)) {
				listaVeiculos.remove(j);
				setListaVeiculos(listaVeiculos);
				System.out.println("Veículo excluído com sucesso!");
				return true;
			}
		}
		
		System.out.println("O veículo cuja placa é" + placaExcluir + "não está cadastrado para o cliente" + this.getNome());
		return false;
	}
	
	//Método que retorna a idade do ClientePJ
	public int getIdade() {
		int idade;
		LocalDate nasc = getDataNascimento();
		LocalDate now = LocalDate.now();
		idade = (int) nasc.until(now, ChronoUnit.YEARS);
		
		return idade;
	}
	
	//Método que imprime todos os veículos do cliente:
	public void visualizarVeiculos() {
		int i = 0;
		for(Veiculo veiculo : listaVeiculos) {
			System.out.println("* Veículo " + ++i + ": " + veiculo.toString());
		}
	}
}


		