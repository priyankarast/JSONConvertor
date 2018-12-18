
package com.ncu.main;
import com.ncu.validators.*;
import com.ncu.processors.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.util.Scanner;
import java.io.File;
public class Test {
  static String exitValue = "exit";
  static String permissionValue="y";
  static String log4jConfigFile = System.getProperty("user.dir") + File.separator + "configs/logger/logger.properties";
  static Logger logger; 

  /* main method - process will be start from here and calling  validatorMethod method to check 
  all validations on file name */

  public static void main(String... args) {
    System.out.println("=======================================");
    System.out.println("||        CSV to JSON Converter      ||");
    System.out.println("=======================================");
    logger = Logger.getLogger(Test.class);
    PropertyConfigurator.configure(log4jConfigFile);
    Test.getCSVName();
  }

  /* Taking file name from user and Perform trim and lower case operation */
  public static void getCSVName(){

    Scanner sc = new Scanner(System.in);

    logger.info(" !...(*.*) Please Enter Your Csv File Name __:- ");

    String fileName = sc.nextLine().trim().toLowerCase();
    //String fileName = sc.nextLine();

    /* Creating object of NameValidator class to check all validation related  to file name */
    NameValidator csvObject=new NameValidator();
    boolean checkValidator=csvObject.nameValidator(fileName, "csv");

    // Test dObject=new Test();
    if(checkValidator){
      Test.getJSONName(fileName);
    }else{
      Test.getCSVName();
    }
  }

  /* Creating object of NameValidator class to check all validation related 
  to csv file */

  public static void getJSONName(String csvFileName) {   
    CSVProcessor processObj=new CSVProcessor();

    Scanner sc = new Scanner(System.in);

    logger.info("!...(*.*) What Will Be Your Converted File Name, Please Enter__:- ");
    String jsonFilename = sc.nextLine().trim().toLowerCase();
    NameValidator csvObject=new NameValidator();

    boolean isValid = csvObject.nameValidator(jsonFilename, "json");
    if(isValid) {
      // Write logic to convert csv data into json here
      boolean processValidator=processObj.processCSV(csvFileName,jsonFilename);
      if(processValidator) {
        // Test.systemCloseMethod();
      }else{
         Test.getJSONName(csvFileName);
      }
    } else{
      Test.getJSONName(csvFileName);
    }
  }
}