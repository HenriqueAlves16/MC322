package projeto.lab05;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.time.LocalDate;

public class Condutor {
	private final String CPF;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private LocalDate dataNascimento;
	private ArrayList<Sinistro> listaSinistros;
	
	//Construtor:
	Condutor(String CPF, String nome, String telefone, String endereco, String email, String dataNascimento){
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		this.CPF = CPF;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.dataNascimento = LocalDate.parse(dataNascimento, formatador);
		this.listaSinistros = new ArrayList<Sinistro>();
	}
	
	//gets e sets:

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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public String getCPF() {
		return CPF;
	}

	//toString():
	@Override
	public String toString() {
		return "Condutor [CPF=" + CPF + ", nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco
				+ ", email=" + email + ", dataNascimento=" + dataNascimento + ", listaSinistros=" + listaSinistros
				+ "]";
	}
	
	//Métodos:
	
	//Método que adiciona um sinistro na lista de sinistros:
	public boolean adicionarSinistro() {
		return true;
	}
	
}
