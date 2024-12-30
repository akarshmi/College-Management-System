import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;

public class ThreeCardsWithLocalImages {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Three Cards with Local Images");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);

        // Replace these file paths with your local image paths
        String imagePath1 = "Assets/new_user.png";
        String imagePath2 = "C:\\Users\\namas\\Desktop\\Akarsh_AkkiSir\\Assets\\new_user.png";
        String imagePath3 = "Assets/new_user.png";

        // Create cards using JPanels with local images
        JPanel card1 = createCard("Card 1", imagePath1);
        JPanel card2 = createCard("Card 2", imagePath2);
        JPanel card3 = createCard("Card 3", imagePath3);

        // Create a panel to hold the cards side by side
        JPanel cardsPanel = new JPanel(new FlowLayout());
        cardsPanel.add(card1);
        cardsPanel.add(card2);
        cardsPanel.add(card3);

        // Add the cards panel to the frame
        frame.add(cardsPanel);

        // Show the frame
        frame.setVisible(true);
    }

    // Method to create a card with local image and text
    private static JPanel createCard(String cardTitle, String imagePath) {
        JPanel card = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel(cardTitle);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(titleLabel, BorderLayout.NORTH);

        // Load and display a local image on the card
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            card.add(imageLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return card;
    }
}
