package com.epam.izh.rd.online.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        Map<String, String> map = new HashMap<>();
        String line = null;
        StringBuilder sb = new StringBuilder();
        File file = new File("C:\\Ахив\\java-data-handling-template\\src\\main\\resources\\sensitive_data.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                sb.append(scanner.next());
                sb.append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        line = sb.toString();
        Pattern pattern = Pattern.compile("([0-9]([0-9]{3}) ?) (([0-9]{4} ?){3})");
        Matcher matcher = pattern.matcher(line);
        line.matches("[0-9]{5}");
        while (matcher.find()) {
            sb = new StringBuilder();
            String result = matcher.group();
            String[] strForReplace = result.split(" ");
            sb.append(strForReplace[0]);
            sb.append(" ");
            sb.append("****");
            sb.append(" ");
            sb.append("****");
            sb.append(" ");
            sb.append(strForReplace[3]);
            sb.append(" ");
            map.put(result, sb.toString());
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            line = line.replace(entry.getKey(), entry.getValue());
        }
        return line.trim();
    }


    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String text = null;
        StringBuilder sb = new StringBuilder();
        File file = new File("C:\\Ахив\\java-data-handling-template\\src\\main\\resources\\sensitive_data.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                sb.append(scanner.next());
                sb.append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String paymentAmountOut;
        String balanceOut;
        if (paymentAmount - Math.floor(paymentAmount) == 0) {
            paymentAmountOut = "" + (int)paymentAmount;
        } else {
            paymentAmountOut = "" + paymentAmount;
        }
        if (balance - Math.floor(balance) == 0) {
            balanceOut = "" + (int)balance;
        } else {
            balanceOut = "" + balance;
        }
        text = sb.toString();
        text = text.replaceFirst("\\$\\{.*?}", paymentAmountOut);
        text = text.replaceFirst("\\$\\{.*?}", "" + balanceOut);
        return text.trim();
    }
}
