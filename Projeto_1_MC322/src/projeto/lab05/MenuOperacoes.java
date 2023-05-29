package projeto.lab05;

public enum MenuOperacoes {
	SAIR (0),
	CADASTRAR(1),
	LISTAR (2),
	EXCLUIR (3),
	GERAR_SINISTRO (4),
	TRANSFERIR_SEGURO (5),
	CALCULAR_RECEITA_SEGURADORA (6),
	ALTERAR_SEGURADORA_ATIVA (7),
	CADASTRAR_CLIENTE (11),
	CADASTRAR_FROTA (12),
	CADASTRAR_VEICULO (13),
	CADASTRAR_SEGURO (14),
	CADASTRAR_CONDUTOR (15),
	CADASTRAR_SINISTRO (16),
	CADASTRAR_SEGURADORA (17),
	VOLTAR_CADASTRO (18),
	LISTAR_CLIENTE_POR_SEG (21),
	LISTAR_SINISTROS_POR_SEG (22),
	LISTAR_VEICULOS_POR_SEG (23),
	LISTAR_SEGUROS_POR_SEG (24),
	LISTAR_SINISTROS_POR_CLIENTE (25),
	LISTAR_SEGUROS_POR_CLIENTE (26),
	LISTAR_VEICULOS_POR_CLIENTE(27),
	LISTAR_CONDUTORES_POR_SEGURO(28),
	LISTAR_SINISTROS_POR_SEGURO (29),
	VOLTAR_LISTAR (30),
	EXCLUIR_CLIENTE (31),
	EXCLUIR_FROTA (32),
	EXCLUIR_VEICULO (33),
	EXCLUIR_SEGURO (34),
	EXCLUIR_CONDUTOR (35),
	EXCLUIR_SINISTRO (36),
	VOLTAR_EXCLUIR (37);
	
	public final int operacao;	
	
	MenuOperacoes(int operacao){
		this.operacao = operacao;
	}
	
	//Método que retorna uma constante do enum a partir de um inteiro target
	public static MenuOperacoes busca(int target) {
		for(MenuOperacoes i : MenuOperacoes.values()) {
			if(i.operacao == target)	return i;
		}
		return null;
	}
	
	public int getOperacao() {
		return this.operacao;
	}	
}
