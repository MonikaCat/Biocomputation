/*
 *
 *	Date: 28/11/2018
 *	Course: Comupter Science 
 */
 
package dataset2;


public class Rules {
    
    int[] rule = new int[7];
    int action; 
    
    public Rules(int[] rule, int action)
    {
        this.rule = rule;
        this.action = action;        
    }
    
    public int[] getRule()
    {
        return rule;
    }
    
    public int getRule(int number)
    {
        return rule[number];
    }
    
    public void setRule(int[] rule)
    {
        this.rule = rule;
    }
    
    public int getAction()
    {
        return action;
    }
    
    public void setAction(int action)
    {
        this.action = action;
    }
    
 
    
}
