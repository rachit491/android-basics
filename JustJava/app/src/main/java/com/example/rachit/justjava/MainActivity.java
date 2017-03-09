package com.example.rachit.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    int costPerItem = 5;
    boolean whippedCream = false;
    boolean chocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        float cost = calculatePrice();

        EditText nameView = (EditText) findViewById(R.id.name_view);
        String name = nameView.getText().toString();

        createOrderSummary(name, cost);
    }

    public void checkBoxClicked(View view) {
        switch (view.getId()) {
            case R.id.chocolate_checkbox :
                chocolate = ((CheckBox) view).isChecked();
                break;
            case R.id.whipped_cream_checkbox :
                whippedCream = ((CheckBox) view).isChecked();
                break;
        }
    }

    public void createOrderSummary(String name, float cost) {
        String message = "Name: " + name + "\n";
        message += "Add Whipped Cream? " + whippedCream + "\n";
        message += "Add Chocolate? " + chocolate + "\n";
        message += "Quantity: " + quantity + "\n";
        message += "Total: $" + cost + "\n";
        message += "Thank You!";
        //displayMessage(message);

        composeEmail("rachit491@gmail.com", "JustJava order for " + name, message);
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int cost = costPerItem;

        if(whippedCream)    cost += 1;
        if(chocolate)       cost += 2;

        int price = quantity * cost;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        if(quantity < 100) {
            quantity++;
        }
        else {
            Toast.makeText(this, "You cannot order more than 100 cups!", Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity > 1) {
            quantity--;
        }
        else {
            Toast.makeText(this, "You cannot order less than 1 cup!", Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    /*private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }*/

    public void composeEmail(String address, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        //intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));   //only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}