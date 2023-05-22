package projeto.lab05;

public enum CalcSeguro {
	VALOR_BASE (10.0),
	FATOR_18_30(1.25),
	FATOR_30_60(1.0),
	FATOR_60_90(1.5);
	
	private double num;
	
	private CalcSeguro(double num) {
		this.num = num;
	}
	
	double getNum() {
		return this.num;
	}
}
