import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.lang.Math;
import java.math.BigInteger;

public class calculatorFrame implements ActionListener {

   Frame frame = new Frame("Java Calculator");
   Panel panel = new Panel();
   TextField resultField = new TextField();
   TextField displayField = new TextField();
   Button[] functionBtn = new Button[14];
   Button[] numpadsBtn = new Button[10];
   Button addBtn, subBtn, divBtn, mulBtn, decBtn, equBtn, delBtn, clrBtn, percentBtn, negBtn, prevBtn, expnBtn, sqrBtn,
         factBtn;

   // add font styles here
   Font displayFont = new Font("Arial", Font.BOLD, 36);
   Font resultFont = new Font("Arial", Font.BOLD, 36);
   Font numpadFont = new Font("Arial", Font.PLAIN, 25);
   Font functionFont = new Font("Arial", Font.PLAIN, 22);
   Font additionalFont = new Font("Arial", Font.PLAIN, 18);

   // Frame Color
   Color buttonColor = new Color(30, 30, 30);

   // button color
   Color frameColor = new Color(247, 247, 247);

   String opr;
   double num1 = 0, num2 = 0, result = 0;
   double temp = 0;

   calculatorFrame() {

      // customizes the frame
      frame.setSize(500, 700);
      frame.setLayout(null);
      frame.setVisible(true);
      frame.setResizable(false);
      frame.setBackground(frameColor);

      percentBtn = new Button("%");
      negBtn = new Button("+/-");
      addBtn = new Button("+");
      subBtn = new Button("-");
      divBtn = new Button("/");
      mulBtn = new Button("x");
      decBtn = new Button(".");
      equBtn = new Button("=");
      delBtn = new Button("Del");
      clrBtn = new Button("C");
      prevBtn = new Button("prev");
      expnBtn = new Button("x^2");
      sqrBtn = new Button("√");
      factBtn = new Button("x!");

      // !add exponent functions
      functionBtn[0] = addBtn;
      functionBtn[1] = subBtn;
      functionBtn[2] = divBtn;
      functionBtn[3] = mulBtn;
      functionBtn[4] = decBtn;
      functionBtn[5] = equBtn;
      functionBtn[6] = delBtn;
      functionBtn[7] = clrBtn;
      functionBtn[8] = percentBtn;
      functionBtn[9] = negBtn;
      functionBtn[10] = prevBtn;
      functionBtn[11] = expnBtn;
      functionBtn[12] = sqrBtn;
      functionBtn[13] = factBtn;

      // add listener to functions
      for (int i = 0; i < 14; i++) {
         functionBtn[i].addActionListener(this);
         functionBtn[i].setFocusable(false);
         functionBtn[i].setFont(functionFont);
         functionBtn[i].setBackground(buttonColor);
         functionBtn[i].setForeground(Color.white);
      }

      // add listeners to numpads
      for (int i = 0; i < 10; i++) {
         numpadsBtn[i] = new Button(String.valueOf(i));
         numpadsBtn[i].addActionListener(this);
         numpadsBtn[i].setFocusable(false);
         numpadsBtn[i].setFont(numpadFont);
         numpadsBtn[i].setBackground(buttonColor);
         numpadsBtn[i].setForeground(Color.white);
      }

      // add and set resultField
      resultField.setBounds(35, 70, 430, 60);
      resultField.setFont(resultFont);
      frame.add(resultField);

      // add and set displayField
      displayField.setBounds(35, 130, 430, 60);
      displayField.setFont(displayFont);
      frame.add(displayField);

      // // set previous button
      // prevBtn.setBounds(360, 140, 90, 40);
      // prevBtn.setFont(additionalFont);
      // frame.add(prevBtn);

      // add and set the panel
      panel = new Panel();
      panel.setBounds(35, 210, 430, 430);
      panel.setLayout(new GridLayout(6, 4, 4, 4));

      // add Buttons to panel and adjust
      panel.add(expnBtn);
      panel.add(prevBtn);
      panel.add(delBtn);
      panel.add(clrBtn);
      panel.add(factBtn);
      panel.add(sqrBtn);
      panel.add(percentBtn);
      panel.add(divBtn);
      panel.add(numpadsBtn[7]);
      panel.add(numpadsBtn[8]);
      panel.add(numpadsBtn[9]);
      panel.add(mulBtn);
      panel.add(numpadsBtn[4]);
      panel.add(numpadsBtn[5]);
      panel.add(numpadsBtn[6]);
      panel.add(subBtn);
      panel.add(numpadsBtn[1]);
      panel.add(numpadsBtn[2]);
      panel.add(numpadsBtn[3]);
      panel.add(addBtn);
      panel.add(negBtn);
      panel.add(numpadsBtn[0]);
      panel.add(decBtn);
      panel.add(equBtn);

      // add components
      frame.add(panel);
      frame.setVisible(true);

      // disposes the frame window onClick (X)
      frame.addWindowListener(
            new WindowAdapter() {

               public void windowClosing(WindowEvent e) {
                  frame.dispose();
               }
            });
   }

