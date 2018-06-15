import Excecoes.FullStackException;

public class Calculadora 
{
	private boolean v1;
	private boolean v2;
	private char opl;
	
	private String[] valoresLogicos;
	private String[] operadoresLogicos;
	
	private Fila filaSaida;
	private Pilha pilhaResultado;
	
	private boolean resultado;
	
 
	public Calculadora(String[] _valoresLogicos, String[] _operadoresLogicos, Fila _filaSaida, Pilha _pilhaResultados) throws Exception
	{							
		valoresLogicos = _valoresLogicos;
		operadoresLogicos = _operadoresLogicos;
		
		filaSaida = _filaSaida;				
		pilhaResultado  = _pilhaResultados;
		
		resultado = false;
	}
	
	public String CalcularOperacaoLogica() throws FullStackException, Exception
	{		
		while(!filaSaida.FilaVazia())
		{
			if(filaSaida.Itens != null)
			{
				String item = filaSaida.RemoverItem();
				
				InserirValoresLogicosNaPilha(item);

				AvaliarValoresDaPilha(item);				
			}
			
		}		
		return toString(resultado);
	}

	private void AvaliarValoresDaPilha(String item) throws Exception {
		for(String o: operadoresLogicos)
		{
			if(item.equals(o))
			{
				opl = toChar(item);
				
				String itemPilha = pilhaResultado.RemoverTopo();
				v2 = toBool(itemPilha);
				
				if(opl != '~')
				{
					itemPilha = pilhaResultado.RemoverTopo();
					v1 = toBool(itemPilha);
					
					resultado = toBool(CalcularResultado(v1, v2, opl));							
				}
				
				else
				{
					if(opl == '~')
					{
						if(v2 == true)
						{
							resultado = false;
							pilhaResultado.InserirTopo(toString(resultado));
						}
						
						if(v2 == false)
						{
							resultado = true;
							pilhaResultado.InserirTopo(toString(resultado));
						}								
					}
				}
			}
		}
	}

	private void InserirValoresLogicosNaPilha(String item) throws Exception {
		for(String v: valoresLogicos)
		{
			if(item.equals(v))
			{
				pilhaResultado.InserirTopo(item);
			}
		}
	}
	
	private String CalcularResultado(boolean valor1, boolean valor2, char operador) throws FullStackException, Exception
	{
		CalcularValoresIguais(valor1, valor2, operador);
		
		CalcularValoresDiferentes(valor1, valor2, operador);
		
		return pilhaResultado.Topo();		
	}

	private void CalcularValoresDiferentes(boolean valor1, boolean valor2, char operador) throws Exception {
		if(valor1 == true && valor2 == false)
		{
			switch (operador)
			{
				case 'V':
					resultado = true;				
					break;
				case '^':
					resultado = false;
					break;
				case '<':
					resultado = false;
					break;
				case '-':
					resultado = false;
					break;				
			}
			pilhaResultado.InserirTopo(toString(resultado));		
		}
		
		 if(valor1 == false && valor2 == true)
		 {
			 switch (operador)
			{
				case 'V':
					resultado = true;				
					break;
				case '^':
					resultado = false;
					break;
				case '<':
					resultado = false;
					break;
				case '-':
					resultado = true;
					break;				
			}
			pilhaResultado.InserirTopo(toString(resultado));			 
		 }
	}

	private void CalcularValoresIguais(boolean valor1, boolean valor2, char operador) throws Exception {
		if(valor1 == true && valor2 == true)
		{
			if(operador == 'V' || operador == '^' || operador == '<' || operador == '-')
			{
				resultado = true;
				pilhaResultado.InserirTopo(toString(resultado));
			}
				
		}
		
		if(valor1 == false && valor2 == false)
		{
			if(operador == 'V' || operador == '^' || operador == '<' || operador == '-')
			{
				resultado = false;
				pilhaResultado.InserirTopo(toString(resultado));
			}		
		}
	}
	
	private boolean toBool(String item)
	{
		if(item.equals("T"))
			return true;
		return false; 
	}
	
	private char toChar(String item)
	{
		char aux;
		aux = item.charAt(0);
		
		return aux;
	}
	
	private String toString(boolean item)
	{		
		return item ? "T" : "F";
	}
	
	
	
}
