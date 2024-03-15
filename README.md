# Testing a kafka producer

This repo is an illustration of how to test a kafka producer using 2 different approaches:
* Using a consumer to consume the messages and assert the content
* Using a producer interceptor to store the messages and assert the content

Those 2 approaches are relying on a real kafka broker started with testcontainers, they can be considered as integration tests.

You can find more information in my Medium article: [Testing a kafka producer](https://medium.com/@clementplop/testing-a-kafka-producer-3e3e3d3e3e3e)