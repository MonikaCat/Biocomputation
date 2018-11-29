/*
 *
 *  Name: Monika Pusz
 *	Student ID: 16024757
 *	Date: 28/11/2018
 *	Course: Comupter Science 
 */
 
package dataset1;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;


public class GAMain 
{

    public static final int P = 50;
    public static final int N = 60;
    int one_rule_size = 5;
    int action_rule = 1;
    int num_of_rows_rule = 32;
    int temp_fitness = 0;
    int numR = 10;
    


    public static void main(String[] args)  throws FileNotFoundException, IOException {

    float mutation_rate = 0.01f;
    int crossover_point;
    int generation = 0;
    int best_generation = 0;
    int num_of_loops = 50;
    String headers = "Generation, Mean, Best";  //headers for csv file
    
    Individual[] population = new Individual[P]; // Create new population array of size P

    Individual[] offspring = new Individual[P]; // Create new offspring array of size P

    int fittest = 0;
    
    // Create constructor to access class methods
    DataSet dataset = new DataSet();
        
        
        
        // Initialize new population generate new individuals and offspring
        for (int i = 0; i < population.length; i++) 
        {
            population[i] = new Individual();
            offspring[i] = new Individual();
        }
        

        // Create new csv file to store values, add headers to first row
        FileWriter csv_file = new FileWriter("DATASET1_RESULTS.csv");
        csv_file.append(headers + "\n");
        
        // Read file and store it in array
        int[] read_file = dataset.readFile("C:\\Users\\Monika\\OneDrive - UWE Bristol (Students)\\University\\Year 3\\Biocomputation\\TESTING\\data1.txt");
        // Split the input file into the rules 
        Rules[] data_set = dataset.transformDataSet(read_file);

        
        //Calculate fitness for each indivudial in population (evaluate population)
        for (int j = 0; j < population.length; j++) 
        {
            population[j].setFitness ( population[j].calculate_fitness(data_set) );
            
        }

        
        //Run the generation 50 times 
       for(int i = 0; i < num_of_loops; i++)
       {

            generation += 1;
          
            
        ///////////////////////
        // Selection Process///
        ///////////////////////    

	for (int a = 0; a < P; a++) {
            Random rand = new Random();
            Random rand2 = new Random();
            int parent1 = rand.nextInt(P);
            //System.out.println("parent1 = " + parent1);
            int parent2 = rand2.nextInt(P);
            //System.out.println(" parent2 = " + parent2);
            
            
            int[] temporary_array = new int[N];
            int[] temporary_array_2 = new int[N];

            for(int v = 0; v < N; v++)
            {
                temporary_array[v] = population[parent1].gene[v];   // Save first individual gene into temporary_array_6 array
                temporary_array_2[v] = population[parent2].gene[v]; // Save second individual gene into temporary_array_6 array
            }
            
            // Check the fitness value of two randomly picked individual
            if (population[parent1].getFitness() > population[parent2].getFitness()) 
            {
                for(int v = 0; v < N; v++)
                {
                    offspring[a].gene[v] = temporary_array[v]; // Save first individual genes (stored in temporary_array_6 array) into offspring 
                }
            } 
            else 
            {
                for(int v = 0; v < N; v++)
                {
                    offspring[a].gene[v] = temporary_array_2[v]; // Save second individual genes (stored in temporary_array_6 array) into offspring 
                }
            }
            
            }

			
	///////////////////////////////////////		
	//Crossover to generate new offspring// 
        //////////////////////////////////////

	Random cross_rand = new Random();
	
	int temporary_array_3;
        int temporary_array_4;

	for (int q = 0; q < P; q = q + 2)  // For every individual in population, increment by 2
        {
	crossover_point = cross_rand.nextInt(P); // Generate random point between 0 and P

            for (int w = crossover_point; w < N; w++)  // For every gene, starting from generated random point (crossover_point)
            {
                temporary_array_3 = offspring[q].gene[w]; // Save offspring gene part into temporary_array_6 array
                temporary_array_4 = offspring[q + 1].gene[w]; // Save the next offspring gene part into temporary_array_6 array
                
                offspring[q].gene[w] = temporary_array_4;    // Save previous offspring gene part into offspring array
                offspring[q + 1].gene[w] = temporary_array_3;  // Save previous offspring gene part into next offspring array
             
            }
            
	}
        
        
	

        //////////////////////////
	///Mutation offspring ///
        /////////////////////////

        Random random = new Random();

        for (int e = 0; e < P; e++)  // for every individual in population
        {
          for (int r = 0; r < N; r++) // for every gene
          {
                float probability = random.nextFloat(); // create random probability float value
                                
                if (probability < mutation_rate) 
                {
                    if (offspring[e].gene[r] == 1)  // If gene bit is 1 select random value between (0 -1)
                    {
                        if(random.nextInt(2) == 0) // If random value is 0
                        {
                            offspring[e].gene[r] = 0; // Flip bit 1 into 0
                        }
                        else
                        {
                            offspring[e].gene[r] = 2; // Else flip bit 1 into 2 (wildcard)
                        }
                    } 
                    else if (offspring[e].gene[r] == 0) // If gene bit is 0 select random value between (0 -1)
                    {
                        if(random.nextInt(2) == 0) // If random value is 0
                        {
                            offspring[e].gene[r] = 1; // Flip bit 0 into 1
                        }
                        else
                        {
                            offspring[e].gene[r] = 2; // Else flip bit 0 into 2 (wildcard)
                        }
                    }
                    else if(offspring[e].gene[r] == 2) // If gene bit is 2 (wildcard) select random value between (0 -1)
                    {
                        if(random.nextInt(2) == 0) // If random value is 0
                        {
                            offspring[e].gene[r] = 0; // Flip bit 2 (wildcard) into 0
                        }
                        else
                        {
                            offspring[e].gene[r] = 1; // Else flip bit 2 (wildcard) into 1
                        }
                        
                    }
                }
            }
        }
       
        
         //Calculate fitness for each indivudial in population (evaluate population)
        for (int j = 0; j < population.length; j++) 
        {
             population[j].setFitness(population[j].calculate_fitness(data_set));   
        }
        
       
        // Calculate fitness of each offspring
        for (int t = 0; t < P; t++) 
        {
            offspring[t].setFitness(offspring[t].calculate_fitness(data_set));
        }
        
        
        // Calculate the fittest value 
        for(int y = 0; y < P; y++)
        {
            // Create temporary_array_5 object to store fitness of each individual
            int temporary_array_5 = population[y].getFitness();
            
            if(temporary_array_5 > fittest) // Check fitness of each individual against fittest value and if it's higher
            {
                fittest = temporary_array_5; // Save fitter individual fittness as new fittest value
                best_generation = generation; // Save generation where fittest value has been found
                
            }    
        }
        
        int temporary_sum = 0;
        int average_fitness = 0;
        
        // Add fitness of each individual to calculate average fitness value for each generation
        for(int e = 0; e < P; e++)
        {
            temporary_sum += population[e].getFitness();
        }
        
        
        // Divide sum of fitness of all individuals per number of individuals to obtain average fitness value
        average_fitness = (temporary_sum / P);
        
        // Create temporary objects to store values
        int[] temporary_array_6 = new int[N];
        int temporary_fitness;
        
        // Save newly generated offspring into population    
        for(int h = 0; h < P; h++)  // For every individual
        {
            for(int b = 0; b < N; b++) // For every gene
            {
                temporary_array_6[b] = offspring[h].gene[b]; // Save offspring gene into temporary array
                population[h].gene[b] = temporary_array_6[b];  //  Save temporary_array(offspring gene) into population array      
            }
            temporary_fitness = offspring[h].getFitness();
            population[h].setFitness(temporary_fitness);
        }
        
        // Save values into csv file 
        csv_file.append(generation + "," + average_fitness + "," + fittest + "\n");
        System.out.println("*******************" + generation +"*******************************");
        System.out.println("Generation: " + generation); // Display Each Generation 
        System.out.println("Fittest: " + fittest);
        System.out.println("Average Fitness: " + average_fitness);
        System.out.println("_______________________________________________________");
        System.out.println("            ");
        }
       
        csv_file.flush();
       
        System.out.println("The best fitness: " + fittest);
        System.out.println("Found in generation " + best_generation);
    
}
    
    

}