package Excecoes;
/**
 * Runtime exception thrown when one tries to perform operation
 * top or pop on an empty stack
 */

@SuppressWarnings("serial")
public class EmptyStackException extends RuntimeException
{
    public EmptyStackException(String e)
    {
        super(e);
    }
}