/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author chris
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class ColorPicker extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private ButtonGroup colorOptionsGroup = new ButtonGroup();
    private String selectedColor;

    public ColorPicker() {
        setTitle("Choose Color");
        setBounds(50, 100, 250, 180);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        contentPanel.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JRadioButton rdbtnRed = new JRadioButton("Red");
        rdbtnRed.setActionCommand("Red");
        colorOptionsGroup.add(rdbtnRed);
        panel.add(rdbtnRed);

        JRadioButton rdbtnYellow = new JRadioButton("Yellow");
        colorOptionsGroup.add(rdbtnYellow);
        panel.add(rdbtnYellow);

        JRadioButton rdbtnGreen = new JRadioButton("Green");
        rdbtnRed.setActionCommand("Green");
        colorOptionsGroup.add(rdbtnGreen);
        panel.add(rdbtnGreen);

        JRadioButton rdbtnBlue = new JRadioButton("Blue");
        rdbtnRed.setActionCommand("Blue");
        colorOptionsGroup.add(rdbtnBlue);
        panel.add(rdbtnBlue);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                submitColor();
                dispose();
            }
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
    }

    public void submitColor() {
        for (Enumeration<AbstractButton> buttons = colorOptionsGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                selectedColor = button.getText();
            }
        }

    }

    public String getSelectedColor() {
        return selectedColor;
    }

}
