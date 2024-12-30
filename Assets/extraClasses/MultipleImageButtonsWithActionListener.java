import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MultipleImageButtonsWithActionListener extends JFrame{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Multiple Image Buttons with ActionListener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setPreferredSize(new Dimension(1300, 600));
        frame.setBounds(50,50,1300,600);
        // Replace these file paths with the paths to your images
        String imagePath1 = "Assets/new_user.png";
        String imagePath2 = "Assets/logout_user.png";
        String imagePath3 = "Assets/new_user.png";
        String imagePath4 = "Assets/new_user.png";
        String imagePath5 = "Assets/new_user.png";
        String imagePath6 = "Assets/new_user.png";
        String imagePath7 = "Assets/new_user.png";
        String imagePath8 = "Assets/new_user.png";
        String imagePath9 = "Assets/new_user.png";
        String imagePath10= "Assets/new_user.png";

        JButton button1 = createImageButton(imagePath1);
        JButton button2 = createImageButton(imagePath2);
        JButton button3 = createImageButton(imagePath3);
        JButton button4 = createImageButton(imagePath4);
        JButton button5 = createImageButton(imagePath5);
        JButton button6 = createImageButton(imagePath6);
        JButton button7 = createImageButton(imagePath7);
        JButton button8 = createImageButton(imagePath8);
        JButton button9 = createImageButton(imagePath9);
        JButton button10 = createImageButton(imagePath10);

        button1.setBounds(50, 50, 100, 100);
        button2.setBounds(300, 50, 100, 100);
        button3.setBounds(550, 50, 100, 100);
        button4.setBounds(800, 50, 100, 100);
        button5.setBounds(1050, 50, 100, 100);
        button6.setBounds(50, 300, 100, 100);
        button7.setBounds(300, 300, 100, 100);
        button8.setBounds(550, 300, 100, 100);
        button9.setBounds(800, 300, 100, 100);
        button10.setBounds(1050, 300, 100, 100);

        // Add ActionListener to each button
        button1.addActionListener(createButtonActionListener("1"));
        button2.addActionListener(createButtonActionListener("2"));
        button3.addActionListener(createButtonActionListener("3"));
        button4.addActionListener(createButtonActionListener("4"));
        button5.addActionListener(createButtonActionListener("5"));
        button6.addActionListener(createButtonActionListener("6"));
        button7.addActionListener(createButtonActionListener("7"));
        button8.addActionListener(createButtonActionListener("8"));
        button9.addActionListener(createButtonActionListener("9"));
        button10.addActionListener(createButtonActionListener("10"));

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(button7);
        frame.add(button8);
        frame.add(button9);
        frame.add(button10);
        frame.pack();
        frame.setVisible(true);
    }

    // Method to create an ActionListener for displaying a message
    private static ActionListener createButtonActionListener(final String buttonName) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JOptionPane.showMessageDialog(null, buttonName + " clicked!");
            }
        };
    }

    // Method to create a JButton with an image
    private static JButton createImageButton(String imagePath) {
        JButton button = new JButton();
        try {
            // Load and set the image on the button
            Image image = ImageIO.read(new File(imagePath));
            button.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return button;
    }
}
