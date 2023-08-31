package com.catfactsapi.catfactsapi

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.GetObjectRequest
import aws.sdk.kotlin.services.s3.model.GetObjectResponse
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.smithy.kotlin.runtime.*
import aws.smithy.kotlin.runtime.content.asByteStream
import kotlinx.coroutines.*
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File

@RestController
public class CatFactsController {

    val bucketName = "kotlin-mini-proj-bucket"
    val objectKey = "testFile.json"

    @GetMapping("/catfacts")
    suspend fun getCatFacts() : String {
//        val catFactsFile = getS3Object(bucketName, "catFacts.json", "/")

        var mockCatFacts = """[
      {
        "catFact": "This is a mock cat fact",
      }
    ]"""

        return mockCatFacts;
    }

    @PostMapping("/catfacts")
    suspend fun addCatFacts(newCatFacts: String) : String {
        println("Cat Facts to add $newCatFacts")


        var mockCatFacts = """[
      {
        "catFact": "This is a mock cat fact",
      }
    ]"""

        return mockCatFacts;
    }
}


suspend fun putS3Object(bucketName: String, objectKey: String, objectPath: String): String {

    val metadataVal = mutableMapOf<String, String>()
    metadataVal["myVal"] = "test"

    val request = PutObjectRequest {
        bucket = bucketName
        key = objectKey
        metadata = metadataVal
        body = File(objectPath).asByteStream()
    }

    S3Client { region = "us-east-2" }.use { s3: S3Client ->
        val response = s3.putObject(request)
        println("Tag information is ${response.eTag}")
        return "File Uploaded"
    }
}

suspend fun getS3Object(bucketName: String, keyName: String, path: String): String {

    val request = GetObjectRequest {
        key = keyName
        bucket = bucketName
    }

    var file = ""

    S3Client { region = "us-east-2" }.use { s3: S3Client ->
        s3.getObject(request) { resp: GetObjectResponse  ->
            println("Response: $resp")
            println("Successfully read $keyName from $bucketName")
//            val myFile = File(path)
//            file = Json.encodeToString(resp)
            file = resp.toString()
        }
    }

    return file
}