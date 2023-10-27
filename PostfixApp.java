import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
         String input;
        int output;
        while(true)
        {
            System.out.print("Enter postfix: ");
            System.out.flush();
            input = getString(); // read a string from kbd
            if( input.equals("") ) // quit if [Enter]
                break;
            // make a parser
            ParsePost aParser = new ParsePost(input);
            output = aParser.doParse(); // do the evaluation
            System.out.println("Evaluates to " + output);
        } // end while
    } // end main()
    //--------------------------------------------------------------
    public static String getString() throws IOException
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
    //--------------------------------------------------------------
}



class ParsePost {
    
    private StackX theStack;
    private String input;
    //--------------------------------------------------------------
    public ParsePost(String s) {
        
        input = s;
        System.out.println(s);
    
    }
    //--------------------------------------------------------------
    public int doParse()
    {
        theStack = new StackX(20); // make new stack
        char ch;
        int j, num1, num2, ans = 0;
        for(j=0; j<input.length(); j++) // for each char,
        {
            ch = input.charAt(j); // read from input
            theStack.displayStack(""+ch+" "); // *diagnostic*
            

            // if itâ€™s an operator...
                // pop operands,
                // do arithmetic,                
                // push result
            if (Character.isDigit(ch)) {

                int s = Character.getNumericValue(ch);
                theStack.push(s);
            }
            else if (ch == '*' || ch == '+' || ch == '-' || ch == '/') {

               num1= theStack.pop();
               num2 = theStack.pop();
               
               switch (ch) {

                case '*':
                            ans = num2 * num1;
                            break;
                case '+':
                            ans = num2 + num1;
                            break;
                case '-':
                            ans = num2 - num1;
                            break;
                case '/':
                            ans = num2 / num1;
                            break;
               }

               theStack.push(ans);
            
            }
            else {
                System.out.println("Invalid Input.");
            }
           
        }
        ans = theStack.pop(); // get answer
        return ans;
    }
}




class StackX {
    
    private int maxSize;
    private int[] stackArray;
    private int top;
    //--------------------------------------------------------------
    public StackX(int size) // constructor
    {
        maxSize = size;
        stackArray = new int[maxSize];
        top = 0;
    }
    //--------------------------------------------------------------
    public void push(int j) // put item on top of stack
    { 
        
        stackArray[top++] = j; 
    }
    //--------------------------------------------------------------
    public int pop() // take item from top of stack
    { 
        return stackArray[--top]; 
    
    }
    //--------------------------------------------------------------
    public int peek() // peek at top of stack
    { 
        return stackArray[top - 1]; 
    }
    //--------------------------------------------------------------
    public boolean isEmpty() // true if stack is empty
    { 
        return (top == 0); 
    }
    //--------------------------------------------------------------
    public boolean isFull() // true if stack is full
    {
        
        return (top - 1 == maxSize);
    }
    //--------------------------------------------------------------
    public int size() // return size
    { 
        
        return top;
    }
    //--------------------------------------------------------------
    public int peekN(int n) // peek at index n
    { 
        
        return stackArray[n];
    }
    //--------------------------------------------------------------
    public void displayStack(String s)
    {
        System.out.print(s);
        System.out.print("Stack (bottom-->top): ");
        for(int j=0; j<size(); j++)
        {
            System.out.print( peekN(j) );
            System.out.print(' ');
        }
        System.out.println("");
    }
}
