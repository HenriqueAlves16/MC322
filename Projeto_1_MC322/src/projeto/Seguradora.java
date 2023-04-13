package projeto;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringJoiner;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private LinkedList<Sinistro> listaSinistros;
	private ArrayList<Cliente> listaClientes;

	
	// Construtor
	public Seguradora(String nome, String telefone, String email, String endereco) {
	this.nome = nome;
	this.telefone = telefone;
	this.email = email;
	this.endereco = endereco;
	this.listaSinistros = new LinkedList<>();
	this.listaClientes = new ArrayList<>();
	}
	
	// Getters e setters
	public String getNome()	{
		return nome;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone (String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public LinkedList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(LinkedList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	//toString
	@Override
	public String toString() {
		StringJoiner str = new StringJoiner("\n");
		str.add("Classe = Seguradora");
		str.add("nome = " + nome);
		str.add("telefone = " + telefone);
		str.add("email = " + email);
		str.add("endereco = " + endereco);
		str.add("listaClientes:");
		for(Cliente cliente : listaClientes) {
			str.add(" ".repeat(4) + cliente.toString());
		}
		for(Sinistro sinistro : listaSinistros) {
			str.add(" ".repeat(4) + sinistro.toString());
		}
		return str.toString();
	}
	
	//Metodos
	public boolean cadastrarCliente(Cliente cliente) {
		if(listaClientes.contains(cliente)) return false;
		listaClientes.add(cliente);
		return true;
	}
	
	//Método que remove um cliente da lista de clientes. 
	//Se o nome do cliente dado na entrada for válido, retorna true; caso contrário, retorna false.
	public boolean removerCliente(String cliente) {
		for(int i = 0; i < listaClientes.size(); i++) {
			if(((listaClientes.get(i)).getNome()).equals(cliente)) {
				listaClientes.remove(i);
				return true;
			}
		}
		return false;
	}
	
	//Método que, dado um tipo de cliente "PF" ou "PJ" retorna uma lista com os clientes do tipo da entrada
	public ArrayList<Cliente> listarClientes(String tipoCliente){
		ArrayList<Cliente> listaTipo = new ArrayList<>();
		for(Cliente cliente : listaClientes) {
			if(tipoCliente.equals(cliente.getTipo())) {
				listaTipo.add(cliente);
			}
		}
		return listaTipo;
	}
	
	//Método que imprime todos os clientes de um dado tipo "PF" ou "PJ"
	public void visualizarClientes(String tipoCliente) {
		int i = 1;
		System.out.println("LISTA DE CLIENTES DO TIPO " + tipoCliente + ":");
		for(Cliente cliente : listaClientes) {
			if(tipoCliente.equals(cliente.getTipo())) {
				System.out.println("* Cliente " + i++);
				System.out.println(cliente + "\n");
			}			
		}
	}
	
	// Método que gera um sinistro, recebendo como parâmetros os objetos que compõem o sinistro
	public Sinistro gerarSinistro(String data, String endereco, Veiculo veiculo, Cliente cliente) {
		Sinistro novoSinistro = new Sinistro(data, endereco, this, veiculo, cliente);
		listaSinistros.add(novoSinistro);
		return novoSinistro;
	}

	// Método que imprime todos os sinistros de um dado cliente
	public boolean visualizarSinistro(String nomeCliente) {
		int i = 0;
		for(Sinistro sinistro : listaSinistros) {
			if(nomeCliente.equals((sinistro.getCliente()).getNome())) {
				i = 1;
				System.out.println(sinistro + "\n");
			}
		}
		if(i == 0) {
			System.out.println("Nenhum sinistro associado a " + nomeCliente);
			return false;
		}
		return true;
	}
	
	//Método que, dado um tipo de cliente "PF" ou "PJ" retorna uma lista com todos os sinistros do tipo de cliente da entrada
	public ArrayList<Sinistro> listarSinistros(String tipoCliente){
		ArrayList<Sinistro> listaTipo = new ArrayList<>();
		
		for(Sinistro sinistro : listaSinistros) {
			if(tipoCliente.equals((sinistro.getCliente()).getTipo())) {
				listaTipo.add(sinistro);
			}
		}
		return listaTipo;
	}
}
