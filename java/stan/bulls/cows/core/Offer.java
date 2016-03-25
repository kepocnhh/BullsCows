package stan.bulls.cows.core;

public abstract class Offer
{
    private OfferElement[] offerElements;
    private String value;
    public int bulls;
    public int cows;

    public Offer(String v)
    {
        this.value = v;
        setElementsOfferFromValue(this.value);
    }

    public String getValue()
    {
        return this.value;
    }
    public void initOfferElementsFromSize(int i)
    {
        this.offerElements = new OfferElement[i];
    }
    public int getLenght()
    {
        return this.offerElements.length;
    }
    public OfferElement getOfferElement(int i)
    {
        return this.offerElements[i];
    }
    public void setOfferElement(int i, OfferElement offerElement)
    {
        this.offerElements[i] = offerElement;
    }

    abstract protected void setElementsOfferFromValue(String value);
}