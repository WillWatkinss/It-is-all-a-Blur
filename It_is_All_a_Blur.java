/* Course: CS 335
 * Author: William Watkins
 * Project: It is All a Blur
 * Date: 10/25/2020
 * Purpose:  read an image and allow the user to apply a Gaussian blurring kernel
 **/

/***************************************************************************

 Loads an image (JPEG or GIF), displays it, selects from
 a small set of image processing routines, and shows the results

 ***************************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;

public class It_is_All_a_Blur extends JFrame {

    // This frame will hold the primary components:
    // 	An object to hold the buffered image and its associated operations
    //	Components to control the interface

    // Instance variables
    private BufferedImage image;   // the image
    private MyImageObj view;       // a component in which to display an image
    private JLabel infoLabel;      // an informative label for the simple GUI
    private JButton OriginalButton;// Button to restore original image
    private JButton b1_0;
    private JButton b1_5;
    private JButton b2_0;
    private JButton b2_5;
    private JButton b3_0;
    private JButton quit;
    private int which_button;
    private float customfiltervalues[];

    // Constructor for the frame
    public It_is_All_a_Blur () {

        super();				// call JFrame constructor

        this.buildMenus();		// helper method to build menus
        this.buildComponents();		// helper method to set up components
        this.buildDisplay();		// Lay out the components on the display
    }

    //  Builds the menus to be attached to this JFrame object
    //  Primary side effect:  menus are added via the setJMenuBar call
    //  		Action listeners for the menu items are anonymous inner
    //		classes here
    //  This helper method is called once by the constructor

    private void buildMenus () {

        final JFileChooser fc = new JFileChooser(".");
        JMenuBar bar = new JMenuBar();
        this.setJMenuBar (bar);
        JMenu fileMenu = new JMenu ("File");
        JMenuItem fileopen = new JMenuItem ("Open");
        JMenuItem fileexit = new JMenuItem ("Exit");

        fileopen.addActionListener(
                new ActionListener () {
                    public void actionPerformed (ActionEvent e) {
                        int returnVal = fc.showOpenDialog(It_is_All_a_Blur.this);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fc.getSelectedFile();
                            try {
                                image = ImageIO.read(file);
                            } catch (IOException e1){};

                            view.setImage(image);
                            view.showImage();
                        }
                    }
                }
        );
        fileexit.addActionListener(
                new ActionListener () {
                    public void actionPerformed (ActionEvent e) {
                        System.exit(0);
                    }
                }
        );

        fileMenu.add(fileopen);
        fileMenu.add(fileexit);
        bar.add(fileMenu);
    }

    //  Allocate components (these are all instance vars of this frame object)
    //  and set up action listeners for each of them
    //  This is called once by the constructor

    private void buildComponents() {

        // Create components to in which to display image and GUI controls
        // reads a default image
        view = new MyImageObj(readImage("Cybercriminal.jpg"));
        infoLabel = new JLabel("Original Image");
        OriginalButton = new JButton("Original");
        b1_0 = new JButton("1.0");
        b1_5 = new JButton("1.5");
        b2_0 = new JButton("2.0");
        b2_5 = new JButton("2.5");
        b3_0 = new JButton("3.0");
        quit = new JButton("Quit");
        customfiltervalues = new float[9];

        // Button listeners activate the buffered image object in order
        // to display appropriate function
        OriginalButton.addActionListener(
                new ActionListener () {
                    public void actionPerformed (ActionEvent e) {
                        view.showImage();
                        infoLabel.setText("Original");
                    }
                }
        );

        b1_0.addActionListener(
                new ActionListener () {
                    public void actionPerformed (ActionEvent e) {
                        which_button = 1;
                        loadcustomvalues();
                        view.CustomImage();
                        infoLabel.setText("Gaussian = 1.0");
                    }
                }
        );
        b1_5.addActionListener(
                new ActionListener () {
                    public void actionPerformed (ActionEvent e) {
                        which_button = 2;
                        loadcustomvalues();
                        view.CustomImage();
                        infoLabel.setText("Gaussian = 1.5");
                    }
                }
        );
        b2_0.addActionListener(
                new ActionListener () {
                    public void actionPerformed (ActionEvent e) {
                        which_button = 3;
                        loadcustomvalues();
                        view.CustomImage();
                        infoLabel.setText("Gaussian = 2.0");
                    }
                }
        );
        b2_5.addActionListener(
                new ActionListener () {
                    public void actionPerformed (ActionEvent e) {
                        which_button = 4;
                        loadcustomvalues();
                        view.CustomImage();
                        infoLabel.setText("Gaussian = 2.5");
                    }
                }
        );
        b3_0.addActionListener(
                new ActionListener () {
                    public void actionPerformed (ActionEvent e) {
                        which_button = 5;
                        loadcustomvalues();
                        view.CustomImage();
                        infoLabel.setText("Gaussian = 3.0");
                    }
                }
        );

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void loadcustomvalues () {

        customfiltervalues = new float[25];
        if (which_button == 1){
            customfiltervalues = new float[]{
                    (float) 0.003765, (float) 0.015019, (float) 0.023792, (float) 0.015019, (float) 0.003765,
                    (float) 0.015019, (float) 0.059912, (float) 0.094907, (float) 0.059912, (float) 0.015019,
                    (float) 0.023792, (float) 0.094907, (float) 0.150342, (float) 0.094907, (float) 0.023792,
                    (float) 0.015019, (float) 0.059912, (float) 0.094907, (float) 0.059912, (float) 0.015019,
                    (float) 0.003765, (float) 0.015019, (float) 0.023792, (float) 0.015019, (float) 0.003765};
        }
        else if (which_button == 2){
            customfiltervalues = new float[]{
                    (float) 0.015026, (float) 0.028569, (float) 0.035391, (float) 0.028569, (float) 0.015026,
                    (float) 0.028569, (float) 0.054318, (float) 0.067288, (float) 0.054318, (float) 0.028569,
                    (float) 0.035391, (float) 0.067288, (float) 0.083355, (float) 0.067288, (float) 0.035391,
                    (float) 0.028569, (float) 0.054318, (float) 0.067288, (float) 0.054318, (float) 0.028569,
                    (float) 0.015026, (float) 0.028569, (float) 0.035391, (float) 0.028569, (float) 0.015026};
        }
        else if(which_button == 3){
            customfiltervalues = new float[]{
                    (float) 0.023528, (float) 0.033969, (float) 0.038393, (float) 0.033969, (float) 0.023528,
                    (float) 0.033969, (float) 0.049045, (float) 0.055432, (float) 0.049045, (float) 0.033969,
                    (float) 0.038393, (float) 0.055432, (float) 0.062651, (float) 0.055432, (float) 0.038393,
                    (float) 0.033969, (float) 0.049045, (float) 0.055432, (float) 0.049045, (float) 0.033969,
                    (float) 0.023528, (float) 0.033969, (float) 0.038393, (float) 0.033969, (float) 0.023528};
        }
        else if(which_button == 4){
            customfiltervalues = new float[]{
                    (float) 0.028672, (float) 0.036333, (float) 0.039317, (float) 0.036333, (float) 0.028672,
                    (float) 0.036333, (float) 0.046042, (float) 0.049824, (float) 0.046042, (float) 0.036333,
                    (float) 0.039317, (float) 0.049824, (float) 0.053916, (float) 0.049824, (float) 0.039317,
                    (float) 0.036333, (float) 0.046042, (float) 0.049824, (float) 0.046042, (float) 0.036333,
                    (float) 0.028672, (float) 0.036333, (float) 0.039317, (float) 0.036333, (float) 0.028672};
        }
        else if (which_button == 5){
            customfiltervalues = new float[]{
                    (float) 0.031827, (float) 0.037541, (float) 0.039665, (float) 0.037541, (float) 0.031827,
                    (float) 0.037541, (float) 0.044281, (float) 0.046787, (float) 0.044281, (float) 0.037541,
                    (float) 0.039665, (float) 0.046787, (float) 0.049434, (float) 0.046787, (float) 0.039665,
                    (float) 0.037541, (float) 0.044281, (float) 0.046787, (float) 0.044281, (float) 0.037541,
                    (float) 0.031827, (float) 0.037541, (float) 0.039665, (float) 0.037541, (float) 0.031827};
        }
    }

    // This helper method adds all components to the content pane of the
    // JFrame object.  Specific layout of components is controlled here

    private void buildDisplay () {

        // Build first JPanel
        JPanel controlPanel = new JPanel();
        GridLayout grid = new GridLayout (1, 5);
        controlPanel.setLayout(grid);
        controlPanel.add(infoLabel);
        controlPanel.add(OriginalButton);
        controlPanel.add(b1_0);
        controlPanel.add(b1_5);
        controlPanel.add(b2_0);
        controlPanel.add(b2_5);
        controlPanel.add(b3_0);
        controlPanel.add(quit);

        // Add panels and image data component to the JFrame
        Container c = this.getContentPane();
        c.add(view, BorderLayout.EAST);
        c.add(controlPanel, BorderLayout.SOUTH);
    }

    // This method reads an Image object from a file indicated by
    // the string provided as the parameter.  The image is converted
    // here to a BufferedImage object, and that new object is the returned
    // value of this method.
    // The mediatracker in this method can throw an exception

    public BufferedImage readImage (String file) {

        Image image = Toolkit.getDefaultToolkit().getImage(file);
        MediaTracker tracker = new MediaTracker (new Component () {});
        tracker.addImage(image, 0);
        try { tracker.waitForID (0); }
        catch (InterruptedException e) {}
        BufferedImage bim = new BufferedImage
                (image.getWidth(this), image.getHeight(this),
                        BufferedImage.TYPE_INT_RGB);
        Graphics2D big = bim.createGraphics();
        big.drawImage (image, 0, 0, this);
        return bim;
    }

    // The main method allocates the "window" and makes it visible.
    // The windowclosing event is handled by an anonymous inner (adapter)
    // class
    // Command line arguments are ignored.

    public static void main(String[] argv) {

        JFrame frame = new It_is_All_a_Blur();
        frame.setSize(1000, 800);
        frame.setVisible(true);
        frame.addWindowListener (
                new WindowAdapter () {
                    public void windowClosing ( WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
    }

    /*****************************************************************

     This is a helper object, which could reside in its own file, that
     extends JLabel so that it can hold a BufferedImage

     I've added the ability to apply image processing operators to the
     image and display the result

     ***************************************************************************/

    public class MyImageObj extends JLabel {

        // instance variable to hold the buffered image
        private BufferedImage bim=null;
        private BufferedImage filteredbim=null;

        //  tell the paintcomponent method what to draw
        private boolean showfiltered=false;

        // Default constructor
        public MyImageObj() {
        }

        // This constructor stores a buffered image passed in as a parameter
        public MyImageObj(BufferedImage img) {
            bim = img;
            filteredbim = new BufferedImage
                    (bim.getWidth(), bim.getHeight(), BufferedImage.TYPE_INT_RGB);
            setPreferredSize(new Dimension(bim.getWidth(), bim.getHeight()));

            this.repaint();
        }

        // This mutator changes the image by resetting what is stored
        // The input parameter img is the new image;  it gets stored as an
        //     instance variable
        public void setImage(BufferedImage img) {
            if (img == null) return;
            bim = img;
            filteredbim = new BufferedImage
                    (bim.getWidth(), bim.getHeight(), BufferedImage.TYPE_INT_RGB);
            setPreferredSize(new Dimension(bim.getWidth(), bim.getHeight()));
            showfiltered=false;
            this.repaint();
        }

        // accessor to get a handle to the bufferedimage object stored here
        public BufferedImage getImage() {
            return bim;
        }

        //  apply the detect-edge operator
        public void CustomImage() {
            if (bim == null) return;
            Kernel kernel = new Kernel (5, 5, customfiltervalues);
            ConvolveOp cop = new ConvolveOp (kernel, ConvolveOp.EDGE_NO_OP, null);

            // make a copy of the buffered image
            BufferedImage newbim = new BufferedImage
                    (bim.getWidth(), bim.getHeight(),
                            BufferedImage.TYPE_INT_RGB);
            Graphics2D big = newbim.createGraphics();
            big.drawImage (bim, 0, 0, null);

            // apply the filter the copied image
            // result goes to a filtered copy
            cop.filter(newbim, filteredbim);
            showfiltered=true;
            this.repaint();
        }

        //  show current image by a scheduled call to paint()
        public void showImage() {
            if (bim == null) return;
            showfiltered=false;
            this.repaint();
        }

        //  get a graphics context and show either filtered image or
        //  regular image
        public void paintComponent(Graphics g) {
            Graphics2D big = (Graphics2D) g;
            if (showfiltered)
                big.drawImage(filteredbim, 0, 0, this);
            else
                big.drawImage(bim, 0, 0, this);
        }
    }

}
