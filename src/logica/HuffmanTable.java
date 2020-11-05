package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

//bggeafeafabcfcfdceg

public class HuffmanTable {
	public int getNumC() {
		return numC;
	}

	public Character[] getSimbolo() {
		return simbolo;
	}

	public int[] getFrecuencia() {
		return frecuencia;
	}

	public int[] getPadre() {
		return padre;
	}

	public int[] getTipo() {
		return tipo;
	}

	public int[] getIzq() {
		return izq;
	}

	public int[] getDer() {
		return der;
	}
        
        public String getMatriz() {
            return matriz;
        }
        
        public String matriz;
	private Character simbolo[];
	private int frecuencia[];
	private int padre[];
	private int tipo[];
	private int izq[];
	private int der[];
	private int numS;
	public int numC;
        public String codigo;
        private int numCusadas;
        
	private ArrayList<Integer> letrasUsadas;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
	

	public HuffmanTable(int numSim) {
		this.numS = numSim;
		this.numC = ((numSim * 2) - 1);
		this.numCusadas=numSim;
		this.simbolo = new Character[this.numC];
		this.frecuencia = new int[this.numC];
		this.padre = new int[this.numC];
		this.tipo = new int[this.numC];
		this.izq = new int[this.numC];
		this.der = new int[this.numC];
		this.letrasUsadas= new ArrayList<Integer>();
	}

	public void inicializar(HashMap<Character, Integer> l) {
		Vector<Character> letrasOrdenadas = new Vector<Character>(l.keySet());
		Collections.sort(letrasOrdenadas);
		Iterator<Character> i = letrasOrdenadas.iterator();
		int j = 0;
		//System.out.println("size: "+letrasOrdenadas.size());
		while (i.hasNext()) {
			Character c = i.next();
			//System.out.println(c);
			this.simbolo[j] = c;
			this.frecuencia[j] = l.get(c);
			j++;
		}
		this.construirTabla();
		this.pintarTabla();
                
	}

	public void pintarTabla() {
                String sim ="";
                String frec ="";
                String pad ="";
                String tip ="";
                String izq ="";
                String der ="";
                
                for (int j = 0; j < this.numC; j++) {
                        sim = sim + this.simbolo[j]+"\t";
		}
		System.out.println("");
		for (int j = 0; j < this.numC; j++) {
                        frec= frec+ this.frecuencia[j]+"\t";
		}
		System.out.println("");
		for (int j = 0; j < this.numC; j++) {
                        pad = pad + this.padre[j]+"\t";
		}
		System.out.println("");
		for (int j = 0; j < this.numC; j++) {
                        tip = tip+this.tipo[j] +"\t";
		}
		System.out.println("");
		for (int j = 0; j < this.numC; j++) {
                        izq= izq + this.izq[j] +"\t";
		}
		System.out.println("");
		for (int j = 0; j < this.numC; j++) {
                        der = der+this.der[j]+"\t";
		}
		System.out.println("");
                this.matriz= sim+"\n"+frec+"\n"+pad+"\n"+tip+"\n"+izq+"\n"+der;
	}
       
	
	private void construirTabla() {
		int i= this.numS;
		while(i!=this.numC) {
			int i1=this.menorF();
			int i2=this.menorF();
			System.out.println("i: "+i+", menores: "+i1+"|"+i2);
			if( i1==-1 || i2==-1 )break;
			this.frecuencia[i]=this.frecuencia[i1]+this.frecuencia[i2];
			int f1=this.frecuencia[i1];
			int f2=this.frecuencia[i2];
			if(f1==f2) {
				if(i1<i2) {
					this.izq[i]=i1;
					this.tipo[i1]=1;
					this.der[i]=i2;
					this.tipo[i2]=2;
				}else {
					this.izq[i]=i2;
					this.tipo[i2]=1;
					this.der[i]=i1;
					this.tipo[i1]=2;
				}
			}else {
				
				if(f1<f2) {
					this.izq[i]=i1;
					this.tipo[i1]=1;
					this.der[i]=i2;
					this.tipo[i2]=2;
				}else {
					this.izq[i]=i2;
					this.tipo[i2]=1;
					this.der[i]=i1;
					this.tipo[i1]=2;
				}
			}
			this.padre[i1]=i;
			this.padre[i2]=i;
			i++;
			this.numCusadas++;
		}
	}
	
	
	
	public int menorF() {
		int menor=0;
		int imenor=-1;
		
		for(int i=0;i<this.numCusadas;i++) {
			if(this.letrasUsadas.contains(i)) {
//				System.out.println(this.letrasUsadas.toString());
				continue;
			}
			if(menor==0) {
				menor=this.frecuencia[i];
				imenor=i;
			}else {
				if(menor>this.frecuencia[i]) {
					menor=this.frecuencia[i];
					imenor=i;
				}
			}
		}
		if(imenor>-1)this.letrasUsadas.add(imenor);
		return imenor;
	}
        
        

}
