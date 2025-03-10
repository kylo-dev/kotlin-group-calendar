package com.gc.storage.framework.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.*
import org.springframework.data.mongodb.core.mapping.MongoMappingContext


@Configuration
@EnableMongoAuditing
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
    mongoMappingContext: MongoMappingContext,
    localDateTimeToDateKstConverter: LocalDateTimeToDateKstConverter,
    dateToLocalDateTimeKstConverter: DateToLocalDateTimeKstConverter,
  ): MappingMongoConverter {
    val dbRefResolver: DbRefResolver = DefaultDbRefResolver(mongoDatabaseFactory)
    val converter = MappingMongoConverter(dbRefResolver, mongoMappingContext)
    converter.setTypeMapper(DefaultMongoTypeMapper(null))

    converter.customConversions = MongoCustomConversions(
      listOf(localDateTimeToDateKstConverter, dateToLocalDateTimeKstConverter)
    )
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

  // 트랜잭션
//  @Bean
//  fun transactionManager(mongoDatabaseFactory: MongoDatabaseFactory): MongoTransactionManager {
//    return MongoTransactionManager(mongoDatabaseFactory)
//  }
}