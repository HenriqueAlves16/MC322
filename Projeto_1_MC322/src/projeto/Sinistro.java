package projeto;

public class Sinistro {
	private String data;
	private String endereco;
	private int id;
	private static int n = 1;
	
	//Construtor da classe
	
	public Sinistro(String data, String endereco) {
		this.data = data;
		this.endereco = endereco;
		this.id = n;
		n++;
	}
		
	//Getters e setters
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//toString
	public String toString() {
		return "Sinistro [data=" + data + ", endereco=" + endereco + ", id=" + id + "]";
	}
	

	
	
	
}
