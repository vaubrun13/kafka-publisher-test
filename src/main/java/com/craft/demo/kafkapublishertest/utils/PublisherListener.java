package com.craft.demo.kafkapublishertest.utils;

import com.craft.demo.kafkapublishertest.avro.MyEventAvro;
import com.craft.demo.kafkapublishertest.avro.MyKeyAvro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/** Interceptor to keep track of sent events and failed to sent events */
public class PublisherListener implements ProducerListener<MyKeyAvro, MyEventAvro> {

  // List to keep track of sent events
  private final Map<MyKeyAvro, List<MyEventAvro>> eventsSent = new HashMap<>();
  private final Map<MyKeyAvro, List<MyEventAvro>> eventsFailedToSent = new HashMap<>();

  @Override
  public void onSuccess(
      ProducerRecord<MyKeyAvro, MyEventAvro> producerRecord, RecordMetadata recordMetadata) {
    this.eventsSent.putIfAbsent(producerRecord.key(), new ArrayList<>());
    this.eventsSent.get(producerRecord.key()).add(producerRecord.value());
  }


  @Override
  public void onError(
      ProducerRecord<MyKeyAvro, MyEventAvro> producerRecord,
      RecordMetadata recordMetadata,
      Exception exception) {
    this.eventsFailedToSent.putIfAbsent(producerRecord.key(), new ArrayList<>());
    this.eventsFailedToSent.get(producerRecord.key()).add(producerRecord.value());
  }

  // Getters

  public Map<MyKeyAvro, List<MyEventAvro>> getEventsSent() {
    return eventsSent;
  }

  public Map<MyKeyAvro, List<MyEventAvro>> getEventsFailedToSent() {
    return eventsFailedToSent;
  }
}
