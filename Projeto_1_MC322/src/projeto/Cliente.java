package projeto;

public class Cliente {
	
	private String nome; 
	private String cpf;
	private String dataNascimento;
	private String endereco;
	private int idade;
	
	
	//Construtor da classe
	
	public Cliente (String nome, String cpf, String dataNascimento, String endereco, int idade) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.idade = idade;
	}
	
	//Getters e setters
	public String getNome()	{
		return nome;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	//toString:
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", endereco="
				+ endereco + ", idade=" + idade + "]";
	}
	
	//Validando o CPF
	public boolean validarCPF() {
		int num, peso = 10, sm = 0, r, j = 1;
		char dig10, dig11;
		
		//Remove os caracteres nao numericos
		cpf = cpf.replaceAll("[^0-9]","");
		
		//Verifica se o cpf tem 11 caracteres
		if(cpf.length() != 11)	return false;
		
		// Verifica se todos os digitos sao iguais
		while(true) {
			if(cpf.charAt(j) != cpf.charAt(j - 1)) {
				break;
			}
			if(j == 10)	return false; 
			j++;
		}

    // Calculo do 1o. Digito Verificador
		
    // converte o i-esimo caractere do CPF em um numero:
    // por exemplo, transforma o caractere '0' no inteiro 0
    // (48 eh a posicao de '0' na tabela ASCII)
		for (int i = 0; i < 9; i++) {
			num = (int)(cpf.charAt(i) - 48);
			sm += (num * peso);
			peso -= 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
           dig10 = '0';
        else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
		
    // Calculo do 2o. Digito Verificador
		sm = 0;
		peso = 11;
		
		for(int i = 0; i < 10; i++) {
			num = (int)cpf.charAt(i) - 48;
			sm += num * peso;
			peso -= 1;
		}
		
		r = 11 - (sm % 11);
		

		if(r == 10 || r == 11) {
			dig11 = '0';
		}	else	{
			dig11 = (char)(r + 48);
		}
		
		// Verifica se os digitos calculados conferem com os digitos informados.		
		if(dig10 == cpf.charAt(9) && dig11 == cpf.charAt(10))
			return true;
		else return false;
	}

	
}
