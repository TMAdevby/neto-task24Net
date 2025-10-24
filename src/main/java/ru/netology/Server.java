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

                    System.out.println("Новое подключение!");
                    System.out.println("IP клиента: " + clientIP);
                    System.out.println("Порт клиента: " + clientPort);

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream())
                    );

                    PrintWriter out = new PrintWriter(
                            clientSocket.getOutputStream(),true
                    );

                    out.println("Привет! Отправь мне строку");
                    String message = in.readLine();
                    System.out.println("Получено от клиента: " + message);

                    String response = String.format(
                            "Привет, %s! Твой порт: %d",
                            message,
                            clientPort
                    );

                    out.println(response);
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
