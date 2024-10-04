import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherAppGui extends JFrame {
    public WeatherAppGui(){
        // Set Our gui and add a title
        super("Weather App");


        // Configure gui to end the program's process once it has been closed

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set the Size of our Gui (in pixels)

        setSize(450, 650);

        // Load our gui at the Center of the screen
        setLocationRelativeTo(null);

        //Make our layout manager null to manually posotion our components withun the gui
        setLayout(null);

        // Prevent any resize of our gui
        setResizable(false);

        addGuiComponents();
    }
    private void addGuiComponents(){
        //Search field
        JTextField searchTextField = new JTextField();

        // set the location and Size of Our Component
        searchTextField.setBounds(15, 15, 351, 45);

        // change the font Style and Size
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(searchTextField);

        //Search button

        JButton searchButton = new JButton(loadImage("Assest/search.png"));

        // change the cursor to a hand cursor when hovering Over this button
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375, 13, 47, 45);
        add(searchButton);

        // Weather image

        JLabel weatherConditionImage = new JLabel(loadImage("Assest/cloudy.png"));
        weatherConditionImage.setBounds(0, 125, 450, 217);
        add(weatherConditionImage);

        // temperature text
        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setBounds(0, 350, 450, 54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));

        // center the text
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        // weather condition description
        JLabel weatherConditionDesc = new JLabel("Cloudy");
        weatherConditionDesc.setBounds(0, 405, 450, 36);
        weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32 ));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);

        // humidity image
        JLabel humidityImage = new JLabel(loadImage("Assest/humidity.png"));
        humidityImage.setBounds(15, 500, 74, 66);
        add(humidityImage);

        // humidity text
        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
        humidityText.setBounds(90, 500,74,66);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(humidityText);

        // WindSpeed Image

        JLabel windspeedImage = new JLabel(loadImage("Assest/windspeed.png"));
        windspeedImage.setBounds(220, 500, 74, 66);
        add(windspeedImage);

        //WindSpeed Text

        JLabel windspeedtext = new JLabel("<html><b>Windspeed</b> 15km/h</html>");
        windspeedtext.setBounds(310, 500, 85, 55);
        windspeedtext.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(windspeedtext);
    }

    //used to create images in our gui components
    private ImageIcon loadImage(String resourcePath){
       try{
           // read the image file from the path given
           BufferedImage Image = ImageIO.read(new File(resourcePath));

           // return an image icone that our components can render it
           return new ImageIcon(Image);

       }catch (IOException e){
           e.printStackTrace();
       }
        System.out.println("Cloud not find resource");
       return null;
    }

}
