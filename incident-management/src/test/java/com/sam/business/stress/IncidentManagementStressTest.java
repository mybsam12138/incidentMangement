package com.sam.business.stress;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncidentManagementStressTest {

    private final String BASE_URL = "http://localhost:8080/incidents";
    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void stressTestCreateIncident() throws InterruptedException {
        int numberOfThreads = 50; // 模拟 50 个并发用户
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    // 构造请求数据
                    String requestBody = String.format("""
                            {
                                "id": "%d",
                                "title": "Test Incident %d",
                                "description": "Performance Test Description",
                                "status": "Open"
                            }
                            """, finalI, finalI);

                    // 发送请求
                    ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL, requestBody, String.class);
                    assertEquals(200, response.getStatusCodeValue());
                } catch (Exception e) {
                    System.err.println("Request failed: " + e.getMessage());
                }
            });
        }

        // 关闭线程池
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(100);
        }
    }
}