import java.io.File;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.IOException;



public class imageEditor{
    public static BufferedImage convertToGrayScale(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for(int i =0; i < height; i++){
            for(int j =0; j < width; j++){
                outputImage.setRGB(j, i, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }

    public static BufferedImage horizontallyInvert(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);  
        for(int i =0; i < height; i++){
            for(int j =0; j < width; j++){
                newImage.setRGB(j, i, inputImage.getRGB(width-j-1, i));
            }
            System.out.println();
        }
        return newImage;
    }

     public static BufferedImage verticallyInvert(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for(int i =0; i < height; i++){
            for(int j =0; j < width; j++){
                newImage.setRGB(j, i, inputImage.getRGB(j, height-i-1));
            }
            System.out.println();
        }
        return newImage;
    }

    public static BufferedImage converToClockWise(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage newImage = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);
        for(int i =0; i < height; i++){
            for(int j =0; j < width; j++){
                newImage.setRGB(height-1-i, j, inputImage.getRGB(j, i));
            }
            System.out.println();
        }
        return newImage;
    }

    public static BufferedImage converToAntiClockWise(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage newImage = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);
        for(int i =0; i < height; i++){
            for(int j =0; j < width; j++){
                newImage.setRGB(i, width-1-j, inputImage.getRGB(j, i));
            }
            System.out.println();
        }
        return newImage;
    }

    
    public static BufferedImage Brightness(BufferedImage inputImage, int strength){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for(int i =0; i < height; i++){
            for(int j =0; j < width; j++){
                Color Pixel = new Color(inputImage.getRGB(j, i));
                int Red = Pixel.getRed();
                int Green = Pixel.getGreen();
                int Blue = Pixel.getBlue();

                Red = Red + (Red * strength)/100;
                Green = Green + (Green * strength) / 100;
                Blue = Blue + (Blue * strength) /100;

                if(Red > 255) Red = 255;
                if(Green > 255) Green = 255;
                if(Blue > 255) Blue = 255;

                if(Red < 0) Red = 0;
                if(Green < 0) Green = 0;
                if(Blue < 0) Blue = 0;

                Color newPixel = new Color(Red, Green, Blue);
                newImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        return newImage;
    }    

    // public static BufferedImage Blur(BufferedImage inputImage, int n){
    //     int height = inputImage.getHeight();
    //     int width = inputImage.getWidth();
    //     BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
    //     for(int i =0; i<height; i=i+n){
    //         for(int j =0; j<width; j=j+n){
    //             int count = 0;
    //             int sumRed = 0, sumGreen = 0, sumBlue=0;
    //             for(int d =0; d<i+n; d++){
    //                 for(int k =0; k<j+n; k++){
    //                     Color Pixel = new Color(inputImage.getRGB(k, d));
    //                     int Red = Pixel.getRed();
    //                     int Green = Pixel.getGreen();
    //                     int Blue = Pixel.getBlue();

    //                     sumRed += Red;
    //                     sumGreen += Green;
    //                     sumBlue += Blue;
    //                 }
    //             }
    //             int avgRed=sumRed/n*n;
    //             int avgGreen = sumGreen/n*n;
    //             int avgBlue = sumBlue/n*n;


    //         }
    //     }
    //     return newImage;
    // }

    public static BufferedImage blurImage(BufferedImage inputImage, int strength) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage((width), (height), BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i += strength) {
            for (int j = 0; j < width; j += strength) {
                int red = 0, green = 0, blue = 0, count = 0;
                for (int m = i; m < (i + strength) && (m < height); m++) {
                    for (int n = j; n < (j + strength) && (n < width); n++) {
                        Color pixel = new Color(inputImage.getRGB(n, m));
                        red = red + pixel.getRed();
                        green = green + pixel.getGreen();
                        blue = blue + pixel.getBlue();
                        count++;
                    }
                }
                red = red / count;
                green = green / count;
                blue = blue / count;
                Color new_pixel = new Color(red, green, blue);
                for (int m = i; m < (i + strength) && (m < height); m++) {
                    for (int n = j; n < (j + strength) && (n < width); n++) {
                        outputImage.setRGB(n, m, new_pixel.getRGB());
                    }
                }
            }
        }
        return outputImage;
    }

   

    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        try{
            File inputFile = new File("kiki.jpeg");
            BufferedImage inputImage = ImageIO.read(inputFile);
            

            System.out.println("Press 1 : To make the image grayScale");
            System.out.println("Press 2 : To make the image horiZontallyInverted");
            System.out.println("Press 3 : To make the image verticallyInverted");
            System.out.println("Press 4 : To make the image clockWiseFile");
            System.out.println("Press 5 : To make the image antiClockWise");
            System.out.println("Press 6 : To make the image Brighterkiki");
            System.out.println("Press 7 : To make the image blur" );

            int Choice = s.nextInt();
            switch (Choice) {
                case 1:
                BufferedImage grayScale = convertToGrayScale(inputImage);
                File graScaleImage = new File("grayscaleImage.jpg");
                ImageIO.write(grayScale, "jpg", graScaleImage);                    
                break;

                case 2:
                BufferedImage inverted = horizontallyInvert(inputImage);
                File invertedFile = new File("horizontallyInverted.jpg");
                ImageIO.write(inverted, "jpg", invertedFile);
                break;

                case 3:
                BufferedImage verticallyinverted = verticallyInvert(inputImage);
                File verticallyinvertedFile = new File("verticallyInverted.jpg");
                ImageIO.write(verticallyinverted, "jpg", verticallyinvertedFile);
                break;

                case 4:
                BufferedImage clockWise = converToClockWise(inputImage);
                File clockwiseFile = new File("clockwiseFile.jpg");
                ImageIO.write(clockWise, "jpg", clockwiseFile);
                break;

                case 5:
                BufferedImage antiClockWise = converToAntiClockWise(inputImage);
                File anticlockwiseFile = new File("anticlockwiseFile.jpg");
                ImageIO.write(antiClockWise, "jpg", anticlockwiseFile);
                break;

                case 6:
                System.out.print("Enter the percent of brightness(Enter negative to dim the image): ");
                int strength=s.nextInt();
                BufferedImage Brighter = Brightness(inputImage, strength);
                File BrighterFile = new File("Brighterkiki.jpg");
                ImageIO.write(Brighter, "jpg", BrighterFile);
                break;

                case 7:
                System.out.print("Enter the strength of blur: ");
                int strengthBlur=s.nextInt();
                BufferedImage blur = blurImage(inputImage, strengthBlur);
                File blurFile = new File("blurImage.jpg");
                ImageIO.write(blur, "jpg", blurFile);
                System.out.println("Image is saved as blurImage.jpg");
                break;
            
                default:
                System.out.println("Invalid Input");
                    break;
            }
        
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}