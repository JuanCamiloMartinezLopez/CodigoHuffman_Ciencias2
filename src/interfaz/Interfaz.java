package interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logica.Huffman;
import logica.HuffmanTable;

public class Interfaz extends JFrame {
	public int flag;
	public int WinWidth = 1000;
	public int WinHeight = 600;
	public int LROffset = 190;
	public int DownOffset = 50;
	public int nodeD = 26;
	public int levelOffset = 80;
	public JPanel control;
	public JPanel lienzo;
	public JLabel Titulo;
	public JButton Ingresar;
	public JLabel labelPalabra;
	public JTextField IngresarPalabra;
        public JTextArea matrices;
        public JTextArea digitos;
	public Graphics graphics;
	public Graphics2D g;
	public Huffman h;
	public HuffmanTable hTable;
        public JScrollPane matriz; 
        public JScrollPane txtencriptado; 
        public String[] codigos;
        public char[] letrasmsj;
        public String valor="";

	public Interfaz() {
		this.h = new Huffman();
                this.codigos= new String[26];
                this.letrasmsj = new char[50];
		this.setTitle("Codigo de Huffman");
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize(WinWidth, WinHeight);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                

		Titulo = new JLabel("CODIGO DE HUFFMAN");
		Titulo.setForeground(Color.RED);
		Titulo.setFont(new Font("Monospaced", Font.BOLD, 36));
		Titulo.setBounds((this.WinWidth / 2) - 185, 20, 400, 50);
		this.getContentPane().add(Titulo);
                
		control = new JPanel();
		control.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		control.setBounds((this.WinWidth / 2) - ((int) (this.WinWidth * 0.60) / 2)-120, 80, (int) (this.WinWidth * 0.45),
				90);
		control.setLayout(null);
		this.getContentPane().add(control);

		lienzo = new JPanel();
		lienzo.setBounds(20, 190, this.WinWidth - 50, (int) (this.WinHeight - 320));
		lienzo.setBackground(Color.WHITE);
		lienzo.setBorder(BorderFactory.createLineBorder(Color.black));
		lienzo.setAutoscrolls(true);
		this.getContentPane().add(lienzo);

		labelPalabra = new JLabel("texto:");
		labelPalabra.setBounds(15, 30, 50, 20);
		// labelPalabra.setBorder(BorderFactory.createLineBorder(Color.black));
		control.add(labelPalabra);
		IngresarPalabra = new JTextField();
		IngresarPalabra.setBounds(70, 30, 190, 20);
		IngresarPalabra.setVisible(true);
		control.add(IngresarPalabra);
                
                matriz = new JScrollPane();
                matriz.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                matriz.setBounds((this.WinWidth / 2)+(int)( this.WinWidth*0.05), 80, (int) (this.WinWidth * 0.40),
				90);
                this.getContentPane().add(matriz);
                
                txtencriptado = new JScrollPane();
                txtencriptado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                txtencriptado.setBounds( 20, this.WinHeight - 120, this.WinWidth - 50 , (int) (this.WinHeight *0.13));
                this.getContentPane().add(txtencriptado);
                
                digitos = new JTextArea();
                digitos.setBounds(10, 300, matriz.getWidth()-10, matriz.getHeight());
                digitos.setVisible(true);
                txtencriptado.setViewportView(digitos);
                
                matrices = new JTextArea();
                matrices.setBounds(10, 5, matriz.getWidth()-10, matriz.getHeight());
                matrices.setBackground(Color.WHITE);
                matrices.setVisible(true);
                matriz.setViewportView(add(matrices));

		Ingresar = new JButton("Codificar");
		Ingresar.setBounds((control.getWidth() / 2) - 50, 60, 100, 20);
		Ingresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = IngresarPalabra.getText();
                                String encriptado = "";
                                letrasmsj = text.toCharArray();
                                
                                
                                
				try {
                                    h.setMensaje(text);
                                    repintar();
                                    matrices.setText(h.getTabla().getMatriz());
                                    System.out.println(h.getTabla().getMatriz());
                                    digitos.setFont(new Font("Courier", Font.PLAIN, 10));
                                    digitos.setText(msjEncrptado());
                                    
				} catch (Exception error) {
					System.out.println(error.toString());
				}
                                
				IngresarPalabra.setText("");

			}

		});
		control.add(Ingresar);
		control.revalidate();
		control.repaint();
                
	}

	public void repintar() {
		graphics = lienzo.getGraphics();
		lienzo.paint(graphics);
		g = (Graphics2D) graphics;
		this.hTable = this.h.getTabla();
		paintArbolH(this.h.ultimoI(), (lienzo.getWidth() / 2) - (this.nodeD / 2), 5, 0, "");
		// lienzo.repaint();
	}

               
	private void paintArbolH(int i, int x, int y, int level, String codigo) {
		int nodeR = nodeD / 2;
		int nextLNodeX = x - LROffset + level * levelOffset;
		int nextRNodeX = x + LROffset - level * levelOffset;
		int nextNodeY = y + DownOffset;
		int hizq = this.hTable.getIzq()[i];
		int hder = this.hTable.getDer()[i];
                g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		if (this.hTable.getSimbolo()[i] == null) {
			g.drawLine(nextLNodeX + nodeR, nextNodeY + nodeR, x + nodeR, y + nodeR);
			g.drawLine(nextRNodeX + nodeR, nextNodeY + nodeR, x + nodeR, y + nodeR);
		}
		g.fillOval(x, y, nodeD - level, nodeD - level);
		g.setColor(Color.WHITE);
		if (this.hTable.getSimbolo()[i] == null) {
			g.drawString(String.valueOf(i), x + nodeR - 10, y + nodeR + 4);
		} else {
			g.drawString(String.valueOf(this.hTable.getSimbolo()[i]), x + nodeR - 10, y + nodeR + 4);
		}
		if (this.hTable.getSimbolo()[i] == null) {
			paintArbolH(hizq, nextLNodeX, nextNodeY, level + 1,codigo+"0");
			paintArbolH(hder, nextRNodeX, nextNodeY, level + 1,codigo+"1");
                        
		}else { 
			g.setColor(Color.BLACK);
			g.drawString(codigo, x-nodeR , y + nodeD+5);
                        try {
                        this.codigos[i]=codigo;
                        this.valor = this.valor + String.valueOf(this.hTable.getSimbolo()[i])+"="+codigos[i]+"\t";
                            
                        int cont=0;
                        
                                              
                    } catch (Exception e) {
                            System.out.println("error");
                    }
		}
                g.setColor(Color.BLACK);
	}
        
        public String msjEncrptado(){
        String mensaje = "";
        String binario = "";
        String encriptado = "";
        String cantidad = "";
        String codificado = "";
        String ahorro = "";
        
        try {       
                    
                    for (int j = 0; j < letrasmsj.length; j++) {
                        mensaje = mensaje + letrasmsj[j];
                        for(int i=0; i<codigos.length;i++){
                            try {
                              if(letrasmsj[j]==this.hTable.getSimbolo()[i]){
                                 binario=binario+this.codigos[i];
                                  for (int k = 1; k < this.codigos[i].length(); k++) {
                                      mensaje = mensaje+ "_";
                                  }
                                 
                                 cantidad=cantidad+this.codigos[i];
                            }  
                            } catch (Exception e) {
                                System.out.print("-");
                            }

                        
                        }
                    }
                    double porc = (5/8);
                    System.out.println("\n"+porc);
                    codificado = Double.toString((cantidad.length()*100)/(letrasmsj.length*8));
                    ahorro = Double.toString(100-((cantidad.length()*100)/(letrasmsj.length*8)));

                } catch (Exception eas) {
                        System.out.println("errror de nuevo");
                }
        encriptado=valor+"\n\n"+mensaje+"\n"+binario+"\n\n"+"codificado ="+codificado+"%\t"+"Ahorro"+ahorro+"%\t";
        return encriptado;
        }

	public static void main(String[] Args) {
		new Interfaz();
	}

}