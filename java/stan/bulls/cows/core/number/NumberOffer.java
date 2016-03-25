package stan.bulls.cows.core.number;

import stan.bulls.cows.core.Offer;

public class NumberOffer
    extends Offer
{
    public NumberOffer(String v)
    {
        super(v);
    }

    @Override
    protected void setElementsOfferFromValue(String value)
    {
        char[] valueCharArray = value.toCharArray();
        initOfferElementsFromSize(value.length());
        for(int i=0; i<getLenght(); i++)
        {
            setOfferElement(i, new NumberOfferElement(valueCharArray[i]));
        }
    }
}