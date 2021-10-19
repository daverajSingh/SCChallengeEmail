package com.company;
import java.net.*;//URL manipulation
import java.io.*;//input output

public class Main {
    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        System.out.println("Please input the username you would like to search: ");  //asks user for input
        BufferedReader inputID = new BufferedReader(new InputStreamReader(System.in));//bufferedReader to get input from console
        String x;//placeholder variable for id
        x = inputID.readLine();//saves input

        String ConstructedURL = "https://www.ecs.soton.ac.uk/people/"; //base url
        ConstructedURL = ConstructedURL + x;//concatenation
        System.out.println(ConstructedURL);//outputs url to console

        URL web = new URL(ConstructedURL);//URL object
        URLConnection connection = web.openConnection();//connects to the URL file
        StringBuilder URLcontents = new StringBuilder();

        BufferedReader URLReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        //gets the information from the open connection, and reads the actual file
        String URLline;//one line of the URL
        while ((URLline = URLReader.readLine()) != null)//reads from the URLconnection through the buffered reader
        {
            URLcontents.append(URLline + "\n");//appends line to the rest of the file
        }
        URLReader.close();//closes reader when there is no more url to read (when the while loop finishes)

        //This final part could be worked on to be more efficient, however it works so yeah


        String[] URLtoArray = URLcontents.toString().split("\n");
        //turns the stringbuilder object into a string, then makes it into an array, splitting each element in the array by a new line
        //This allows for me to pick the specific line the name is on

        String nameUnfiltered = URLtoArray[6];//finds 6th line of the URL, this has the name of the person

        //these lines remove the unnecessary HTML from the URL, to allow for only the name to be printed.
        String nameFiltered = nameUnfiltered.replace("| Electronics and Computer Science | University of Southampton</title>", "");
        nameFiltered = nameFiltered.replace("    <title>", "");

        System.out.println(nameFiltered);//outputs name

    }
}

