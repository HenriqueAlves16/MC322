package projeto;

class Main {
	public static void main(String[] args) {
	Cliente cliente1 = new Cliente("Henrique Alves", "45048735838", "16/04/2002", "Campinas", 20);
	Cliente cliente2 = new Cliente("Tony Stark", "00000000000", "16/04/2002", "Campinas", 20);

	Seguradora seguradora = new Seguradora("Muito Seguro", "35420176", "seguradora@gmail.com", "Araras");
	Sinistro sinistro1 = new Sinistro("15/04/2042", "Sao Paulo");
	Sinistro sinistro2 = new Sinistro("20/09/2042", "Natal");
	Sinistro sinistro3 = new Sinistro("27/12/2042", "Minas Gerais");
	Veiculo veiculo = new Veiculo("ABC1234", "Ford", "Ka");
		
	System.out.println(cliente1.toString());
	System.out.println(seguradora.toString());
	System.out.println(sinistro1.toString());
	System.out.println(sinistro2.toString());
	System.out.println(sinistro3.toString());
	System.out.println(veiculo.toString() + "\n");
	
	System.out.printf("%s, de cpf %s, nascido em %s, residente de %s, de %s anos eh cliente da seguradora %s \n", cliente1.getNome(), cliente1.getCpf(), cliente1.getDataNascimento(), cliente1.getEndereco(), cliente1.getIdade(), seguradora.getNome());
	System.out.printf("Os ids dos sinistros sao %s, %s e %s, do mais antigo para o mais novo\n", sinistro1.getId(), sinistro2.getId(), sinistro3.getId());
	System.out.printf("o veiculo %s %s, cuja placa eh %s eh propriedade de %s e teve sinistro no dia %s em %s\n\n", veiculo.getMarca(), veiculo.getModelo(), veiculo.getPlaca(), cliente1.getNome(), sinistro2.getData(), sinistro2.getEndereco());
	
	System.out.printf("O CPF do cliente1 eh valido: %b/true\n", cliente1.validarCPF());
	System.out.printf("O CPF do cliente2 eh valido: %b/false\n", cliente2.validarCPF());
	cliente1.setCpf("12345678");
	cliente2.setCpf("09581854843");
	System.out.printf("O CPF do cliente1 eh valido: %b/false\n", cliente1.validarCPF());
	System.out.printf("O CPF do cliente2 eh valido: %b/true\n", cliente2.validarCPF());
	cliente1.setCpf("abc123456");
	cliente2.setCpf("01245678935");
	System.out.printf("O CPF do cliente1 eh valido: %b/false\n", cliente1.validarCPF());
	System.out.printf("O CPF do cliente2 eh valido: %b/false\n", cliente2.validarCPF());
	cliente1.setCpf("abcde45048735838");
	System.out.printf("O CPF do cliente1 eh valido: %b/false\n\n", cliente1.validarCPF());
	
	/*
	sinistro3.setId(2);
	System.out.printf("id sinistro2: %d; id sinistro3: %d\n\n", sinistro2.getId(), sinistro3.getId());
	*/
	
	veiculo.setMarca("Ferrari");
	veiculo.setModelo("SP51");
	System.out.println(veiculo.toString() + "\n");

	}
}
