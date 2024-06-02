import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Main {

  JFrame frame;
  int frameLength = 600, frameHeight = 550;

  Main() {
    frame = new JFrame();
    frame.setTitle("IDBMS PROJECT");
    frame.setSize(frameLength, frameHeight);
    frame.setLayout(null);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  protected void setTitle(String text) {
    int l = frameLength;
    int h = 50;
    int x = (frameLength - l) / 2;
    int y = 10;

    JLabel title = createJLabel(text, x, y, l, h);
    title.setFont(new Font("Arial", Font.PLAIN, 24));
    title.setOpaque(true);
    title.setForeground(Color.black);
    title.setBackground(Color.lightGray);
    frame.add(title);
  }

  protected void displayCustomerRecords() {

  }

  protected void mainMenu() {

    int h = 30;
    int l = 300;
    int y = 80;
    int x = frameLength / 4;

    // button 1
    JButton cust_rec = createButton("Customers Records", x, y, l, h);
    cust_rec.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        int tableFrameLength = 700, tableFrameHeight = 400;
        JFrame displayTable = new JFrame("Customer Records");

        // adding table to frame
        String data[][] = { { "101", "Amit", "670000" },
            { "102", "Jai", "780000" },
            { "101", "Sachin", "700000" } };
        String attributes[] = { "ID", "NAME", "SALARY" };

        JTable records = new JTable(data, attributes);
        records.setRowHeight(records.getRowHeight() + 20);
        records.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        records.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane sp = new JScrollPane(records);

        displayTable.add(sp);
        displayTable.setSize(tableFrameLength, tableFrameHeight);
        displayTable.setLocationRelativeTo(null);
        displayTable.setVisible(true);
      }

    });
    ;
    frame.add(cust_rec);

    // button 2
    JButton addCustomer = createButton("Add Customer", x, y + (h + 10), l, h);
    frame.add(addCustomer);

    // button 3
    JButton deleteCustomer = createButton("Delete Customer", x, y + 2 * (h + 10), l, h);
    frame.add(deleteCustomer);

    // button 4
    JButton updateCustomer = createButton("Update Customer", x, y + 3 * (h + 10), l, h);
    frame.add(updateCustomer);

    // button 5
    JButton accountDetails = createButton("Account Details", x, y + 4 * (h + 10), l, h);
    frame.add(accountDetails);

    // button 6
    JButton loanDetails = createButton("Loan Details", x, y + 5 * (h + 10), l, h);
    frame.add(loanDetails);

    // button 7
    JButton depositMoney = createButton("Deposit Money", x, y + 6 * (h + 10), l, h);
    frame.add(depositMoney);

    // button 8
    JButton withdrawMoney = createButton("Withdraw Money", x, y + 7 * (h + 10), l, h);
    frame.add(withdrawMoney);

  }

  protected JLabel createJLabel(String text, int x, int y, int length, int height) {
    JLabel label = new JLabel(text, SwingConstants.CENTER);
    label.setBounds(x, y, length, height);
    return label;
  }

  protected JButton createButton(String text, int... properties) {
    JButton button = new JButton(text);

    if (properties.length >= 4) {
      button.setBounds(properties[0], properties[1], properties[2], properties[3]);
    }
    return button;
  }

  public static void main(String[] args) {
    Main obj = new Main();
    obj.setTitle("Banking Management System");
    obj.mainMenu();
  }

}
