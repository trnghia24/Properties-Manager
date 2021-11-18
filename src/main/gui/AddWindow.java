package gui;

import model.ManagementList;
import model.Property;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.ManagerAppGUI2.SCREEN_HEIGHT;
import static gui.ManagerAppGUI2.SCREEN_WIDTH;

public class AddWindow extends JFrame {
    private JFrame frame;
    private JButton buttonAdd;
    private JTextField textAddress;
    private JTextField textPrice;
    private JTextField textCapacity;
    private JTextField textStatus;
    private JTextField textPaid;
    private JLabel titlePanel;
    private JPanel addressPanel;
    private JPanel mainPanel;
    private ManagementList properties = ManagerAppGUI2.getManagementList();


    public AddWindow() {
        setUpFrame();

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonAdd) {

                    Property p = new Property();
                    p.setAddress(textAddress.getText());

                    Double price = Double.valueOf(textPrice.getText());
                    p.setPrice(price);

                    Integer capacity = Integer.valueOf(textCapacity.getText());
                    p.setCapacity(capacity);

                    Boolean status = Boolean.valueOf(textStatus.getText());
                    p.setStatus(status);

                    Boolean paid = Boolean.valueOf(textPaid.getText());
                    p.setPaid(paid);

                    properties.addProperty(p);

                    for (Property x: properties.getProperties()) {
                        System.out.println(x.displayInfo());

                    }


                }

            }
        });
    }

    private void setUpFrame() {
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.add(mainPanel);


        this.setVisible(true);
    }
}
