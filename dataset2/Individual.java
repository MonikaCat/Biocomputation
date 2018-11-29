/*
 *
 *  Name: Monika Pusz
 *	Student ID: 16024757
 *	Date: 28/11/2018
 *	Course: Comupter Science 
 */
 
package dataset2;

import java.util.Arrays;
import java.util.Random;

class Individual {

    public static final int P = 200;
    public static final int N = 80;

    public int[] gene = new int[N];
    public int fitness = 0;
    int one_rule_size = 7;
    int action_rule = 1;
    int num_of_rows_rule = 64;
    int numR = 10;

    // Constructor
    public Individual() {
        // Initialise the individuals 
        Random rand_number = new Random();


        for (int j = 0; j < N; j++) 
        { int total_count = 0;
            if(total_count < one_rule_size)
            {
                gene[j] = Math.abs(rand_number.nextInt() % 3); // Generate random string of genes between (0-2) for classifier
                total_count += 1;
            }
            else
            {
                gene[j] = Math.abs(rand_number.nextInt() % 2); // Generate random bit between 0-1 for output 
                total_count = 0;
            }
        fitness = 0;
    }
    }
 
    
    public int calculate_fitness(Rules[] file)
    {
        int values_matching = 0; 
        Rules[] gene_array = new Rules[numR];
        gene_array = transformToRules(gene); 
        
            fitness = 0;
            
            for(int j = 0; j < num_of_rows_rule; j++) // for every rule in dataset
            {

                for(int k = 0; k < (numR); k++ ) // for every rule in gene
                {
                    values_matching = 0;
                    
                    for(int m = 0; m < one_rule_size; m++) // for every bit of data set
                    
                    {
                     // check if classifier values are matching
                        if ((file[j].getRule(m) == gene_array[k].getRule(m)) || gene_array[k].getRule(m) == 2 ) 
                               {
                                   values_matching++;
                               }
                    }
                     // check if output value is matching
                     if(values_matching == one_rule_size)
                     {
                         if(file[j].getAction() == gene_array[k].getAction())
                         {
                             fitness++;
                         }
                         break; 
                     }
                     
                    
                            
                }
            }
            
        setFitness(fitness);
      return fitness;  
    
        
    }
    

     public Rules[] transformToRules(int[] rule)
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
    
   

    public int getFitness() {
        return fitness;
    }
    
    public void setFitness(int newFitness) {
        fitness = newFitness;
    }
    
    public void setGene(int offset, int genotype)
    {
        this.gene[offset] = genotype;
    }
    
    public int getGene(int offset)
    {
        return this.gene[offset];
    }
    
    
    
    public String toString() {
		String gene_string = "";
		for (int c = 0; c < this.gene.length; c++) {
			gene_string += this.gene[c];
		}
		return gene_string;
	}
    
    
}
