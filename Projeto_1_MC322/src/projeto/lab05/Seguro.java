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
	private ArrayList<Sinistro> listaSinistros;
	private ArrayList<Condutor> listaCondutores;
	
	//Construtor:
	Seguro(String dataInicio, String dataFim, Seguradora seguradora){
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		this.ID = ++geradorID;
		this.dataInicio = LocalDate.parse(dataFim, formatador);
		this.dataFim = LocalDate.parse(dataFim, formatador);
		this.seguradora = seguradora;
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
	
	public abstract double getValorMensal();

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
	
	//Método que imprime a lista de condutores do seguro:
	public void visualizarListaCondutores() {
		int i = 0;
		if(getListaCondutores().size() == 0) {
			System.out.println("Nenhum condutor autorizado para o seguro de ID " + getID() + ".");
			return;
		}
		
		System.out.println("Lista de condutores do seguro de ID " + getID() + ":");
		
		for(Condutor condutor : getListaCondutores()) {
			System.out.println("Condutor " + ++i + ": " + condutor);
		}
		System.out.println("");
	}	
	
	//Método que imprime a lista de sinistros do seguro:
		public void visualizarListaSinistros() {
			int i = 0;
			if(getListaSinistros().size() == 0) {
				System.out.println("Nenhum sinistro para o seguro de ID " + getID() + ".");
				return;
			}
			
			System.out.println("Lista de sinistros do seguro de ID " + getID() + ":");
			
			for(Sinistro sinistro : getListaSinistros()) {
				System.out.println("Sinistro " + ++i + ": " + sinistro);
			}
			System.out.println("");
		}	
	
	//Método que calcula a quantidade de sinistros de todas as pessoas na lista de condutores:
	public int quantidadeSinistrosCondutores(ArrayList<Condutor> listaCondutores) {
		int qtdSinistros = 0;
		for(Condutor cdt : this.getListaCondutores()) {
			for(Seguro seguro : getSeguradora().getListaSeguros()) {
				for(Condutor condutor : seguro.getListaCondutores()) {
					if(cdt.getCPF().equals(condutor.getCPF())) {
						qtdSinistros += condutor.getListaSinistros().size();
					}
				}
			}
		}
		return qtdSinistros;
	}

	//Método que calcula a quantidade de sinistros de um cliente na seguradora:
	public int quantidadeSinistrosCliente(Cliente cliente) {
		int qtdSinistros = 0;
		for(Seguro seguro : getSeguradora().getListaSeguros()) {
			if(seguro.getCliente().getDocumento().equals(cliente.getDocumento())) {
				qtdSinistros += seguro.getListaSinistros().size();
			}
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
			return false;
		}
		listaSinistros.add(sinistro);
		setListaSinistros(listaSinistros);
		atualizaValorMensal();
		return true;
	}
	
	//Método que atualiza o valor mensal do seguro:
	public abstract void atualizaValorMensal();
	
	//Método que lê informações para criar um sinistro e o cadastra na lista de sinistros:
	public abstract boolean gerarSinistro();
	
	//Método que calcula o valor do seguro:
	public abstract double calcularValor();
	
	//Método abstrato que retorna o cliente do seguro:
	public abstract Cliente getCliente();
	
}
