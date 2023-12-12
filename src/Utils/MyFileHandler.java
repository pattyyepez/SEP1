package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class used to write information to binary and text files, information read from binary and text files
 * and to append information to binary and text files.
 *
 * @author Allan <3
 * @version 1.0
 */
public class MyFileHandler
{
   /** Writes the given string to a file with the given file name
    *
    * @param fileName                  Name of the file.
    * @param str                       String to be written to file.
    * @throws FileNotFoundException    If the specified file isn't found.
    */
   public static void writeToTextFile(String fileName, String str) throws FileNotFoundException
   {
      writeText(fileName, str, false);
   }

   /** Appends the given string to a file with the given file name
    *
    * @param fileName                  Name of the file.
    * @param str                       String to be appended to file.
    * @throws FileNotFoundException    If the specified file isn't found.
    */
   public static void appendToTextFile(String fileName, String str) throws FileNotFoundException
   {
      writeText(fileName, str, true);
   }

   /** writeToTextFile and appendToTextFile are almost identical - only the boolean in the constructor
    * of the FileOutputStream differs. So I made this private method that both methods call
    *
    * @param fileName                  Name of the file.
    * @param str                       String to be written or appended to file.
    * @param append                    True if the user wants to append information, false otherwise.
    * @throws FileNotFoundException    When the specified file cannot be found.
    */
   private static void writeText(String fileName, String str, boolean append) throws FileNotFoundException
   {
      PrintWriter writeToFile = null;

      try
      {
         FileOutputStream fileOutStream = new FileOutputStream(fileName, append);
         writeToFile = new PrintWriter(fileOutStream);
         writeToFile.println(str);
      }
      finally
      {
         if (writeToFile != null)
         {
            writeToFile.close();
         }
      }
   }

   /** Writes the strings in the given array to a file with the given file name
    *
    * @param fileName                  Name of file.
    * @param strs                      Array of strings to be written to file.
    * @throws FileNotFoundException    When the specified file cannot be found.
    */
   public static void writeArrayToTextFile(String fileName, String[] strs) throws FileNotFoundException
   {
      writeText(fileName, strs, false);
   }

   /** Appends the strings in the given array to a file with the given file name
    *
    * @param fileName                  Name of file.
    * @param strs                      Array of strings to be appended to file.
    * @throws FileNotFoundException    When the specified file cannot be found.
    */
   public static void appendArrayToTextFile(String fileName, String[] strs) throws FileNotFoundException
   {
      writeText(fileName, strs, true);
   }

   /** Again, the writeArrayToTextFile and appendArrayToTextFile methods are almost identical.
    * So I made this private method that both methods call
    *
    * @param fileName                  Name of file.
    * @param strs                      Array of strings to be written to file.
    * @param append                    True if the user wants to append information, false otherwise.
    * @throws FileNotFoundException    When the specified file cannot be found.
    */
   private static void writeText(String fileName, String[] strs, boolean append) throws FileNotFoundException
   {
      PrintWriter writeToFile = null;

      try
      {
         FileOutputStream fileOutStream = new FileOutputStream(fileName, append);
         writeToFile = new PrintWriter(fileOutStream);

         for (int i = 0; i < strs.length; i++)
         {
            writeToFile.println(strs[i]);
         }
      }
      finally
      {
         if (writeToFile != null)
         {
            writeToFile.close();
         }
      }
   }

   /** Reads the first line from the file with the given file name and returns it as a String
    *
    * @param fileName                  Name of file.
    * @return                          Returns text read from file.
    * @throws FileNotFoundException    When the specified file cannot be found.
    */
   public String readFromTextFile(String fileName) throws FileNotFoundException
   {
      Scanner readFromFile = null;
      String str = "";

      try
      {
         FileInputStream fileInStream = new FileInputStream(fileName);
         readFromFile = new Scanner(fileInStream);
         str = readFromFile.nextLine();
      }
      finally
      {
         if (readFromFile != null)
         {
            readFromFile.close();
         }
      }
      return str;
   }

