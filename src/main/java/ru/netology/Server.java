package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8085;

        System.out.println("Сервер запущен на порту " + port);
        System.out.println("Ожидаем подключение ... ");

        try (ServerSocket serverSocket = new ServerSocket(port)){
            while(true){
                try(Socket clientSocket = serverSocket.accept()){
                    String clientIP = clientSocket.getInetAddress().getHostAddress();
                    int clientPort = clientSocket.getPort();

                    System.out.println("New connection accepted");
                    System.out.println("IP клиента: " + clientIP);
                    System.out.println("Порт клиента: " + clientPort);

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream())
                    );

                    PrintWriter out = new PrintWriter(
                            clientSocket.getOutputStream(),true
                    );

                    out.println("Привет! Напишите свое имя?");
                    String name = in.readLine();
                    System.out.println("Получено от клиента: " + name);

                    out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));

                    System.out.println("Ответ отправлен клиенту");
                } catch (IOException e) {
                    System.err.println("Ошибка при работе с клиентом: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при работе с клиентом: " + e.getMessage());
        }
    }
}
