package projeto.lab05;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;
import java.util.ArrayList;


public class ClientePJ extends Cliente {
	private final String CNPJ;
	private LocalDate dataFundacao;
	private ArrayList<Frota> listaFrota;
	
	//Metodo construtor:
	public ClientePJ(String nome, String endereco, String telefone, String email, String CNPJ, String dataFundacao) {
		// chama o construtor da superclasse
		super(nome, endereco, telefone, email);
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.CNPJ = CNPJ;
		this.dataFundacao = LocalDate.parse(dataFundacao, formatador);
	}

	//Getters e setters:
	
	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public ArrayList<Frota> getListaFrota() {
		return listaFrota;
	}

	public void setListaFrota(ArrayList<Frota> listaFrota) {
		this.listaFrota = listaFrota;
	}

	public String getCNPJ() {
		return CNPJ;
	}


	//toString:
	@Override
	public String toString() {
		return "ClientePJ [CNPJ=" + CNPJ + ", dataFundacao=" + dataFundacao + ", listaFrota=" + listaFrota + "]";
	}	
	
	// Métodos:
	public boolean cadastrarFrota() {
		return true;
	}
	
	public boolean atualizarFrota() {
		return true;
	}
	
	public boolean listarVeiculosPorFrota() {
		return true;
	}
}


		