package projeto;

public class Sinistro {
	private String data;
	private String endereco;
	private int id;
	
	//Construtor da classe
	public Sinistro(String data, String endereco, int id) {
		this.data = data;
		this.endereco = endereco;
		this.id = id;
	}

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
	
	
	
	
}
