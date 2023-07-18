package com.platzi.functional._04_functional;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class CLIArgumentsUtils {

    static void showHelp(CLIArguments cliArguments){
        Consumer<CLIArguments> consumerHelper = cliArguments1 -> {
            if (cliArguments1.isHelp()){
                System.out.println("Ayuda manual");
            }
        };

        consumerHelper.accept(cliArguments);
    }

    static CLIArguments generateCLI(boolean isHelp){
        Supplier<CLIArguments> generator = () -> new CLIArguments(isHelp);
        return generator.get();
    }

}
