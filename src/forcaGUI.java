import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class forcaGUI {

	private JFrame frmJogoDaForca;
	private JTextField textField;
	private JLabel label;
	private JLabel label_1;
	private JogoDaForca jogo;
	private JButton button;
	private String[] penalidades = {"perna1", "perna2", "braco1", "braco2", "tronco", "cabeca"};
	private String letra;
	private String[] letrasAdivinhadas; 	//letras adivinhadas
	private ArrayList<Integer> posicoes;	//posicoes adivinhadas
	private int index; // numero de itens da penalidade
	private int tentativas;
	/**
	 * Launch the application.
	 */
	
	//classe para limitar o jtext
	class JTextFieldLimit extends PlainDocument {
		   private int limit;
		   JTextFieldLimit(int limit) {
		      super();
		      this.limit = limit;
		   }
		   JTextFieldLimit(int limit, boolean upper) {
		      super();
		      this.limit = limit;
		   }
		   public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		      if (str == null)
		         return;
		      if ((getLength() + str.length()) <= limit) {
		         super.insertString(offset, str, attr);
		      }
		   }
		}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					forcaGUI window = new forcaGUI();
					window.frmJogoDaForca.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public forcaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJogoDaForca = new JFrame();
		frmJogoDaForca.getContentPane().setBackground(new Color(119, 136, 153));
		frmJogoDaForca.setTitle("Jogo da Forca");
		frmJogoDaForca.setBounds(100, 100, 752, 441);
		frmJogoDaForca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJogoDaForca.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Jogo da forca");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(366, 0, 98, 32);
		frmJogoDaForca.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		javax.swing.ImageIcon icon = new javax.swing.ImageIcon(forcaGUI.class.getResource("/imagens/6.png"));
		lblNewLabel_1.setIcon(icon);
		lblNewLabel_1.setBounds(535, 52, 191, 200);
		frmJogoDaForca.getContentPane().add(lblNewLabel_1);
		
		JLabel label_1_1 = new JLabel("");
		label_1_1.setBounds(10, 116, 254, 32);
		frmJogoDaForca.getContentPane().add(label_1_1);
		
		JLabel label_6 = new JLabel("TENTATIVA = ");
		label_6.setBounds(402, 159, 155, 14);
		frmJogoDaForca.getContentPane().add(label_6);
		
		JLabel label_4 = new JLabel("letrinhas:");
		label_4.setBounds(526, 263, 200, 27);
		frmJogoDaForca.getContentPane().add(label_4);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(307, 43, 200, 58);
		frmJogoDaForca.getContentPane().add(label_3);
		
		JLabel lblNewLabel_2 = new JLabel("ACERTOS = 0 ");
		lblNewLabel_2.setBackground(new Color(0, 255, 0));
		lblNewLabel_2.setBounds(473, 109, 84, 14);
		frmJogoDaForca.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ERROS = 0 ");
		lblNewLabel_3.setBackground(new Color(255, 0, 0));
		lblNewLabel_3.setBounds(485, 134, 72, 14);
		frmJogoDaForca.getContentPane().add(lblNewLabel_3);
		
		JLabel label_2 = new JLabel("New label");
		label_2.setBounds(10, 168, 238, 14);
		frmJogoDaForca.getContentPane().add(label_2);
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
					javax.swing.ImageIcon icon = new javax.swing.ImageIcon(forcaGUI.class.getResource("/imagens/6.png"));
					lblNewLabel_1.setIcon(icon);
					index = penalidades.length;
					textField.setDocument(new JTextFieldLimit(1));
					lblNewLabel_2.setText("ACERTOS = 0");
					lblNewLabel_3.setText("ERROS = 0");
					label_6.setText("TENTATIVAS = 0");
					label_4.setText("");
					label_3.setText("");
					tentativas = 0;
					jogo = new JogoDaForca("palavras.csv") ;
					jogo.iniciar();
					label_1_1.setText(jogo.getDica());
					label_2.setText("tamanho da palavra: "+jogo.getTamanho());
					letrasAdivinhadas = new String[jogo.getTamanho()];
					Arrays.fill(letrasAdivinhadas, "");
				}catch(Exception ex) {
					label_1.setText(ex.getMessage());
			}}
		});
		
		
		
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(230, 230, 250));
		btnNewButton.setBounds(10, 7, 89, 23);
		frmJogoDaForca.getContentPane().add(btnNewButton);
		
		
		textField = new JTextField();
		textField.setDocument(new JTextFieldLimit(1));
		textField.setVisible(true);
		textField.setBackground(new Color(230, 230, 250));
		textField.setBounds(10, 218, 124, 20);
		frmJogoDaForca.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Dicas:");
		lblNewLabel_4.setBounds(10, 91, 169, 14);
		frmJogoDaForca.getContentPane().add(lblNewLabel_4);
		
		label = new JLabel("Digite uma letra:");
		label.setBounds(10, 193, 135, 14);
		frmJogoDaForca.getContentPane().add(label);
		
		label_1 = new JLabel("Possíveis erros:");
		label_1.setBounds(10, 39, 254, 14);
		frmJogoDaForca.getContentPane().add(label_1);
		
		button = new JButton("Tentar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (textField.getText().isEmpty()) {
						if (!jogo.terminou()) {
							label_1.setText("Digite uma letra no campo indicado: ");
						}
					} else {
						letra = textField.getText();
						posicoes = jogo.getPosicoes(letra);
						if (posicoes.size()>0) {
							tentativas+=1;
							for(int i : posicoes)
								letrasAdivinhadas[i] = letra;
							label_4.setText(Arrays.toString(letrasAdivinhadas));
							lblNewLabel_2.setText("ACERTOS = "+jogo.getAcertos());
						} 
						else {
							tentativas+=1;
							lblNewLabel_3.setText("ERROS = "+jogo.getPenalidade());
							index -= 1;
							javax.swing.ImageIcon icon = new javax.swing.ImageIcon(forcaGUI.class.getResource("/imagens/"+index+".png"));
							lblNewLabel_1.setIcon(icon);
						}
					label_6.setText("TENTATIVAS = "+ tentativas);
					if(jogo.terminou()) {
						String resultado = jogo.getResultado();
						label_3.setText(resultado);
						textField.setDocument(new JTextFieldLimit(0));
						label_1.setText("Inicie um novo jogo");
					}
					}
			} catch(Exception ex) {
				label_1.setText("Inicie um novo jogo");
				}
			}
		});
		button.setBounds(139, 217, 72, 23);
		frmJogoDaForca.getContentPane().add(button);
		
		JLabel label_5 = new JLabel("Tutorial: tu tem 6 tentativas ai vai tentando. Entendesse ?");
		label_5.setBounds(23, 306, 411, 70);
		frmJogoDaForca.getContentPane().add(label_5);

		
	}
}

