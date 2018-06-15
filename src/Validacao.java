public class Validacao 
{
	private String expressaoLogica;
	private String[] operadoresLogicos;
	
	public String simbolosCorrigidos;
	
	Validacao(String _expressaoLogica, String[] _operadoresLogicos) throws Exception
	{ 
		expressaoLogica = _expressaoLogica;
		operadoresLogicos = _operadoresLogicos;
		
	}	

	public boolean ValidarExpressaoLogica() throws Exception
	{
		boolean simbolosEntrada = ValidarSimbolosEntrada();
		System.out.println("Teste de simbolos validos: " + simbolosEntrada);
		boolean valido = false;
				
		if(!simbolosEntrada)
		{
			throw new Exception("Existem simbolos invalidos na entrada");
		}
		
		else
		{	
			simbolosCorrigidos = TrocarSimbolosExpressao();
			System.out.println("Expressao Logica: " + simbolosCorrigidos);
			
			boolean simbolosValidos = ValidarSimbolos(simbolosCorrigidos);
			boolean parentesesValidos = ValidarQuantParenteses(simbolosCorrigidos);
			boolean ordemExpressao = ValidarOrdemExpressao(simbolosCorrigidos);
			
			
			if(simbolosValidos == true && 
			   parentesesValidos == true && 
			   ordemExpressao == true)
				valido = true;
			
			else 
			{
				valido = false;
				throw new Exception("EXPRESSAO INVALIDA! Tente novamente.");			
			}			
		}	
				
		return valido;
	}	

	private boolean ValidarSimbolosEntrada() throws Exception
	{
		String[] expLogica = expressaoLogica.split("");
		boolean[] validadorSimbolos = new boolean[expLogica.length];
		boolean simboloValido = true;
		
		for(int i = 0; i < expLogica.length; i++){validadorSimbolos[i] = true;}		
						
		for(int i = 0; i < expLogica.length; i++)
		{
			if(expLogica[i].equals("T") || expLogica[i].equals("F") || expLogica[i].equals("(") || 
			   expLogica[i].equals(")") || expLogica[i].equals("^") || expLogica[i].equals("V") || 
			   expLogica[i].equals("~") || expressaoLogica.contains("<->") || expressaoLogica.contains("->"))
				validadorSimbolos[i] = true;
			else 
				validadorSimbolos[i] = false;			
		}
		
		for(boolean v: validadorSimbolos)
		{
			if(v != true)
				throw new Exception("A expressao contem simbolos invalidos");
			else simboloValido = true;			
		}
				
		return simboloValido;
	}
	
	private boolean ValidarSimbolos(String expressaoLogica) throws Exception
	{
		
		int tam_expLogica = expressaoLogica.length();
		boolean[] validadorSimbolos = new boolean[tam_expLogica];
		boolean valido = false;
		
		for(int i = 0; i < tam_expLogica; i++){ validadorSimbolos[i] = true; }
					
		for(String o: operadoresLogicos)
		{			
			//NOT é um operador válido para se iniciar a expressao logica
			if(!expressaoLogica.startsWith("~") && expressaoLogica.startsWith(o))
				validadorSimbolos[0] = false;
			
			if(expressaoLogica.endsWith(o))
				validadorSimbolos[tam_expLogica-1] = false;
		}
		
		for(boolean v: validadorSimbolos)
		{
			if(v != true)
				throw new Exception("A expresão é invalida. Ela inicia ou termina com um operador");
			else valido = true;			
		}
		
		return valido;
	}
	
	private boolean ValidarQuantParenteses(String expressaoLogica)
	{
		String[] expLogica = expressaoLogica.split("");
		int tam_expLogica = expressaoLogica.length();
		int count = 0;
		
		boolean valido = true;
		
		if(expressaoLogica.startsWith(")") || expressaoLogica.endsWith("("))
		{
			System.out.println("Os parenteses foram colocados errados na expressao");
			valido = false;			
		}			
		
		for(int i = 0; i < tam_expLogica; i++)
		{
			//Verifica se a expressao possui a quantidade correta de parenteses.
			//Se sim, sempre que abrir, deve fechar
			if(expLogica[i].equals("("))
				count++;
			if(expLogica[i].equals(")"))
				count--;				
		}
		
		if(count!= 0)
		{
			valido = false;
			System.out.println("Faltam parenteses na expressao");
		}
		else if(count == 0)
			return valido = true;
		
		return valido;			
		
	}
	
	private boolean ValidarOrdemExpressao(String expressaoLogica) 
	{
		String[] expLogica = expressaoLogica.split("");
		int tam_expLogica = expressaoLogica.length();
		boolean valido = true;
		
		//Verifica se o proximo simbolo é do mesmo tipo que o anterior
		for(int i = 0; i < tam_expLogica; i++)
		{	
			if(i < tam_expLogica-1)
			{
				valido = ValidarOrdemOperadoresLogicos(expLogica, valido, i);
				
				valido = ValidarOrdemValoresLogicos(expLogica, tam_expLogica, valido, i);				
			}			
		}		
		return valido;
	}

	private boolean ValidarOrdemOperadoresLogicos(String[] expLogica, boolean valido, int i) {
		for(String o : operadoresLogicos)
		{
			if((expLogica[i].equals("~") && expLogica[i+1].equals(o)))
			{
				System.out.println("Existem dois operadores seguidos");
				valido = false;
			}

			if((expLogica[i].equals(o) && expLogica[i+1].equals(o)))
			{
				System.out.println("Existem dois operadores iguais seguidos");
				valido = false;
			}				
		}
		return valido;
	}

	private boolean ValidarOrdemValoresLogicos(String[] expLogica, int tam_expLogica, boolean valido, int i) {
		if(expLogica[i].equals("T") && expLogica[i+1].equals("T") ||
		   expLogica[i].equals("F") && expLogica[i+1].equals("F") ||
		   expLogica[i].equals("T") && expLogica[i+1].equals("F") ||
		   expLogica[i].equals("F") && expLogica[i+1].equals("T"))
		{
			System.out.println("Existem dois valores logicos seguidos");
			valido = false;						
		}
		
		return valido;
	}
	
	private String TrocarSimbolosExpressao() 
	{
		String expressaoLogicaCorrigida;
		expressaoLogicaCorrigida = expressaoLogica.replace("<->", "<").replace("->", "-");
		
		return expressaoLogicaCorrigida;
	}

}
