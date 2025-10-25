package ru.netology.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String serverIP = "netology.homework"; // 127.0.0.1

        int serverPort = 8085;

        try(Socket socket = new Socket(serverIP, serverPort)){
            System.out.println("Подключились успешно!");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(),true
            );

            for (int i = 0; i < 3; i++) {
                if(i == 0){
                    String greating = in.readLine();
                    System.out.println("Сервер: " + greating);
                    String name = "Сережа";
                    out.println(name);
                }
                if(i == 1){
                    String result = Math.random() < 0.5 ? "no" : "yes";
                    out.println(result);
                }


            }
            String greating = in.readLine();
            System.out.println("Сервер: " + greating);

            String name = "Сережа";
            out.println(name);
            System.out.println("Отправили: " + name);

            String response = in.readLine();
            System.out.println("Сервер: " + response);

        } catch (IOException e) {
            System.err.println("Ошибка подключения: " + e.getMessage());
        }
    }
}
