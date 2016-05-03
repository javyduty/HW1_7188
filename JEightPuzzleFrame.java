/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class JEightPuzzleFrame extends JFrame implements ActionListener{ 
    private int width; //variable for width of the picture
    private int height; //variable for the height of the picture     
    private int position[][]; //array for the position of buttons
    private int count[] = {1,2,3,4,5,6,7,8,9};// track pieces 
    private Image image1;
    private JPanel centerPanel;
    private JButton[][] listOfBtn = new JButton[3][3];
    private String Source;
    private String Title;
    private int ShuffleCount = 0;

    public JEightPuzzleFrame(String Title, String Source){
        this.Source = Source; //storing of path
        this.Title = Title; //storing of the program title

        set(); //call set function
    }
    /**
     * method to crop image 
     * and put pieces in separate 
     * buttons
     */
    private Image getIcon(int i, int j){
         // Read the image from folder
        BufferedImage image=null;
        try{
            image = ImageIO.read(new File(Source));
        }catch(IOException e){
            System.err.println("Image not found");
            System.exit(1);
        }       
        // get height and width of image to resize window
         width = image.getWidth();
         height = image.getHeight(); 
         Image source = (Image) image;
         image1 = createImage(new FilteredImageSource(source.getSource(),
                 new CropImageFilter(j*width/3, i*height/3, 
                     (width/3)+1, height/3)));
         return image1;
    }
    
    /**
     * metohd to set the initial
     * position of the pieces
     * for easy solution, attempted
     * to randomize but couldn't
     */
    private void set(){
        if(ShuffleCount != 0){
            shuffle();
        }
        position = new int[][] {
                {0, 1, 2}, 
                {3, 4, 5}, 
                {6, 7, 8}};
        
        
         centerPanel = new JPanel();
         centerPanel.setLayout(new GridLayout(3, 3, 0, 0)); // 3x3 grid
         add(centerPanel, BorderLayout.CENTER);

         // Read the image from folder
        BufferedImage image=null;
        try{
            image = ImageIO.read(new File(Source));
        }catch(IOException e){
            System.err.println("Image not found");
            System.exit(1);
        }       
        // get the height and width of the image for the window size
         width = image.getWidth();
         height = image.getHeight(); 
         // Set up for initial Game        
         //Button 1 position location
         listOfBtn[0][0] = new JButton();
         centerPanel.add(listOfBtn[0][0]);
         count [0] = 9;
         // Button 2 position location
         listOfBtn[0][1] = new JButton();
         listOfBtn[0][1].addActionListener(this);
         centerPanel.add(listOfBtn[0][1]);
         listOfBtn[0][1].setIcon(new ImageIcon(getIcon(0,0)));
         listOfBtn[0][1].setVisible(true);
         count[1] = 1;
         //Button 3 position location
         listOfBtn[0][2] = new JButton();
         listOfBtn[0][2].addActionListener(this);
         centerPanel.add(listOfBtn[0][2]);
         listOfBtn[0][2].setIcon(new ImageIcon(getIcon(0,1)));
         listOfBtn[0][2].setVisible(true);
         count[2] = 2;
       //Button 4 position location
         listOfBtn[1][0] = new JButton();
         listOfBtn[1][0].addActionListener(this);
         centerPanel.add(listOfBtn[1][0]);
         listOfBtn[1][0].setIcon(new ImageIcon(getIcon(1,1)));
         listOfBtn[1][0].setVisible(true);
         count[3] = 5;
       //Button 5 position location
         listOfBtn[1][1] = new JButton();
         listOfBtn[1][1].addActionListener(this);
         centerPanel.add(listOfBtn[1][1]);
         listOfBtn[1][1].setIcon(new ImageIcon(getIcon(1,2)));
         listOfBtn[1][1].setVisible(true);
         count[4] = 6;
       //Button 6 position location
         listOfBtn[1][2] = new JButton();
         listOfBtn[1][2].addActionListener(this);
         centerPanel.add(listOfBtn[1][2]);
         listOfBtn[1][2].setIcon(new ImageIcon(getIcon(0,2)));
         listOfBtn[1][2].setVisible(true);
         count[5] = 3;
       //Button 7 position location
         listOfBtn[2][0] = new JButton();
         listOfBtn[2][0].addActionListener(this);
         centerPanel.add(listOfBtn[2][0]);
         listOfBtn[2][0].setIcon(new ImageIcon(getIcon(1,0)));
         listOfBtn[2][0].setVisible(true);
         count[6] = 4;
       //Button 8 position location
         listOfBtn[2][1] = new JButton();
         listOfBtn[2][1].addActionListener(this);
         centerPanel.add(listOfBtn[2][1]);
         listOfBtn[2][1].setIcon(new ImageIcon(getIcon(2,0)));
         listOfBtn[2][1].setVisible(true);
         count[7] = 7;
       //Button 9 position location
         listOfBtn[2][2] = new JButton();
         listOfBtn[2][2].addActionListener(this);
         centerPanel.add(listOfBtn[2][2]);
         listOfBtn[2][2].setIcon(new ImageIcon(getIcon(2,1)));
         listOfBtn[2][2].setVisible(true);
         count[8] = 8;

        validate();
        setSize(width, height);
        setTitle(Title);
        setResizable(false); //doesnot allow the size to be altered
        setLocationRelativeTo(null); // center the image
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * attempted to try 
     * and shuffle after first 
     * game is finished
     */
    public void shuffle(){
        Random rand = new Random();
        int index = 0;
        int duplicate = 0;
        int x = 0;
        int y = 0;
        for(int i=0;i<9;i++){
            index = rand.nextInt(9);
            if(index != duplicate){
                duplicate = index;
                position[x][y] = index;
                y++;
                if(y==2){
                    y = 0;
                    x++;
                }
            }
            
        }
    }

    /**
     * main method asks user
     * for file path and sets
     * the title for the game
     */
    public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter file path: ");
            String source = scan.nextLine();
            new JEightPuzzleFrame("Puzzle", source);   
            System.out.println();
    }

    /**
     * method to determine
     * positions of buttons 
     * and where they moved to
     */
    public void actionPerformed(ActionEvent e){

     JButton button = (JButton) e.getSource();
        Dimension size = button.getSize();

        int emptyX = listOfBtn[0][0].getX();
        int emptyY = listOfBtn[0][0].getY();

        int buttonX = button.getX();
        int buttonY = button.getY();
        int buttonPosX = buttonX / size.width;
        int buttonPosY = buttonY / size.height;
        int buttonIndex = position[buttonPosY][buttonPosX];

        if (emptyX == buttonX && (emptyY - buttonY) == size.height ) {          

             int labelIndex = buttonIndex + 3;

             centerPanel.remove(buttonIndex);
             centerPanel.add(listOfBtn[0][0], buttonIndex);
             centerPanel.add(button,labelIndex);
             centerPanel.validate();

             int a = count[buttonIndex];
             count[buttonIndex] = count[labelIndex];
             count[labelIndex] = a;              
        }

        if (emptyX == buttonX && (emptyY - buttonY) == -size.height ) {

             int labelIndex = buttonIndex - 3;
             centerPanel.remove(labelIndex);
             centerPanel.add(button,labelIndex);
             centerPanel.add(listOfBtn[0][0], buttonIndex);

             centerPanel.validate();
             int a = count[buttonIndex];
             count[buttonIndex] = count[labelIndex];
             count[labelIndex] = a; 
        }

        if (emptyY == buttonY && (emptyX - buttonX) == size.width ) {

             int labelIndex = buttonIndex + 1;        
             centerPanel.remove(buttonIndex);
             centerPanel.add(listOfBtn[0][0], buttonIndex);
             centerPanel.add(button,labelIndex);                                 
             centerPanel.validate(); 
             int a = count[buttonIndex];
             count[buttonIndex] = count[labelIndex];
             count[labelIndex] = a; 
        }

        if (emptyY == buttonY && (emptyX - buttonX) == -size.width ) {

             int labelIndex = buttonIndex - 1;            
             centerPanel.remove(buttonIndex);
             centerPanel.add(listOfBtn[0][0], labelIndex);
             centerPanel.add(button,labelIndex);
             centerPanel.validate();

             int a = count[buttonIndex];
             count[buttonIndex] = count[labelIndex];
             count[labelIndex] = a; 
        } 
        if(count[0] == 1 &&
                count[1] == 2 &&
                count[2] == 3 &&
                count[3] == 4 &&
                count[4] == 5 &&
                count[5] == 6 &&
                count[6] == 7 &&
                count[7] == 8 &&
                count[8] == 9){

            JOptionPane.showMessageDialog(centerPanel,"Congratulations!, "
                    + "click ok for new game");
            ShuffleCount++;
            //starts new game with same parameters
            new JEightPuzzleFrame("Puzzle", Source); 
            
        }
    }
}