package creditcard.commandpattern;

import java.util.Stack;

public class AccountHistory {
    Stack<Command>commands=new Stack<>();
    Stack<Command>commandsUndo=new Stack<>();
    Command command;
    public void addCommand(Command command) {
       commands.push(command);
    }


    public void undo(){
        if(!commands.empty()) {
            command = commands.peek();
            command.unExcute();
            commands.pop();
            commandsUndo.push(command);
        }
    }
    public void redo(){
        if(!commandsUndo.empty()) {
            command=commandsUndo.peek();
            command.excute();
            commands.push(command);
            commandsUndo.pop();
        }
    }
}
