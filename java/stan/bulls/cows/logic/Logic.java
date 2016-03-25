package stan.bulls.cows.logic;

import stan.bulls.cows.core.Offer;

public class Logic
{
//    static public Offer checkCountBulls(String offer_value, String secret_value)
//    {
//        Offer offer = new Offer(offer_value);
//        char[] offerCharArray = offer_value.toCharArray();
//        char[] secretCharArray = secret_value.toCharArray();
//        for(int i = 0; i < offer_value.length(); i++)
//        {
//            if(offerCharArray[i] == secretCharArray[i])
//            {
//                offer.bulls++;
//            }
//        }
//        return offer;
//    }
//    static public Offer checkCountBullsAndCows(String offer_value, String secret_value)
//    {
//        return checkCountBullsAndCows(offer_value, secret_value.toCharArray());
//    }
//    static public Offer checkCountBullsAndCows(String offer_value, char[] secretCharArray)
//    {
//        Offer offer = new Offer(offer_value);
//        int length = offer_value.length();
//        char[] offerCharArray = offer_value.toCharArray();
//        int[] bulls = new int[length];
//        int[] cows = new int[length];
//        for(int i = 0; i < length; i++)
//        {
//            if(offerCharArray[i] == secretCharArray[i])
//            {
//                offer.bulls++;
//                bulls[i] = 1;
//            }
//        }
//        for(int i = 0; i < length; i++)
//        {
//            if(bulls[i] == 1)
//            {
//                continue;
//            }
//            for(int j = 0; j < length; j++)
//            {
//                if(bulls[j] == 1)
//                {
//                    continue;
//                }
//                if(cows[j] == 1)
//                {
//                    continue;
//                }
//                if(offerCharArray[i] == secretCharArray[j])
//                {
//                    offer.cows++;
//                    cows[j] = 1;
//                    break;
//                }
//            }
//        }
//        return offer;
//    }
    static public Offer checkCountBulls(Offer offer, Offer secret)
    {
        for(int i = 0; i < offer.getLenght(); i++)
        {
            if(offer.getOfferElement(i).isEquals(secret.getOfferElement(i)))
            {
                offer.bulls++;
            }
        }
        return offer;
    }
    static public Offer checkCountBullsAndCows(Offer offer, Offer secret)
    {
        int[] bulls = new int[offer.getLenght()];
        int[] cows = new int[offer.getLenght()];
        for(int i = 0; i < offer.getLenght(); i++)
        {
            if(offer.getOfferElement(i).isEquals(secret.getOfferElement(i)))
            {
                offer.bulls++;
                bulls[i] = 1;
            }
        }
        for(int i = 0; i < offer.getLenght(); i++)
        {
            if(bulls[i] == 1)
            {
                continue;
            }
            for(int j = 0; j < offer.getLenght(); j++)
            {
                if(bulls[j] == 1)
                {
                    continue;
                }
                if(cows[j] == 1)
                {
                    continue;
                }
                if(offer.getOfferElement(i).isEquals(secret.getOfferElement(j)))
                {
                    offer.cows++;
                    cows[j] = 1;
                    break;
                }
            }
        }
        return offer;
    }
}