package projeto.lab05;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Sinistro {
	private LocalDate data;
	private String endereco;
	private Condutor condutor;
	private Seguro seguro;
	final int ID;
	private static int n = 1;	
	
	//Construtor da classe	
	public Sinistro(String data, String endereco, Condutor condutor, Seguro seguro) {
		DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.data = LocalDate.parse(data, formatterSTR);
		this.endereco = endereco;
		this.condutor = condutor;
		this.seguro = seguro;
		this.ID = n;
		n++;
	}
		
	//Getters e setters:
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public int getID() {
		return ID;
	}
	
	//toString
	@Override
	public String toString() {
		return "ID do sinistro: " + getID() + ", Condutor: " + getCondutor() + ", Seguro: " + getSeguro();
	}

	
	
}
