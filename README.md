# Hướng dẫn chạy dự án

## Cách chạy dự án

1. **Tạo package configuration** trong dự án Java để cho phép truy cập từ bên ngoài vào web.
2. **Tạo class `WebConfig`** với nội dung như sau:

    ```java
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // cho phép các phương thức HTTP
                    .allowedHeaders("*");
        }
    }
    ```

3. **Thêm annotation `@CrossOrigin`** vào controller:

    ```java
    @CrossOrigin("http://localhost:3000")
    ```

   Để cho phép dự án frontend truy cập được.

4. **Sửa file `application.yml`** (dự án Java) và `docker-compose.yml` (Docker):

    ```yaml
    SPRING_DATASOURCE_URL: jdbc:mysql://mysql_shopee:3306/shopee
    ```

   *Lưu ý:* `mysql_shopee` là tên của `container_name` của MySQL được viết trong file `docker-compose.yml`.

5. **Chạy các lệnh sau để build và khởi động dự án**:

    ```bash
    ## đặt con trỏ tại thư mục backend
    cd shopee_BE
    docker build -t shopee-be:v1 .
    ## đặt con trỏ tại thư mục frontend
    cd .. 
    cd shope-clone-fe
    docker build -t shopee-fe:v1 .
   ## đặt con trỏ tại thư mục docker
    cd ..
    cd docker
    docker-compose up -d
    ```

## Hoàn tất

Sau khi thực hiện các bước trên, dự án của bạn sẽ được khởi động và sẵn sàng để sử dụng.
