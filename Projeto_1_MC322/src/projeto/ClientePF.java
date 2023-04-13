package projeto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class ClientePF extends Cliente {
	private String educacao;
	private String genero;
	private String classeEconomica;
	final String CPF;
	private LocalDate dataNascimento;
	
	//Metodo construtor:
	public ClientePF(String nome, String endereco, String dataLicenca, String tipo, String educacao, 
			String genero, String classeEconomica, String cpf, String dataNascimento) {
		// chama o construtor da superclasse
		super(nome, endereco, dataLicenca, tipo);
		
		DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
		this.CPF = cpf;
		this.dataNascimento = LocalDate.parse(dataNascimento, formatterSTR);
	}

	//Getters e setters:
	public String getcpf() {
		return CPF;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = LocalDate.parse(dataNascimento);
	}
	
	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getClasseEconomica() {
		return classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}
	
	//toString:
	@Override
	public String toString() {
		StringJoiner str = new StringJoiner("\n");
		DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataLicencaFormatada = (getDataLicenca()).format(formatterSTR);
		String dataNascimentoFormatada = (dataNascimento).format(formatterSTR);
		str.add("Classe = ClientePF");
		str.add("nome = " + getNome());
		str.add("endereco = " + getEndereco());
		str.add("dataLicenca = " + dataLicencaFormatada);
		str.add("tipo = " + getTipo());
		str.add("educacao = " + educacao);
		str.add("genero = " + genero);
		str.add("classeEconomica = " + classeEconomica);
		str.add("CPF = " + CPF);
		str.add("dataNascimento = " + dataNascimentoFormatada);
		str.add("listaVeiculos :");
		for(Veiculo veiculo : getListaVeiculos()) {
			str.add(" ".repeat(4) + veiculo.toString());
		}
		return str.toString();
	}
		
	//Validando o cpf
	public boolean validarCPF() {
		int num, peso = 10, sm = 0, r, j = 1;
		char dig10, dig11;
		String cpf = CPF;
			
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
		
    // converte o i-esimo caractere do cpf em um numero:
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


		