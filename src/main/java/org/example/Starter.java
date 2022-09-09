package org.example;

import org.example.config.AppConfig;
import org.example.service.DictionaryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Starter {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        DictionaryService dictionaryService = context.getBean(DictionaryService.class);
        Scanner scan = new Scanner(System.in);

        //Приветствие :)
        System.out.println(
                "----------------------------------------------\n" +
                        "Здравствуйте! Я очень странное консольное приложение :)\n" +
                        "Во мне находится 2 словаря:\n" +
                        "1)Словарь только из латинских слов, но слово не может быть больше 4 символов!\n" +
                        "2)Словарь только из чисел, но число не может превышать 5 цифр!\n" +
                        "Поэтому пожалуйста соблюдайте эти скромные правила :)\n" +
                        "----------------------------------------------"
        );

        while (true){
            System.out.println(
                    "----------------------------------------------\n" +
                            "С чего хотите начать?\n" +
                            "1 - просмотр содержимого словарей\n" +
                            "2 - поиск слова по ключу\n" +
                            "3 - добавление пары слов\n" +
                            "4 - удаление слова по ключу\n" +
                            "Введите номер действия:"
            );
            int actionNumber = 0;
            try {
                actionNumber = scan.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Это не номер действия!");
                scan.next();
                continue;
            }

            switch (actionNumber){
                case 1:
                    dictionaryService.printDictionaries();
                    break;
                case 2:
                    dictionaryService.findByKey(dictionaryService.choiceDictionary());
                    break;
                case 3:
                    dictionaryService.putWord(dictionaryService.choiceDictionary());
                    break;
                case 4:
                    dictionaryService.deleteByKey(dictionaryService.choiceDictionary());
                    break;
                default:
                    System.out.println("Это не номер действия!");
            }
        }
    }
}
