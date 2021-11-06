package com.dinner.dinnergenerator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    
    public String getMeat() throws Exception{
        Process pb = new ProcessBuilder("./randomItem.sh", "meats").start();
        BufferedReader reader =new BufferedReader(new InputStreamReader(pb.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {builder.append(line);}
        return builder.toString();
    }

    public String getVegetable() throws Exception{
        Process pb = new ProcessBuilder("./randomItem.sh", "vegetables").start();
        BufferedReader reader =new BufferedReader(new InputStreamReader(pb.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {builder.append(line);}
        return builder.toString();
    }


    public String getStarch() throws Exception{
        Process pb = new ProcessBuilder("./randomItem.sh", "starches").start();
        BufferedReader reader =new BufferedReader(new InputStreamReader(pb.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {builder.append(line);}
        return builder.toString();
    }

    public String getAlternative() throws Exception{
        Process pb = new ProcessBuilder("./randomItem.sh", "alternatives").start();
        BufferedReader reader =new BufferedReader(new InputStreamReader(pb.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {builder.append(line);}
        return builder.toString();
    }



}