   // action performed for buttons and functions

   @Override
   public void actionPerformed(ActionEvent e) {

      for (int i = 0; i < 10; i++) {
         if (e.getSource() == numpadsBtn[i]) {
            resultField.setText(resultField.getText().concat(String.valueOf(i)));

         }
      }
      if (e.getSource() == addBtn) {
         

         if(num1 <= 0){
            num1 = Double.parseDouble(resultField.getText());
         }
         else{
            num2 = Double.parseDouble(resultField.getText());
            result = num1 + num2;
            num1 = result;
         }
         opr = "+";
         resultField.setText("");
      }
      if (e.getSource() == subBtn) {
         if (num1 == 0) {
            num1 = Double.parseDouble(resultField.getText());
         } else {
            num2 = Double.parseDouble(resultField.getText());
            result = num1 - num2;
            num1 = result;
         }
         opr = "-";
         resultField.setText("");
      }
      if (e.getSource() == mulBtn) {
         if (num1 == 0) {
            num1 = Double.parseDouble(resultField.getText());
         } else {
            num2 = Double.parseDouble(resultField.getText());
            result = num1 * num2;
            num1 = result;
         }
         opr = "X";
         resultField.setText("");
      }
      if (e.getSource() == divBtn) {
         if (num1 == 0) {
            num1 = Double.parseDouble(resultField.getText());
         } else {
            num2 = Double.parseDouble(resultField.getText());
            result = num1 / num2;
            num1 = result;
         }
         opr = "/";
         resultField.setText("");
      }

      // !not working yet
      if (e.getSource() == percentBtn) {
         num1 = Double.parseDouble(resultField.getText());
         opr = "%";
      }
      // exponent function
      if (e.getSource() == expnBtn) {
         num1 = Double.parseDouble(resultField.getText());
         opr = "x^2";
      }

      // root function
      if (e.getSource() == sqrBtn) {
         num1 = Double.parseDouble(resultField.getText());
         opr = "root";
      }

      if(e.getSource() == factBtn){
         num1 = Double.parseDouble(resultField.getText());
         opr = "x!";
      }


      if (e.getSource() == equBtn) {

         num2 = Double.parseDouble(resultField.getText());

         switch (opr) {
            case "+":
               result = num1 + num2;
               opr = "";
               break;
            case "-":
               result = num1 - num2;
               opr = "";
               break;
            case "X":
               result = num1 * num2;
               opr = "";
               break;
            case "/":
               result = num1 / num2;
               opr = "";
               break;
            case "%":
               result = num1 * (num2 / 100);
               opr = "";
               break;
            case "root":
               result = Math.sqrt(num1);
               opr = "";
               break;
            case "x^2":
               result = num1 * num1;
               opr = "";
               break;
            case "x!":
               BigInteger factorial = BigInteger.ONE;
               for (int i = 1; i <= num1; i++) {
                  factorial = factorial.multiply(BigInteger.valueOf(i));
               }
               result = factorial.doubleValue();
               break;
         }
         resultField.setText(String.valueOf(result));
         num1 = 0;
         opr = "";
         temp = result;
      }

      // gets the previous result
      if (e.getSource() == prevBtn) {
         if (temp != 0) {
            num1 = temp;
            resultField.setText(String.valueOf(num1));
            temp = 0;
         }
      }

      // Decimal function
      if (e.getSource() == decBtn) {
         if (!resultField.getText().contains(".")) {
            resultField.setText(resultField.getText() + ".");
         }
      }

      // deletes the previous number
      if (e.getSource() == delBtn) {
         String string = resultField.getText();
         resultField.setText("");
         for (int i = 0; i < string.length() - 1; i++) {
            resultField.setText(resultField.getText() + string.charAt(i));
         }
      }
      // deletes the entirem resultField
      if (e.getSource() == clrBtn) {
         resultField.setText("");
      }

      // funciton for negate function
      if (e.getSource() == negBtn) {
         String val = resultField.getText();
         Double value = Double.parseDouble(val);

         boolean negateNum1 = false; // Flag to indicate if num1 should be negated
         boolean negateNum2 = false; // Flag to indicate if num2 should be negated

         // Check the negate flags and apply negation accordingly
         if (negateNum1) {
            num1 = -value;
            negateNum1 = false; // Reset the flag
         } else if (negateNum2) {
            num2 = -value;
            negateNum2 = false; // Reset the flag
         } else {
            // By default, negate num1
            num1 = -value;
         }

         resultField.setText(String.valueOf(num1));
      }
   }

   // create new instance of the class
   public static void main(String[] args) {
      new calculatorFrame();
   }
}
