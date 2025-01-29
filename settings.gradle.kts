plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "calendar"
include("common")
include("customer")
//include("message")
//include("storage")
//include("client")
include("common:logging")
findProject(":common:logging")?.name = "logging"
include("common:monitoring")
findProject(":common:monitoring")?.name = "monitoring"
include("common:exception")
findProject(":common:exception")?.name = "exception"
include("common:security")
findProject(":common:security")?.name = "security"
include("storage:document")
findProject(":storage:document")?.name = "document"
