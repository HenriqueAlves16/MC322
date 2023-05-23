package projeto.lab05;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Seguro {
	private final int ID;
	private static int geradorID;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private Seguradora seguradora;
	private double valorMensal;
	private ArrayList<Sinistro> listaSinistros;
	private ArrayList<Condutor> listaCondutores;
	
	//Construtor:
	Seguro(String dataInicio, String dataFim, Seguradora seguradora){
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		this.ID = ++geradorID;
		this.dataInicio = LocalDate.parse(dataFim, formatador);
		this.dataFim = LocalDate.parse(dataFim, formatador);
		this.seguradora = seguradora;
		this.valorMensal = calcularValor();
		this.listaSinistros = new ArrayList<Sinistro>();
		this.listaCondutores = new ArrayList<Condutor>();		
	}

	//gets e sets:
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public double getValorMensal() {
		return valorMensal;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public ArrayList<Condutor> getListaCondutores() {
		return listaCondutores;
	}

	public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
		this.listaCondutores = listaCondutores;
	}

	public int getID() {
		return ID;
	}

	//toString():
	@Override
	public String toString() {
		return "Seguro [ID=" + ID + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", seguradora=" + seguradora
				+ ", valorMensal=" + valorMensal + ", listaSinistros=" + listaSinistros + ", listaCondutores="
				+ listaCondutores + "]";
	}
	
	//Métodos:
	
	//Método que inclui um Condutor na lista de condutores. Se ele já está nela retorna false; caso contrário, retorna true:
	public boolean autorizarCondutor(Condutor condutor) {
		ArrayList<Condutor> listaCondutores = getListaCondutores();
		if(listaCondutores.contains(condutor))	return false;
		
		listaCondutores.add(condutor);
		setListaCondutores(listaCondutores);
		atualizaValorMensal();

		return true;
	}
	
	//Método que exclui um Condutor da lista de condutores Se ele não está nela retorna false; caso contrário, retorna true:
	public boolean desautorizarCondutor(Condutor condutor) {
		ArrayList<Condutor> listaCondutores = getListaCondutores();
		if(!listaCondutores.contains(condutor))	return false;
		
		listaCondutores.remove(condutor);
		setListaCondutores(listaCondutores);
		atualizaValorMensal();
		
		return true;
	}
	
	//Método que calcula a quantidade de sinistros de todas as pessoas na lista de condutores:
	public int quantidadeSinistrosCondutores(ArrayList<Condutor> listaCondutores) {
		int qtdSinistros = 0;
		for(Condutor condutor : listaCondutores) {
			qtdSinistros += condutor.getListaSinistros().size();
		}
		return qtdSinistros;
	}
	
	//Método que, a partir de um documento válido, retorna o condutor correspondente.
	public Condutor encontraCondutor(String documento) {
		for(Condutor condutor : listaCondutores) {
			if(condutor.getCPF().equals(documento)) {
				return condutor;
			}
		}
		
		System.out.println("Condutor não encontrado.");
		return null;
	}
	
	//Método que cadastra um sinistro na lista de sinistros deste Seguro. Se o sinistro já está cadastrado, retorna false. Retorna true caso contrário:
	public boolean cadastrarSinistro(Sinistro sinistro) {
		ArrayList<Sinistro> listaSinistros = getListaSinistros();
		if(listaSinistros.contains(sinistro)) {
			System.out.println("O sinistro já está cadastrado.");
			return false;
		}
		
		listaSinistros.add(sinistro);
		setListaSinistros(listaSinistros);
		atualizaValorMensal();
		System.out.println("Sinistro cadastrado com sucesso!");


		return true;
	}
	
	//Método que atualiza o valor mensal do seguro:
	public void atualizaValorMensal() {
		this.valorMensal = calcularValor();
	}
	
	//Método que lê informações para criar um sinistro e o cadastra na lista de sinistros:
	public abstract boolean gerarSinistro();
	
	//Método que calcula o valor do seguro:
	public abstract double calcularValor();
	
}
