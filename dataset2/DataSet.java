/*
 *
 *  Name: Monika Pusz
 *	Student ID: 16024757
 *	Date: 28/11/2018
 *	Course: Comupter Science 
 */
 
package dataset2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class DataSet {
    
    int one_rule_size = 7;
    int action_rule = 1;
    int num_of_rows_rule = 64;
    int temp_fitness = 0;
    int numR = 10;
    
        
    public int[] readFile(String file) throws IOException
    {   
        // Read the file and ignore the white space 
         String file_in, file_in_1, file_in_2;
        
        file_in = new String(Files.readAllBytes(Paths.get(file))); 
        file_in_1= file_in.trim();
        file_in_2 = file_in_1.replaceAll("\\s", "");
        
        int[] save_values = new int[file_in_2.length()];
        for(int i = 0; i < file_in_2.length(); i++)
        {
            save_values[i] = (int) (file_in_2.charAt(i) - '0');
        }
        
        return save_values;
    }
    
    
    public Rules[] transformDataSet(int[] rule)
    {
        // Split rules to size of rule + 1 action c
       int divide_to_rules = (rule.length / (one_rule_size + action_rule));

       // Create constructor for rules of size 
       Rules[] rules = new Rules[divide_to_rules];
       Rules copy_of_rules2 = null;
       int[] single_rule = null;
       
       for(int i = 0; i < divide_to_rules; i++)
       {
           int index = (i) * (one_rule_size + action_rule);
           
           // Separate into seperate rules
          single_rule = Arrays.copyOfRange(rule, index, (index + (one_rule_size + action_rule)));
          
          copy_of_rules2 = new Rules(Arrays.copyOfRange(single_rule, 0, (one_rule_size + action_rule)), single_rule[one_rule_size]);

          rules[i] = copy_of_rules2;
          
       }
       return rules;
        
    }
    
    
    
}
