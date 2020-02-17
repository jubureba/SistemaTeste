package util;

public enum Situacao {
	Inativo("Inativo"),
	Ativo("Ativo");
	
	private String label;
	
	Situacao(String label) { 
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
