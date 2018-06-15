package Excecoes;
/**
 * Runtime exception thrown when one tries to perform operation
 * push on a full stack
 */
@SuppressWarnings("serial")
public class FullStackException extends RuntimeException
{
    public FullStackException(String e)
    {
        super(e);
    }
}