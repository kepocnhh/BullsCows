package stan.bulls.cows.logic;

import stan.bulls.cows.core.Offer;

public class Logic
{
    static public Offer checkCountBullsAndCows(String offer_value, String secret_value)
    {
        Offer offer = new Offer(offer_value);
        char[] offerCharArray = offer_value.toCharArray();
        char[] secretCharArray = secret_value.toCharArray();
        for(int i = 0; i < offer_value.length(); i++)
        {
            if(offerCharArray[i] == secretCharArray[i])
            {
                offer.bulls++;
            }
        }
        return offer;
    }
}