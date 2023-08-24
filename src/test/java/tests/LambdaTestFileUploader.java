package tests;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Base64;

public class LambdaTestFileUploader {

    public static String uploadFile() throws Exception {
        String username = "LT_USERNAME";
        String accessKey = "LT_ACCESS_KEY";
        String apiUrl = "https://api.lambdatest.com/automation/api/v1/files";

        // Use Java's Class#getResource to fetch the path relative to the classpath
        String filePath;
        try {
            filePath = new File(LambdaTestFileUploader.class.getResource("resources/sikuliImages/LoginButton.png").toURI()).getPath();
            System.out.println("Image Uploaded");
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to get the resource path", e);
        }

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(apiUrl);
        post.setHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + accessKey).getBytes()));

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", new File(filePath));

        HttpEntity entity = builder.build();
        post.setEntity(entity);

        CloseableHttpResponse response = client.execute(post);
        String responseBody = EntityUtils.toString(response.getEntity());

        // Parse the response to get the uploaded file path (or handle it as per your needs)
        JSONObject json = new JSONObject(responseBody);
        String uploadedFilePath = json.getString("data");

        return uploadedFilePath;
    }

    // You can also add a main method for testing purposes
    public static void main(String[] args) {
        try {
            String uploadedPath = uploadFile();
            System.out.println("File uploaded to: " + uploadedPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
