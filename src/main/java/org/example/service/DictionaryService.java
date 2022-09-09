package org.example.service;

import org.example.Dictionary;
import org.example.DictionaryLatin;
import org.example.DictionaryNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class DictionaryService {

    private DictionaryLatin dictionaryLatin;
    private DictionaryNumber dictionaryNumber;


    @Autowired
    public DictionaryService(DictionaryLatin dictionaryLatin, DictionaryNumber dictionaryNumber){
        this.dictionaryLatin = dictionaryLatin;
        this.dictionaryNumber = dictionaryNumber;
    }


    public void printDictionaries(){
        System.out.println("Содержимое первого словаря:");
        dictionaryLatin.printToConsole();
        System.out.println();

        System.out.println("Содержимое второго словаря:");
        dictionaryNumber.printToConsole();
        System.out.println();
    }

    //Возвращает словарь выбранный пользователем
    public Dictionary choiceDictionary() {
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.println(
                    "Какой из словарей вы хотите выбрать?\n" +
                            "1 - латинский словарь\n" +
                            "2 - числовой словарь\n" +
                            "Введите номер словаря:"
            );
            if (scan.hasNextInt()){
                int numberDictionary = scan.nextInt();

                if (numberDictionary == 1){
                    return dictionaryLatin;
                } else if (numberDictionary == 2){
                    return dictionaryNumber;
                } else {
                    System.out.println("Это не номер словаря!!!");
                    continue;
                }
            } else {
                System.out.println("Это не номер словаря!!!");
                scan.next();
                continue;
            }
        }
    }

    public void findByKey(Dictionary dictionary){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите ключ слово:");
        String key = scan.nextLine();
        try {
            System.out.println("Вот оно: " + dictionary.findByKey(key));
            System.out.println();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    public void putWord(Dictionary dictionary){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите ключ слово:");
        String key = scan.nextLine();
        System.out.println("Введите значение слова:");
        String value = scan.nextLine();
        try {
            dictionary.putWord(key, value);
            System.out.println("Слово было добавлено! :)");
            System.out.println();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteByKey(Dictionary dictionary){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите ключ слово:");
        String key = scan.nextLine();
        try {
            dictionary.deleteByKey(key);
            System.out.println("Слово было удалено! :)");
            System.out.println();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
