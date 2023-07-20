package com.platzi.functional._14_optionals;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OptionalsV2 {

    public static void main(String[] args) {
        List<String> names = getNames();
        if (names != null){

        }

        Optional<List<String>> namesOptional = getNamesOptional();
        if (namesOptional.isPresent()){

        }
        namesOptional.ifPresent(System.out::println);
        namesOptional.map(name -> name.size());

        Optional<String> valuablePlayer = optionalValuablePlayer();

        String valuablePlayerName = valuablePlayer.orElseGet(() -> "No player");
    }

    static List<String> getNames() {
        List<String> list = new LinkedList<>();

        return Collections.emptyList();
    }

    static String mostValueblePlayer() {
        return null;
    }

    static int mostExpensiveItem(){
        return -1;
    }

    static Optional<List<String>> getNamesOptional() {
        List<String> list = new LinkedList<>();

        return Optional.of(list);
    }

    static Optional<String> optionalValuablePlayer() {
//        return Optional.ofNullable();
        try {
            return Optional.of("Luijo");
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }



}
