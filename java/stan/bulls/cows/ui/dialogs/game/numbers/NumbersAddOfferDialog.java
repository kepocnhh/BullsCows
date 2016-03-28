package stan.bulls.cows.ui.dialogs.game.numbers;

import android.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import stan.bulls.cows.R;
import stan.bulls.cows.listeners.dialogs.game.IGameDialogListener;
import stan.bulls.cows.ui.dialogs.StanDialog;

public class NumbersAddOfferDialog
        extends StanDialog
{
    static public final int AMOUNT_DIFFICULT_EASY = 3;
    static public final int AMOUNT_DIFFICULT_MEDIUM = 6;
    static public final int AMOUNT_DIFFICULT_HARD = 9;

    static public NumbersAddOfferDialog createNumbersAddOfferDialogEasy(int count, IGameDialogListener listener)
    {
        return NumbersAddOfferDialog.createNumbersAddOfferDialog(count, NumbersAddOfferDialog.AMOUNT_DIFFICULT_EASY, listener);
    }

    static public NumbersAddOfferDialog createNumbersAddOfferDialogMedium(int count, IGameDialogListener listener)
    {
        return NumbersAddOfferDialog.createNumbersAddOfferDialog(count, NumbersAddOfferDialog.AMOUNT_DIFFICULT_MEDIUM, listener);
    }

    static public NumbersAddOfferDialog createNumbersAddOfferDialogHard(int count, IGameDialogListener listener)
    {
        return NumbersAddOfferDialog.createNumbersAddOfferDialog(count, NumbersAddOfferDialog.AMOUNT_DIFFICULT_HARD, listener);
    }

    static private NumbersAddOfferDialog createNumbersAddOfferDialog(int count, int amountDifficult, IGameDialogListener listener)
    {
        NumbersAddOfferDialog numbersAddOfferDialog = new NumbersAddOfferDialog();
        numbersAddOfferDialog.setCount(count);
        numbersAddOfferDialog.setAmountDifficult(amountDifficult);
        numbersAddOfferDialog.setCancelable(false);
        numbersAddOfferDialog.init(listener);
        return numbersAddOfferDialog;
    }

    //_______________VIEWS
    private TextView offer_value;
    private Button number_element_0;
    //__123
    private Button number_element_1;
    private Button number_element_2;
    private Button number_element_3;

    //_______________FIELDS
    private int count;
    private int amountDifficult;
    private String offerValue = "";
    private View.OnClickListener clickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.number_element_0:
                    addElement(0);
                    break;
                case R.id.number_element_1:
                    addElement(1);
                    break;
                case R.id.number_element_2:
                    addElement(2);
                    break;
                case R.id.number_element_3:
                    addElement(3);
                    break;
            }
        }
    };

    public NumbersAddOfferDialog()
    {
        super(R.layout.numbers_add_offer_dialog, "NumbersAddOfferDialog");
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.StanDialog);
    }

    public void setCount(int c)
    {
        this.count = c;
    }

    public void setAmountDifficult(int a)
    {
        this.amountDifficult = a;
    }

    private void addElement(int e)
    {
        if (offerValue.length() < count)
        {
            changeOfferValue(offerValue + e);
        }
    }
    private void changeOfferValue(String newOfferValue)
    {
        offerValue = newOfferValue;
        offer_value.setText(offerValue);
    }

    @Override
    protected void realize(View v)
    {
        v.findViewById(R.id.add_offer).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (offerValue.length() != count)
                {
                    return;
                }
                getListener().addOffer(offerValue);
                dismiss();
            }
        });
        v.findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                changeOfferValue("");
            }
        });
        offer_value = (TextView) v.findViewById(R.id.offer_value);
        number_element_0 = (Button) v.findViewById(R.id.number_element_0);
        number_element_0.setOnClickListener(clickListener);
        if (amountDifficult == NumbersAddOfferDialog.AMOUNT_DIFFICULT_EASY || amountDifficult == NumbersAddOfferDialog.AMOUNT_DIFFICULT_MEDIUM || amountDifficult == NumbersAddOfferDialog.AMOUNT_DIFFICULT_HARD)
        {
            v.findViewById(R.id.number_element_container_123).setVisibility(View.VISIBLE);
            number_element_1 = (Button) v.findViewById(R.id.number_element_1);
            number_element_2 = (Button) v.findViewById(R.id.number_element_2);
            number_element_3 = (Button) v.findViewById(R.id.number_element_3);
            number_element_1.setOnClickListener(clickListener);
            number_element_2.setOnClickListener(clickListener);
            number_element_3.setOnClickListener(clickListener);
        }
        if (amountDifficult == NumbersAddOfferDialog.AMOUNT_DIFFICULT_MEDIUM || amountDifficult == NumbersAddOfferDialog.AMOUNT_DIFFICULT_HARD)
        {
        }
        if (amountDifficult == NumbersAddOfferDialog.AMOUNT_DIFFICULT_HARD)
        {
        }
    }

    private IGameDialogListener getListener()
    {
        return (IGameDialogListener) listener;
    }
}