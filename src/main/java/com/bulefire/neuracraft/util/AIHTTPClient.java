package com.bulefire.neuracraft.util;

import com.bulefire.neuracraft.config.yy.BaseInformation;
import com.mojang.logging.LogUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class AIHTTPClient {
    private static final Logger log = LogUtils.getLogger();

    public static @NotNull String POST(@NotNull String urls, @NotNull String body) throws Exception {
        log.info("send to ai url: {}", urls);
        log.info("send to ai: {}", body);

        var connection = getConnection(urls, body);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            log.info("Response from AI: {}", response);

            return response.toString();
        } else {
            return "POST request failed with response code: " + responseCode+","+connection.getResponseMessage();
        }
    }

    private static @NotNull HttpURLConnection getConnection(@NotNull String urls, @NotNull String body) throws IOException {
        URL url = new URL(urls);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("Authorization", "Bearer " + BaseInformation.token);

        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = body.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        return connection;
    }
}
