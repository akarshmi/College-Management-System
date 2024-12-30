import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class DashboardFrame extends JFrame {


    JLabel sri,uri,t1,t2,t3;
    public DashboardFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 610);

        setLocationRelativeTo(null);
        setLayout(null); // Using null layout for absolute positioning
        // Set a custom background image (replace the URL with your own image URL)
        try {
            URL imageUrl = new URL("https://i.ibb.co/Jx5d95F/DSC-0164.jpg"); // Replace with your image URL
            Image backgroundImage = ImageIO.read(imageUrl);
            setContentPane(new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            // If the image fails to load, continue without setting a background
        }




        // Create and add buttons
        JButton button1 = createButton("Sign In", 50, 50, 200, 100);
        JButton button2 = createButton("Log In", 300, 50, 200, 100);
      /*  JButton button3 = createButton("Button 3", 550, 50, 200, 100);
        JButton button4 = createButton("Button 4", 200, 300, 200, 100);
        JButton button5 = createButton("Button 5", 450, 300, 200, 100);*/

        add(button1);
        add(button2);
       /* add(button3);
        add(button4);
        add(button5);*/

       /* sri = new JLabel("Student Related Info.");
        sri.setBounds(100,500,500,100);
        add(sri);
        sri.setFont(new Font("Arial",Font.BOLD,55));*/



    }
    // Helper method to create buttons with specified properties
    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(new ButtonClickListener());
        return button;
    }

    // ActionListener for button click events
    class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Button clicked!"); // Display a message on button click
        }
    }


      public void  dashboardstart(){
            SwingUtilities.invokeLater(() -> {
                DashboardFrame frame = new DashboardFrame();
                frame.setVisible(true);
            });
        }
}
