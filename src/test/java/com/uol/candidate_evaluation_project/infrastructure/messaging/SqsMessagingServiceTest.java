package com.uol.candidate_evaluation_project.infrastructure.messaging;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class SqsMessagingServiceTest {
    @Mock
    private SqsTemplate sqsTemplate;

    @InjectMocks
    private SqsMessagingService sqsMessagingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {
        String queueName = "test-queue";
        String message = "Hello, SQS!";

        sqsMessagingService.sendMessage(queueName, message);

        verify(sqsTemplate).send(queueName, message);
    }
}
