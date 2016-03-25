package stan.bulls.cows.core.number;

import stan.bulls.cows.core.OfferElement;

public class NumberOfferElement
    extends OfferElement
{
    private int value;

    public NumberOfferElement(int v)
    {
        this.value = v;
    }

    public int getValue()
    {
        return this.value;
    }

    @Override
    public boolean isEquals(OfferElement offerElement)
    {
        return this.value == ((NumberOfferElement)offerElement).getValue();
    }
}