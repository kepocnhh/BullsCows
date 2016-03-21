package stan.bulls.cows.core;

public class Offer
{
    private char[] offerCharArray;
    private String value;
    public int bulls;
    public int cows;

    public Offer(String v)
    {
        this.value = v;
        this.offerCharArray = v.toCharArray();
    }

    public String getValue()
    {
        return this.value;
    }
    public int getLenght()
    {
        return offerCharArray.length;
    }
    public char getItem(int i)
    {
        return offerCharArray[i];
    }
}