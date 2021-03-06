package com.epam.izh.rd.online.service;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTextService implements TextService {

    /**
     * Реализовать функционал удаления строки из другой строки.
     * <p>
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base   - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {

        System.out.println(base.replace(remove, ""));
        return base.replace(remove, "");
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     * <p>
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        return text.endsWith("?");
    }

    /**
     * Реализовать функционал соединения переданных строк.
     * <p>
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        StringBuilder sb = new StringBuilder();
        for (String s : elements) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     * <p>
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {
        String[] arr = text.split("");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                sb.append(arr[i].toLowerCase());
            } else {
                sb.append(arr[i].toUpperCase());
            }
        }
        return sb.toString();
    }

    /**
     * Метод определяет, является ли строка палиндромом.
     * <p>
     * Палиндром - строка, которая одинаково читается слева направо и справа налево.
     * <p>
     * Например для строки "а роза упала на лапу Азора" вернется true, а для "я не палиндром" false
     */
    @Override
    public boolean isPalindrome(String string) {
            StringBuilder builder = new StringBuilder();
            builder.append(string.replace(" ", ""));
            String reverseResult = builder.reverse().toString();
            if(string.isEmpty()){return false;};
            if(string.replace(" ", "").equalsIgnoreCase(reverseResult))
                return true;
            return false;
        }
}
