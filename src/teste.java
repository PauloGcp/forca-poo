

public class teste {

	public static void main(String[] args) throws Exception {
		JogoDaForca j = new JogoDaForca("palavras.csv");
		j.iniciar();
		System.out.println(j.getTamanho());
		System.out.println(j.getDica());
		System.out.println(j);

	}

}
