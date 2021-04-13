# mysuni-apache-kafka

- 아파치 카프카 홈페이지 : https://kafka.apache.org/
- 아파치 카프카 다운로드 : https://downloads.apache.org/kafka/2.7.0/kafka_2.12-2.7.0.tgz

# 개발 환경

- 터미널 또는 윈도우즈 WSL
  - WSL 가이드 : https://docs.microsoft.com/ko-kr/windows/wsl/install-win10
- 인텔리제이 Community Edition
  - 다운로드 : https://www.jetbrains.com/ko-kr/idea/
- openJDK 1.8

# 카프카 관련 컨텐츠

- 카프카 서밋 : https://www.kafka-summit.org/
- 컨플루언트 유튜브 채널 : https://www.youtube.com/c/Confluent/videos

# Command list

## 주키퍼, 카프카 실행

```
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```

```
$ vi config/server.properties
advertised.listeners=PLAINTEXT://localhost:9092

$ bin/kafka-server-start.sh config/server.properties
```

## 토픽 생성, 조회(kafka-topics.sh)

```
$ bin/kafka-topics.sh --bootstrap-server localhost:9092 \
  --topic test \
  --create
$ bin/kafka-topics.sh --bootstrap-server localhost:9092 \
  --topic test \
  --describe
```
파티션 개수 또는 복제 개수와 함께 토픽 생성하기
```
$ bin/kafka-topics.sh \
  --bootstrap-server localhost:9092 \
  --topic test2 \
  --partitions 2 \
  --create
$ bin/kafka-topics.sh \
  --bootstrap-server localhost:9092 \
  --topic test3 \
  --replication-factor 2 \
  --create
```
retention.ms 설정과 함께 토픽 생성하기 
```
$ bin/kafka-topics.sh \
  --bootstrap-server localhost:9092 \
  --topic test10 \
  --partitions 1 \
  --config retention.ms=10000 \
  --create
```

## 토픽에 데이터 전송(kafka-console-producer.sh)

```
$ bin/kafka-console-producer.sh \
  --bootstrap-server localhost:9092 \
  --topic test
$ bin/kafka-console-producer.sh \
  --bootstrap-server localhost:9092 \
  --topic test2 \
  --property "parse.key=true" \
  --property "key.separator=:"
```

## 토픽의 데이터 확인(kafka-console-consumer.sh)

```
$ bin/kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic test 
$ bin/kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic test2 \
  --property print.key=true \
  --property key.separator=":"
$ bin/kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic test \
  --from-beginning
```
컨슈머 그룹으로 데이터 가져오기 
```
$ bin/kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic test \
  --group test-group
```

## 컨슈머 그룹 조회(kafka-consumer-groups.sh)

```
$ bin/kafka-consumer-groups.sh \
  --bootstrap-server localhost:9092 \
  --list
  
$ bin/kafka-consumer-groups.sh \
  --bootstrap-server localhost:9092 \
  --group test-group \
  --describe
 
$ bin/kafka-consumer-groups.sh \
  --bootstrap-server localhost:9092 \
  --topic test \
  --group test-group \
  --reset-offsets \
  --to-earliest \
  --execute
```

## 토픽에 저장된 데이터 삭제하기 

```
$ vi config/delete-record.conf
{
  "partitions": [
    {
      "topic": "test",
      "partition": 0,
      "offset": 2
    }
  ],
  "version": 1
}
```

```
$ bin/kafka-delete-records.sh \
  --bootstrap-server localhost:9092 \
  --offset-json-file config/delete-record.conf
```
