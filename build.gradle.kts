plugins {
    id("java")
}

group = "org.longbox"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.toedter:jcalendar:1.4")
    implementation("com.jgoodies:jgoodies-forms:1.8.0")
}

tasks.test {
    useJUnitPlatform()
}