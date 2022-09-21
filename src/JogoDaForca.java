import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class JogoDaForca {
	private ArrayList<String> palavras = new ArrayList<>(); // lista de palavras lidas do arquivo
	private ArrayList<String> dicas = new ArrayList<>(); // lista de dicas lidas do arquivo
	private String dica = ""; // dica da palavra sorteada
	private String palavra = ""; //palavra sorteada
	private String[] letras; // letras da palavra sorteada
	private int acertos = 0; // contador de acertos
	private int penalidade= 0 ; // penalidade atual
	
	public JogoDaForca(String nomearquivo) throws Exception{
		try {
			Scanner arquivo = new Scanner(new File(nomearquivo));
			//variavel 'palavra' =! atributo palavra
			String palavra, dica, entrada;
			String [] partes;
			while(arquivo.hasNextLine()) {
				entrada = arquivo.nextLine();
				partes = entrada.split(";");
				palavra = partes[0];
				dica = partes[1];
				palavras.add(palavra);
				dicas.add(dica);
			}
		} catch (FileNotFoundException e) {
			throw new Exception("arquivo inexistente");
		}
	}
//	public String toString() {
//		return palavras.toString() + dicas.toString() + dica + palavra;
//	}
	/*O método iniciar gera um numero aleatorio entre 0 e o numero de palavras no csv, pega a palavra
	 * e dica referente ao numero(indice) gerado e deixa as letras maiúsculas (para padronizar o processamento dentro da classe)*/
	public void iniciar(){
		Random random = new Random();
		int indice = random.nextInt(palavras.size());
		palavra = palavras.get(indice);
		dica = dicas.get(indice);
		letras =  palavra.toUpperCase().split("");
	}
	public String getDica(){
		return dica;
	}
	public int getTamanho(){
		return letras.length;
	}
	
	public ArrayList<Integer> getPosicoes(String letra) throws Exception{
		//pelo sentido lógico da aplicação nós criamos um arraylist de posiçoes para retornar
		ArrayList<Integer> posicoes = new ArrayList<>();
		int contador = 0;
		//enquanto o indice for menor que o tamanho da palavra, o processamento continua
		for (int indice = 0; indice < getTamanho(); indice++ ) {
			//se a letra em determinada posição for igual à letra passada como parametro, então essa posição esta certa
			//logo, devemos adicionar esse indice ao arraylist que será retornado, contabilizamos 1 acerto e substituimos a letra por um *
			//para caso o usuário digite a letra novamente, conte como penalidade
			if (letras[indice].equals(letra.toUpperCase())){
				posicoes.add(indice);
				acertos+=1;
				letras[indice] = "*";
			//esse else não pode retornar diretamente a penalidade pq iria fugir da lógica do programa, por isso há necessidade de um contador
			} else { contador+=1; }
		}
		if (contador == getTamanho()) {
			penalidade +=1;
		}
		return posicoes;
	}
	public boolean terminou() {
		if( (penalidade == 6) || ( acertos == getTamanho())) {
			return true;
		} return false;
	}
	public int getAcertos() {
		return acertos;
	}
	public int getPenalidade(){
		return penalidade;
	}
	public String getResultado() {
		if (terminou()) {
			if(penalidade == 6) {
				return "voce foi enforcado";
			}else {
				return "voce venceu";
			}
		} return "jogo em andamento";
	}
}
