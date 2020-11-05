package logica;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Huffman {
	
	
	public HuffmanTable tabla;
	
	public Huffman(){
		
	}
	
	public void setMensaje(String msg) {
		msg.toLowerCase();
		HashMap<Character, Integer> letras = new HashMap<Character, Integer>();
		for(int i=0; i<msg.length();i++) {
			if(!Character.isLetter(msg.charAt(i))) {
				continue;
			}
			Character key= msg.charAt(i);
			if(letras.containsKey(key)) {
				letras.replace(key, letras.get(key)+1);
			}else {
				letras.put(key, 1);
			}
		}
		System.out.println(letras.toString());
		this.tabla= new HuffmanTable(letras.size());
		this.tabla.inicializar(letras);
                
	}
	
	public int ultimoI() {
		return tabla.getNumC()-1;
	}

	public HuffmanTable getTabla() {
		return tabla;
	}

         public String [][]matrizHuffman(){
            
                String [][]tabla = new String [6][this.tabla.getNumC()];
                for (int j = 0; j < this.tabla.getNumC(); j++){
                    tabla[0][j]= Character.toString(this.tabla.getSimbolo()[j]);
                    tabla[1][j]= Integer.toString(this.tabla.getFrecuencia()[j]);
                    tabla[2][j]= Integer.toString(this.tabla.getPadre()[j]);
                    tabla[3][j]= Integer.toString(this.tabla.getTipo()[j]);
                    tabla[4][j]= Integer.toString(this.tabla.getIzq()[j]);
                    tabla[5][j]= Integer.toString(this.tabla.getDer()[j]);
                }
                
            return tabla;
            
        }
        
        

}
