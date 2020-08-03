package Challenge;


public class Peca implements Comparable<Peca> {
	
	Integer id;
	int largura;
	int altura;
	
	public int getArea() {
		return this.area();
	}
	
	Peca(int id, int largura, int altura){
		this.id = id;
		this.largura = largura;
		this.altura = altura;
	}
	
	int area() {
		return this.altura*this.largura;
	}

	public String toString() {
		return "Id: " + this.id + " Altura: " + this.altura + " Largura: " + this.largura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + altura;
		result = prime * result + id;
		result = prime * result + largura;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Peca outro = (Peca) obj;
		boolean idIgual = outro.id.equals(this.id);
		
		return idIgual;
	}

	@Override
	public int compareTo(Peca aPeca) {
		return (aPeca.getArea()-this.area());
	}
	
	

}
