import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.lang.Math;
import java.math.BigInteger;
import java.util.regex.Pattern;

public class calculatorFrame implements ActionListener {

   Frame frame = new Frame("Calculator");
   Panel panel = new Panel();
   TextField displayField = new TextField();
   TextField resultField = new TextField();
   TextArea history = new TextArea();
   Panel historyPanel = new Panel();
   Scrollbar scroll = new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, 255);
   StringBuilder historyBuilder = new StringBuilder();
   Button[] functionBtn = new Button[14];
   Button[] numpadsBtn = new Button[10];
   Button addBtn, subBtn, divBtn, mulBtn, decBtn, equBtn, delBtn, clrBtn, percentBtn, negBtn, prevBtn, expnBtn, sqrBtn,
         factBtn;
   Button deleteHistory;

   // add font styles here
   Font displayFont = new Font("Arial", Font.BOLD, 36);
   Font resultFont = new Font("Arial", Font.BOLD, 36);
   Font numpadFont = new Font("Arial", Font.PLAIN, 25);
   Font functionFont = new Font("Arial", Font.PLAIN, 22);
   Font additionalFont = new Font("Arial", Font.PLAIN, 18);

   // Frame Color
   Color buttonColor = new Color(30, 30, 30);

   // Delte button color and font
   Color deleteColor = new Color(0, 67, 220);

   // button color
   Color frameColor = new Color(247, 247, 247);

   String opr;
   double num1 = 0, num2 = 0, result = 0, negtemp = 0;
   double temp = 0;

   calculatorFrame() {

      // customizes the frame
      frame.setSize(750, 700);
      frame.setLayout(null);
      frame.setVisible(true);
      frame.setResizable(false);
      frame.setBackground(frameColor);

      // funstion chuchu
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
      factBtn = new Button("!");

      deleteHistory = new Button("Delete History");
      deleteHistory.addActionListener(this);

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

      // add action listner for button

      // add and set displayField
      displayField.setBounds(35, 70, 430, 60);
      displayField.setFont(resultFont);
      frame.add(displayField);

      // add and set resultField
      resultField.setBounds(35, 130, 430, 60);
      resultField.setFont(displayFont);
      frame.add(resultField);

      // panel for history
      historyPanel.setLayout(new BorderLayout());
      historyPanel.setBounds(490, 70, 200, 430);
      historyPanel.setSize(230, 497);
      historyPanel.add(scroll);
      historyPanel.add(history, BorderLayout.CENTER);
      frame.add(historyPanel);

      // clear history button

      deleteHistory.setBounds(490, 578, 230, 60);
      deleteHistory.setFont(functionFont);
      deleteHistory.setFocusable(false);
      deleteHistory.setBackground(buttonColor);
      deleteHistory.setForeground(Color.white);

      frame.add(deleteHistory);

      // text field for history
      history.setBounds(10, 10, 200, 430);

      history.setFont(additionalFont);
      historyPanel.add(history);

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
            displayField.setText(displayField.getText().concat(String.valueOf(i)));
         }
      }

      if (e.getSource() == addBtn) {

        
            if (num1 == 0 && num2 == 0) {
               num1 = Double.parseDouble(displayField.getText());
               displayField.setText(String.valueOf(num1));
            } else {
               if (num1 != 0 && num2 != 0) {
                  displayField.setText(String.valueOf(result));
                  num1 = result;
               } else {
                  displayField.setText(String.valueOf(num1));
                  result = num1 + num2;
               }
            }

            opr = "+";
            displayField.setText(displayField.getText() + opr);
            resultField.setText("");
         
      }
      if (e.getSource() == subBtn) {
         if (num1 == 0 && num2 == 0) {
            num1 = Double.parseDouble(displayField.getText());
            displayField.setText(String.valueOf(num1));
         } else {
            if (num1 != 0 && num2 != 0) {
               displayField.setText(String.valueOf(result));
               num1 = result;
            } else {
               displayField.setText(String.valueOf(num1));
               result = num1 - num2;
            }
         }

         opr = "-";
         displayField.setText(displayField.getText() + opr);
         resultField.setText("");
      }
      if (e.getSource() == mulBtn) {
         if (num1 == 0 && num2 == 0) {
            num1 = Double.parseDouble(displayField.getText());
            displayField.setText(String.valueOf(num1));
         } else {
            if (num1 != 0 && num2 != 0) {
               displayField.setText(String.valueOf(result));
               num1 = result;
            } else {
               displayField.setText(String.valueOf(num1));
               result *= num1;
            }
         }

         opr = "x";
         displayField.setText(displayField.getText() + opr);
         resultField.setText("");
      }
      if (e.getSource() == divBtn) {
         if (num1 == 0 && num2 == 0) {
            num1 = Double.parseDouble(displayField.getText());
            displayField.setText(String.valueOf(num1));
         } else {
            if (num1 != 0 && num2 != 0) {
               displayField.setText(String.valueOf(result));
               num1 = result;
            } else {
               displayField.setText(String.valueOf(num1));
               result = num1 / num2;
            }
         }

         opr = "/";
         displayField.setText(displayField.getText() + opr);
         resultField.setText("");
      }

      // !not working yet
      if (e.getSource() == percentBtn) {
         num1 = Double.parseDouble(displayField.getText());
         num1 = num1 / 100;
         displayField.setText(String.valueOf(num1));
         opr = "%";
      }
      // exponent function
      if (e.getSource() == expnBtn) {
         num1 = Double.parseDouble(displayField.getText());
         opr = "x^2";
      }

      // root function
      if (e.getSource() == sqrBtn) {
         num1 = Double.parseDouble(displayField.getText());
         opr = "√";
         result = Math.sqrt(num1);
         num1 = result;
      }

      if (e.getSource() == factBtn) {
         num1 = Double.parseDouble(displayField.getText());
         opr = "!";
      }

      String newLine = System.lineSeparator();
      if (e.getSource() == equBtn) {

         if (opr != "!" && opr != "√" && opr != "x^2") {
            String expression = displayField.getText();
            String[] parts = expression.split(Pattern.quote(opr));

            num1 = Double.parseDouble(parts[0]);
            num2 = Double.parseDouble(parts[1]);

         }

         // num2 = Double.parseDouble(displayField.getText());

         switch (opr) {
            case "+":
               result = num1 + num2;
               historyBuilder
                     .append(num1)
                     .append(" + ")
                     .append(num2)
                     .append(" = ")
                     .append(result)
                     .append(newLine);
               opr = "";
               break;
            case "-":
               result = num1 - num2;
               historyBuilder
                     .append(num1)
                     .append(" - ")
                     .append(num2)
                     .append(" = ")
                     .append(result)
                     .append(newLine);
               opr = "";
               break;
            case "x":
               result = num1 * num2;
               historyBuilder
                     .append(num1)
                     .append(" X ")
                     .append(num2)
                     .append(" = ")
                     .append(result)
                     .append(newLine);
               opr = "";
               break;
            case "/":
               result = num1 / num2;
               historyBuilder
                     .append(num1)
                     .append(" / ")
                     .append(num2)
                     .append(" = ")
                     .append(result)
                     .append(newLine);
               opr = "";
               break;
            case "%":
               result = num1 * (num2 / 100);
               historyBuilder
                     .append(num1)
                     .append(" % ")
                     .append(num2)
                     .append(" = ")
                     .append(result)
                     .append(newLine);
               opr = "";
               break;
            case "√":
               num1 = Double.parseDouble(displayField.getText());
               result = Math.sqrt(num1);
               historyBuilder
                     .append("√")
                     .append(num1)
                     .append(" = ")
                     .append(result)
                     .append(newLine);
               opr = "";
               break;
            case "x^2":
               result = num1 * num1;
               historyBuilder
                     .append(num1)
                     .append("^2 = ")
                     .append(result)
                     .append(newLine);
               opr = "";
               break;
            case "!":
               BigInteger factorial = BigInteger.ONE;
               for (int i = 1; i <= num1; i++) {
                  factorial = factorial.multiply(BigInteger.valueOf(i));
               }
               result = factorial.doubleValue();
               historyBuilder
                     .append(num1)
                     .append("! = ")
                     .append(result)
                     .append(newLine);
               break;
         }
         resultField.setText(String.valueOf(result));
         history.setText(historyBuilder.toString());
         num1 = result;
         opr = "";
         temp = result;
      }

      // gets the previous result
      if (e.getSource() == prevBtn) {
         if (temp != 0) {
            num1 = temp;
            displayField.setText(String.valueOf(num1));
            temp = 0;
         }
      }

      // Decimal function
      if (e.getSource() == decBtn) {
         if (!displayField.getText().contains(".")) {
            displayField.setText(displayField.getText() + ".");
         }
      }

      // deletes the previous number
      if (e.getSource() == delBtn) {
         String string = displayField.getText();
         displayField.setText("");
         for (int i = 0; i < string.length() - 1; i++) {
            displayField.setText(displayField.getText() + string.charAt(i));
         }
      }
      // deletes the entirem displayField
      if (e.getSource() == clrBtn) {
         displayField.setText("");
         num1 = 0;
         num2 = 0;
         opr = "";
         resultField.setText("");
      }

      // funciton for negate function
      if (e.getSource() == negBtn) {

         if (opr == null || opr.isEmpty()) {
            num1 = Integer.parseInt(displayField.getText());
            num1 *= -1;
            displayField.setText(String.valueOf(num1));
         } else {
            String val = displayField.getText();
            String[] parts = val.split(Pattern.quote(opr));

            if (parts.length >= 2) {
               num2 = Integer.parseInt(parts[1]);
               num2 *= -1;
               displayField.setText(String.valueOf(num1) + opr + String.valueOf(num2));
               num1 = 0;
            }
         }
         num1 = negtemp;
      }

      if (e.getSource() == deleteHistory) {
         historyBuilder.setLength(0);
         history.setText("");
      }
   }

   // create new instance of the class
   public static void main(String[] args) {
      new calculatorFrame();
   }
}
