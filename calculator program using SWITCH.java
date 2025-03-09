    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        double result = 0;

        System.out.print("1st number: ");
        double num1 = input.nextDouble();
        
        System.out.print("Operator (+, -, *, /, ^): ");
        char operator = input.next().charAt(0); // Correct way to get a char
        
        System.out.print("2nd number: ");
        double num2 = input.nextDouble();
        
        switch (operator) {  
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) { 
                    System.out.println("Error: Division by zero is not allowed.");
                    return; 
                }
                result = num1 / num2;
                break;
            case '^':
                result = Math.pow(num1, num2);
                break;
            default:
                System.out.println("Error: Invalid operator.");
                return;
        } 
        
        System.out.println("The result: " + result);

      
    }
