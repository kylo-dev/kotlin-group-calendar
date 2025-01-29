package com.gc.framework.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.DbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.*


@Configuration
class MongoDBConfig: AbstractMongoClientConfiguration() {

  @Value("\${mongodb.uri}")
  lateinit var mongodbUri: String

  override fun mongoClient(): MongoClient {

    val connectionString = ConnectionString(mongodbUri)
    val mongoClientSettings = MongoClientSettings
      .builder()
      .applyConnectionString(connectionString)
      .build()

    return MongoClients.create(mongoClientSettings)
  }

  // Collection에 _class 지우기
  @Bean
  fun customMongoConverter(
    mongoDatabaseFactory: MongoDatabaseFactory,
    mongoMappingContext: MongoMappingContext
  ): MappingMongoConverter {
    val dbRefResolver: DbRefResolver = DefaultDbRefResolver(mongoDatabaseFactory)
    val converter = MappingMongoConverter(dbRefResolver, mongoMappingContext)
    converter.setTypeMapper(DefaultMongoTypeMapper(null))
    return converter
  }

  @Bean
  override fun mongoTemplate(
    mongoDatabaseFactory: MongoDatabaseFactory,
    customMongoConverter: MappingMongoConverter,
  ): MongoTemplate {
    return MongoTemplate(mongoDatabaseFactory, customMongoConverter)
  }

  override fun getDatabaseName(): String {
    return "calendar_db"
  }

  // Mongo DateTime Format
  @Bean
  fun mongoAuditingDateTimeProvider(): DateTimeProvider {
    return DateTimeProvider {
      Optional.of(OffsetDateTime.now(ZoneId.of("Asia/Seoul")))
    }
  }

  // 트랜잭션
//  @Bean
//  fun transactionManager(mongoDatabaseFactory: MongoDatabaseFactory): MongoTransactionManager {
//    return MongoTransactionManager(mongoDatabaseFactory)
//  }
}