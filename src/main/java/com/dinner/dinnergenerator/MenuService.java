package com.dinner.dinnergenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class MenuService {
    
    public String menuItem(String type) throws Exception{
        dinnerScript("meats");
        File script = new File(type + "_dinner.sh");
        while(!script.exists()){
            this.wait();
        }
        Process pb = new ProcessBuilder("./" + type + "_dinner.sh").start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(pb.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {builder.append(line);}
        return builder.toString();
    }


    public String dinnerScript(String type){

        File myObj = new File(type + "_dinner.sh");
        try {

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());

                try {
                    FileWriter myWriter = new FileWriter(myObj);
                    myWriter.write(script(type));
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                    setPermission(myObj);
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

            } else {
                System.out.println("File already exists.");
                try {
                    myObj.delete();
                    FileWriter myWriter = new FileWriter(myObj);
                    myWriter.write(script(type));
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                    setPermission(myObj);
                } catch (Exception e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }


    public String script(String type){
        StringBuilder sb = new StringBuilder();
        
        sb.append("#!/bin/bash\n");
        sb.append("set +x\n");
        sb.append("cat " + type + ".txt | sed -n \"$(echo $(( $RANDOM % $(cat " + type + ".txt | wc -l) + 1 )))p\"");


        return sb.toString();
    }

    public static void setPermission(File file) throws IOException{

        Set<PosixFilePermission> perms = new HashSet<>();

        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);
        perms.add(PosixFilePermission.OWNER_EXECUTE);

        perms.add(PosixFilePermission.OTHERS_READ);
        perms.add(PosixFilePermission.OTHERS_WRITE);
        perms.add(PosixFilePermission.OTHERS_EXECUTE);

        perms.add(PosixFilePermission.GROUP_READ);
        perms.add(PosixFilePermission.GROUP_WRITE);
        perms.add(PosixFilePermission.GROUP_EXECUTE);

        Files.setPosixFilePermissions(file.toPath(), perms);

    }
}