   /** Reads all lines from the file with the given file name and returns it as a String[]
    *
    * @param fileName                  Name of file.
    * @return                          Returns all text read from file in a String[] array.
    * @throws FileNotFoundException    When the specified file cannot be found.
    */
   public static String[] readArrayFromTextFile(String fileName) throws FileNotFoundException
   {
      Scanner readFromFile = null;
      ArrayList<String> strs = new ArrayList<String>();

      try
      {
         FileInputStream fileInStream = new FileInputStream(fileName);
         readFromFile = new Scanner(fileInStream);

         while (readFromFile.hasNext())
         {
            strs.add(readFromFile.nextLine());
         }
      }
      finally
      {
         if (readFromFile != null)
         {
            readFromFile.close();
         }
      }

      String[] strsArray = new String[strs.size()];
      return strs.toArray(strsArray);
   }

   /** Writes the given object to a file with the given file name
    *
    * @param fileName                  Name of file.
    * @param obj                       Object to be written to binary file.
    * @throws FileNotFoundException    When the specified file cannot be found.
    * @throws IOException              When an IO error occurs.
    */
   public static void writeToBinaryFile(String fileName, Object obj) throws FileNotFoundException, IOException
   {
      ObjectOutputStream writeToFile = null;

      try
      {
         FileOutputStream fileOutStream = new FileOutputStream(fileName);
         writeToFile = new ObjectOutputStream(fileOutStream);

         writeToFile.writeObject(obj);
      }
      finally
      {
         if (writeToFile != null)
         {
            try
            {
               writeToFile.close();
            }
            catch (IOException e)
            {
               System.out.println("IO Error closing file " + fileName);
            }
         }
      }
   }

   /** Writes the objects in the given array to a file with the given file name
    *
    * @param fileName                  Name of file.
    * @param objs                      Array of objects to be written to binary file.
    * @throws FileNotFoundException    When the specified file cannot be found.
    * @throws IOException              When an IO error occurs.
    */
   public static void writeArrayToBinaryFile(String fileName, Object[] objs) throws FileNotFoundException, IOException
   {
      ObjectOutputStream writeToFile = null;

      try
      {
         FileOutputStream fileOutStream = new FileOutputStream(fileName);
         writeToFile = new ObjectOutputStream(fileOutStream);

         for (int i = 0; i < objs.length; i++)
         {
            writeToFile.writeObject(objs[i]);
         }
      }
      finally
      {
         if (writeToFile != null)
         {
            try
            {
               writeToFile.close();
            }
            catch (IOException e)
            {
               System.out.println("IO Error closing file " + fileName);
            }
         }
      }
   }

   /** Reads the first object from the file with the given file name and returns it
    * Whoever calls the method will need to cast it from type Object to its actual type
    *
    * @param fileName                  Name of file.
    * @return                          First object from the file
    * @throws FileNotFoundException    When the specified file cannot be found.
    * @throws IOException              When an IO error occurs.
    * @throws ClassNotFoundException   When the right class wasn't found.
    */
   public static Object readFromBinaryFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
   {
      Object obj = null;
      ObjectInputStream readFromFile = null;
      try
      {
         FileInputStream fileInStream = new FileInputStream(fileName);
         readFromFile = new ObjectInputStream(fileInStream);
         try
         {
            obj = readFromFile.readObject();
         }
         catch (EOFException eof)
         {
           //Done reading
         }
      }
      finally
      {
         if (readFromFile != null)
         {
            try
            {
               readFromFile.close();
            }
            catch (IOException e)
            {
               System.out.println("IO Error closing file " + fileName);
            }
         }
      }

      return obj;
   }

   /** Reads all objects from the file with the given file name and returns it as an Object[].
    * Whoever calls the method will need to cast the Objects to their actual type
    *
    * @param fileName                  Name of file.
    * @return                          All objects from the file as an Object[].
    * @throws FileNotFoundException    When the specified file cannot be found.
    * @throws IOException              When an IO error occurs.
    * @throws ClassNotFoundException   When the right class wasn't found.
    */
   public static Object[] readArrayFromBinaryFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
   {
      ArrayList<Object> objs = new ArrayList<Object>();

      ObjectInputStream readFromFile = null;
      try
      {
         FileInputStream fileInStream = new FileInputStream(fileName);
         readFromFile = new ObjectInputStream(fileInStream);
         while (true)
         {
            try
            {
               objs.add(readFromFile.readObject());
            }
            catch (EOFException eof)
            {
              //Done reading
               break;
            }
         }
      }
      finally
      {
         if (readFromFile != null)
         {
            try
            {
               readFromFile.close();
            }
            catch (IOException e)
            {
               System.out.println("IO Error closing file " + fileName);
            }
         }
      }

      return objs.toArray();
   }
 }
