package com.craft.demo.kafkapublishertest.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record MyEvent(UUID id, Integer version, LocalDateTime occurredAt) {}
