package com.company;

import java.util.EmptyStackException;

//Name: Nestor Molina
//Last Edited: 10-10-17
//This class is used to take in an infix expression and convert it to postfix, indicate if there is an error and evaluate the postfix expression.
public class Expression {
    //<editor-fold desc="Data member">
    private String infixExp;
    private String postfixExp;
    private String[] tokens;
    private ReferenceStack<String> postfixStack;
    private ReferenceStack<Integer> evalStack;
    private int value;
    private boolean error;
    //</editor-fold>

    // Method Name: Expression()
    // Return Type: n/a
    // Arguments: String expression
    // Method Desc: constructor for Expression class
    public Expression(String expression) {
        infixExp = expression;
        postfixExp = "";
        tokens = infixExp.split(" ");
        postfixStack = new ReferenceStack<>();
        evalStack = new ReferenceStack<>();
        error = false;
        value = 0;

        try {
            convertToPostfix();
            evaluateExpression();
        }
        catch(EmptyStackException e) {
            error = true;
        }
    }

    //<editor-fold desc="Getters and Setters">
    public String getInfixExp() {
        return infixExp;
    }

    public String getPostfixExp() {
        return postfixExp;
    }

    public int getValue() {
        return value;
    }

    public boolean isError() {
        return error;
    }
    //</editor-fold>

    //<editor-fold desc="Private class methods">
    // Method Name: convertToPostfix()
    // Return Type: void
    // Arguments:
    // Method Desc: takes infix expression and converts to postfix by using switches and calling checkPrecedence()
    private void convertToPostfix() {
        for(int i = 0; i < tokens.length; i++) {
            if(tryParseInt(tokens[i])) {
                postfixExp += tokens[i] + " ";
            }//end of if
            else {
                switch (tokens[i]) {
                    case "*":
                        if(postfixStack.isEmpty()) {
                            postfixStack.push("*");
                        }
                        else{
                            checkPrecedence("*");
                        }
                        break;
                    case "/":
                        if(postfixStack.isEmpty()) {
                            postfixStack.push("/");
                        }
                        else{
                            checkPrecedence("/");
                        }
                        break;
                    case "%":
                        if(postfixStack.isEmpty()) {
                            postfixStack.push("%");
                        }
                        else{
                            checkPrecedence("%");
                        }
                        break;
                    case "+":
                        if(postfixStack.isEmpty()) {
                            postfixStack.push("+");
                        }
                        else{
                            checkPrecedence("+");
                        }
                        break;
                    case "-":
                        if(postfixStack.isEmpty()) {
                            postfixStack.push("-");
                        }
                        else{
                            checkPrecedence("-");
                        }
                        break;
                    case "(":
                        postfixStack.push("(");
                        break;
                    case ")":
                        while(!postfixStack.peek().equals("(")) {
                            postfixExp += postfixStack.pop() + " ";
                        }

                        postfixStack.pop();
                        break;

                }//end of switch
            }//end of else
        }//end of for loop

        while(!postfixStack.isEmpty()) {
            postfixExp += postfixStack.pop() + " ";
        }
    }//end of convertToPostfix()

    // Method Name: evaluateExpression()
    // Return Type: void
    // Arguments:
    // Method Desc: takes the postfix expression and evaluate it using a stack
    private void evaluateExpression() {
        postfixExp.trim();
        String[] postTokens = postfixExp.split(" ");

        for(int i = 0; i < postTokens.length; i++) {
            if(tryParseInt(postTokens[i])) {
                evalStack.push(Integer.parseInt(postTokens[i]));
            }//end of if
            else{
                Integer temp;
                Integer numTwo = (Integer) evalStack.pop();
                Integer numOne = (Integer) evalStack.pop();

                switch (postTokens[i]) {
                    case "*":
                        temp = numOne * numTwo;
                        evalStack.push(temp);
                        break;
                    case "/":
                        temp = numOne / numTwo;
                        evalStack.push(temp);
                        break;
                    case "%":
                        temp = numOne % numTwo;
                        evalStack.push(temp);
                        break;
                    case "+":
                        temp = numOne + numTwo;
                        evalStack.push(temp);
                        break;
                    case "-":
                        temp = numOne - numTwo;
                        evalStack.push(temp);
                        break;
                }//end of switch
            }//end of else
        }//end of for loop

        value = (Integer)evalStack.pop();

        if(!evalStack.isEmpty()) error = true;
    }//end of evaluateExpression()

    // Method Name: tryParseInt()
    // Return Type: boolean
    // Arguments: String s
    // Method Desc: method used to check if the String sent to it is a numerical value or not
    private boolean tryParseInt(String s) {
        try{
            Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }//end of tryParseInt()

    // Method Name: checkPrecedence()
    // Return Type: void
    // Arguments: String s
    // Method Desc: method called by convertToPostFix() to compare between a character of infix and characters inside the postfixStack
    private void checkPrecedence(String s) {
        boolean higherEqual = true;

        switch (s) {
            case "*":
                while(!postfixStack.isEmpty() && higherEqual) {
                    switch ((String)postfixStack.peek()) {
                        case "*":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "/":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "%":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "+":
                            postfixStack.push("*");
                            higherEqual = false;
                            break;
                        case "-":
                            postfixStack.push("*");
                            higherEqual = false;
                            break;
                        case "(":
                            postfixStack.push("*");
                            higherEqual = false;
                            break;
                    }//end of inside switch
                }//end of while loop
                if(postfixStack.isEmpty()) {
                    postfixStack.push("*");
                }
                break;
            case "/":
                while(!postfixStack.isEmpty() && higherEqual) {
                    switch ((String)postfixStack.peek()) {
                        case "*":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "/":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "%":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "+":
                            postfixStack.push("/");
                            higherEqual = false;
                            break;
                        case "-":
                            postfixStack.push("/");
                            higherEqual = false;
                            break;
                        case "(":
                            postfixStack.push("/");
                            higherEqual = false;
                            break;
                    }//end of inside switch
                }//end of while loop
                if(postfixStack.isEmpty()) {
                    postfixStack.push("/");
                }
                break;
            case "%":
                while(!postfixStack.isEmpty() && higherEqual) {
                    switch ((String)postfixStack.peek()) {
                        case "*":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "/":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "%":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "+":
                            postfixStack.push("%");
                            higherEqual = false;
                            break;
                        case "-":
                            postfixStack.push("%");
                            higherEqual = false;
                            break;
                        case "(":
                            postfixStack.push("%");
                            higherEqual = false;
                            break;
                    }//end of inside switch
                }//end of while loop
                if(postfixStack.isEmpty()) {
                    postfixStack.push("%");
                }
                break;
            case "+":
                while(!postfixStack.isEmpty() && higherEqual) {
                    switch ((String)postfixStack.peek()) {
                        case "*":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "/":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "%":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "+":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "-":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "(":
                            postfixStack.push("+");
                            higherEqual = false;
                            break;
                    }//end of inside switch
                }//end of while loop
                if(postfixStack.isEmpty()) {
                    postfixStack.push("+");
                }
                break;
            case "-":
                while(!postfixStack.isEmpty() && higherEqual) {
                    switch ((String)postfixStack.peek()) {
                        case "*":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "/":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "%":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "+":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "-":
                            postfixExp += postfixStack.pop() + " ";
                            break;
                        case "(":
                            postfixStack.push("-");
                            higherEqual = false;
                            break;
                    }//end of inside switch
                }//end of while loop
                if(postfixStack.isEmpty()) {
                    postfixStack.push("-");
                }
                break;
        }//end of outside switch
    }//end of checkPrecedence()
    //</editor-fold>
}//end of Expression
