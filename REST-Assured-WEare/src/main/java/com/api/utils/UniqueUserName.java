package com.api.utils;

import java.sql.SQLException;

import static com.api.utils.Constants.faker;
import static dataBaseManipulations.BaseSetup.usernames;
import static dataBaseManipulations.SelectAllUsers.selectAllUsers;

public class UniqueUserName {



  public static String nameUnique() throws SQLException {
      String name=faker.name().firstName();
      System.out.println("This is the name im cheking "+name);
      while (selectAllUsers().contains(name)) {
          System.out.println("Username already exists. Generating a new one...");
          name=faker.name().firstName();
          System.out.println("This is the name generated after check"+ name);
      }

      usernames.add(name);
      System.out.println("This is the name im trying to return" + name);
      System.out.println("There are existing names in database:"+usernames.size());
  return name;}
}
