public class NotacaoPosFixa 
{
	private String[] expressaoLogica;
	private String[] operadoresLogicos;
	private String[] valoresLogicos;
	private String[] parenteses;
	
	private Fila filaSaida;
	private Pilha pilhaOperadores;	
	
	NotacaoPosFixa(String[] _expressaoLogica, String[] _operadores, String[] _valoresLogicos, String[] _parenteses, Fila _filaSaida) throws Exception
	{
		expressaoLogica = _expressaoLogica;
		operadoresLogicos = _operadores;
		valoresLogicos = _valoresLogicos;
		parenteses = _parenteses;
		
		filaSaida = _filaSaida;
		pilhaOperadores = new Pilha(_expressaoLogica.length);

	}		
	
	public void ConverterNotacao() throws Exception
	{
		for (String e: expressaoLogica)
		{
			if(e.equals(parenteses[0]) || e.equals(parenteses[1]))
				VerificarParenteses(e);
			
			if(e.equals(valoresLogicos[0]) || e.equals(valoresLogicos[1]))
				VerificarValoresLogicos(e);
			
			for(String o: operadoresLogicos)
				if(e.equals(o))
					VerificarOperadoresLogicos(e);
		}	

		VerificarPilhaFila();
		
		filaSaida.MostrarFila();
		pilhaOperadores.MostrarPilha();	
	}
	
	private void VerificarParenteses(String e) throws Exception 
	{
		if(e.equals(parenteses[0]))
		{
			pilhaOperadores.InserirTopo(parenteses[0]);
		}
		
		if(e.equals(parenteses[1]))
		{
			
			while(!pilhaOperadores.Topo().equals("("))
			{
				String item = pilhaOperadores.RemoverTopo();
				filaSaida.AdicionarItem(item);
				
			}
			pilhaOperadores.RemoverTopo();					

		}
	}

	private void VerificarValoresLogicos(String e) throws Exception 
	{
		filaSaida.AdicionarItem(e);		
	}

	private void VerificarOperadoresLogicos(String e) throws Exception 
	{	
		String topo = pilhaOperadores.Topo();
		if(!pilhaOperadores.PilhaVazia() && CompararPilhaExpressao(e, topo) == 1)
		{			
			if(!topo.equals("("))
			{   
				String item = pilhaOperadores.RemoverTopo(); 							
				filaSaida.AdicionarItem(item);
			}										
		}
		pilhaOperadores.InserirTopo(e);				
				
	}

	private void VerificarPilhaFila() throws Exception {
		if(!pilhaOperadores.PilhaVazia() && !filaSaida.FilaCheia())
		{
			for(String i: pilhaOperadores.Itens)
			{
				for(String op: operadoresLogicos)
				{
					if(i!= null && i.equals(op))
					{
						String item = pilhaOperadores.RemoverTopo();
						filaSaida.AdicionarItem(item);
					}					
				}
			}
			
		}
	}

	private int CompararPilhaExpressao(String simbExpressao, String topoPilha) 
	{
		int valor = 0;		
		if(topoPilha != null)
		{
			switch (simbExpressao) 
			{
			case "^":
				if(topoPilha.equals("(")) valor = 0;
				if(topoPilha.equals("~")) valor = 1;
				if(topoPilha.equals("^")) valor = 1;
				if(topoPilha.equals("V")) valor = 0;
				if(topoPilha.equals("-")) valor = 0;
				if(topoPilha.equals("<")) valor = 0;
				if(topoPilha.equals(")")) valor = 0;		
				break;
			case "V":
				if(topoPilha.equals("(")) valor = 0;
				if(topoPilha.equals("~")) valor = 1;
				if(topoPilha.equals("^")) valor = 1;
				if(topoPilha.equals("V")) valor = 1;
				if(topoPilha.equals("-")) valor = 0;
				if(topoPilha.equals("<")) valor = 0;
				if(topoPilha.equals(")")) valor = 0;		
				break;
			case "-":
				if(topoPilha.equals("(")) valor = 0;
				if(topoPilha.equals("~")) valor = 1;
				if(topoPilha.equals("^")) valor = 1;
				if(topoPilha.equals("V")) valor = 1;
				if(topoPilha.equals("-")) valor = 1;
				if(topoPilha.equals("<")) valor = 1;
				if(topoPilha.equals(")")) valor = 0;		
				break;
			case "<":
				if(topoPilha.equals("(")) valor = 0;
				if(topoPilha.equals("~")) valor = 1;
				if(topoPilha.equals("^")) valor = 1;
				if(topoPilha.equals("V")) valor = 1;
				if(topoPilha.equals("-")) valor = 1;
				if(topoPilha.equals("<")) valor = 1;
				if(topoPilha.equals(")")) valor = 0;		
				break;
			case ")":
				if(topoPilha.equals("(")) valor = 1;
				if(topoPilha.equals("~")) valor = 1;
				if(topoPilha.equals("^")) valor = 1;
				if(topoPilha.equals("V")) valor = 1;
				if(topoPilha.equals("-")) valor = 1;
				if(topoPilha.equals("<")) valor = 1;
				if(topoPilha.equals(")")) valor = 0;		
				break;
			}						
		}	
		return valor;		
	}
}
