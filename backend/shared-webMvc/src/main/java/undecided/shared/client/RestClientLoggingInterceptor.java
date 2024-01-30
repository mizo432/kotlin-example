package undecided.shared.client;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class RestClientLoggingInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger log = LoggerFactory.getLogger(RestClientLoggingInterceptor.class);

    @Override
    public @NotNull ClientHttpResponse intercept(@NotNull HttpRequest request, byte @NotNull [] body, @NotNull ClientHttpRequestExecution execution) throws IOException {
        // リクエストの中身をログに落とす
        log.info("RestClient Request: URI={}, Headers={}, Body={}",
                request.getURI(),
                request.getHeaders(),
                new String(body));

        // レスポンスを取得する
        ClientHttpResponse response = new BufferingClientHttpResponseWrapper(execution.execute(request, body));

        // レスポンスのボディを読み込む
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }

        // レスポンスの中身をログに落とす
        log.info("RestClient Response: StatusCode={} {}, Headers={}, Body={}",
                response.getStatusCode(),
                response.getStatusText(),
                response.getHeaders(),
                inputStringBuilder.toString()
        );

        return response;
    }

    private static class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

        private final ClientHttpResponse response;

        private byte[] body;


        BufferingClientHttpResponseWrapper(ClientHttpResponse response) {
            this.response = response;
        }


        @Override
        public @NotNull HttpStatus getStatusCode() throws IOException {
            return (HttpStatus) this.response.getStatusCode();
        }

        /**
         * 非推奨
         *
         * @return 使用しない事
         * @throws IOException ?
         * @deprecated
         */
        @SuppressWarnings("removal")
        @Override
        @Deprecated(forRemoval = true)
        public int getRawStatusCode() throws IOException {
            //noinspection removal
            return this.response.getRawStatusCode();
        }

        @Override
        @NotNull
        public String getStatusText() throws IOException {
            return this.response.getStatusText();
        }

        @Override
        public @NotNull HttpHeaders getHeaders() {
            return this.response.getHeaders();
        }

        @Override
        public @NotNull InputStream getBody() throws IOException {
            if (this.body == null) {
                this.body = StreamUtils.copyToByteArray(this.response.getBody());
            }
            return new ByteArrayInputStream(this.body);
        }

        @Override
        public void close() {
            this.response.close();
        }

    }
}
