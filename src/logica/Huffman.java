package logica;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Huffman {
	
	String mensaje ="";
	private HuffmanTable tabla;
	
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

}
